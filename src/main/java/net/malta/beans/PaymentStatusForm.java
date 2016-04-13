package net.malta.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts.action.ActionForm;

public class PaymentStatusForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private Integer purchaseId;
	private String paymentStatus;	
	private String transactionReference;
	private Date transactionDate;
	private String transactionDateS;
	
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
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
	public String getTransactionDateS() {
		return transactionDateS;
	}
	public void setTransactionDateS(String transactionDateS) {
		this.transactionDateS = transactionDateS;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		try {
			Date date = dateFormat.parse(transactionDateS);
			setTransactionDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		setTransactionDate(transactionDate);
	}
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	
	public static void main(String[] args) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		try {
			System.out.println(dateFormat.parse("5-10-2016 15:26:10"));
			System.out.println(dateFormat.parse("15-4-2016 15:26:10"));			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
