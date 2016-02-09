/**
 * @author SB
 */
package net.malta.model.json.mapper;

import net.malta.model.Item;

public class ItemMapper implements IMapper<Item, net.malta.model.json.Item>{

	@Override
	public net.malta.model.json.Item map(Item item, net.malta.model.json.Item itemJSON) {
		itemJSON.setName(item.getName());
		itemJSON.setDescription(item.getDescription());
		itemJSON.setId(item.getId());
		itemJSON.setPricewithtax(item.getPricewithtax());
		itemJSON.setCarriage(item.getCarriage().getValue());
		itemJSON.setCatchcopy(item.getCatchcopy());		
		return itemJSON;
	}

}
