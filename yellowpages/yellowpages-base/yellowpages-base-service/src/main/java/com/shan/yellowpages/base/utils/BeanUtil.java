//package com.shan.yellowpages.base.utils;
//
//import java.util.Map;
//
//import org.apache.commons.beanutils.BeanUtils;
//
///**
// * bean操作工具类
// *
// * @author :xuejw
// * @version 1.0
// */
//public class BeanUtil {
//
//	/**
//	 * Map 转换成 Bean，使用泛型免去了类型转换的麻烦。
//	 *
//	 * @param map map集合
//	 * @param clazz 类
//	 * @param <T> 泛型对象
//	 * @return 对象
//	 */
//	public static <T> T map2Bean(Map<String, String> map, Class<T> clazz) {
//		T bean = null;
//		try {
//			bean = clazz.newInstance();
//			BeanUtils.populate(bean, map);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return bean;
//	}
//
//	/**
//	 * 对象复制
//	 *
//	 * @param source 源对象
//	 * @param target 目标对象
//	 */
//	public static void copyProperties(Object source, Object target) {
//		if (source == null || target == null) {
//			return;
//		}
//		org.springframework.beans.BeanUtils.copyProperties(source, target);
//	}
//
//}
