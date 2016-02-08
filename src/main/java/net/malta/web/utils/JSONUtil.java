package net.malta.web.utils;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.GsonBuilder;

import net.malta.model.Choise;
import net.malta.model.ChoiseImpl;
import net.malta.model.Item;
import net.malta.model.ItemImpl;
import net.malta.model.Purchase;
import net.malta.model.PurchaseImpl;
import net.malta.model.json.mapper.PurchaseMapper;

/**
 * 
 * @author SB
 * 
 * use this call for all json serialization
 */
public class JSONUtil {
	
	public static String serialize(Object o) {		
		return new GsonBuilder().create().toJson(o);
	}
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-mapper.xml");

		PurchaseMapper mapper = (PurchaseMapper) context.getBean("purchaseMapper");
		Purchase purchase = new PurchaseImpl();
		purchase.setId(1);
		Choise choise = new ChoiseImpl();
		Item item = new ItemImpl();
		item.setId(1);
		choise.setId(1);
		choise.setItem(item);
		choise.setPurchase(purchase);
		purchase.getChoises().add(choise);
		System.out.println(serialize(mapper.map(purchase, new net.malta.model.json.Purchase())));

	}
}