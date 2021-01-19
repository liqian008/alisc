package com.shan.yellowpages.base.utils.network;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlUtil {

    private static final Logger logger = LoggerFactory.getLogger(UrlUtil.class);

    /**
     * join url
     * @param origURL 源url
     * @param appendUrl  拼接url
     * @return 处理后的url
     */
    public static String joinUrl(String origURL, String appendUrl) {

        String result = null;
        if (origURL != null && origURL.length() > 0) {
            if (appendUrl != null && appendUrl.length() > 0) {
                if (origURL.indexOf("?") == -1) {
                    // 不含问号
                    result = origURL + "?" + appendUrl;
                } else {
                    // 含问号
                    if (origURL.endsWith("?")) {
                        result = origURL + appendUrl;
                    } else {
                        result = origURL + "&" + appendUrl;
                    }
                }
            } else {
                result = origURL;
            }
        } else {
            result = "";
        }
        return result;
    }

    /**
     * 拼接转义url
     * @param origURL 源url
     * @param appendUrl 拼接url
     * @return 处理后url
     */
    public static String joinUrlForHyperLink(String origURL, String appendUrl) {

        String result = null;
        if (origURL != null && origURL.length() > 0) {
            if (appendUrl != null && appendUrl.length() > 0) {
                if (origURL.indexOf("?") == -1) {
                    result = origURL + "?" + appendUrl;
                } else {
                    result = origURL + "&amp;" + appendUrl;
                }
            } else {
                result = origURL;
            }
        } else {
            result = "";
        }
        return result;
    }

    // /**
    // * join anchor
    // *
    // * @param origURL
    // * @param appendUrl
    // * @return
    // */
    // public static String joinAnchor(String origURL, String anchor) {
    // if (origURL != null && origURL.length() > 0) {
    // if (origURL.indexOf("#") == -1) {
    // return origURL+"#"+anchor;
    // } else {
    // return origURL;
    // }
    // } else {
    // return "";
    // }
    // }

    /**
     * 判断refer中是否含有问号
     * @param url 链接
     * @return 是否包含问号
     */
    public static boolean isContainQuestionMark(String url) {
        // if (!StringUtils.isEmpty(url)) {
        // return url.contains("?");
        // }
        if (url != null && url.length() > 0) {
            return url.contains("?");
        }
        return false;
    }

    // public static void main(String[] args) {
    // String gameUrl =
    // "http://h5.g1758.cn/play/login/startGameV4?appKey=55d4fbe9fae219129e05f39c6f733fde";
    // System.out.println(removeParameter(gameUrl, "appKey"));
    // }

    /**
     * 将请求参数转换为字符串
     * @param request http请求
     * @return 请求参数按utf-8编码encode后拼接的字符串
     */
    public static String getParamters(HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        Enumeration<String> emu = (Enumeration<String>) request.getParameterNames();
        StringBuffer sb = new StringBuffer();
        while (emu.hasMoreElements()) {
            String str = emu.nextElement();
            sb.append(str);
            sb.append("=");
            String temp = request.getParameter(str);
            try {
                temp = URLEncoder.encode(temp, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.error("[UrlUtil][getParamters]error:{}", e); //$NON-NLS-1$
            }
            sb.append(temp);
            if (emu.hasMoreElements()) {
                sb.append("&");
            }
        }
        return sb.toString();
    }

    /**
     * 给URL添加参数，结果为urlencode过的
     *
     * @param url 链接
     * @param key 键
     * @param value 值
     * @return 添加了kv后的url
     */
    public static String addParameter(String url, String key, String value) {
        String param = getUrlParamString(key, value);
        return joinUrl(url, param);
    }

    /**
     * 给URL添加唯一参数(先删再加)，结果为urlencode过的
     *
     * @param url 链接
     * @param key 键
     * @param value 值
     * @return 添加了kv后的url 并保证当前key只存在一个
     */
    public static String addUniqueParameter(String url, String key, String value) {
        // 先删除，再添加
        url = removeParameter(url, key);
        url = addParameter(url, key, value);
        return url;
    }

    /**
     * kv拼接成字符串 并且v是按utf-8编码后的字符串
     * @param key 键
     * @param value 值
     * @return kv拼接成的字符串
     */
    public static String getUrlParamString(String key, String value) {
        StringBuffer sb = new StringBuffer();

        if (key != null && key.length() > 0) {
            sb.append(key);
            sb.append("=");
            if (value != null) {
                try {
                    value = URLEncoder.encode(value, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    logger.error("[UrlUtil][getUrlParamString]error:{}, key:{}, value:{}", e, key, value); //$NON-NLS-1$
                }
                sb.append(value);
            }
        }
        return sb.toString();
    }

    /**
     * 将url中的某些参数去掉
     * @param url 链接
     * @param param 参数
     * @return 去除链接中param为key的kv后的字符串
     */
    public static String removeParameter(String url, String param) {
        if (url != null) {
            String regx = "(&" + param + "=[\\%0-9A-Za-z-_]*)|(" + param + "=[\\%0-9A-Za-z-_]*&)|(" + param
                    + "=[\\%0-9A-Za-z-_]*)";
            Pattern p = Pattern.compile(regx);
            Matcher m = p.matcher(url);
            String result = m.replaceAll("");
            return result;
        }
        return url;
    }

    /**
     * 从request中，获取referer
     * @param request http请求
     * @return 请求中的referer
     */
    public static String getRefererUrl(HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        if (StringUtils.isBlank(referer)) {
            referer = "";
        }
//        if (referer == null || "".equals(referer)) {
//            return "";
//        }
        return referer;
    }

    /**
     * 从request中，获取原始的url（仅能支持get方法的请求）
     *
     * @param request http请求
     * @return get请求的原始url
     */
    public static String getRequestUrl(HttpServletRequest request) {
        logger.trace("[RequestUtil][getRequestUrl]entering");
        String srcUrl = request.getRequestURL().toString();
        String queryString = request.getQueryString();
        if (StringUtils.isNotBlank(queryString)) {
            srcUrl = joinUrl(srcUrl, queryString);
        }
        logger.debug("[RequestUtil][getRequestUrl]result:{}", srcUrl);
        return srcUrl;
    }

    public static String getFullUrl(String path) {
        // return ConstFront.CONTEXT_PATH + path;
        return "";
    }

    /**
     * 替换为wx1758域名的url 逻辑越来越重，TODO 重构
     *
     * @param url 链接
     * @param scheme 是否指定scheme
     * @param domain 域名
     * @return 替换域名及协议后的url
     */
    public static String replace2BasicUrl(String url, String scheme, String domain) {
        logger.trace("[RequestUtil][replace2BasicUrl]entering, url:{}, scheme:{}, domain:{}", url, scheme, domain);

        String result = null;

        if (url == null) {
            result = url;
        } else {
            if (domain == null) {
//                // 默认使用1758的域名下
//                domain = "wx.1758.com";
                url = url.replaceFirst("(http:|https:)?//(([a-zA-Z0-9_-])+(\\.)?)*(:\\d+)?/", "");
            }else {
                url = url.replaceFirst("(http:|https:)?\\/\\/(([a-zA-Z0-9_-])+(\\.)?)*(:\\d+)?", "//" + domain);
            }

            if (scheme == null) {// 不指定scheme，默认忽略
                result = url;
            } else {// 指定了scheme
                result = scheme + ":" + url;
            }
        }
        logger.debug("[RequestUtil][replace2BasicUrl]result:{}, url:{}, scheme:{}, domain:{}", result, url, scheme,
                domain);
        return result;
    }

    /**
     * 替换为 wx1758 域名的 url
     * @param url 链接
     * @return 替换为 wx1758 域名的url
     */
    public static String replace2BasicUrl(String url) {
        return replace2BasicUrl(url, null, null);
    }

    /**
     * 将 url参数类型String 转为 map
     *
     * @param param aa=11&bb=22&cc=33
     * @return map
     */
    public static Map<String, String> getUrlParams(String param) {
        Map<String, String> map = new HashMap<>();
        if (StringUtils.isBlank(param)) {
            return map;
        }

        if(StringUtils.startsWith(param, "?")){
            param = StringUtils.substring(param, 1);
        }

        String[] params = param.split("&");
        for (int i = 0; i < params.length; i++) {
            String[] p = params[i].split("=");
            if (p.length == 2) {
                map.put(p[0], p[1]);
            }
        }
        return map;
    }

    /**
     * 将 map 转换成 url
     * @param map kv对
     * @return 将map转换为k=v&格式的字符串
     */
    public static String getUrlParamsByMap(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = StringUtils.substringBeforeLast(s, "&");
        }
        return s;
    }

    /**
     * 获取请求中的所有参数(认为参数值只有一个,用于获取所有请求参数，若参数没有值，则给"")
     * @param request http请求
     * @return http请求中包含的参数组成的kv对
     */
    public static Map<String, String> getRequstParams(HttpServletRequest request) {
        // 获取所有requst方法和参数
        Map<String, String> params = new HashMap<String, String>();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String paramValue = request.getParameter(paramName);
            if (StringUtils.isBlank(paramValue)) {
                paramValue = "";
            }
            params.put(paramName, paramValue);
        }
        return params;
    }

    /**
     * 替换域名和端口号
     * @param domain 域名
     * @param url 链接
     * @param port 端口号
     * @return 替换域名和端口号后的链接
     */
    public static String replaceDomainAndPort(String domain, String port, String url) {
        String url_bak;

        if (url.indexOf("//") != -1 && url.startsWith("http")) {
            String[] splitTemp = url.split("//");
            url_bak = splitTemp[0] + "//";
            if (StringUtils.isNotBlank(port)) {
                url_bak = url_bak + domain + ":" + port;
            } else {
                url_bak = url_bak + domain;
            }

            if (splitTemp.length >= 1 && splitTemp[1].indexOf("/") != -1) {
                String[] urlTemp2 = splitTemp[1].split("/");
                if (urlTemp2.length > 1) {
                    for (int i = 1; i < urlTemp2.length; i++) {
                        url_bak = url_bak + "/" + urlTemp2[i];
                    }
                }
                System.out.println("url_bak:" + url_bak);
            } else {
                System.out.println("url_bak:" + url_bak);
            }
        } else {
            return url;
        }
        return url_bak;
    }

    /**
     * 替换域名
     * @param originDomain 源域名
     * @param scheme 协议
     * @param originLink 源链接
     * @return 替换了域名和协议后的链接
     */
    public static String replaceDomain(String originDomain, String originLink, String scheme) {
        if (originLink.startsWith("http://") || originLink.startsWith("https://")) {
            originLink = UrlUtil.replaceDomainAndPort(originDomain, "", originLink);
        } else {
            if (originLink.startsWith("/")) {
                originLink = scheme + "://" + originDomain + originLink;
            } else {
                originLink = scheme + "://" + originDomain + "/" + originLink;
            }
        }
        return originLink;
    }

    /**
     * 去除 url 中的协议头
     * @param url 链接
     * @return 去除了协议头的链接
     */
    public static String removeProtocolHeader(String url) {
        if (StringUtils.isNotBlank(url)) {
            url = url.replaceFirst("(http:|https:)?\\/\\/", "//");
        }
        return url;
    }

//    public static void main(String[] args) {
//        String a = "http://w.g1758.cn/play/game/quickstart/7ec86a1ccdae55746a9d517a1ce825f2";
//        a = removeProtocolHeader(a);
//        System.out.println("a = " + a);
//    }

    public static void main(String[] args) {
        System.out.println( getUrlParams("?a=1&b=2"));
    }
}
