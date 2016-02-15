/**
 * @author SB
 */
package net.malta.model.user.json;

import java.io.Serializable;

public class PublicUser implements Serializable{

	private static final long serialVersionUID = 5613064174276789363L;
	
	private Integer id; 
	private String name;
	private String kana;
	private String address;
	private String mail;
	private int zip;
	private String pref;
	private String buildingname;
	private String phone;
	private Integer fax;
	private Integer prefecture;
	private Boolean registered;
	
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
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public String getPref() {
		return pref;
	}
	public void setPref(String pref) {
		this.pref = pref;
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
}
