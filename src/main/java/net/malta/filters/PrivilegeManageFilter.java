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
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.malta.web.utils.SessionData;

public class PrivilegeManageFilter implements Filter {
	
	public static final ThreadLocal thread = new ThreadLocal();
	private static Log log = LogFactory.getLog(PrivilegeManageFilter.class);
	private ServletContext context = null;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req2 = (HttpServletRequest)req;
		HttpServletResponse res2 = (HttpServletResponse)res;
		if(!req2.getRequestURI().contains(".do")){
			chain.doFilter(req, res);
		}else if(req.getParameter("login") == null || req.getParameter("ajax") == null){
				synchronized (thread) {

	            	String sessionCookie = SessionData.getSessionCookie(req2);
	            	
					boolean cookieexists = StringUtils.isNotBlank(sessionCookie);
					
	            	if(cookieexists) {            			
						System.err.println(" session cookie malta is available ---------------------------------------- " + sessionCookie);

	            		if( SessionData.getSessionPuchaseInfo(req2) == null ) {            				
	        				SessionData.createTempPurchase(req2, this.context, Integer.valueOf(sessionCookie));
	            			SessionData.updateSessionCookie(req2, res2);            				
	            		}

//		            	Criteria criteria = session.createCriteria(IntraUser.class);
//		            	criteria.add(Restrictions.eq("mail",mailstr));
//		            	IntraUser intraUser = (IntraUser)criteria.uniqueResult();
//		            	req.setAttribute("u",intraUser);
//		            	req.setAttribute("p",intraUser.getPrivilege());
//		            	session.refresh(intraUser);
//		            	criteria = null;
	            		
	            	}else{

						System.err.println(" session cookie malta is not available ----------------------------------------");
						
            			SessionData.createUserAndPurchase(req2,this.context);
            			SessionData.updateSessionCookie(req2, res2);
	            			
	            }
			}

		}
		chain.doFilter(req, res);
	}

	public void init(FilterConfig config) throws ServletException {
		context = config.getServletContext();

	}

}
