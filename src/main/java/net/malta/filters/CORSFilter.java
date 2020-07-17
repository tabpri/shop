/**
 * @author SB
 */
package net.malta.filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// simple CORS filters adds the Access-Control-Allow-Origin, should not be in the production
//  web.xml should not have this entry in the production build or more specific secual domains for access
public class CORSFilter implements Filter {

	public CORSFilter() { }

	public void init(FilterConfig fConfig) throws ServletException { }

	public void destroy() {	}

	public void doFilter(
		ServletRequest request, ServletResponse response, 
		FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		
		if ( !httpRequest.getRequestURI().contains("/admin/") ) { // admin requests should not have the CORS headers			
			System.err.println("setting up the Access-Control-Allow-Origin in CORSFilter");
			HttpServletResponse res = (HttpServletResponse)response;
			HttpServletRequest req = (HttpServletRequest)request;
			
			res.addHeader(
				"Access-Control-Allow-Origin", "*"
			);
			System.err.println("setting up the Access-Control-Expose-Headers in CORSFilter");		
			res.setHeader("Access-Control-Expose-Headers", "secual-auth-token,malta");
			if ( req.getHeader("Access-Control-Request-Headers") != null ) {
				System.err.println("setting up the Access-Control-Allow-Headers in CORSFilter " + 
								req.getHeader("Access-Control-Request-Headers"));
				res.setHeader("Access-Control-Allow-Headers", req.getHeader("Access-Control-Request-Headers"));			
			}			
		}
		chain.doFilter(request, response);
	}
}