//package com.shan.yellowpages.base.utils;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//
//import com.shan.yellowpages.base.exception.IKhErrorCode;
//import com.shan.yellowpages.base.exception.KhRuntimeException;
//import org.springframework.util.CollectionUtils;
//
//
///**
// * @author xuejw
// * @version 1.0
// * @className BeanValidatorUtil
// * @description bean数据校验工具
// * @date 2019-08-20 16:03
// */
//public class BeanValidatorUtil {
//
//	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//
//	/**
//	 * 验证某个bean的参数
//	 * <p>
//	 * 该方法使用hibernate-validation校验，使用该方法，需要在bean实体属性上添加对应的注解
//	 * </p>
//	 * <p>
//	 * 具体示例可以参考微信模块下demo
//	 * {@link com.bruce.geekway.weixin.model.mch.struct.WxPrepayRequestStruct}
//	 * </p>
//	 * <p>
//	 * 如有其它疑问，可以查看在线文档
//	 * </p>
//	 *
//	 * @param object 被校验的参数
//	 *
//	 * @see <a href=
//	 *      "https://www.cnblogs.com/albert1024/articles/8436270.html">https://www.cnblogs.com/albert1024/articles/8436270.html</a>
//	 * @throws KhRuntimeException 如果参数校验不成功则抛出此异常
//	 */
//	public static <T> void validate(T object) {
//		// 获得验证器
//		// 执行验证
//		Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);
//		// 如果有验证信息，则取出来包装成异常返回
//		if (CollectionUtils.isEmpty(constraintViolations)) {
//			return;
//		}
//		throw new KhRuntimeException(IKhErrorCode.SYSTEM_PARAM_ERROR,
//				convertErrorMsg(constraintViolations));
//	}
//
//	/**
//	 * 转换异常信息
//	 *
//	 * @param set
//	 * @param <T>
//	 * @return
//	 */
//	private static <T> String convertErrorMsg(Set<ConstraintViolation<T>> set) {
//		Map<String, StringBuilder> errorMap = new HashMap<>();
//		String property;
//		for (ConstraintViolation<T> cv : set) {
//			// 这里循环获取错误信息，可以自定义格式
//			property = cv.getPropertyPath().toString();
//			if (errorMap.get(property) != null) {
//				errorMap.get(property).append(",").append(cv.getMessage());
//			} else {
//				StringBuilder sb = new StringBuilder();
//				sb.append(cv.getMessage());
//				errorMap.put(property, sb);
//			}
//		}
//		return JsonUtil.gsonDisableHtml.toJson(errorMap);
//	}
//}
