package com.shan.yellowpages.web.interceptor;

import com.shan.yellowpages.annotation.AuthorizeConfig;
import com.shan.yellowpages.controller.base.AbstractBaseController;
import com.shan.yellowpages.security.model.KhAdminUserEntity;
import com.shan.yellowpages.util.KhInterceptorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权拦截器
 *
 * @author liqian
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor, InitializingBean {


//	private static final String COOKIE_NAME = "kh_user_token";
//	/** 使用 map 作为local缓存, 避免每次通过反射获取 */
//	private static Map<HandlerMethod, AuthorizeConfig.AuthorizeStrategy> authorizeStrategyMap = new HashMap<>();

	@Override
	public void afterPropertiesSet() {
		//		Assert.notNull(khRpcPassportTokenService, "khRpcPassportTokenService can't not null");
	}

	/**
	 * 用户操作拦截检查
	 */
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
			throws Exception {

		String reqUri = req.getRequestURI();

		if (!(handler instanceof HandlerMethod)) {
			/*
			 * When the method has @CrossOrigin, error is occured. and called
			 * "preHandle"twice.
			 *
			 * @SEE
			 * https://stackoverflow.com/questions/35168092/how-to-get-method-information-at
			 * -interceptor-prehandle-method-in-spring-boot-1-3
			 */
			log.warn("[preHandle]not HandlerMethod, object:{}, schema:{},  reqURI:{}", handler,
					req.getScheme(), req.getRequestURI());
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		KhAdminUserEntity khAdminUserEntity = (KhAdminUserEntity) req.getSession().getAttribute(AbstractBaseController.SESSION_USER_KEY);
		AuthorizeConfig.AuthorizeStrategy authorizeStrategy = KhInterceptorUtil.getAuthorizeType(req, handlerMethod);

		//默认拒绝访问
		boolean accessDenied = true;
		// 必须要有用户授权
		if (AuthorizeConfig.AuthorizeStrategy.FORCE == authorizeStrategy) {
			//强制登录情况下，需要判断是否有用户身份
			if (KhAdminUserEntity.isValid(khAdminUserEntity)) {
				accessDenied = false;
			}
		}else{
			accessDenied = false;
		}

		if(accessDenied){
			//如果拒绝访问，则跳转到失败的处理
			buildFailure(req, res);
			return false;
		}
		return true;
	}

	/**
	 * 构造失败的响应结果
	 *
	 * @param req {@link HttpServletRequest}
	 * @param res {@link HttpServletResponse}
	 */
	private void buildFailure(HttpServletRequest req, HttpServletResponse res){

		try {
			if(RequestTypeInterceptor.isAjaxRequest(req)){
				//ajax
				res.setContentType("text/html;charset=UTF-8");
				res.getWriter().write("authorize error json");
			}else{
				//页面
				res.sendRedirect("/passport/login");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

//	protected void writeJson(HttpServletResponse res, ){
//
//		try {
//			res.setContentType("text/html;charset=UTF-8");
//			res.getWriter().write(JSON.toJSONString(error));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}

//	/**
//	 * 校验cookie，反解出用户token
//	 *
//	 * @param cookie cookie
//	 * @return 用户登录令牌
//	 */
//	protected String getTokenFromCookie(Cookie cookie) {
//		String tokenStr;
//		String cookieValue = null;
//		try {
//			cookieValue = URLDecoder.decode(cookie.getValue(), "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//		}
//		tokenStr = cookieValue;
//		return tokenStr;
//	}

}
