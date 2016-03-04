package net.malta.web.utils;

import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import net.enclosing.util.StringFullfiller;
import net.malta.model.PublicUser;
import net.malta.model.PublicUserImpl;
import net.malta.model.Purchase;
import net.malta.model.PurchaseImpl;
import net.malta.service.purchase.IPurchaseService;
import net.malta.service.user.IPublicUserService;
import net.malta.web.model.PurchaseInfo;

public class SessionData {

	public static final String PURCHASE_INFO = "PURCHASEINFO";

	public static void update(Purchase purchase, PublicUser publicUser,Session session) {
		purchase.setPublicUser(publicUser);
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(publicUser);
		session.saveOrUpdate(purchase);
		transaction.commit();		
		session.evict(publicUser);		
		session.evict(purchase);
	}

	public static void updateCookie(PublicUser publicUser,
			HttpServletRequest req, HttpServletResponse res) {

		
		Calendar calendarnum = Calendar.getInstance();
		Cookie cookie = new Cookie("malta",publicUser.getId().toString());
		cookie.setValue(publicUser.getId().toString());
		cookie.setComment("hoge");
		cookie.setMaxAge(60 * 60 * 168);

		res.addCookie(cookie); 
		// flush should not happen here - after the response is set.
/*		try {
			res.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
*/	}

	public static void update(PublicUser u, Purchase purchase,
			HttpServletRequest req, HttpServletResponse res,Session session) {
		update(purchase, u,session);
		updateCookie(u, req, res);

		req.getSession().setAttribute("u", u);
		req.getSession().setAttribute("purchase", purchase);
	}

	public static void 	updateSessionCookie(HttpServletRequest req, HttpServletResponse res) {
		
		PurchaseInfo purchaseInfo = getSessionPuchaseInfo(req);
		String userIdString = purchaseInfo.getUserId().toString();
				
		Cookie cookie = new Cookie("malta",userIdString);
		cookie.setValue(userIdString);
		cookie.setComment("hoge");
		cookie.setMaxAge(60 * 60 * 168);
		System.out.println("setting the cookie path to / -----------------------------------");
		cookie.setPath("/");
		res.addCookie(cookie);
		res.setHeader("malta", userIdString);
	}
	
	public static String getSessionCookie(HttpServletRequest req) {
    	if(req.getCookies()!=null){

        	for (int i = 0; i < req.getCookies().length; i++) {
    			if(req.getCookies()[i].getName().equals("malta")){
    				return req.getCookies()[i].getValue();
    			}
			}
    	}
    	return null;
	}
	public static PurchaseInfo getSessionPuchaseInfo(HttpServletRequest req) {
		return (PurchaseInfo) req.getSession().getAttribute(PURCHASE_INFO);
	}
	
	
	public static PurchaseInfo createUserAndPurchase(HttpServletRequest req,ServletContext context) {		
		Purchase purchase = new PurchaseImpl();
		PublicUser publicUser = new PublicUserImpl();
		StringFullfiller.fullfil(purchase);
		StringFullfiller.fullfil(publicUser);
		publicUser.setTemp(true);
		purchase.setTemp(true);
		purchase.setPublicUser(publicUser);
		
		IPurchaseService purchaseService = (IPurchaseService) BeanUtil.getBean("purchaseService", 
				context);

		IPublicUserService userService = (IPublicUserService) BeanUtil.getBean("publicUserService", 
				context);

		userService.createUser(publicUser);
		
		purchase = purchaseService.createPurchase(purchase);
		
		PurchaseInfo purchaseInfo = new PurchaseInfo(purchase.getId(), purchase.getPublicUser().getId());
		
		req.getSession().setAttribute(PURCHASE_INFO, purchaseInfo);

		return purchaseInfo;
	}

	public static void createTempPurchase(HttpServletRequest req,ServletContext context, Integer userId) {

		IPublicUserService userService = (IPublicUserService) BeanUtil.getBean("publicUserService", 
				context);

		PublicUser publicUser = userService.getUser(userId);

		IPurchaseService purchaseService = (IPurchaseService) BeanUtil.getBean("purchaseService", 
				context);

		Purchase purchase = new PurchaseImpl();
		StringFullfiller.fullfil(purchase);
		purchase.setTemp(true);
		
		purchase.setPublicUser(publicUser);
		purchase = purchaseService.createPurchase(purchase);
		
		PurchaseInfo purchaseInfo = new PurchaseInfo(purchase.getId(), purchase.getPublicUser().getId());
		
		req.getSession(false).setAttribute(PURCHASE_INFO, purchaseInfo);		
	}
	
	public static void updateSessionPurchaseInfoAndCookie(HttpServletRequest req,HttpServletResponse res,Integer userId) {
		PurchaseInfo sessionPuchaseInfo = getSessionPuchaseInfo(req);
		sessionPuchaseInfo.setUserId(userId);
		req.getSession(false).setAttribute(PURCHASE_INFO, sessionPuchaseInfo);
		updateSessionCookie(req, res);
	}
	
	public static PurchaseInfo getPurchaseUsingSessionCookie(HttpServletRequest req, ServletContext context,String cookie) {
		Integer cookieUserId = Integer.parseInt(cookie);
		IPurchaseService purchaseService = (IPurchaseService) BeanUtil.getBean("purchaseService", 
				context);
		
		Purchase currentPurchase = purchaseService.getUserCurrentPurchase(cookieUserId);
		
		PurchaseInfo purchaseInfo = new PurchaseInfo(currentPurchase.getId(), currentPurchase.getPublicUser().getId());
		
		req.getSession(false).setAttribute(PURCHASE_INFO, purchaseInfo);
		
		return purchaseInfo;
	}
}
