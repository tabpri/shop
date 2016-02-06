package net.malta.model.json.mapper;

import net.malta.model.Choise;

public class ChoiseMapper implements IMapper<Choise, net.malta.model.json.Choise>{

	@Override
	public net.malta.model.json.Choise map(Choise choise, net.malta.model.json.Choise choiseJSON) {
		choiseJSON.setId(choise.getId());
		choiseJSON.setItem(choise.getItem().getId());
		choiseJSON.setImg(choise.getImg());
		choiseJSON.setOrdernum(choise.getOrdernum());
		choiseJSON.setPricewithtax(choise.getPricewithtax());
		choiseJSON.setWrapping(choise.isWrapping());
		choiseJSON.setVarietychoise(choise.getVarietychoise());
		return choiseJSON;		
	}

}
