package net.malta.model.json.mapper;

import java.util.Collection;

import net.malta.mapper.IMapper;
import net.malta.model.Choise;

public class ChoisesMapper implements IMapper<Collection<Choise>, Collection<net.malta.model.purchase.json.Choise>> {

	private ChoiseMapper choiseMapper;
	
	@Override
	public Collection<net.malta.model.purchase.json.Choise> map(Collection<Choise> choises, Collection<net.malta.model.purchase.json.Choise> choisesJSON) {
		for (Choise choise : choises) {
			net.malta.model.purchase.json.Choise choiseJSON = new net.malta.model.purchase.json.Choise();
			choiseMapper.map(choise, choiseJSON);
			choisesJSON.add(choiseJSON);
		}
		return choisesJSON;
	}

	public void setChoiseMapper(ChoiseMapper choiseMapper) {
		this.choiseMapper = choiseMapper;
	}
}
