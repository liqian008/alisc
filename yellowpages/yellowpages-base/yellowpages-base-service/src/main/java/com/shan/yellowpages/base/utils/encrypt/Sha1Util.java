package com.shan.yellowpages.base.utils.encrypt;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sha1算法工具类
 * 
 * @author brcue
 * @version 1.0
 */
public class Sha1Util {

	private static final Logger logger = LoggerFactory.getLogger(Sha1Util.class);

	/**
	 * 获取安全哈希算法结果
	 * 
	 * @param str 源字符串
	 * @return 加密结果
	 */
	public static String getSha1(String str) {
		logger.debug("[Sha1Util][getSha1]entering, str:{}", str);
		if (str == null || str.length() == 0) {
			return null;
		}

		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes());

			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			String result = new String(buf);
			logger.debug("[Sha1Util][getSha1]result:{}, str:{}", result, str);
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	// public static void main(String[] args) {
	// Set<String> dataSet = new TreeSet<String>();
	// dataSet.add("da35978c973b9e64b332e13465e8b9a6523a408f");
	// dataSet.add("intvopenid-8896");
	// dataSet.add("1");
	// dataSet.add("1462857957749");
	//
	// StringBuilder sb = new StringBuilder();
	// for(String key: dataSet){
	// sb.append(key);
	// }
	// System.out.println(sb.toString());
	// System.out.println(getSha1(sb.toString()));
	// }

}
