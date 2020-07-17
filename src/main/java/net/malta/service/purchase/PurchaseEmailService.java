package net.malta.service.purchase;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.MimeUtility;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.getsecual.email.client.Email;
import com.getsecual.email.client.IEmailSender;
import com.getsecual.email.client.exception.EmailException;

import net.malta.dao.meta.StaticDataDAO;
import net.malta.dao.purchase.PurchaseEmailDAO;
import net.malta.model.DeliveryAddress;
import net.malta.model.Purchase;
import net.malta.model.PurchaseEmail;
import net.malta.model.StaticData;
import net.malta.model.purchase.wrapper.PurchaseDeliveryAddress;

@Component
public class PurchaseEmailService implements IPurchaseEmailService {

	private static final Logger logger = LoggerFactory.getLogger(PurchaseEmailService.class);
	
	@Autowired
	StaticDataDAO staticDataDAO;

	@Autowired
	PurchaseEmailDAO purchaseEmailDAO;
	
	@Autowired
	IEmailSender emailSender;
	
	@Autowired
	VelocityEngine velocityEngine;
	
	private final String subject = "【A&L】＊ご注文ありがとうございました（自動配信メール）＊";
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)	
	public void sendConfirmationEmail(Purchase purchase) {
		
       	@SuppressWarnings("rawtypes")
		Map model = new HashMap();
		model.put("purchase", purchase);
		DeliveryAddress deliveryAddress = new PurchaseDeliveryAddress(purchase).getDeliveryAddress();
		logger.debug("delivery address prefecture " + deliveryAddress.getPrefecture());
		logger.debug("delivery address prefecture " + deliveryAddress.getPrefecture().getName());
		
		model.put("deliveryaddress", deliveryAddress);
		Locale l = new Locale("ja", "JP");
		model.put("dateFormatter", new SimpleDateFormat("yyyy/MM/dd"));
		model.put("dayFormatter", new SimpleDateFormat("E", l));		
		model.put("timeFormatter", new SimpleDateFormat("HH:mm"));

		StaticData staticData = staticDataDAO.find(1);	

		String from = staticData.getFromaddress();
		
		try {
			from = MimeUtility.encodeText("AFRICA & LEO", "ISO-2022-JP", "B") + "<"+staticData.getFromaddress()+">";
		} catch (UnsupportedEncodingException uee) {
			logger.error(uee.getMessage(),uee);
		}
		
		model.put("staticData", staticData);

    	String userEmail = purchase.getPublicUser().getMail();

		PurchaseEmail purchaseEmail = new PurchaseEmail();
		purchaseEmail.setUserEmailAddress(userEmail);
		purchaseEmail.setAdminEmailAddressesString(staticData.getAdminEmailAddressesString());
		purchaseEmail.setPurchaseId(purchase.getId());
		purchase.setPurchaseEmail(purchaseEmail);
		
		try {
	    	logger.info("email sending to the user---------------------------------------------");
	    	String userEmailString = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"MailAboutPurchaseToPublicUser.eml","UTF-8",model);
	    	System.out.println("userEmailString:\n" +userEmailString);
			emailSender.sendEmail(new Email(subject, from, userEmail, userEmailString));
			purchaseEmail.setUserEmailSent(Boolean.TRUE);
			purchaseEmail.setUserEmailSentDate(new Date());
			logger.info("email sent to the user --------------------------------------------- " + purchase.getPublicUser().getMail());
		} catch (EmailException ee) {
			logger.error("email sending to the user - failed ---------------------------------------------" + + purchase.getId(),ee);
			purchaseEmail.setUserEmailErrorCode(ee.getStatusCode() != null ? ee.getStatusCode().toString() : "");
			purchaseEmail.setUserEmailErrorMessage(ee.getMessage());
			purchaseEmail.setUserEmailSent(Boolean.FALSE);
		} catch (VelocityException e) {
			purchaseEmail.setUserEmailErrorMessage(e.getMessage());
			purchaseEmail.setUserEmailSent(Boolean.FALSE);			
			logger.error("email sending to the user - failed ---------------------------------------------" + + purchase.getId(),e);			
		}

		try {
	    	logger.info("email sending to the admin users---------------------------------------------");
	    	String adminEmailString = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"MailAboutPurchaseToAdmin.eml","UTF-8",model);
	    	System.out.println("adminEmailString:\n" +adminEmailString);	    	
	    	emailSender.sendEmail(new Email(subject, from, staticData.getAdminEmailAddresses(), adminEmailString));
			purchaseEmail.setAdminEmailSent(Boolean.TRUE);
			purchaseEmail.setAdminEmailSentDate(new Date());	    	
			logger.info("email sent to the admins --------------------------------------------- " + staticData.getAdminEmailAddressesString());	    	
		} catch (EmailException ee) {
			purchaseEmail.setAdminEmailErrorCode(ee.getStatusCode() != null ? ee.getStatusCode().toString() : "");
			purchaseEmail.setAdminEmailErrorMessage(ee.getMessage());
			purchaseEmail.setAdminEmailSent(Boolean.FALSE);			
			logger.error("email sending to the admin users - failed ---------------------------------------------" + + purchase.getId(),ee);
		}
		catch (VelocityException e) {
			purchaseEmail.setAdminEmailErrorMessage(e.getMessage());
			purchaseEmail.setAdminEmailSent(Boolean.FALSE);
			logger.error("email sending to the admin users - failed ---------------------------------------------" + + purchase.getId(),e);
		}
		purchaseEmailDAO.saveOrUpdate(purchaseEmail);
	}

/*	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-mail.xml","classpath:applicationContext-localDataSource.xml","classpath:applicationContext.xml");
		PurchaseEmailService emailService = new PurchaseEmailService();
		emailService.setContext(context);
		PurchaseService purchaseService = context.getBean(PurchaseService.class);
		Purchase purchase = purchaseService.getPurchase(335);
		emailService.sendConfirmationEmail(purchase);
	}*/
}
