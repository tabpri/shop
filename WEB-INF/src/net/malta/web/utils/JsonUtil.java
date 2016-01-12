package net.malta.web.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author ITRex Group
 */
public enum JsonUtil {

    INSTANCE;

    public String toJson(Object objectToJson) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.toJson(objectToJson);
    }
}
