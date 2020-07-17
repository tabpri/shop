package net.malta.model;

import java.io.Serializable;
import java.util.Date;

import net.malta.model.payment.PaymentStatusEnum;

public class PaymentStatus implements Serializable {

	private static final long serialVersionUID = -2798297866147350440L;

	private Integer id;
	
	private PaymentStatusEnum paymentStatus;

	private String paymentStatusString;

	private String acs;
	
	private String MD;

	private String paReq;
	
	private String paRes;

	private String acsURL;

	private String transactionReference;

	private Date transactionDate;
	
	private String orderId;

	private Integer purchaseId;
	
	public PaymentStatus() {
		setPaymentStatus(PaymentStatusEnum.PENDING);
	}

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public PaymentStatusEnum getPaymentStatus() {
		return paymentStatus;
	}


	public void setPaymentStatus(PaymentStatusEnum paymentStatus) {
		this.paymentStatus = paymentStatus;
	}


	public String getAcs() {
		return acs;
	}


	public void setAcs(String acs) {
		this.acs = acs;
	}


	public String getMD() {
		return MD;
	}


	public void setMD(String mD) {
		MD = mD;
	}


	public String getPaReq() {
		return paReq;
	}


	public void setPaReq(String paReq) {
		this.paReq = paReq;
	}


	public String getPaRes() {
		return paRes;
	}


	public void setPaRes(String paRes) {
		this.paRes = paRes;
	}


	public String getAcsURL() {
		return acsURL;
	}


	public void setAcsURL(String acsURL) {
		this.acsURL = acsURL;
	}


	public Integer getPurchaseId() {
		return purchaseId;
	}


	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}


	public String getPaymentStatusString() {
		return this.paymentStatus.toString();
	}

	public void setPaymentStatusString(String paymentStatusString) {
		setPaymentStatus(PaymentStatusEnum.valueOf(paymentStatusString));
	}

	public String getTransactionReference() {
		return transactionReference;
	}


	public void setTransactionReference(String transactionReference) {
		this.transactionReference = transactionReference;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public boolean isPayable() {
		// the purchase is payable only when it neither completed nor in the acs confirm stage
		return !( this.paymentStatus.equals(PaymentStatusEnum.COMPLETED) || 
				this.paymentStatus.equals(PaymentStatusEnum.ACS_CONFIRM) );  
	}
	
	public boolean isAcsSecure() {
		return !(this.acs == null || this.acs.equals("") ) ;
	}
		
}