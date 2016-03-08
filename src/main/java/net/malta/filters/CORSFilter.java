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
		System.err.println("setting up the Access-Control-Allow-Origin in CORSFilter");
		HttpServletResponse res = (HttpServletResponse)response;
		HttpServletRequest req = (HttpServletRequest)request;
		
		res.addHeader(
			"Access-Control-Allow-Origin", "*"
		);
		System.err.println("setting up the Access-Control-Expose-Headers in CORSFilter");		
		res.setHeader("Access-Control-Expose-Headers", "Set-Cookie");
		chain.doFilter(request, response);
	}
}