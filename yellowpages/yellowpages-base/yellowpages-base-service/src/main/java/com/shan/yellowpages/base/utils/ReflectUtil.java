package com.shan.yellowpages.base.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 反射工具类
 * 可能存在性能问题，慎用
 * 
 * @author bruce
 * @version 1.0
 */
public class ReflectUtil {

	/**
	 * 通过反射方式获取类中定义的fields（包含父类）
	 * @param clazz
	 * @return
	 */
	public static Field[] getAllFields(Class clazz){
		List<Field> fieldList = new ArrayList<>();
		while (clazz != null){
			fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
			clazz = clazz.getSuperclass();
		}
		Field[] fields = new Field[fieldList.size()];
		fieldList.toArray(fields);
		return fields;
	}



	/**
	 * 字符串方式输出给定对象
	 * 
	 * @param obj 对象
	 * @return 对象String输出值
	 */
	@SuppressWarnings("rawtypes")
	public static String printBeanProperties(Object obj) {
		StringBuilder sb = new StringBuilder();

		sb.append("[" + Object.class.getName() + " - ");// 类名
		if (obj != null) {
			Class clazz = obj.getClass();
			Field[] fields = clazz.getDeclaredFields();
			if (fields != null && fields.length > 0) {
				for (Field field : fields) {
					field.setAccessible(true); // 设置些属性是可以访问的
					String fieldName = field.getName();
					try {
						sb.append(fieldName);
						sb.append(":");
						Object val = field.get(obj);
						sb.append(val);
					} catch (Exception e) {
						e.printStackTrace();
					} // 得到此属性的值
				}
			} else {
				sb.append(" is empty");
			}
		} else {
			sb.append("is null");
		}
		sb.append("]");
		return sb.toString();
	}
}
