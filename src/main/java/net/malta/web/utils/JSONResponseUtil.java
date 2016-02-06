package net.malta.web.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class JSONResponseUtil {

	private static final String APPLICATION_JSON = "application/json; charset=utf-8";
	
	public static void writeObjectAsJSON(HttpServletResponse response,Object o) throws IOException {
		response.setContentType(APPLICATION_JSON);
		response.getWriter().print(JSONUtil.serialize(o));	
	}
}
