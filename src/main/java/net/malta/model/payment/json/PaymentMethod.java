/**
 * @author SB
 */
package net.malta.model.payment.json;

import java.io.Serializable;

public class PaymentMethod implements Serializable{

	private static final long serialVersionUID = -3542129926964107168L;
	
	private Integer id;
	private String name;
	private String note;
	
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}	
}
