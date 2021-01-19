//package com.shan.yellowpages.base.testcase.utils;
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
//public class Test_UrlUtil_Replace2BasicUrl extends Test_Base {
//
//	/**
//	 * 测试 @UrlUtil#replace2BasicUrl 方法 无参数，正常结尾的情况
//	 */
//	@Test // 标明是测试方法
//	// @Transactional 标明此方法需使用事务
//	// @Rollback(false) 标明使用完此方法后事务不回滚,true时为回滚
//	public void replace2BasicUrl_test_1() {
//
//		String originalUrl = "http://h5.g1758.cn/play/index";
//		String domain = "wx.1758.com";
//		String scheme = null;
//
//		String expectResult = "//wx.1758.com/play/index";
//
//		String result = UrlUtil.replace2BasicUrl(originalUrl, scheme, domain);
//
//		Assert.isTrue(expectResult.equals(result), "expectResult [" + expectResult + "] not equlas [" + result + "]");
//	}
//
//	/**
//	 * 测试 @UrlUtil#replace2BasicUrl 方法 无参数，顶级域名情况
//	 */
//	@Test // 标明是测试方法
//	// @Transactional 标明此方法需使用事务
//	// @Rollback(false) 标明使用完此方法后事务不回滚,true时为回滚
//	public void replace2BasicUrl_test_2() {
//
//		String originalUrl = "http://g1758.cn/play/index";
//		String domain = "wx.1758.com";
//		String scheme = null;
//
//		String expectResult = "//wx.1758.com/play/index";
//
//		String result = UrlUtil.replace2BasicUrl(originalUrl, scheme, domain);
//
//		Assert.isTrue(expectResult.equals(result), "expectResult [" + expectResult + "] not equlas [" + result + "]");
//	}
//
//	/**
//	 * 测试 @UrlUtil#replace2BasicUrl 方法 无参数，改用https的情况
//	 */
//	@Test // 标明是测试方法
//	// @Transactional 标明此方法需使用事务
//	// @Rollback(false) 标明使用完此方法后事务不回滚,true时为回滚
//	public void replace2BasicUrl_test_3() {
//
//		String originalUrl = "http://h5.g1758.cn/play/index";
//		String domain = "wx.1758.com";
//		String scheme = "https";
//
//		String expectResult = "https://wx.1758.com/play/index";
//
//		String result = UrlUtil.replace2BasicUrl(originalUrl, scheme, domain);
//
//		Assert.isTrue(expectResult.equals(result), "expectResult [" + expectResult + "] not equlas [" + result + "]");
//	}
//
//	/**
//	 * 测试 @UrlUtil#replace2BasicUrl 方法 有参数，改用https的情况
//	 */
//	@Test // 标明是测试方法
//	// @Transactional 标明此方法需使用事务
//	// @Rollback(false) 标明使用完此方法后事务不回滚,true时为回滚
//	public void replace2BasicUrl_test_4() {
//
//		String originalUrl = "http://h5.g1758.cn/play/index?a=1";
//		String domain = "wx.1758.com";
//		String scheme = "https";
//
//		String expectResult = "https://wx.1758.com/play/index?a=1";
//
//		String result = UrlUtil.replace2BasicUrl(originalUrl, scheme, domain);
//
//		Assert.isTrue(expectResult.equals(result), "expectResult [" + expectResult + "] not equlas [" + result + "]");
//	}
//
//}
