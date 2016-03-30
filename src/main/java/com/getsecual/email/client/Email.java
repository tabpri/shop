package com.getsecual.email.client;

import java.util.ArrayList;
import java.util.List;

public class Email {

	private List<String> to;

	private List<String> bcc;

	private List<String> cc;

	private String subject;

	private String htmlText;

	private String text;

	private String from;

	public Email(String subject, String from, String to, String text) {
		this.subject = subject;
		this.from = from;
		this.text = text;
		this.addTo(to);
	}

	public Email(String subject, String from, String[] to, String text) {
		this.subject = subject;
		this.from = from;
		this.text = text;
		for (String toOne : to) {
			this.addTo(toOne);			
		}
	}

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public void addTo(String to) {
		if (this.to == null) {
			this.to = new ArrayList<String>();
		}
		this.to.add(to);
	}

	public void addCc(String cc) {
		if (this.cc == null) {
			this.cc = new ArrayList<String>();
		}
		this.cc.add(cc);
	}

	public void addBcc(String bcc) {
		if (this.bcc == null) {
			this.bcc = new ArrayList<String>();
		}
		this.bcc.add(bcc);
	}

	public List<String> getBcc() {
		return bcc;
	}

	public void setBcc(List<String> bcc) {
		this.bcc = bcc;
	}

	public List<String> getCc() {
		return cc;
	}

	public void setCc(List<String> cc) {
		this.cc = cc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getHtmlText() {
		return htmlText;
	}

	public void setHtmlText(String htmlText) {
		this.htmlText = htmlText;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Email [to=" + to + ", bcc=" + bcc + ", cc=" + cc + ", subject=" + subject + ", htmlText=" + htmlText
				+ ", from=" + from + "]";
	}

}