/**
 * @author SB
 */
package net.malta.model.json;

import java.io.Serializable;

public class DeliveryAddress implements Serializable {

	private static final long serialVersionUID = 7549047017611068748L;

	private Integer publicuserid;
    private Integer id;
    private String name;
    private String kana;
    private int zip;
    private String phone;
    private String address;
    private String buildingname;
    private String pref;
    private String preferreddate;
    private String preferredtime;
    private boolean hasgiftcard;
    private Integer giftcardid;
	private String giftcardname;
	private Integer prefectureid;
	private String prefecturename;
    	
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


	public int getZip() {
		return zip;
	}


	public void setZip(int zip) {
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


	public java.lang.String getPreferreddate() {
		return preferreddate;
	}


	public void setPreferreddate(java.lang.String preferreddate) {
		this.preferreddate = preferreddate;
	}


	public java.lang.String getPreferredtime() {
		return preferredtime;
	}


	public void setPreferredtime(java.lang.String preferredtime) {
		this.preferredtime = preferredtime;
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
}