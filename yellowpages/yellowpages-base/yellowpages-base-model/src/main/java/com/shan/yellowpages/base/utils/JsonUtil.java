package com.shan.yellowpages.base.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * google gson json 工具类
 * 
 * @author bruce
 * @version 1.0
 */
public class JsonUtil {

	/**
	 * 通用json
	 */
	public static Gson gson = new GsonBuilder().create();

	/**
	 * 通用json
	 */
	public static Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();
	/**
	 * Gson 不把 html 标签转换为 Unicode 转义字符
	 */
	public static Gson gsonDisableHtml = new GsonBuilder().disableHtmlEscaping().create();

	public static String toJson(Object obj) {
		return gsonPretty.toJson(obj);
	}
}
