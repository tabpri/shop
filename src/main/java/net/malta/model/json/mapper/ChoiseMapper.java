package net.malta.model.json.mapper;

import net.malta.model.Choise;
import net.malta.model.json.Item;

public class ChoiseMapper implements IMapper<Choise, net.malta.model.json.Choise>{

	private ItemMapper itemMapper;
	
	@Override
	public net.malta.model.json.Choise map(Choise choise, net.malta.model.json.Choise choiseJSON) {
		choiseJSON.setId(choise.getId());
		choiseJSON.setImg(choise.getImg());
		// item mapping
		Item itemJSON = itemMapper.map(choise.getItem(), new Item());
		choiseJSON.setItem(itemJSON);
		choiseJSON.setOrdernum(choise.getOrdernum());
		choiseJSON.setPricewithtax(choise.getPricewithtax());
		choiseJSON.setWrapping(choise.isWrapping());
		choiseJSON.setVarietychoise(choise.getVarietychoise());
		return choiseJSON;		
	}

	public void setItemMapper(ItemMapper itemMapper) {
		this.itemMapper = itemMapper;
	}
}
