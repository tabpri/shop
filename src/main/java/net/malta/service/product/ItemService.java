package net.malta.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.malta.dao.product.ItemDAO;
import net.malta.model.Item;

@Service
public class ItemService implements IItemService {

	@Autowired
	private ItemDAO itemDAO;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Item getItem(Integer id) {
		Item item = itemDAO.find(id);
		initialize(item);
		return item;
	}

	private void initialize(Item item) {
		item.getCarriage().getName();
		item.getProduct().getName();
	}

	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}
}
