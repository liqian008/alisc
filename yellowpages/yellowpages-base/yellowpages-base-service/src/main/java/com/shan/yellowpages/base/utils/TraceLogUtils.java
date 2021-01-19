package com.shan.yellowpages.base.utils;

import java.util.UUID;

/**
 * @author xuejw
 * @version 1.0
 * @className TraceLogUtils
 * @description TODO
 * @date 2019-05-30 15:28
 */
public class TraceLogUtils {

    /**
     * @return 随机生成 32 位 traceId
     */
    public static String getTraceId() {
        String uuid = UUID.randomUUID().toString();
        //替换-字符
        return uuid.replaceAll("-", "");
    }
}
