package net.malta.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.malta.model.PurchaseInfo;
import net.malta.model.validator.ValidationException;
import net.malta.web.utils.JSONResponseUtil;
import net.malta.web.utils.SessionData;

public class PrivilegeManageFilter implements Filter {
	
	public static final ThreadLocal thread = new ThreadLocal();
	private static Log log = LogFactory.getLog(PrivilegeManageFilter.class);
	private ServletContext context = null;

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)req;
		HttpServletResponse httpResponse = (HttpServletResponse)res;
		boolean isNotCORSPreflightOptionsRequest = !httpRequest.getMethod().equals("OPTIONS");
		
		if ( isNotCORSPreflightOptionsRequest) {
			if(!httpRequest.getRequestURI().contains(".do") || httpRequest.getRequestURI().contains("/admin/")){
				chain.doFilter(req, res);
			}else if(req.getParameter("login") == null || req.getParameter("ajax") == null){
					synchronized (thread) {
						
						//malta - cookie/header
		            	String sessionCookie = SessionData.getInstance(context).getSessionCookie(httpRequest);
		            	
						boolean cookieexists = StringUtils.isNotBlank(sessionCookie);
						
						PurchaseInfo purchaseInfo  = null;
						
		            	if(cookieexists) {
							System.err.println(" session cookie malta is available ---------------------------------------- " + sessionCookie);
							
	            			try{ // check for the session
	            				purchaseInfo = SessionData.getInstance(context).checkUserSession(httpRequest);		            				
	            			} catch(ValidationException ve) {
	            				JSONResponseUtil.sendErrorJSON(httpResponse, ve.getErrors());
	            				return;
	            			}							
							if( purchaseInfo == null ) {
		        				purchaseInfo = SessionData.getInstance(context).createTempPurchase(
		        						Integer.valueOf(sessionCookie));
		            		}
		            	}else{
							System.err.println(" session cookie malta is not available ----------------------------------------");						
	            			purchaseInfo = SessionData.getInstance(context).createUserAndPurchase();       			
		            	}
		            SessionData.getInstance(context).setResponseHeaders(httpResponse,purchaseInfo);
				}
			}
			chain.doFilter(req, res);			
		} else {
			chain.doFilter(req, res);			
		}
	}


	public void init(FilterConfig config) throws ServletException {
		context = config.getServletContext();
	}	
}