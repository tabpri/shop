package net.malta.service.payment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.getsecual.shop.payment.BankingPaymentGatewayConfiguration;
import com.getsecual.shop.payment.GMOPaymentWrapper;
import com.getsecual.shop.payment.PaymentGatewayConfiguration;

import net.malta.dao.payment.PaymentMethodDAO;
import net.malta.dao.payment.PaymentStatusDAO;
import net.malta.error.Errors;
import net.malta.error.PaymentError;
import net.malta.model.PaymentMethod;
import net.malta.model.PaymentStatus;
import net.malta.model.Purchase;
import net.malta.model.mapper.BankingPaymentGatewayConfigurationMapper;
import net.malta.model.payment.PaymentConstants;
import net.malta.model.payment.PaymentInfo;
import net.malta.model.payment.PaymentStatusEnum;
import net.malta.model.payment.RequestInfo;
import net.malta.model.payment.validator.PaymentInfoValidator;
import net.malta.model.payment.validator.PaymentStatusValidator;
import net.malta.service.purchase.IPurchaseService;

@Service
public class PaymentService implements IPaymentService {

	@Autowired
	private PaymentMethodDAO paymentMethodDAO;
	
	@Autowired
	private PaymentStatusDAO paymentStatusDAO;
	
	@Autowired
	BankingPaymentGatewayConfigurationMapper paymentGatewayConfigurationMapper;
	
	@Autowired
	IPurchaseService purchaseService;
	
	@Autowired
	PaymentInfoValidator paymentInfoValidator;

	@Autowired
	PaymentStatusValidator paymentStatusValidator;
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<PaymentMethod> getPaymentMethods() {
		return paymentMethodDAO.getPaymentMethods();
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public String paymentRequest(Integer purchaseId,PaymentInfo paymentInfo,
			RequestInfo requestDetails) throws PaymentException {

		Purchase purchase = purchaseService.getPurchase(purchaseId);
		
		PaymentStatus paymentStatus = paymentStatusDAO.getPaymentStatus(purchaseId);
		
		paymentStatusValidator.validate(paymentStatus, new Errors());			
		
		paymentInfoValidator.validate(paymentInfo, new Errors());

		Integer paymentMethod = paymentInfo.getPaymentMethod();
		
		purchaseService.updatePaymentMethod(purchase, paymentMethod);
		
		if ( paymentStatus == null ) { // set payment status
			paymentStatus = new PaymentStatus();
			paymentStatus.setPurchaseId(purchaseId);
			purchase.setPayment(paymentStatus);
		}
				
		if( paymentMethod.equals(2) ){
			paymentStatus.setPaymentStatus(PaymentStatusEnum.WAITING_FOR_PAYMENT);			
			purchaseService.confirmPurchase(purchaseId);
			return null; // return no need of payment gateway call
		}		
		
		// payment method 1
		BankingPaymentGatewayConfiguration paymentGatewayConfiguration = paymentGatewayConfigurationMapper.map(paymentInfo, 
				new BankingPaymentGatewayConfiguration());
		mapRequestDetails(requestDetails, paymentGatewayConfiguration);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhMMss");		
		String paymentOrderId = "id" + purchase.getId().toString() + "date" +  dateFormat.format(new Date());
		paymentGatewayConfiguration.setOrderId(paymentOrderId);
		paymentGatewayConfiguration.setAmount(purchase.getTotal());
		paymentGatewayConfiguration.setTax(purchase.getCarriage());
		
		GMOPaymentWrapper gmoPaymentWrapper = new GMOPaymentWrapper();

		try {
			gmoPaymentWrapper.executePayment(paymentGatewayConfiguration);
		} catch (com.gmo_pg.g_pay.client.common.PaymentException e) {
			paymentStatus.setPaymentStatus(PaymentStatusEnum.FAILED);
			paymentStatusDAO.saveOrUpdate(paymentStatus);
			logger.error("exception when entry/executing the payment",e);
			throw new PaymentException(e);
		}
		
		paymentStatus.setAcs(paymentGatewayConfiguration.getAcs());
		paymentStatus.setAcsURL(paymentGatewayConfiguration.getAcsUrl());
		paymentStatus.setMD(paymentGatewayConfiguration.getMD());
		paymentStatus.setPaReq(paymentGatewayConfiguration.getPaReq());
		paymentStatus.setTransactionReference(paymentGatewayConfiguration.getTransactionReference());		
		paymentStatus.setTransactionDate(parseTransactionDate(paymentGatewayConfiguration));
		paymentStatus.setOrderId(paymentOrderId);
		
		@SuppressWarnings("rawtypes")
		List errList = paymentGatewayConfiguration.getErrList();
		
		if ( CollectionUtils.isEmpty(errList) ) {
			paymentStatus.setPaymentStatus(paymentStatus.isAcsSecure() ? 
					PaymentStatusEnum.ACS_CONFIRM : PaymentStatusEnum.COMPLETED);
			paymentStatusDAO.saveOrUpdate(paymentStatus);
			if ( !paymentStatus.isAcsSecure() ) {
				purchaseService.confirmPurchase(purchaseId);		
			}
			return paymentGatewayConfiguration.getRedirectContents();
		} else {
			paymentStatus.setPaymentStatus(PaymentStatusEnum.FAILED);
			paymentStatusDAO.saveOrUpdate(paymentStatus);			
			Errors errors = mapToErrors(errList);
			throw new PaymentException(errors);
		}
	}


	private Date parseTransactionDate(PaymentGatewayConfiguration paymentGatewayConfiguration) {
		SimpleDateFormat transactionDateFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			String transactionDateString = paymentGatewayConfiguration.getTransactionDate();
			if ( transactionDateString != null ) {
				Date transactionDate = transactionDateFormatter.parse(transactionDateString);
				return transactionDate;				
			} else {
				return null;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void executePayment(Integer purchaseId, ACSResponse acsResponse) 
			throws PaymentException {
		
		GMOPaymentWrapper gmoPaymentWrapper = new GMOPaymentWrapper();
		PaymentGatewayConfiguration paymentGatewayConfiguration = new BankingPaymentGatewayConfiguration();
		paymentGatewayConfiguration.setPaRes(acsResponse.getPaRes());
		paymentGatewayConfiguration.setMD(acsResponse.getMD());
		
		PaymentStatus paymentStatus = paymentStatusDAO.getPaymentStatus(purchaseId);

		boolean isMDMatched = acsResponse.getMD().equals(paymentStatus.getMD()); 
		
		if ( !isMDMatched ) {
			logger.error("May be a attack : the payment requests doesnt match paymentStatus.MD acsResponse.MD ",paymentStatus.getMD(),
					acsResponse.getMD());
			Errors errors = new Errors();
			errors.add(new PaymentError(PaymentConstants.ACS_PAYMENTREQUESTS_MISMATCH, new Object[0]));
			throw new PaymentException(errors);			
		}
		
		if ( acsResponse.getPaRes() == null ) {
			paymentStatus.setPaymentStatus(PaymentStatusEnum.ACS_CONFIRM_FAILED);
			paymentStatusDAO.saveOrUpdate(paymentStatus);
			Errors errors = new Errors();
			errors.add(new PaymentError(PaymentConstants.PURCHASE_PAYMENT_SECUREAUTHENTICATIONFAILED, new Object[0]));			
			throw new PaymentException(errors);
		}
		
		paymentStatus.setPaRes(acsResponse.getPaRes());
		try {
			gmoPaymentWrapper.executePayment(paymentGatewayConfiguration);
			System.out.println("paymentGatewayConfiguration.getTransactionReference() " + paymentGatewayConfiguration.getTransactionReference());
			System.out.println("paymentGatewayConfiguration.getTransactionDate() " + paymentGatewayConfiguration.getTransactionDate());			
			paymentStatus.setTransactionReference(paymentGatewayConfiguration.getTransactionReference());		
			paymentStatus.setTransactionDate(parseTransactionDate(paymentGatewayConfiguration));
		} catch (com.gmo_pg.g_pay.client.common.PaymentException e) {
			paymentStatus.setPaymentStatus(PaymentStatusEnum.FAILED);
			paymentStatusDAO.saveOrUpdate((PaymentStatus) paymentStatus);			
			logger.error("exception when executing the payment",e);
			throw new PaymentException(e);
		}
		List errList = paymentGatewayConfiguration.getErrList();
		
		if ( CollectionUtils.isEmpty(errList) ) { // no errors
			paymentStatus.setPaymentStatus(PaymentStatusEnum.COMPLETED);
			paymentStatusDAO.saveOrUpdate(paymentStatus);
			purchaseService.confirmPurchase(purchaseId);
		} else {
			paymentStatus.setPaymentStatus(PaymentStatusEnum.FAILED);
			paymentStatusDAO.saveOrUpdate(paymentStatus);
			Errors errors = mapToErrors(errList);
			throw new PaymentException(errors);			
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PaymentStatus getPaymentStatus(Integer purchaseId) {
		PaymentStatus paymentStatus = paymentStatusDAO.getPaymentStatus(purchaseId);
		if ( paymentStatus == null ) {
			paymentStatus = new PaymentStatus();
		}
		return paymentStatus;
	}

	private Errors mapToErrors(List errList) {
		Errors errors = new Errors();
		Object[] blankvalues = new Object[0];
		for (Object error : errList) {
			String[] errorA = (String[]) error;
			errors.add(new PaymentError(errorA[0], errorA[1], blankvalues));
		}
		return errors;
	}


	private void mapRequestDetails(RequestInfo requestDetails,
			BankingPaymentGatewayConfiguration paymentGatewayConfiguration) {
		paymentGatewayConfiguration.setTermUrl(requestDetails.getUri());
		paymentGatewayConfiguration.setHttpAccept(requestDetails.getAccept());
		paymentGatewayConfiguration.setHttpUserAgent(requestDetails.getAgent());
	}

	public void setPaymentMethodDAO(PaymentMethodDAO paymentMethodDAO) {
		this.paymentMethodDAO = paymentMethodDAO;
	}
	
	public static void main(String[] args) {	
		SimpleDateFormat transactionDateFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			Date transactionDate = transactionDateFormatter.parse("20160318220727");
			System.out.println(transactionDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
