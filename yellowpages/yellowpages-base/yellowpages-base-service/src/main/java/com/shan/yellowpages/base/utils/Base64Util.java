//package com.shan.yellowpages.base.utils;
//
//import java.io.UnsupportedEncodingException;
//import java.nio.charset.Charset;
//
//import org.apache.commons.codec.binary.Base64;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class Base64Util {
//
//
//	private static final Logger logger = LoggerFactory.getLogger(Base64Util.class);
//
//
//	/**
//	 * 将二进制数据编码为BASE64字符串
//	 *
//	 * @param binaryData 字节数组
//	 * @return 字节数组通过base64以utf-8编码后的字符串
//	 */
//	public static String encode(byte[] binaryData) {
//		try {
//			return new String(Base64.encodeBase64(binaryData), "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			return null;
//		}
//	}
//
//	/**
//	 * 将BASE64字符串恢复为二进制数据
//	 *
//	 * @param base64String utf-8编码的base64字符串
//	 * @return 通过base64以utf-8解码的字节数组
//	 */
//	public static byte[] decode(String base64String) {
//		logger.trace("[Base64Util][decode]entering, base64String:{}", base64String);
//		try {
//			return Base64.decodeBase64(base64String.getBytes("UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			return null;
//		}
//	}
//
//	// 7niu URL安全的Base64编码和解码
//	/**
//	 * 七牛Base64编码字符串
//	 *
//	 * @param data 待编码字符串
//	 * @return 结果字符串
//	 */
//	public static String qiNiuEncodeToString(String data) {
//		String result =  qiNiuEncodeToString(data.getBytes(Charset.forName("UTF-8")));
//		logger.trace("[Base64Util][qiNiuEncodeToString]result:{}, data:{}", result, data);
//		return result;
//	}
//
//	/**
//	 * 七牛Base64编码数据
//	 *
//	 * @param data
//	 *            字节数组
//	 * @return 结果字符串
//	 */
//	public static String qiNiuEncodeToString(byte[] data) {
//		return Base647Niu.encodeToString(data, Base647Niu.URL_SAFE | Base647Niu.NO_WRAP);
//	}
//
//	/**
//	 * 七牛Base64方式解码数据
//	 *
//	 * @param data
//	 *            编码过的字符串
//	 * @return 原始数据
//	 */
//	public static byte[] qiNiuDecode(String data) {
//		return Base647Niu.decode(data, Base647Niu.URL_SAFE | Base647Niu.NO_WRAP);
//	}
//
//}
