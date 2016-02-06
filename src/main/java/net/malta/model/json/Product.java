/**
 * 
 * @author SB
 * Product JSON contract
 */
package net.malta.model.json;

import java.io.Serializable;
public class Product implements Serializable {

	private static final long serialVersionUID = 6545848130393188584L;
	
	private Integer id;
	private String name;
	private String description;
	private Integer imageId;
	private Item mainItem;
	
	public Product () {	
	}
	
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
	public Item getMainItem() {
		return mainItem;
	}
	public void setMainItem(Item mainItem) {
		this.mainItem = mainItem;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getImageId() {
		return imageId;
	}
	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}	
	
}
