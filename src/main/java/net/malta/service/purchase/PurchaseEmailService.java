package net.malta.service.purchase;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.enclosing.util.SimpleMail;
import net.malta.dao.meta.StaticDataDAO;
import net.malta.model.Purchase;
import net.malta.model.StaticData;

@Component
public class PurchaseEmailService implements IPurchaseEmailService {

	private static final Logger logger = LoggerFactory.getLogger(PurchaseEmailService.class);
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	StaticDataDAO staticDataDAO;

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)	
	public void sendConfirmationEmail(Purchase purchase) {
		
		SimpleMail mail = SimpleMail.create(context);

       	@SuppressWarnings("rawtypes")
		Map model = new HashMap();
		model.put("purchase", purchase);
		Locale l = new Locale("ja", "JP");
		model.put("dateFormatter", new SimpleDateFormat("yyyy/MM/dd"));
		model.put("dayFormatter", new SimpleDateFormat("E", l));		
		model.put("timeFormatter", new SimpleDateFormat("HH:mm"));

		StaticData staticData = staticDataDAO.find(1);	

		try {
			model.put("fromstring", MimeUtility.encodeText("AFRICA & LEO", "ISO-2022-JP", "B") + "<"+staticData.getFromaddress()+">");
		} catch (UnsupportedEncodingException uee) {
			logger.error(uee.getMessage(),uee);
		}
		
		model.put("staticData", staticData);

		try {
	    	logger.info("email sending to the admin---------------------------------------------");
			mail.send("MailAboutPurchaseToPublicUser.eml", purchase.getPublicUser().getMail(), model);
			//mail.send("MailAboutPurchaseToAdmin.eml", "aandl.order@gmail.com", model);
			//mail.send("MailAboutPurchaseToAdmin.eml", "toukubo+africaandleo@gmail.com", model);
			logger.info("email sent to the --------------------------------------------- " + purchase.getPublicUser().getMail());
		} catch (Exception e) {			
			logger.error("sending emails failed for the purchase " + purchase.getId(),e);
		}		
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-mail.xml","classpath:applicationContext-localDataSource.xml","classpath:applicationContext.xml");
		PurchaseEmailService emailService = new PurchaseEmailService();
		emailService.setContext(context);
		PurchaseService purchaseService = context.getBean(PurchaseService.class);
		Purchase purchase = purchaseService.getPurchase(335);
		emailService.sendConfirmationEmail(purchase);
	}
}
