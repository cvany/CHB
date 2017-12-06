package com.chb.utils;

import java.lang.reflect.Type;
import com.google.gson.Gson;

/**
 * json与对象转换工具类
 * @author shilim
 *
 */
public class JsonUtil {
	private final static Gson gson = new Gson();

	/**
	 * json字符串转换成对象
	 * @param json
	 * @param clazz
	 * @return T
	 */
	public static <T> T jsonToObject(String json,Class<T> clazz) throws Exception {
		return gson.fromJson(json, clazz);
	}

	/**
	 * json字符串转换成对象
	 * @param json
	 * @param type (new TypeToken<List<T>>(){}.getType() ,new TypeToken<Map<T,T>>(){}.getType())
	 * @return T
	 */
	public static <T> T jsonToObject(String json,Type type) throws Exception {
		return gson.fromJson(json, type);
	}

	/**
	 * 对象转换成json字符串
	 * @param object
	 * @return
	 */
	public static String objectToJson(Object object) throws Exception {
		return gson.toJson(object);
	}

}
