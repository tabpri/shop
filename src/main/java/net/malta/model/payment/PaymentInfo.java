package net.malta.model.payment;

import java.io.Serializable;

public class PaymentInfo implements Serializable {

	private static final long serialVersionUID = -6863861808997693336L;

	private String cardNo;	
	private String expire;	
	private String securityCode;	
	private String memberId;	
	private String seqMode;	
	private Integer cardSeq;	
	private String cardPass;
	private Integer paymentMethod;

	private String MD;
	private String paRes;
	
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
	public String getMD() {
		return MD;
	}
	public void setMD(String mD) {
		MD = mD;
	}
	public String getPaRes() {
		return paRes;
	}
	public void setPaRes(String paRes) {
		this.paRes = paRes;
	}
	public Integer getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}	
}