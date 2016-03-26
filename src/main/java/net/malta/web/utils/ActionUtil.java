package net.malta.web.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.ModuleUtils;

public class ActionUtil {

	// for example: returns /admin as admin/
	public static String getModulePrefix(HttpServletRequest request) {
		ModuleConfig moduleConfig = ModuleUtils.getInstance().getModuleConfig(request);
		String prefix = moduleConfig.getPrefix();
		prefix = (prefix == null ) ? "" : prefix.substring(1, prefix.length()) + "/";
		return prefix;
	}	
}
