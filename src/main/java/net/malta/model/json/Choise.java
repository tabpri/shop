package net.malta.model.json;

import java.io.Serializable;

public class Choise implements Serializable{

	private static final long serialVersionUID = 1749126775249703892L;

	private Purchase purchase;

	private Integer id;
	private Integer item;
	private String img;	
	private Integer ordernum;
	private Integer pricewithtax;
	private Boolean wrapping;
	private String varietychoise;	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getItem() {
		return item;
	}
	public void setItem(Integer item) {
		this.item = item;
	}
	public Integer getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}
	public Integer getPricewithtax() {
		return pricewithtax;
	}
	public void setPricewithtax(Integer pricewithtax) {
		this.pricewithtax = pricewithtax;
	}
	public Boolean getWrapping() {
		return wrapping;
	}
	public void setWrapping(Boolean wrapping) {
		this.wrapping = wrapping;
	}
	public String getVarietychoise() {
		return varietychoise;
	}
	public void setVarietychoise(String varietychoise) {
		this.varietychoise = varietychoise;
	}

	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}

	public Purchase getPurchase() {
		return purchase;
	}
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

}
