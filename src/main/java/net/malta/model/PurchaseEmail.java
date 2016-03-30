package net.malta.model;

import java.util.Date;

public class PurchaseEmail {
	
	private Integer id;
	
	private String userEmailAddress;
	
	private Boolean userEmailSent;

	private Date userEmailSentDate;

	private String userEmailErrorMessage;
	
	private String userEmailErrorCode;

	private String adminEmailAddressesString;
	
	private Boolean adminEmailSent;

	private Date adminEmailSentDate;
	
	private String adminEmailErrorMessage;
	
	private String adminEmailErrorCode;
	
	private Integer purchaseId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserEmailAddress() {
		return userEmailAddress;
	}

	public void setUserEmailAddress(String userEmailAddress) {
		this.userEmailAddress = userEmailAddress;
	}

	public Boolean getUserEmailSent() {
		return userEmailSent;
	}

	public void setUserEmailSent(Boolean userEmailSent) {
		this.userEmailSent = userEmailSent;
	}

	public String getUserEmailErrorMessage() {
		return userEmailErrorMessage;
	}

	public void setUserEmailErrorMessage(String userEmailErrorMessage) {
		this.userEmailErrorMessage = userEmailErrorMessage;
	}

	public String getUserEmailErrorCode() {
		return userEmailErrorCode;
	}

	public void setUserEmailErrorCode(String userEmailErrorCode) {
		this.userEmailErrorCode = userEmailErrorCode;
	}

	public Boolean getAdminEmailSent() {
		return adminEmailSent;
	}

	public void setAdminEmailSent(Boolean adminEmailSent) {
		this.adminEmailSent = adminEmailSent;
	}

	public String getAdminEmailErrorMessage() {
		return adminEmailErrorMessage;
	}

	public void setAdminEmailErrorMessage(String adminEmailErrorMessage) {
		this.adminEmailErrorMessage = adminEmailErrorMessage;
	}

	public String getAdminEmailErrorCode() {
		return adminEmailErrorCode;
	}

	public void setAdminEmailErrorCode(String adminEmailErrorCode) {
		this.adminEmailErrorCode = adminEmailErrorCode;
	}

	public String getAdminEmailAddressesString() {
		return adminEmailAddressesString;
	}

	public void setAdminEmailAddressesString(String adminEmailAddressesString) {
		this.adminEmailAddressesString = adminEmailAddressesString;
	}

	public Integer getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Date getUserEmailSentDate() {
		return userEmailSentDate;
	}

	public void setUserEmailSentDate(Date userEmailSentDate) {
		this.userEmailSentDate = userEmailSentDate;
	}

	public Date getAdminEmailSentDate() {
		return adminEmailSentDate;
	}

	public void setAdminEmailSentDate(Date adminEmailSentDate) {
		this.adminEmailSentDate = adminEmailSentDate;
	}
	
}
