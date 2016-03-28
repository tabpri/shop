package net.malta.web.app;

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

import net.malta.error.ValidationError;
import net.malta.model.validator.ValidationException;
import net.malta.service.user.IPublicUserSessionService;
import net.malta.web.utils.BeanUtil;
import net.malta.web.utils.SessionData;

public class SignOutAction extends Action {

	private static final String SIGNOUT_SESSIONDOESNOTEXIST = "SIGNOUT_SESSIONDOESNOTEXIST";
	private static final String SIGNOUT_FAILED = "SIGNOUT_FAILED";
	private static final String SIGNOUT_INVALIDTOKENHEADERS = "SIGNOUT_INVALIDTOKENHEADERS";

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		

		IPublicUserSessionService publicUserSessionService = (IPublicUserSessionService) BeanUtil.getBean("publicUserSessionService",
				this.getServlet().getServletContext());

		String sessionToken = SessionData.getInstance(this.getServlet().getServletContext()).getSessionToken(request);
		if ( sessionToken != null ) {
			publicUserSessionService.expireSession(sessionToken);
		} else {
			throw new ValidationException(new ValidationError(SIGNOUT_SESSIONDOESNOTEXIST, new Object[]{}));							
		}

		UserAuthServiceClient authClient = (UserAuthServiceClient) BeanUtil.getBean("userAuthServiceClient",
				this.getServlet().getServletContext());

		String accessToken = request.getHeader(AuthApiConstants.AUTHAPI_HEADER_ACCESS_TOKEN);
		String client = request.getHeader(AuthApiConstants.AUTHAPI_HEADER_CLIENT);
		String uid = request.getHeader(AuthApiConstants.AUTHAPI_HEADER_UID);
	
		UserAuthApiTokens tokens = new UserAuthApiTokens(accessToken, client, uid);

		try {
			boolean success = authClient.signout(tokens);
			if ( !success ) {
				throw new ValidationException(new ValidationError(SIGNOUT_FAILED, new Object[]{accessToken,client,uid}));				
			}
		} catch (AuthenticationException ae) {
			throw new ValidationException(new ValidationError(SIGNOUT_INVALIDTOKENHEADERS, new Object[]{accessToken,client,uid}));
		}

		return null;
	}

}