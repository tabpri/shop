package net.malta.web.utils;

import com.google.gson.GsonBuilder;

/**
 * 
 * @author SB
 * 
 * use this call for all json serialization
 */
public class JSONUtil {
	
	public static String serialize(Object o) {		
		return new GsonBuilder().create().toJson(o);
	}
}