package net.malta.web.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.malta.web.validator.Errors;

public class JSONResponseUtil {

	private static final String APPLICATION_JSON = "application/json; charset=utf-8";
	
	public static void writeResponseAsJSON(HttpServletResponse response,Object o) throws IOException {
		response.setContentType(APPLICATION_JSON);
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(JSONUtil.serialize(o));
	}
	
	public static void sendErrorJSON(HttpServletResponse res,Errors errors) throws IOException {
		res.setStatus(HttpServletResponse.SC_BAD_REQUEST);		
		JSONResponseUtil.writeResponseAsJSON(res, errors);
	}	
}
