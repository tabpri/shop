package net.malta.web.app.admin;

import static net.malta.web.utils.ActionUtil.getModulePrefix;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.enclosing.util.HTTPGetRedirection;
import net.malta.beans.UserForm;
import net.malta.error.Errors;
import net.malta.error.IError;
import net.malta.mapper.IMapper;
import net.malta.model.User;
import net.malta.model.validator.ValidationException;
import net.malta.service.user.IUserService;
import net.malta.web.utils.BeanUtil;

public class UserAction extends DispatchAction {

	private static final Logger logger = LoggerFactory.getLogger(UserAction.class);

	public ActionForward saveUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

		logger.debug("saveUser");
		UserForm userForm = (UserForm) form;
		
		@SuppressWarnings("unchecked")
		IMapper<UserForm, User> userFormMapper = (IMapper<UserForm, User>) BeanUtil.getBean("userFormMapper", 
				this.getServlet().getServletContext());
		
		User user = userFormMapper.map(userForm, new User());
		
		IUserService userService = (IUserService) BeanUtil.getBean("userService", 
				this.getServlet().getServletContext());
		try {
			logger.debug("saving the user - userService.createUser");
			if ( user.getId() != null ) {
				userService.updateUser(user);				
			} else {
				userService.createUser(user);				
			}
		} catch (ValidationException ve) {
			logger.debug("saving the user - validation errors");						
			toActionErrors(request,ve.getErrors());
			return mapping.findForward("user");
		}
		logger.debug("saving the user - successful, return to the userlist");
		new HTTPGetRedirection(request, response, getModulePrefix(request)+"User.do?action=userlist", null);
		return  null;
    }

	@SuppressWarnings("deprecation")
	private void toActionErrors(HttpServletRequest request,Errors errors) {
		ActionErrors actionErrors = new ActionErrors();
		for (IError iError : errors) {
			actionErrors.add("",new ActionError(iError.getErrorCode(),iError.getValues()));
		}
		request.setAttribute(Globals.ERROR_KEY, actionErrors);
		request.getSession().setAttribute(
				Globals.LOCALE_KEY, Locale.JAPAN);
			
	}

	public ActionForward deleteUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

		Integer userId = Integer.parseInt(request.getParameter("id"));		

		IUserService userService = (IUserService) BeanUtil.getBean("userService", 
				this.getServlet().getServletContext());
				
		userService.deleteUser(userId);

		new HTTPGetRedirection(request, response, getModulePrefix(request)+"User.do?action=userlist", null);
		
		return null;
    }

	@SuppressWarnings("unchecked")
	public ActionForward showUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {		
		IUserService userService = (IUserService) BeanUtil.getBean("userService", 
				this.getServlet().getServletContext());
		
		String sId = request.getParameter("id");
		if ( StringUtils.isNotBlank(sId) ) {
			Integer userId = Integer.parseInt(sId);
			User user = userService.getUser(userId);
			IMapper<User, UserForm> userToUserFormMapper = (IMapper<User, UserForm>) BeanUtil.getBean("userToUserFormMapper", 
					this.getServlet().getServletContext());		
			UserForm userForm = userToUserFormMapper.map(user, new UserForm());			
			request.setAttribute("userForm", userForm);			
		}		
		return mapping.findForward("user");
	}
	
	public ActionForward userlist(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

		IUserService userService = (IUserService) BeanUtil.getBean("userService", 
				this.getServlet().getServletContext());

		List<User> users = userService.getUsers();
		
		request.setAttribute("userlist", users);
		
		return mapping.findForward("userlist");
   }
}