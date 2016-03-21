package net.malta.model.purchase.json;

import java.io.Serializable;
import java.util.Date;

public class Payment implements Serializable {

	private static final long serialVersionUID = 7285347260568953190L;
	
	private String	status;
	private String	transactionReference;
	private Date	transactionDate;
	private String	orderId;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
		
}
