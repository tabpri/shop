/**
 * 
 * @author SB
 * Item JSON model
 */
package net.malta.model.json;

import java.io.Serializable;

public class Item implements Serializable {

	private static final long serialVersionUID = -659834830796866054L;
	
	private Integer id;
	private String name;
	private String description;
	private Integer pricewithtax;
	private String catchcopy;
	
	public String getCatchcopy() {
		return catchcopy;
	}
	public void setCatchcopy(String catchcopy) {
		this.catchcopy = catchcopy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPricewithtax() {
		return pricewithtax;
	}
	public void setPricewithtax(Integer pricewithtax) {
		this.pricewithtax = pricewithtax;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
