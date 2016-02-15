/**
 * @author SB
 */
package net.malta.web.app;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import net.malta.beans.PublicUserForm;
import net.malta.beans.mapper.PublicUserFormMapper;
import net.malta.model.PublicUser;
import net.malta.model.PublicUserImpl;
import net.malta.model.Purchase;
import net.malta.model.json.mapper.PublicUserMapper;
import net.malta.model.validator.ValidationException;
import net.malta.service.purchase.IPurchaseService;
import net.malta.service.user.IPublicUserService;
import net.malta.web.model.PurchaseInfo;
import net.malta.web.utils.BeanUtil;
import net.malta.web.utils.JSONResponseUtil;
import net.malta.web.utils.SessionData;

public class PostPublicUserVPAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		
		PublicUserForm publicUserform = (PublicUserForm) form;

		try {

			PublicUser publicUser = mapUserForm(publicUserform);
			
			persistUser(publicUserform, publicUser);

			updatePurchase(req, res,publicUser);
			
			sendJSON(res, publicUser);

		} catch(ValidationException ve) {
			JSONResponseUtil.sendErrorJSON(res, ve.getErrors());
		}		
		return null;
	}


	private void updatePurchase(HttpServletRequest req, HttpServletResponse res,PublicUser publicUser) {
		IPurchaseService purchaseService = (IPurchaseService) BeanUtil.getBean("purchaseService", 
				this.getServlet().getServletContext());
		
		PurchaseInfo purchaseInfo = SessionData.getSessionPuchaseInfo(req);
		
		Purchase purchase = purchaseService.getPurchase(purchaseInfo.getPurchaseId());
		
		purchase.setPublicUser(publicUser);
		
		purchaseService.updatePurchase(purchase);

		SessionData.updateSessionPurchaseInfoAndCookie(req, res,publicUser.getId());
	}


	private PublicUser mapUserForm(PublicUserForm publicUserform) {
		PublicUserFormMapper mapper = (PublicUserFormMapper) BeanUtil.getBean("publicUserFormMapper",
				this.getServlet().getServletContext());
		PublicUser publicUser = mapper.map(publicUserform, new PublicUserImpl());
		return publicUser;
	}

	
	private void persistUser(PublicUserForm publicUserform, PublicUser publicUser) {
		IPublicUserService userService = (IPublicUserService) BeanUtil.getBean("publicUserService",
				this.getServlet().getServletContext());

		if (publicUserform.getId() != null) {
			userService.updateUser(publicUser);
		} else {
			userService.createUser(publicUser);
		}
	}

	private void sendJSON(HttpServletResponse res, PublicUser publicUser) throws IOException {
		PublicUserMapper mapper = (PublicUserMapper) BeanUtil.getBean("publicUserMapper",
				this.getServlet().getServletContext());
		net.malta.model.user.json.PublicUser publicUserJson = mapper.map(publicUser, new net.malta.model.user.json.PublicUser());
		JSONResponseUtil.writeResponseAsJSON(res, publicUserJson);
	}

}