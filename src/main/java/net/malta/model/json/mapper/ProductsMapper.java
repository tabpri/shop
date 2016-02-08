package net.malta.model.json.mapper;

import java.util.List;

import net.malta.model.Product;

public class ProductsMapper implements IMapper<List<Product>, List<net.malta.model.json.Product>>{

	ProductMapper productMapper;
	
	@Override
	public List<net.malta.model.json.Product> map(List<Product> products, List<net.malta.model.json.Product> productsJSON) {
		for (Product product : products) {
			net.malta.model.json.Product productJSON = new net.malta.model.json.Product();
			productMapper.map(product, productJSON);
			productsJSON.add(productJSON);
		}
		return productsJSON;
		
	}

	public void setProductMapper(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}
}
