package com.shan.yellowpages.base.utils.encrypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * Md5编码工具
 * 
 * @author bruce
 * @version 1.0
 */
public class Md5Utils {

	private static final Logger logger = LoggerFactory.getLogger(Md5Utils.class);


	/**
	 * md5加密，32位
	 * 
	 * @param str 原始字符串
	 * @return Md5值
	 */
	public static String md5Encode(String str) {
		logger.trace("[Md5Utils][md5Encode]entering, str:{}", str);
		byte[] bytesData = str.getBytes();
		String result = md5Encode(bytesData);
		logger.debug("[Md5Utils][md5Encode]result:{}, str:{}", result, str);
		return result;
	}

	/**
	 * md5加密，32位
	 *
	 * 适用与文件md5等场景下
	 *
	 * @param bytesData 字节数组
	 * @return Md5值
	 */
	public static String md5Encode(byte[] bytesData) {
		StringBuffer buf = new StringBuffer();
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(bytesData);
			byte bytes[] = md5.digest();
			for (int i = 0; i < bytes.length; i++) {
				String s = Integer.toHexString(bytes[i] & 0xff);
				if (s.length() == 1) {
					buf.append("0");
				}
				buf.append(s);
			}

		} catch (Exception ex) {
		}
		return buf.toString();
	}



	/**
	 * md5加密，16位
	 * 
	 * @param plainText 原始字符串
	 * @return Md5值
	 */
	public static String md5Encode16(String plainText) {
		String md5Result = md5Encode(plainText);
		if (md5Result != null) {
			return md5Result.substring(8, 24);
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(md5Encode("abc"));
		System.out.println(md5Encode("123"));
	}

}
