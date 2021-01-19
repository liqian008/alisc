package com.shan.yellowpages.base.utils.network;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.server.Session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie工具类（纯工具类）
 * 
 * @author bruce
 * @version 1.0
 */
public class CookieUtils {

	private static final Logger logger = LoggerFactory.getLogger(CookieUtils.class);

	/**
	 * 根据名字获取cookie
	 * 
	 * @param request request请求
	 * @param cookieName cookie 的 name
	 * @return 有值返回 {@link Cookie}, 没有则返回 null
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String cookieName) {
		logger.trace("[CookieUtils] [getCookieByName] enter, cookieName = {}", cookieName);

		Cookie result = null;

		Cookie[] cookies = request.getCookies();

		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if (cookie != null && cookie.getName().equals(cookieName)) {
					result = cookie;

					break;
				}
			}
		}

		logger.debug("[CookieUtils] [getCookieByName] exit, result = {}", result);
		return result;
	}

	/**
	 * 添加cookie
	 * 
	 * @param response response响应
	 * @param cookieName cookie名称
	 * @param cookieValue cookie值，需要做encode
	 * @param domain 域名
	 * @param maxAge 默认最大数值，单位秒
	 * @return boolean值，是否添加成功
	 */
	public static boolean addCookie(HttpServletResponse response, String cookieName, String cookieValue, String domain,
			Integer maxAge) {
		logger.trace("[CookieUtils] [addCookie] enter, cookieName = {}, cookieValue = {}, domain = {}, maxAge = {}",
				cookieName, cookieValue, domain, maxAge);

		boolean result = false;

		String path = "/";// 处理 path，默认使用 /
		try {
			Cookie cookie = new Cookie(cookieName, cookieValue);

			if (StringUtils.isNotBlank(domain)) {
				cookie.setDomain(domain);// 处理 domain
			}

			cookie.setPath(path);

			if (maxAge == null) {
				maxAge = Integer.MAX_VALUE;
			}
			cookie.setMaxAge(maxAge);// 处理maxAge

			response.addCookie(cookie);// 写 cookie

			result = true;
		} catch (Exception e) {
			logger.error("[CookieUtils] [addCookie] Exception", e);
		}

		logger.debug("[CookieUtils] [addCookie] exit, result = {}, path: = {}", result, path);
		return result;
	}

	/**
	 * 删除cookie
	 * 
	 * @param response response响应
	 * @param cookieName cookie名称
	 * @param domain 域名
	 * @return boolean值，是否删除成功
	 */
	public static boolean deleteCookie(HttpServletResponse response, String cookieName, String domain) {
		logger.trace("[CookieUtils] [deleteCookie] enter, cookieName = {}, domain = {}", cookieName, domain);

		boolean result = false;
		try {
			Cookie cookie = new Cookie(cookieName, null);

			if (StringUtils.isNotBlank(domain)) {
				cookie.setDomain(domain);// 处理domain
			}

			cookie.setPath("/");// 处理path，默认使用 /

			cookie.setMaxAge(0);// 设置生命周期为0

			response.addCookie(cookie);// 写cookie（其实是清空）

			result = true;
		} catch (Exception e) {
			logger.error("[CookieUtils] [addCookie] Exception = {}", e);
		}

		logger.debug("[CookieUtils] [deleteCookie] exit, result = {}", result);
		return result;
	}

}
