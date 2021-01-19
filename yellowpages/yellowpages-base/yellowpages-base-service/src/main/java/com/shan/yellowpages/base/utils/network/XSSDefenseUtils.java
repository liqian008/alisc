package com.shan.yellowpages.base.utils.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * XSS 防御，对特殊字符进行处理的工具类
 *
 * @author maxl
 * @date 2016年10月18日 上午10:45:49
 * @version 1.0
 */
public class XSSDefenseUtils {

    private static final Logger logger = LoggerFactory.getLogger(XSSDefenseUtils.class);

    private static final String EMPTY_STRING = "";

    /***
     * 将 String 中的特殊符号 去除 < \> \? ; : & " ‘
     *
     * @param str 原始字符串
     * @return String
     */
    public static String htmlEncode(String str) {
        logger.debug("[XSSDefenseUtils] [htmlEncode] enter, str = {}", str);

        if (str == null || str.equals("")) {
            return str;
        }

        StringBuilder encodeStrBuilder = new StringBuilder();

        for (int i = 0, len = str.length(); i < len; i++) {
            String htmlEncode = htmlEncode(str.charAt(i));

            encodeStrBuilder.append(htmlEncode);
        }

        String result = encodeStrBuilder.toString();

        logger.info("[XSSDefenseUtils] [htmlEncode] exit, result = {}", result);
        return result;
    }

    /***
     * 对特殊符号进行判断处理 单引号进行特殊处理
     *
     * @param c 原始字符
     * @return String
     */
    private static String htmlEncode(char c) {

        switch (c) {
            case '&':
                return EMPTY_STRING;
            case '<':
                return EMPTY_STRING;
            case '>':
                return EMPTY_STRING;
            case '"':
                return EMPTY_STRING;
            case ' ':
                return EMPTY_STRING;
            case ';':
                return EMPTY_STRING;
            case '?':
                return EMPTY_STRING;
            case ':':
                return EMPTY_STRING;
            case ',':
                return EMPTY_STRING;
            case '\'':// 单引号
                return EMPTY_STRING;
            default:
                return String.valueOf(c);
        }

    }

}
