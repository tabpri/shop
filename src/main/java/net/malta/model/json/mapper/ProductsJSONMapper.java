package net.malta.model.json.mapper;

import java.util.List;

import net.malta.model.Product;

public class ProductsJSONMapper implements IMapper<List<Product>, List<net.malta.model.json.Product>>{

	@Override
	public void map(List<Product> products, List<net.malta.model.json.Product> productsJSON) {
		for (Product product : products) {
			ProductJSONMapper productJSONMapper = new ProductJSONMapper();
			net.malta.model.json.Product productJSON = new net.malta.model.json.Product();
			productJSONMapper.map(product, productJSON);
			productsJSON.add(productJSON);
		}
		
	}

}
