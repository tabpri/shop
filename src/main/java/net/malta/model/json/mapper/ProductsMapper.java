package net.malta.model.json.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.malta.mapper.IMapper;
import net.malta.model.Product;

@Component
public class ProductsMapper implements IMapper<List<Product>, List<net.malta.model.product.json.Product>>{

	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public List<net.malta.model.product.json.Product> map(List<Product> products, List<net.malta.model.product.json.Product> productsJSON) {
		for (Product product : products) {
			net.malta.model.product.json.Product productJSON = new net.malta.model.product.json.Product();
			productMapper.map(product, productJSON);
			productsJSON.add(productJSON);
		}
		return productsJSON;
		
	}

	public void setProductMapper(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}
}
