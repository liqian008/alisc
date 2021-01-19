//package com.shan.yellowpages.base.testcase.utils;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//
//import com.shan.yellowpages.base.utils.network.UrlUtil;
//import org.junit.Test;
//import org.springframework.util.Assert;
//
///**
// * 类 @UrlUtil 的测试用例 继承了类 @Test_Base，节省了注解
// *
// * @author bruce
// *
// */
//public class Test_UrlUtil_AddParameter extends Test_Base {
//
//	// @Resource //自动注入,默认按名称
//	// private IBaseDao baseDao;
//
//	/**
//	 * 测试 @UrlUtil#addParameter 方法 无参数，正常结尾的情况
//	 */
//	@Test // 标明是测试方法
//	// @Transactional 标明此方法需使用事务
//	// @Rollback(false) 标明使用完此方法后事务不回滚,true时为回滚
//	public void addParameter_test_1() {
//
//		String originalUrl = "http://wx.1758.com";
//		String key = "a";
//		String value = "1";
//
//		String expectResult = "http://wx.1758.com?a=1";
//
//		String result = UrlUtil.addParameter(originalUrl, key, value);
//
//		Assert.isTrue(expectResult.equals(result), "expectResult [" + expectResult + "] not equlas [" + result + "]");
//	}
//
//	/**
//	 * 测试 @UrlUtil#addParameter 方法 参数为null
//	 */
//	@Test
//	public void addParameter_test_2() {
//
//		String originalUrl = null;
//		String key = "a";
//		String value = "1";
//
//		String expectResult = "";
//
//		String result = UrlUtil.addParameter(originalUrl, key, value);
//
//		Assert.isTrue(expectResult.equals(result), "expectResult [" + expectResult + "] not equlas [" + result + "]");
//	}
//
//	/**
//	 * 测试 @UrlUtil#addParameter 方法 无参数，且以?结尾结尾的情况
//	 */
//	@Test
//	public void addParameter_test_3() {
//
//		String originalUrl = "http://wx.1758.com?";
//		String key = "a";
//		String value = "1";
//
//		String expectResult = "http://wx.1758.com?&a=1";
//
//		String result = UrlUtil.addParameter(originalUrl, key, value);
//
//		Assert.isTrue(expectResult.equals(result), "expectResult [" + expectResult + "] not equlas [" + result + "]");
//	}
//
//	/**
//	 * 测试 @UrlUtil#addParameter 方法 有参数，且正常结尾结尾的情况
//	 */
//	@Test
//	public void addParameter_test_4() {
//
//		String originalUrl = "http://wx.1758.com?x=1";
//		String key = "a";
//		String value = "1";
//
//		String expectResult = "http://wx.1758.com?x=1&a=1";
//
//		String result = UrlUtil.addParameter(originalUrl, key, value);
//
//		Assert.isTrue(expectResult.equals(result), "expectResult [" + expectResult + "] not equlas [" + result + "]");
//	}
//
//	/**
//	 * 测试 @UrlUtil#addParameter 方法 有参数，且以&符结尾的情况
//	 */
//	@Test
//	public void addParameter_test_5() {
//
//		String originalUrl = "http://wx.1758.com?x=1&";
//		String key = "a";
//		String value = "1";
//
//		String expectResult = "http://wx.1758.com?x=1&&a=1";
//
//		String result = UrlUtil.addParameter(originalUrl, key, value);
//
//		Assert.isTrue(expectResult.equals(result), "expectResult [" + expectResult + "] not equlas [" + result + "]");
//	}
//
//	/**
//	 * 测试 @UrlUtil#addParameter 方法 测试重复参数的情况
//	 */
//	@Test
//	public void addParameter_test_6() {
//
//		String originalUrl = "http://wx.1758.com?a=1";
//		String key = "a";
//		String value = "1";
//
//		String expectResult = "http://wx.1758.com?a=1&a=1";
//
//		String result = UrlUtil.addParameter(originalUrl, key, value);
//
//		Assert.isTrue(expectResult.equals(result), "expectResult [" + expectResult + "] not equlas [" + result + "]");
//	}
//
//	/**
//	 * 测试 @UrlUtil#addParameter 方法 测试中文参数的情况
//	 */
//	@Test
//	public void addParameter_test_7() {
//
//		String originalUrl = "http://wx.1758.com?a=1";
//		String key = "a";
//		String value = "测试";
//
//		String valueEncode = "";
//		try {
//			valueEncode = URLEncoder.encode(value, "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		String expectResult = "http://wx.1758.com?a=1&a=" + valueEncode;
//
//		String result = UrlUtil.addParameter(originalUrl, key, value);
//
//		Assert.isTrue(expectResult.equals(result), "expectResult [" + expectResult + "] not equlas [" + result + "]");
//	}
//
//}
