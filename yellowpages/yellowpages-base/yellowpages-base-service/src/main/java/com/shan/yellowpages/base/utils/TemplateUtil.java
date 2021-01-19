package com.shan.yellowpages.base.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 模板操作的工具类
 * 
 * @author bruce
 * @version 1.0
 */
public class TemplateUtil {


	private static final Pattern pt = Pattern.compile("\\$\\{\\s*([^\\}\\s]+)\\s*\\}");


	/**
	 * 取出templateText文本中${parameter}的parameter<br>
	 * 作为key将parameterMap中对应的value放在templateText模板中，返回内容
	 *
	 *
	 * @param templateText 模板文本，如：感谢您注册1758,ID是${id},昵称是${username},你的密码是${password}。
	 * @param parameterMap 参数映射表
	 * @return 替换之后的结果
	 */
	public static String produceTemplate(String templateText, Map<String, String> parameterMap) {
		// parameter前后空格已经去掉,对应的map中key前后应该不应该有空格了
		Matcher mt = pt.matcher(templateText);
		StringBuffer sb = new StringBuffer();
		while (mt.find()) {
			// 将替换后的子串以及其之前到上次匹配子串之后的字符串段添加到一个StringBuffer对象里(matcher.appendReplacement(
			// sb, "b")就是在Matcher.find()找到匹配的地方用“b”替换掉，然后加进StringBuffer中去)

			String itemValue = parameterMap.get(mt.group(1));
			itemValue = StringUtils.trimToEmpty(itemValue);

			mt.appendReplacement(sb, itemValue);
		}
		// 将最后一次匹配工作后剩余的字符串添加到一个StringBuffer对象里
		mt.appendTail(sb);
		String result = sb.toString();
		return result;
	}

	// public static void main(String[] args) {
	// String templateText = "感谢您注册1758,ID是${id},昵称是${username}, 你的密码是${password}。";
	// Map<String, String> paramMap = new HashMap<String, String>();
	// paramMap.put("id", "13132");
	// System.out.println(produceTemplate(templateText, paramMap));
	//
	// }

}
