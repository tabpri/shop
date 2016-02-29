package net.malta.model.payment;

import java.io.Serializable;

public class RequestInfo implements Serializable {

	private static final long serialVersionUID = 750874585867704210L;
	
	private String uri;	
	private String accept;	
	private String agent;
	
	
	public RequestInfo(String uri, String accept, String agent) {
		this.uri = uri;
		this.accept = accept;
		this.agent = agent;
	}
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getAccept() {
		return accept;
	}
	public void setAccept(String accept) {
		this.accept = accept;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
}
