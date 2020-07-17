/**
 * @author SB
 */
package net.malta.model.user.json;

import java.io.Serializable;

public class DeliveryAddress implements Serializable {

	private static final long serialVersionUID = 7549047017611068748L;

    private Integer id;
    private String name;
    private String kana;
    private Integer zip;
    private String phone;
    private String address;
    private String buildingname;
    private String city;
	private String pref;
    private boolean hasgiftcard;
    private Integer giftcardid;
	private String giftcardname;
	private Integer prefectureid;
	private String prefecturename;
	private Integer publicuserid;
    	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getKana() {
		return kana;
	}


	public void setKana(String kana) {
		this.kana = kana;
	}


	public Integer getZip() {
		return zip;
	}


	public void setZip(Integer zip) {
		this.zip = zip;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public java.lang.String getAddress() {
		return address;
	}


	public void setAddress(java.lang.String address) {
		this.address = address;
	}


	public java.lang.String getBuildingname() {
		return buildingname;
	}


	public void setBuildingname(java.lang.String buildingname) {
		this.buildingname = buildingname;
	}


	public java.lang.String getPref() {
		return pref;
	}


	public void setPref(java.lang.String pref) {
		this.pref = pref;
	}


	public boolean isHasgiftcard() {
		return hasgiftcard;
	}


	public void setHasgiftcard(boolean hasgiftcard) {
		this.hasgiftcard = hasgiftcard;
	}

    public Integer getGiftcardid() {
		return giftcardid;
	}


	public void setGiftcardid(Integer giftcardid) {
		this.giftcardid = giftcardid;
	}


	public String getGiftcardname() {
		return giftcardname;
	}


	public void setGiftcardname(String giftcardname) {
		this.giftcardname = giftcardname;
	}

    public Integer getPrefectureid() {
		return prefectureid;
	}


	public void setPrefectureid(Integer prefectureid) {
		this.prefectureid = prefectureid;
	}


	public void setPrefecturename(String prefecturename) {
		this.prefecturename = prefecturename;
	}
	
	public String getPrefecturename() {
		return prefecturename;
	}
	
    public Integer getPublicuserid() {
		return publicuserid;
	}


	public void setPublicuserid(Integer publicuserid) {
		this.publicuserid = publicuserid;
	}
	
    public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}	
}