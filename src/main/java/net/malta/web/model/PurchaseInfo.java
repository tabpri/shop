package net.malta.web.model;

import java.io.Serializable;

public class PurchaseInfo implements Serializable {
	
	private static final long serialVersionUID = -912357252887203921L;
	
	private Integer purchaseId;
	private Integer userId;
	
	public PurchaseInfo(Integer purchaseId, Integer userId) {
		this.purchaseId = purchaseId;
		this.userId = userId;
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
}
