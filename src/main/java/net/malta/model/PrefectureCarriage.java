package net.malta.model;

import java.io.Serializable;

public class PrefectureCarriage implements Serializable {

	private static final long serialVersionUID = 9107417901890960695L;

	private Integer id;	
	private Integer prefecture;	
	private Integer carriageValue;
	
	public Integer getPrefecture() {
		return prefecture;
	}
	public void setPrefecture(Integer prefecture) {
		this.prefecture = prefecture;
	}
	public Integer getCarriageValue() {
		return carriageValue;
	}
	public void setCarriageValue(Integer carriageValue) {
		this.carriageValue = carriageValue;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
}