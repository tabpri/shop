package net.malta.service.purchase;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import net.enclosing.util.SimpleMail;
import net.malta.dao.meta.StaticDataDAO;
import net.malta.model.Choise;
import net.malta.model.Purchase;
import net.malta.model.StaticData;

public class PurchaseEmailService {

	@Autowired
	ApplicationContext context;
	
	@Autowired
	StaticDataDAO staticDataDAO;
	
	public void confirmPurchaseEmail(Purchase purchase) {
		
		SimpleMail mail = SimpleMail.create(context);

       	Map model = new HashMap();
		model.put("purchase", purchase);
		Locale l = new Locale("ja", "JP");
		model.put("dateFormatter", new SimpleDateFormat("yyyy/MM/dd"));
		model.put("dayFormatter", new SimpleDateFormat("E", l));		
		model.put("timeFormatter", new SimpleDateFormat("HH:mm"));

		StaticData staticData = staticDataDAO.find(1);	

		// this code needs to be part of template
		StringBuilder builder = new StringBuilder();
				//when there are no delivery address ( direct to public User)
				builder.append("▼配送先情報");
				builder.append("\r\n");
				builder.append("================================================================");
				builder.append("\r\n");
				builder.append("お名前　　　　　　：" );
				System.out.println("purchase object "+purchase);
				System.out.println("publicuser "+purchase.getPublicUser());
				builder.append(purchase.getPublicUser().getName() +  "様");
				builder.append("\r\n");
				builder.append("フリガナ　　　　　：" );
				builder.append(purchase.getPublicUser().getKana());
				builder.append("\r\n");
				builder.append("郵便番号　　　　　："  );
				builder.append(purchase.getPublicUser().getZipfour());
				builder.append("\r\n");
				builder.append("ご住所　　　　　　：" );
				if(purchase.getPublicUser().getPrefecture()!=null){
					builder.append(purchase.getPublicUser().getPrefecture().getName() + " " + purchase.getPublicUser().getAddress() + " " + purchase.getPublicUser().getBuildingname());
				}
				builder.append("\r\n");
				builder.append("電話番号　　　　　：" );
				builder.append( purchase.getPublicUser().getPhone());
				builder.append("\r\n");
			builder.append("\r\n");
			for (Iterator iterator = purchase.getChoises().iterator(); iterator.hasNext();) {
				Choise choise = (Choise) iterator.next();
				builder.append("----------------------------------------------------------------");
				builder.append("\r\n");
				builder.append("商品詳細" );
				builder.append("\r\n");
				builder.append("----------------------------------------------------------------");
				builder.append("\r\n");
				builder.append("商品名　　　　　　：");
				builder.append(choise.getName());
				builder.append("\r\n");
				builder.append("価格（税込）　　　：");
				builder.append(choise.getPricewithtax()+"円");
				builder.append("\r\n");
				builder.append("数量　　　　　　　：");
				builder.append(choise.getOrdernum());
				builder.append("\r\n");
				builder.append("----------------------------------------------------------------");
				builder.append("\r\n");
				builder.append("\r\n");
			}
			model.put("deliveryaddress",builder.toString().replaceAll("～", " - "));
//
		try {
			model.put("fromstring", MimeUtility.encodeText("AFRICA & LEO", "ISO-2022-JP", "B") + "<"+staticData.getFromaddress()+">");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		model.put("staticData", staticData);

		try {
	    	System.err.println("email sending to the admin---------------------------------------------");
			mail.send("MailAboutPurchaseToPublicUser.eml", purchase.getPublicUser().getMail(), model);
			mail.send("MailAboutPurchaseToAdmin.eml", "aandl.order@gmail.com", model);
			mail.send("MailAboutPurchaseToAdmin.eml", "toukubo+africaandleo@gmail.com", model);
	    	System.err.println("email sent to the --------------------------------------------- " + purchase.getPublicUser().getMail());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}
