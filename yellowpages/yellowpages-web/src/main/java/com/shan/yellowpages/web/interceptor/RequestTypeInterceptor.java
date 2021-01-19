package com.shan.yellowpages.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 请求类型拦截器
 * @author bruce
 */
@Slf4j
public class RequestTypeInterceptor implements HandlerInterceptor {

	/** */
	public static final String IS_AJAX_REQUEST = "isAjaxRequest";

	/**
	 *
	 * @param req
	 * @param res
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
			throws Exception {

		if (!(handler instanceof HandlerMethod)) {
			return true;
		}

		String reqURI = req.getRequestURI();

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();


		//判断条件: 1，是否是RestController修饰；2，是否有ResponseBody；3. 是否是.json结尾
		boolean isAjaxRequest = false;
		if(handlerMethod.getBeanType().isAnnotationPresent(RestController.class) || method.isAnnotationPresent(
				ResponseBody.class) || StringUtils.endsWithIgnoreCase(reqURI, ".json")){
			isAjaxRequest = true;
		}
		req.setAttribute(IS_AJAX_REQUEST, isAjaxRequest);
		return true;
	}

	/**
	 *
	 * @param req
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest req){
		Boolean result = (Boolean) req.getAttribute(IS_AJAX_REQUEST);
		return result==null?false:result;
	}

}
