package net.malta.web.app;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.getsecual.auth.client.UserAuthServiceClient;
import com.getsecual.auth.client.exception.AuthenticationException;
import com.getsecual.auth.client.model.json.AuthenticationUserRequest;
import com.getsecual.auth.client.model.json.AuthenticationUserResponse;

import net.malta.beans.LoginForm;
import net.malta.model.PublicUser;
import net.malta.model.Purchase;
import net.malta.model.PurchaseInfo;
import net.malta.model.user.json.AuthenticationResponse;
import net.malta.service.purchase.IPurchaseService;
import net.malta.service.user.IPublicUserService;
import net.malta.web.utils.BeanUtil;
import net.malta.web.utils.JSONResponseUtil;
import net.malta.web.utils.SessionData;

public class LoginAction extends Action{
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws Exception{
		
		LoginForm loginForm = (LoginForm) form;
		try {
			UserAuthServiceClient authClient = (UserAuthServiceClient) BeanUtil.getBean("userAuthServiceClient", 
					this.getServlet().getServletContext());
			AuthenticationUserRequest authenticationRequest = new AuthenticationUserRequest(loginForm.getEmail(), 
					loginForm.getPassword());
			AuthenticationUserResponse authenticationResponse = authClient.authenticateUser(authenticationRequest);
			
			IPublicUserService userService = (IPublicUserService) BeanUtil.getBean("publicUserService", this.getServlet().getServletContext());
			PublicUser publicUser = userService.getUserByAuthUser(authenticationResponse.getId());

			updatePurchase(req, res, publicUser);
			
			sendJSON(res, authenticationResponse, publicUser);
			
		} catch (AuthenticationException ae) {
			res.setStatus(ae.getStatusCode());
		}
		return null;
	}

	private void sendJSON(HttpServletResponse res, AuthenticationUserResponse authenticationResponse,
			PublicUser publicUser) throws IOException {
		AuthenticationResponse responseJSON = new AuthenticationResponse();
		responseJSON.setId(publicUser.getId());
		responseJSON.setAuthuserid(authenticationResponse.getId()); // auth userid
		responseJSON.setEmail(authenticationResponse.getEmail());
		responseJSON.setName(authenticationResponse.getName());
		res.setContentType("application/json");
		res.setHeader("access-token", authenticationResponse.getAccessToken());
		res.setHeader("client", authenticationResponse.getClient());
		res.setHeader("uid", authenticationResponse.getUid());			
		JSONResponseUtil.writeResponseAsJSON(res, responseJSON);
	}
	
	private void updatePurchase(HttpServletRequest req, HttpServletResponse res,PublicUser publicUser) {
		ServletContext context = this.getServlet().getServletContext();
		IPurchaseService purchaseService = (IPurchaseService) BeanUtil.getBean("purchaseService", 
				context);
		
		PurchaseInfo purchaseInfo = SessionData.getInstance(context).getSessionPuchaseInfo(req);
		
		Purchase purchase = purchaseService.getPurchase(purchaseInfo.getPurchaseId());
		
		purchase.setPublicUser(publicUser);
		
		purchaseService.updatePurchase(purchase);

		SessionData.getInstance(context).createNewSessionAndSetResponseHeaders(req, res,purchase);
	}
	
}