package com.shan.yellowpages.base.utils;

import com.shan.yellowpages.base.context.KhContext;

/**
 * KhContext 工具类
 *
 * @author bruce
 */
public class KhContextUtil {

	/**
	 * 用于从 threadLocal 中, 获取 khContext
	 * 
	 * @return KhContext对象
	 */
	public static KhContext getKhContext() {
        return KhContext.getThreadLocalKhContext();
	}

	/**
	 * 用于从 threadLocal 中, 注入 khContext
	 * 
	 * @param khContext 上下文
	 */
	public static void setKhContext(KhContext khContext) {
		KhContext.setThreadLocalKhContext(khContext);
	}
}
