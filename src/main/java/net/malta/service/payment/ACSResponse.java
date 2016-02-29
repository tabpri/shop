package net.malta.service.payment;

import java.io.Serializable;

public class ACSResponse implements Serializable {
	
	private static final long serialVersionUID = 2964709686183604239L;
	
	private String paRes;
	private String MD;

	public ACSResponse(String paRes, String mD) {
		this.paRes = paRes;
		this.MD = mD;
	}
	
	public String getPaRes() {
		return paRes;
	}

	public void setPaRes(String paRes) {
		this.paRes = paRes;
	}

	public String getMD() {
		return MD;
	}

	public void setMD(String MD) {
		this.MD = MD;
	}	
}