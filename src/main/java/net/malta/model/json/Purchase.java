/**
 * @author SB
 */
package net.malta.model.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Purchase implements Serializable {

	private static final long serialVersionUID = 6043814705230305549L;

	private Integer purchaseId;
	private Integer purchasetotal;

	private List<Choise> choises;

	public void addChoise(Choise choise) {
		if ( choises == null ) {
			choises = new ArrayList<Choise>();
		}
		choises.add(choise);
	}
	
	public Integer getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Integer getPurchasetotal() {
		return purchasetotal;
	}

	public void setPurchasetotal(Integer purchasetotal) {
		this.purchasetotal = purchasetotal;
	}

	public List<Choise> getChoises() {
		return choises;
	}

	public void setChoises(List<Choise> choises) {
		this.choises = choises;
	}	
}
