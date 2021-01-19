/**
 * $Id: ErrorResponseBuilderUtils.java 44014 2011-08-02 06:04:55Z jun.liu@XIAONEI.OPI.COM $
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.shan.yellowpages.base.utils.network;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 请求处理，工具类 将被包下的取代
 *
 * @author bruce
 * @version 1.0
 */
public final class RequestUtil {

    private static final Logger logger = LoggerFactory.getLogger(RequestUtil.class);

    /**
     * json 请求后缀
     */
    private static final String JSON_SUFFIX = ".json";

    /**
     * jsonp 请求后缀
     */
    private static final String JSONP_SUFFIX = ".jsonp";

    /**
     * 获取 Get 请求的完整 url
     *
     * @param request request 请求
     * @return url
     */
    public static String getFullGetRequestUrl(HttpServletRequest request) {
        logger.trace("[RequestUtil][getRemoteIP]entering");

        if (!isGet(request)) {
            throw new IllegalArgumentException("request method should be [GET]");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(request.getScheme() + "://" + request.getServerName());

        if (StringUtils.isNotBlank(request.getServletPath())) {
            sb.append(request.getServletPath());
        }
        if (StringUtils.isNotBlank(request.getQueryString())) {
            sb.append("?" + request.getQueryString());
        }

//		String result = request.getScheme() + "://" + request.getServerName() // 域名地址，自己的外网请求的域名，如：wx.1758.com
//				+ request.getServletPath() + "?"// 请求页面或其他地址， 如 /play/index
//				+ (request.getQueryString()); // 请求参数 如：a=1&b=2

        String result = sb.toString();

        logger.debug("[RequestUtil] [getRemoteIP] exit, result = {}", result);
        return result;
    }

    /**
     * 获取客户端 ip 地址. 因为使用了各种反向代理, 因此不能简单的通过 getRemoteAddr() 来做判断
     *
     * @param request request 请求
     * @return 远程 IP
     */
    public static String getRemoteIP(HttpServletRequest request) {
        logger.trace("[getRemoteIP] enter");

        String result;

        String header = request.getHeader("x-forwarded-for");

        if (header == null) {
            result = request.getRemoteAddr();
        } else {
            result = header;
        }

        if (StringUtils.contains(result, ",")) {// 如果通过了多级反向代理的话, X-Forwarded-For 的值并不止一个, 而是一串 IP 值
            String ip = result;

            result = StringUtils.substring(result, 0, ip.indexOf(","));// 取 X-Forwarded-For 中第一个非 unknown 的有效 IP 字符串

            logger.warn("[getRemoteIP] multiple ip, ip = {}, result = {}", ip, result);
        }

        logger.debug("[getRemoteIP] exit, result:{}", result);
        return result;
    }

    /**
     * 是否为post请求
     *
     * @param request request请求
     * @return boolean值
     */
    public static boolean isPost(HttpServletRequest request) {
        return StringUtils.equalsIgnoreCase("post", request.getMethod());
    }

    /**
     * 是否为get请求
     *
     * @param request request请求
     * @return boolean值
     */
    public static boolean isGet(HttpServletRequest request) {
        return StringUtils.equalsIgnoreCase("get", request.getMethod());
    }

    /**
     * 是否为json请求
     *
     * @param request request请求
     * @return boolean值
     */
    public static boolean isJsonRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return StringUtils.endsWith(uri, JSON_SUFFIX);
    }

    /**
     * 是否为jsonp请求
     *
     * @param request request请求
     * @return boolean值
     */
    public static boolean isJsonpRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return StringUtils.endsWith(uri, JSONP_SUFFIX);
    }

    /**
     * 判断是否为ajax 请求
     *
     * @param request {@link HttpServletRequest}
     * @return true 为ajax请求
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String xRequestedWith = "x-requested-with";
        String xmlHttpRequest = "XMLHttpRequest";

        // 如果是 ajax 请求响应头会有 x-requested-with
        if (request.getHeader(xRequestedWith) != null && request.getHeader(xRequestedWith).equalsIgnoreCase(xmlHttpRequest)) {
            return true;
        }
        // 非 ajax 请求时, session 失效的处理
        return false;
    }

    /**
     * 获取request中的所有参数
     *
     * 潜在bug，使用checkbox等多选元素时，获取value[]拼装存在问题
     *
     * @param request
     * @return
     */
	public static Map<String, String> getRequstParams(HttpServletRequest request) {
		Map<String, String> params = new HashMap<>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		return params;
	}

}
