package net.malta.beans.mapper;

import org.springframework.stereotype.Component;

import net.malta.beans.ChoiseForm;
import net.malta.mapper.IMapper;
import net.malta.model.Choise;
import net.malta.model.Item;
import net.malta.model.ItemImpl;

@Component
public class ChoiseFormMapper implements IMapper<ChoiseForm, Choise> {

	@Override
	public Choise map(ChoiseForm choiseForm, Choise choise) {
		choise.setId(choiseForm.getId());
		int ordernum = (choiseForm.getOrdernum() == 0) ? 1 : choiseForm.getOrdernum();		
		choise.setOrdernum(ordernum);
		choise.setWrapping(choiseForm.isWrapping());
		choise.setVarietychoise(choiseForm.getVarietychoise());
		Item item = new ItemImpl();
		item.setId(choiseForm.getItem());
		choise.setItem(item);
		choise.setWp_posts_id(item.getId());		
		return choise;
	}

}
