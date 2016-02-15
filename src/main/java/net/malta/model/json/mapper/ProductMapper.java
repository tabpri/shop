package net.malta.model.json.mapper;

import java.util.Collection;

import net.malta.mapper.IMapper;
import net.malta.model.Item;
import net.malta.model.Product;

public class ProductMapper implements IMapper<Product, net.malta.model.product.json.Product>{

	@Override
	public net.malta.model.product.json.Product map(Product product, net.malta.model.product.json.Product productJSON) {
		productJSON.setId(product.getId());
		productJSON.setName(product.getName());		
		productJSON.setDescription(product.getDescription());
		productJSON.setImageId(product.getThumnail().getId());
		Item mainItem = mainItem(product.getItems());
		if ( mainItem != null ) {
			net.malta.model.product.json.Item mainItemJSON = new net.malta.model.product.json.Item();
			mainItemJSON.setId(mainItem.getId());
			mainItemJSON.setName(mainItem.getName());
			mainItemJSON.setDescription(mainItem.getDescription());
			mainItemJSON.setPricewithtax(mainItem.getPricewithtax());
			mainItemJSON.setCatchcopy(mainItem.getCatchcopy());
			productJSON.setMainItem(mainItemJSON);
			
		}
		return productJSON;
	}

	private Item mainItem(Collection items) {
		for (Object object : items) {
			Item item = (Item) object;
			if ( item.isMain() ) {
				return item;
			}
		}
		return null;
	}

}