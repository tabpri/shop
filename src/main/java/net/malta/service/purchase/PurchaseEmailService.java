package net.malta.service.purchase;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.MimeUtility;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.getsecual.email.client.Email;
import com.getsecual.email.client.IEmailSender;
import com.getsecual.email.client.exception.EmailException;

import net.enclosing.util.SimpleMail;
import net.malta.dao.meta.StaticDataDAO;
import net.malta.model.DeliveryAddress;
import net.malta.model.Purchase;
import net.malta.model.StaticData;
import net.malta.model.purchase.wrapper.PurchaseDeliveryAddress;

@Component
public class PurchaseEmailService implements IPurchaseEmailService {

	private static final Logger logger = LoggerFactory.getLogger(PurchaseEmailService.class);
	
	@Autowired
	StaticDataDAO staticDataDAO;

	@Autowired
	IEmailSender emailSender;
	
	@Autowired
	VelocityEngine velocityEngine;
	
	private final String subject = "【A&L】＊ご注文ありがとうございました（自動配信メール）＊";
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)	
	public void sendConfirmationEmail(Purchase purchase) {
		
       	@SuppressWarnings("rawtypes")
		Map model = new HashMap();
		model.put("purchase", purchase);
		DeliveryAddress deliveryAddress = new PurchaseDeliveryAddress(purchase).getDeliveryAddress();
		System.out.println("delivery address prefecture " + deliveryAddress.getPrefecture());
		System.out.println("delivery address prefecture " + deliveryAddress.getPrefecture().getName());
		
		model.put("deliveryaddress", deliveryAddress);
		Locale l = new Locale("ja", "JP");
		model.put("dateFormatter", new SimpleDateFormat("yyyy/MM/dd"));
		model.put("dayFormatter", new SimpleDateFormat("E", l));		
		model.put("timeFormatter", new SimpleDateFormat("HH:mm"));

		StaticData staticData = staticDataDAO.find(1);	

		String from = staticData.getFromaddress();
		
		try {
			model.put("fromstring", MimeUtility.encodeText("AFRICA & LEO", "ISO-2022-JP", "B") + "<"+staticData.getFromaddress()+">");
		} catch (UnsupportedEncodingException uee) {
			logger.error(uee.getMessage(),uee);
		}
		
		model.put("staticData", staticData);

		try {
	    	logger.info("email sending to the user---------------------------------------------");
	    	String userEmailString = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"MailAboutPurchaseToPublicUser.eml","UTF-8",model);
	    	String adminEmailString = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"MailAboutPurchaseToAdmin.eml","UTF-8",model);
	    	
	    	//emailSender.sendEmail(new Email(subject, from, purchase.getPublicUser().getMail(), userEmailString));
	    	emailSender.sendEmail(new Email(subject, from, "amruthasuri@gmail.com", adminEmailString));
	    	//emailSender.sendEmail(new Email(subject, from, purchase.getPublicUser().getMail(), userEmailString));
	    	
			//mail.send("MailAboutPurchaseToPublicUser.eml", purchase.getPublicUser().getMail(), model);
			
			//mail.send("MailAboutPurchaseToAdmin.eml", "aandl.order@gmail.com", model);
			//mail.send("MailAboutPurchaseToAdmin.eml", "toukubo+africaandleo@gmail.com", model);
			logger.info("email sent to the --------------------------------------------- " + purchase.getPublicUser().getMail());
		} catch (EmailException ee) {
			logger.error("sending emails failed for purchase:" + + purchase.getId(),ee);
			logger.error("email confirmation failed for purchase:" + + purchase.getId());			
		}
		catch (Exception e) {
			logger.error("email confirmation failed for purchase:" + + purchase.getId(),e);
		}		
	}

/*	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-mail.xml","classpath:applicationContext-localDataSource.xml","classpath:applicationContext.xml");
		PurchaseEmailService emailService = new PurchaseEmailService();
		emailService.setContext(context);
		PurchaseService purchaseService = context.getBean(PurchaseService.class);
		Purchase purchase = purchaseService.getPurchase(335);
		emailService.sendConfirmationEmail(purchase);
	}*/
}
