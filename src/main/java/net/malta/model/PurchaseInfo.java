package net.malta.model;

import java.io.Serializable;

public class PurchaseInfo implements Serializable {
	
	private static final long serialVersionUID = -912357252887203921L;
	
	private Integer purchaseId;
	private Integer userId;
	private String userSessionToken;
	
	public PurchaseInfo(Integer purchaseId, Integer userId) {
		this.purchaseId = purchaseId;
		this.userId = userId;
	}

	public PurchaseInfo(Integer purchaseId, Integer userId,String userSessionToken) {
		this.purchaseId = purchaseId;
		this.userId = userId;
		this.userSessionToken = userSessionToken;
	}

	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserSessionToken() {
		return userSessionToken;
	}	
}
