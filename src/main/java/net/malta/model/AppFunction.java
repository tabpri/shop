package net.malta.model;

import java.io.Serializable;

public class AppFunction implements Serializable {
	
	private static final long serialVersionUID = 1921036541594645660L;
	
	private Integer id;	
	private String functionName;
	private String functionURI;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getFunctionURI() {
		return functionURI;
	}
	public void setFunctionURI(String functionURI) {
		this.functionURI = functionURI;
	}
	
}
