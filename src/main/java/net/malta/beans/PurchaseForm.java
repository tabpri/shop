package net.malta.beans;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;
public class PurchaseForm
    extends org.apache.struts.validator.ValidatorForm
    implements java.io.Serializable
{
private static final java.text.DateFormat simpleformat = new java.text.SimpleDateFormat("yyyy/MM/dd");private static final java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy/MM/dd hh:mm:ss");static { format.setLenient(true); }private Integer id;
public void setId(Integer id){
this.id = id;
}
public Integer getId(){
return this.id;
}
private Integer publicUser;
public void setPublicUser(Integer publicUser){
this.publicUser = publicUser;
}
public Integer getPublicUser(){
return this.publicUser;
}
private int total;
public void setTotal(int total){
this.total = total;
}
public int getTotal(){
return this.total;
}
private int carriage;
public void setCarriage(int carriage){
this.carriage = carriage;
}
public int getCarriage(){
return this.carriage;
}
private boolean temp;
public void setTemp(boolean temp){
this.temp = temp;
}
public boolean isTemp(){
return this.temp;
}
private int totalordernum;
public void setTotalordernum(int totalordernum){
this.totalordernum = totalordernum;
}
public int getTotalordernum(){
return this.totalordernum;
}
private boolean shipped;
public void setShipped(boolean shipped){
this.shipped = shipped;
}
public boolean isShipped(){
return this.shipped;
}
private boolean cancelled;
public void setCancelled(boolean cancelled){
this.cancelled = cancelled;
}
public boolean isCancelled(){
return this.cancelled;
}
private Date date;
public void setDate(Date date){
this.date = date;
}
public Date getDate(){
return this.date;
}
private boolean dateIsValid = false;
public void setDateIsValid(boolean dateIsValid){
this.dateIsValid = dateIsValid;
}
public boolean isDateIsValid(){
return this.dateIsValid;
}
private java.lang.String dateAsRawString ="";
public java.lang.String getDateAsString(){
    return (date== null) ? null : simpleformat.format(date);
}
public void setDateAsString(java.lang.String date){
	this.dateIsValid = true;
	this.dateAsRawString = date;
try{
		if(StringUtils.isNotBlank(date)){ simpleformat.parse(date);}
}catch (java.text.ParseException pe){
this.dateIsValid = false;
}
	if(dateIsValid){
		try {
			this.date = (org.apache.commons.lang.StringUtils.isBlank(date)) ? null : simpleformat.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
public void setDateAsRawString(java.lang.String date){
	this.dateAsRawString = date;
}

public String getDateAsRawString(){
return this.dateAsRawString;
}
private int dateyear = 0;
public void setDateyear(int dateyear){
    this.dateyear = dateyear;
}
public int getDateyear(){
   return this.dateyear;
}
private int datemonth = 0;
public void setDatemonth(int datemonth){
    this.datemonth = datemonth;
}
public int getDatemonth(){
   return this.datemonth;
}
private int dateday = 0;
public void setDateday(int dateday){
    this.dateday = dateday;
}
public int getDateday(){
   return this.dateday;
}
private int datehour = 0;
public void setDatehour(int datehour){
    this.datehour = datehour;
}
public int getDatehour(){
   return this.datehour;
}
private int dateminute = 0;
public void setDateminute(int dateminute){
    this.dateminute = dateminute;
}
public int getDateminute(){
   return this.dateminute;
}
private int datesecound = 0;
public void setDatesecound(int datesecound){
    this.datesecound = datesecound;
}
public int getDatesecound(){
   return this.datesecound;
}
private boolean datechooser = false;
public void setDatechooser(boolean datechooser){
    this.datechooser = datechooser;
if(this.datechooser){
try{
   Date date = format.parse(dateyear + "/" + datemonth + "/" + dateday + " " + datehour+ ":" + dateminute + ":" + datesecound);   this.date = date;
}catch (java.text.ParseException pe){
		pe.printStackTrace();
}
}}
public boolean isDatechooser(){
   return this.datechooser;
}
private Integer paymentMethod;
public void setPaymentMethod(Integer paymentMethod){
this.paymentMethod = paymentMethod;
}
public Integer getPaymentMethod(){
return this.paymentMethod;
}
private boolean removed;
public void setRemoved(boolean removed){
this.removed = removed;
}
public boolean isRemoved(){
return this.removed;
}
FormFile[] formFiles = new FormFile[10];
public void setFormFiles(FormFile[] formFiles){
this.formFiles = formFiles;
}
public FormFile[] getFormFiles(){
return this.formFiles;
}

private String shopId;          // ショップID
private String shopPass;        // ショップパスワード
private String orderId;         // オーダーID
private String itemCode;        // 商品コード
private String siteId;          // サイトID
private String sitePass;        // サイトパスワード
private String jobCd;           // 処理区分
private Integer amount;         // 利用金額
private Integer tax;            // 税送料
private String tdFlag;          // セキュア使用フラグ 0:行なわない(デフォルト)
private String tdTenantName;    // 3D セキュア表示店舗名
private String cardNo;          // カード番号
private String expire;          // 有効期限
private String securityCode;    // セキュリティーコード
private String clientField1;    // 加盟店自由項目 1
private String clientField2;    // 加盟店自由項目 2
private String clientField3;    // 加盟店自由項目 3
private String clientFieldFlag; // 加盟店自由項目返却フラグ
private String memberId;        // 会員ID
private String seqMode;         // カード登録連番モード
private Integer cardSeq;        // カード登録連番
private String cardPass;        // カードパスワード
private String accessId;        // 取引ID 同じオーダーIDで再決済かけるときに必要
private String accessPass;      // 取引パスワード 同じオーダーIDで再決済かけるときに必要
private String method;          // 支払方法
private Integer payTimes;       // 支払回数
private String deviceCategory;  // 使用端末情報
private String acsUrl;          // 本人認証パスワード入力画面URL.
private String paReq;           // 本人認証サービス要求電文.
private String termUrl;         // 結果受取用URL.

public String getShopId() {
	return shopId;
}
public void setShopId(String shopId) {
	this.shopId = shopId;
}
public String getShopPass() {
	return shopPass;
}
public void setShopPass(String shopPass) {
	this.shopPass = shopPass;
}
public String getOrderId() {
	return orderId;
}
public void setOrderId(String orderId) {
	this.orderId = orderId;
}
public String getItemCode() {
	return itemCode;
}
public void setItemCode(String itemCode) {
	this.itemCode = itemCode;
}
public String getSiteId() {
	return siteId;
}
public void setSiteId(String siteId) {
	this.siteId = siteId;
}
public String getSitePass() {
	return sitePass;
}
public void setSitePass(String sitePass) {
	this.sitePass = sitePass;
}
public String getJobCd() {
	return jobCd;
}
public void setJobCd(String jobCd) {
	this.jobCd = jobCd;
}
public Integer getAmount() {
	return amount;
}
public void setAmount(Integer amount) {
	this.amount = amount;
}
public Integer getTax() {
	return tax;
}
public void setTax(Integer tax) {
	this.tax = tax;
}
public String getTdFlag() {
	return tdFlag;
}
public void setTdFlag(String tdFlag) {
	this.tdFlag = tdFlag;
}
public String getTdTenantName() {
	return tdTenantName;
}
public void setTdTenantName(String tdTenantName) {
	this.tdTenantName = tdTenantName;
}
public String getCardNo() {
	return cardNo;
}
public void setCardNo(String cardNo) {
	this.cardNo = cardNo;
}
public String getExpire() {
	return expire;
}
public void setExpire(String expire) {
	this.expire = expire;
}
public String getSecurityCode() {
	return securityCode;
}
public void setSecurityCode(String securityCode) {
	this.securityCode = securityCode;
}
public String getClientField1() {
	return clientField1;
}
public void setClientField1(String clientField1) {
	this.clientField1 = clientField1;
}
public String getClientField2() {
	return clientField2;
}
public void setClientField2(String clientField2) {
	this.clientField2 = clientField2;
}
public String getClientField3() {
	return clientField3;
}
public void setClientField3(String clientField3) {
	this.clientField3 = clientField3;
}
public String getClientFieldFlag() {
	return clientFieldFlag;
}
public void setClientFieldFlag(String clientFieldFlag) {
	this.clientFieldFlag = clientFieldFlag;
}
public String getMemberId() {
	return memberId;
}
public void setMemberId(String memberId) {
	this.memberId = memberId;
}
public String getSeqMode() {
	return seqMode;
}
public void setSeqMode(String seqMode) {
	this.seqMode = seqMode;
}
public Integer getCardSeq() {
	return cardSeq;
}
public void setCardSeq(Integer cardSeq) {
	this.cardSeq = cardSeq;
}
public String getCardPass() {
	return cardPass;
}
public void setCardPass(String cardPass) {
	this.cardPass = cardPass;
}
public String getAccessId() {
	return accessId;
}
public void setAccessId(String accessId) {
	this.accessId = accessId;
}
public String getAccessPass() {
	return accessPass;
}
public void setAccessPass(String accessPass) {
	this.accessPass = accessPass;
}
public String getMethod() {
	return method;
}
public void setMethod(String method) {
	this.method = method;
}
public Integer getPayTimes() {
	return payTimes;
}
public void setPayTimes(Integer payTimes) {
	this.payTimes = payTimes;
}
public String getDeviceCategory() {
	return deviceCategory;
}
public void setDeviceCategory(String deviceCategory) {
	this.deviceCategory = deviceCategory;
}
public String getAcsUrl() {
	return acsUrl;
}
public void setAcsUrl(String acsUrl) {
	this.acsUrl = acsUrl;
}
public String getPaReq() {
	return paReq;
}
public void setPaReq(String paReq) {
	this.paReq = paReq;
}
public String getTermUrl() {
	return termUrl;
}
public void setTermUrl(String termUrl) {
	this.termUrl = termUrl;
}
public void reset(org.apache.struts.action.ActionMapping mapping, javax.servlet.http.HttpServletRequest request){	temp =false;
	shipped =false;
	cancelled =false;
	removed =false;
}
}
