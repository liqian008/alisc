package com.shan.yellowpages.base.model.struct;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * ip查询结果的response
 *
 * api介绍： http://whois.pconline.com.cn/#tabs-1
 *
 * @author bruce
 */
@Data
public class KhIpResultStruct implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ip : 202.96.159.254
	 * pro : 广东省
	 * proCode : 440000
	 * city : 广州市
	 * cityCode : 440100
	 * region :
	 * regionCode : 0
	 * addr : 广东省广州市
	 * regionNames :
	 * err :
	 */

	/** ip */
	private String ip;
	/** 省 */
	private String pro;
	/** 省编码 */
	private String proCode;
	/** 城市 */
	private String city;
	/** 城市编码 */
	private String cityCode;
	/** 地址 */
	private String addr;
	/** 错误标志，目前分析空串表示无错误 */
	private String err;

	/**
	 *
	 * @param obj
	 * @return
	 */
	public static boolean isValid(KhIpResultStruct obj){
		boolean result = false;
		if(obj!=null && StringUtils.isBlank(obj.getErr())){
			result = true;
		}
		return result;
	}

}
