package net.malta.web.app;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.getsecual.auth.client.UserAuthApiTokens;
import com.getsecual.auth.client.UserAuthServiceClient;
import com.getsecual.auth.client.constant.AuthApiConstants;
import com.getsecual.auth.client.exception.AuthenticationException;
import com.getsecual.auth.client.model.json.AuthenticationUserResponse;

import net.malta.error.ValidationError;
import net.malta.model.PublicUser;
import net.malta.model.PurchaseInfo;
import net.malta.model.json.mapper.PublicUserMapper;
import net.malta.model.validator.ValidationException;
import net.malta.service.user.IPublicUserService;
import net.malta.web.utils.BeanUtil;
import net.malta.web.utils.JSONResponseUtil;
import net.malta.web.utils.SessionData;

public class SignupConfirmationAction extends Action {

	private static final String SIGNUPCONFIRMATION_USERDOESNOTEXISTWITHTHISUID = "SIGNUPCONFIRMATION..USERDOESNOTEXISTWITHTHISUID";
	private static final String SIGNUPCONFIRMATION_INVALIDTOKENHEADERS = "SIGNUPCONFIRMATION.INVALIDTOKENHEADERS";

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ServletContext servletContext = this.getServlet().getServletContext();

		UserAuthServiceClient authClient = (UserAuthServiceClient) BeanUtil.getBean("userAuthServiceClient",
				this.getServlet().getServletContext());

		String accessToken = request.getHeader(AuthApiConstants.AUTHAPI_HEADER_ACCESS_TOKEN);
		String client = request.getHeader(AuthApiConstants.AUTHAPI_HEADER_CLIENT);
		String uid = request.getHeader(AuthApiConstants.AUTHAPI_HEADER_UID);

		UserAuthApiTokens tokens = new UserAuthApiTokens(accessToken, client, uid);

		AuthenticationUserResponse authenticationUserResponse = null;
		try {
			authenticationUserResponse = authClient.validateToken(tokens);
		} catch (AuthenticationException ae) {
			throw new ValidationException(new ValidationError(SIGNUPCONFIRMATION_INVALIDTOKENHEADERS, new Object[]{accessToken,client,uid}));
		}

		IPublicUserService publicUserService = (IPublicUserService) BeanUtil.getBean("publicUserService",
				servletContext);

		PurchaseInfo puchaseInfo = SessionData.getInstance(servletContext).getSessionPuchaseInfo(request);			;

		if ( puchaseInfo == null ) { // no session found then get the user by the uid(email)
			PublicUser publicUser = publicUserService.getUserByEmail(uid);
			if ( publicUser == null ) {
				throw new ValidationException(new ValidationError(SIGNUPCONFIRMATION_USERDOESNOTEXISTWITHTHISUID, null, uid));
			}
			puchaseInfo = SessionData.getInstance(servletContext).createTempPurchase(publicUser.getId());
		}
		
		PublicUser publicUser = publicUserService.updateAuthUser(puchaseInfo.getUserId(),
				authenticationUserResponse.getId());

        SessionData.getInstance(this.getServlet().getServletContext()).setResponseHeaders(response,puchaseInfo);

		PublicUserMapper mapper = (PublicUserMapper) BeanUtil.getBean("publicUserMapper",
				this.getServlet().getServletContext());
		net.malta.model.user.json.PublicUser publicUserJson = mapper.map(publicUser,
				new net.malta.model.user.json.PublicUser());
		JSONResponseUtil.writeResponseAsJSON(response, publicUserJson);

		return null;
	}

}