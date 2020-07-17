package net.malta.model.user.json;

import java.io.Serializable;

public class PublicUser implements Serializable{

	private static final long serialVersionUID = 5613064174276789363L;
	
	private Integer id; 
	private String name;
	private String kana;
	private String address;
	private String mail;
	private Integer zip;
	private String buildingname;
	private String city;	
	private String phone;
	private Integer fax;
	private Integer prefecture;
	private Boolean registered;
	private Integer authuserid;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKana() {
		return kana;
	}
	public void setKana(String kana) {
		this.kana = kana;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getBuildingname() {
		return buildingname;
	}
	public void setBuildingname(String buildingname) {
		this.buildingname = buildingname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getPrefecture() {
		return prefecture;
	}
	public void setPrefecture(Integer prefecture) {
		this.prefecture = prefecture;
	}
	public Boolean getRegistered() {
		return registered;
	}
	public void setRegistered(Boolean registered) {
		this.registered = registered;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getFax() {
		return fax;
	}
	public void setFax(Integer fax) {
		this.fax = fax;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getAuthuserid() {
		return authuserid;
	}
	public void setAuthuserid(Integer authuserid) {
		this.authuserid = authuserid;
	}
	public Integer getZip() {
		return zip;
	}
	public void setZip(Integer zip) {
		this.zip = zip;
	}	
}