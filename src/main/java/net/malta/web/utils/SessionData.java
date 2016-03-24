package net.malta.web.utils;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import net.malta.error.ValidationError;
import net.malta.model.PublicUser;
import net.malta.model.PublicUserSession;
import net.malta.model.Purchase;
import net.malta.model.PurchaseInfo;
import net.malta.model.validator.ValidationException;
import net.malta.service.purchase.IPurchaseService;
import net.malta.service.user.IPublicUserService;
import net.malta.service.user.IPublicUserSessionService;

public class SessionData {

	public static final String MALTA = "malta";

	public static final String SECUAL_AUTH_TOKEN = "secual-auth-token";

	public static final String PURCHASE_INFO = "PURCHASEINFO";

	private static SessionData sessionData;
	
	private IPurchaseService purchaseService;
	
	private IPublicUserService publicUserService;
	
	private IPublicUserSessionService  publicUserSessionService;
	
	public SessionData(ServletContext context) {
		initBeans(context);
	}
	
	private void initBeans(ServletContext context) {
		this.publicUserService = (IPublicUserService) BeanUtil.getBean("publicUserService", context);
		this.purchaseService = (IPurchaseService) BeanUtil.getBean("purchaseService", context);
		this.publicUserSessionService = (IPublicUserSessionService) BeanUtil.getBean("publicUserSessionService", context);		
	}

	// TODO: need to implement double checking
	public static SessionData getInstance(ServletContext context) {
		if ( sessionData == null ) {
			sessionData = new SessionData(context);
		}
		return sessionData;
	}
	
/*	public static void updateCookie(PublicUser publicUser,
			HttpServletRequest req, HttpServletResponse res) {

		
		Calendar calendarnum = Calendar.getInstance();
		Cookie cookie = new Cookie("malta",publicUser.getId().toString());
		cookie.setValue(publicUser.getId().toString());
		cookie.setComment("hoge");
		cookie.setMaxAge(60 * 60 * 168);

		res.addCookie(cookie); 
		// flush should not happen here - after the response is set.
		try {
			res.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
/*	public static void update(PublicUser u, Purchase purchase,
			HttpServletRequest req, HttpServletResponse res,Session session) {
		update(purchase, u,session);
		updateCookie(u, req, res);

		req.getSession().setAttribute("u", u);
		req.getSession().setAttribute("purchase", purchase);
	}

*/	
	public void updateSessionCookie(HttpServletRequest req, HttpServletResponse res) {
		
		PurchaseInfo purchaseInfo = getSessionPuchaseInfo(req);
		String userIdString = purchaseInfo.getUserId().toString();
				
		Cookie cookie = new Cookie(MALTA,userIdString);
		cookie.setValue(userIdString);
		cookie.setComment("hoge");
		cookie.setMaxAge(60 * 60 * 168);
		System.out.println("setting the cookie path to / -----------------------------------");
		cookie.setPath("/");
		res.addCookie(cookie);
		res.setHeader(MALTA, userIdString);
	}
	
	public String getSessionCookie(HttpServletRequest req) {
    	if(req.getCookies()!=null){

        	for (int i = 0; i < req.getCookies().length; i++) {
    			if(req.getCookies()[i].getName().equals(MALTA)){
    				return req.getCookies()[i].getValue();
    			}
			}
    	}
    	return req.getHeader(MALTA);
	}
	
	public PurchaseInfo getSessionPuchaseInfo(HttpServletRequest req) {
		String sessionToken = getSessionToken(req);
		
		PublicUserSession userSession = publicUserSessionService.getSession(sessionToken);
		
		// this exception would be caught by the ShopRequestProcessor and sends the errors json
		if ( userSession == null ) {
			throw new ValidationException(
					new ValidationError("PUBLICUSER.SESSION.DOESNOTEXIST", new Object[]{sessionToken}));			
		}

		PurchaseInfo purchaseInfo = new PurchaseInfo(userSession.getPurchase(), userSession.getPublicUser(), 
				userSession.getSessionToken());
		return purchaseInfo;
	}

	public String getSessionToken(HttpServletRequest req) {
		String sessionToken = req.getHeader(SECUAL_AUTH_TOKEN);
		
		// for payment callback
		sessionToken = (sessionToken==null) ? req.getParameter(SECUAL_AUTH_TOKEN) : sessionToken;
		return sessionToken;
	}
	
	
	public PurchaseInfo createUserAndPurchase() {

		PublicUser user = publicUserService.createTempUser();
		Purchase purchase = purchaseService.createTempPurchase(user);

		PurchaseInfo purchaseInfo = createUserSession(user, purchase, null);
		
		return purchaseInfo;
	}

	private PurchaseInfo createUserSession(PublicUser user,
			Purchase purchase,String sessionToken ) {

		Integer purchaseId = purchase.getId();

		Integer userId = user.getId();
		
		PublicUserSession userSession = publicUserSessionService.createSession(userId,purchaseId,sessionToken);
		
		PurchaseInfo purchaseInfo = new PurchaseInfo(purchaseId, userId, userSession.getSessionToken());
		return purchaseInfo;
	}


	public PurchaseInfo createTempPurchase(Integer userId, String sessionToken) {

		PublicUser publicUser = publicUserService.getUser(userId);

		Purchase purchase = purchaseService.createTempPurchase(publicUser);
		
		Integer purchaseId = purchase.getId();

		PurchaseInfo purchaseInfo = createUserSession(publicUser, purchase,sessionToken);
		
		return purchaseInfo;		
	}
	
	public PurchaseInfo createTempPurchase(Integer userId) {
		return createTempPurchase(userId,null);
	}
	
	public void updateSessionPurchaseInfoAndCookie(HttpServletRequest req,HttpServletResponse res,Integer userId) {
		PurchaseInfo sessionPuchaseInfo = getSessionPuchaseInfo(req);
		sessionPuchaseInfo.setUserId(userId);
		req.getSession(false).setAttribute(PURCHASE_INFO, sessionPuchaseInfo);
		updateSessionCookie(req, res);
	}

	public void createNewSessionAndSetResponseHeaders(HttpServletRequest req,HttpServletResponse res,Purchase purchase) {
		String sessionToken = req.getHeader(SECUAL_AUTH_TOKEN);
		PurchaseInfo purchaseInfo = createUserSession(purchase.getPublicUser(), purchase,sessionToken);
		setResponseHeaders(res, purchaseInfo);
	}

	public PurchaseInfo getPurchaseUsingSessionCookie(HttpServletRequest req, ServletContext context,String cookie) {
		Integer cookieUserId = Integer.parseInt(cookie);
		IPurchaseService purchaseService = (IPurchaseService) BeanUtil.getBean("purchaseService", 
				context);
		
		Purchase currentPurchase = purchaseService.getUserCurrentPurchase(cookieUserId);
		
		PurchaseInfo purchaseInfo = new PurchaseInfo(currentPurchase.getId(), currentPurchase.getPublicUser().getId());
		
		req.getSession(false).setAttribute(PURCHASE_INFO, purchaseInfo);
		
		return purchaseInfo;
	}
	
	public PurchaseInfo checkUserSession(HttpServletRequest req) {
		
		String sessionToken = req.getHeader(SECUAL_AUTH_TOKEN);
		
		if ( StringUtils.isBlank(sessionToken)) {
			return null;
		}
				
		PublicUserSession userSession = publicUserSessionService.checkUserSession(sessionToken);
		PurchaseInfo purchaseInfo = new PurchaseInfo(userSession.getPurchase(), 
				userSession.getPublicUser(), userSession.getSessionToken());
		
		return purchaseInfo;
	}
	
	public void setResponseHeaders(HttpServletResponse res,PurchaseInfo purchaseInfo) {
		String userId = purchaseInfo.getUserId().toString();
		res.setHeader(SECUAL_AUTH_TOKEN, purchaseInfo.getUserSessionToken());
		res.setHeader(MALTA, userId);
	}

}