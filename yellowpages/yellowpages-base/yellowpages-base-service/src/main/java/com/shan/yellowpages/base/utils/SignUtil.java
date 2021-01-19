package com.shan.yellowpages.base.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于 api 对请求的参数签名
 *
 * @author liqian
 */
public class SignUtil {

    private static final Logger logger = LoggerFactory.getLogger(SignUtil.class);

    private final static String salt = "KUDE12S";

    public static String sign(String secretKey, Map<String, String> paramMap) {
        logger.debug("[sign] enter, secretKey = {}, paramMap = {}", secretKey, paramMap);

        String content = SignUtil.getSignData(paramMap, secretKey);
        logger.debug("[sign] start1, content = {}", content);

        String md5Sign = DigestUtils.md5Hex(content);

        logger.info("[sign] exit, md5Sign = {}", md5Sign);
        return md5Sign;
    }

    /**
     * 计算签名
     *
     * @param params    需要签名的kv对
     * @param secretKey 私钥
     * @return 公私钥拼接的字符串
     */
    public static String getSignData(Map<String, String> params, String secretKey) {
        StringBuilder content = new StringBuilder(getOriginalPlainSignText(params));

        if (StringUtils.isNotBlank(secretKey)) {
            content.append(secretKey);
        }

        return content.toString();
    }

    /**
     * 计算最原始的 text
     *
     * @param params 公钥kv对
     * @return kv对拼接成的字符串 &连接多个kv
     */
    public static String getOriginalPlainSignText(Map<String, String> params) {
        StringBuilder content = new StringBuilder();
        if (params != null) {
            // 按照 key 做排序
            List<String> keys = new ArrayList<>(params.keySet());
            Collections.sort(keys);
            for (int i = 0; i < keys.size(); i++) {
                String key = (String) keys.get(i);
                if ("sign".equals(key) || "sign_type".equals(key)) {
                    continue;
                }
                String value = (String) params.get(key);
                if (value != null) {
                    content.append(i == 0 ? "" : "&").append(key).append("=").append(value);
                } else {
                    content.append(i == 0 ? "" : "&").append(key).append("=");
                }
            }
        }
        return content.toString();
    }

    /**
     * 验证 签名
     *
     * @param params 公钥kv 对
     * @param sig    签名
     * @param secret 私钥
     * @return 公私钥加密和sig对比是否相等
     */
    public static boolean verify(Map<String, String> params, String sig, String secret) {
        String content = getContent(params, secret);

        String md5Sign = DigestUtils.md5Hex(content);
        return StringUtils.equalsIgnoreCase(md5Sign, sig);
    }

    /**
     * 验证 签名
     *
     * @param params 公钥kv 对
     * @param sig    签名
     * @return 公私钥加密和sig对比是否相等
     */
    public static boolean verify(Map<String, String> params, String sig) {
        return verify(params, sig, salt);
    }

    /**
     * 计算最原始的 text
     *
     * @param params 公钥kv对
     * @param secret 私钥
     * @return kv对及私钥拼接成的字符串 &连接多个kv
     */
    public static String getContent(Map<String, String> params, String secret) {
        StringBuilder content = new StringBuilder();

        // 按照key做排序
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);

        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);

            if ("sign".equals(key) || "sign_type".equals(key)) {
                continue;
            }
            String value = (String) params.get(key);
            if (value != null) {
                content.append(i == 0 ? "" : "&").append(key).append("=").append(value);
            } else {
                content.append(i == 0 ? "" : "&").append(key).append("=");
            }
        }

        return content.toString() + secret;
    }

//	public static String getContent(Map<String, String> params,String secret) {
//		StringBuilder content = new StringBuilder();
//
//		// 按照key做排序
//		List<String> keys = new ArrayList<>(params.keySet());
//		Collections.sort(keys);
//
//		for (int i = 0; i < keys.size(); i++) {
//			String key = (String) keys.get(i);
//			if ("sign".equals(key) || "sign_type".equals(key)) {
//				continue;
//			}
//			String value = (String) params.get(key);
//			if (value != null) {
//				content.append((i == 0 ? "" : "&") + key + "=" + value);
//			} else {
//				content.append((i == 0 ? "" : "&") + key + "=");
//			}
//
//		}
//
//		return content.toString()+secret;
//	}

}