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
import com.getsecual.auth.client.constant.AuthApiConstants;
import com.getsecual.auth.client.exception.AuthenticationException;
import com.getsecual.auth.client.model.json.AuthenticationUserRequest;
import com.getsecual.auth.client.model.json.AuthenticationUserResponse;

import net.malta.beans.LoginForm;
import net.malta.model.PublicUser;
import net.malta.model.Purchase;
import net.malta.model.PurchaseInfo;
import net.malta.model.user.json.AuthenticationResponse;
import net.malta.model.validator.ValidationException;
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
			
			publicUser = updatePurchase(req, res, publicUser);
			
			userService.updateAuthUser(publicUser.getId(), 
						authenticationResponse.getId());
				
			sendJSON(req,res, authenticationResponse, publicUser);
			
		} catch (AuthenticationException ae) {
			res.setStatus(ae.getStatusCode());
		}
		return null;
	}

	private void sendJSON(HttpServletRequest req, HttpServletResponse res, AuthenticationUserResponse authenticationResponse,
			PublicUser publicUser) throws IOException {
		
		AuthenticationResponse responseJSON = new AuthenticationResponse();
		responseJSON.setId(publicUser.getId());
		responseJSON.setAuthuserid(authenticationResponse.getId()); // auth userid
		responseJSON.setEmail(authenticationResponse.getEmail());
		responseJSON.setName(authenticationResponse.getName());
		res.setContentType("application/json");
		res.setHeader(AuthApiConstants.AUTHAPI_HEADER_ACCESS_TOKEN, authenticationResponse.getAccessToken());
		res.setHeader(AuthApiConstants.AUTHAPI_HEADER_CLIENT, authenticationResponse.getClient());
		res.setHeader(AuthApiConstants.AUTHAPI_HEADER_UID, authenticationResponse.getUid());
		res.setHeader("Access-Control-Expose-Headers", "secual-auth-token,malta,access-token,client,uid");
		JSONResponseUtil.writeResponseAsJSON(res, responseJSON);
	}
	
	private PublicUser updatePurchase(HttpServletRequest req, HttpServletResponse res,PublicUser loggedInPublicUser) {		
		ServletContext context = this.getServlet().getServletContext();
		IPurchaseService purchaseService = (IPurchaseService) BeanUtil.getBean("purchaseService", 
				context);

		PurchaseInfo purchaseInfo = null;
		Purchase purchase = null;
		
		purchaseInfo = SessionData.getInstance(context).getSessionPuchaseInfo(req);
		// session found
		if ( purchaseInfo != null ) {
			purchase = purchaseService.getPurchase(purchaseInfo.getPurchaseId());
			if ( loggedInPublicUser != null ) { // if public user found with the logged in user auth user id then map it to purchase
				purchase.setPublicUser(loggedInPublicUser);
				purchaseService.updatePurchase(purchase);
			} else { 
				// do nothing - purchase is already mapped to the its a session public user							
			}
			purchaseInfo = SessionData.getInstance(context).createNewSessionAndSetResponseHeaders(req, res, purchase);				
		}
		else { // no session found
			if ( loggedInPublicUser == null ) { // no public user available then create both public user and purchase
				purchaseInfo = SessionData.getInstance(context).createUserAndPurchase();
			} else { // if user found with the logged in user auth user id then create the  purchase and map it to purchase
				purchaseInfo = SessionData.getInstance(context).createTempPurchase(loggedInPublicUser.getId());
			}
			SessionData.getInstance(context).setResponseHeaders(res, purchaseInfo);			
		}

		IPublicUserService publicUserService = (IPublicUserService) BeanUtil.getBean("publicUserService", this.getServlet().getServletContext());
		return publicUserService.getUser(purchaseInfo.getUserId());
	}
	
}