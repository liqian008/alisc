package com.shan.yellowpages.base.utils.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

/**
 * 域名工具类
 *
 * @author bruce
 * @version 1.0
 */
public class DomainUtil {

    private static final Logger logger = LoggerFactory.getLogger(DomainUtil.class);

    private static final String GENERIC_DOMAIN_SUFFIX = ".com.cn|.net.cn|.org.cn|.gov.cn|.com|.net|.cn|.org|.xyz|.top|.wang|.cc|.me|.tel|.mobi|.asia|.biz|.info|.name|.tv|.hk|.公司|.中国|.网络";

    /**
     * 给定域名，获取顶级域名，方法2
     * <p>
     * TODO 可考虑另外一个算法：（http://blog.csdn.net/kuluzs/article/details/51981212 ， http://blog.csdn.net/kuluzs/article/details/51986759）
     *
     * @param host 域名
     * @return 顶级域名
     */
    public static String getTopDomain(String host) {
        logger.debug("[DomainUtil] [getTopDomain] enter, host = {}", host);

        String str = host.toLowerCase();// 此处获取值转换为小写
        if (str.indexOf('.') > 0) {
            // String[] strArr = host.split(".");
            // String lastStr = strArr[(strArr.length - 1)].toString();
            boolean isIp = false;
            if (isIp) {// (IsNumeric(lastStr)) //如果最后一位是数字，那么说明是IP地址
//				if (false){// (IsNumeric(lastStr)) //如果最后一位是数字，那么说明是IP地址
                return str.replace(".", ""); // 替换.为纯数字输出
            } else {// 否则为域名
                String[] domainRules = GENERIC_DOMAIN_SUFFIX.split("\\|");
                String findStr;// String.Empty;
                String replaceStr;// String.Empty;
                String returnStr = "";// String.Empty;

                for (int i = 0; i < domainRules.length; i++) {

                    String loopDomain = domainRules[i];

                    if (str.endsWith(loopDomain.toLowerCase())) { // 如果最后有找到匹配项
                        findStr = loopDomain; // www.px915.COM
                        replaceStr = str.substring(0, str.length() - loopDomain.length());
//						replaceStr = str.replace(findStr, ""); // 将匹配项替换为空，便于再次判断
                        if (replaceStr.indexOf('.') > 0) // 存在二级域名或者三级，比如：www.px915
                        {
                            String[] replaceArr = replaceStr.split("\\."); // www.px915
                            returnStr = replaceArr[replaceArr.length - 1].toString() + findStr;
                            return returnStr;
                        } else // px915
                        {
                            returnStr = replaceStr + findStr; // 连接起来输出为：px915.com
                            return returnStr;
                        }
                    } else {
                        returnStr = str;
                    }
                }

                logger.debug("[DomainUtil][getTopDomain]result:{}, host:{}", returnStr, host);
                return returnStr;
            }
        } else {
            logger.debug("[DomainUtil][getTopDomain]result:{}, host:{}", str, host);
            return str;
        }
    }

    /**
     * 获取 IP 与 域名 方法2 使用 java 工具类实现 http://blog.csdn.net/u013217757/article/details/53838250
     *
     * @param url 标准url
     * @return 域名
     */
    public static String getHost(String url) {
        logger.debug("[DomainUtil] [getHost] enter, url = {}", url);

        String result = "";
        URI effectiveURI;
        try {
            effectiveURI = new URI(url);
        } catch (Exception e) {
            effectiveURI = null;
        }

        if (effectiveURI != null) {
            result = effectiveURI.getHost();
        }

        logger.info("[getHost] exit, result = {}", result);
        return result;
    }

}
