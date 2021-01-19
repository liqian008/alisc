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
//public class Test_UrlUtil_RemoveParameter extends Test_Base {
//
//	// @Resource //自动注入,默认按名称
//	// private IBaseDao baseDao;
//
//	/**
//	 * 测试 @UrlUtil#removeParameter 方法 无参数，正常结尾的情况
//	 */
//	@Test // 标明是测试方法
//	// @Transactional 标明此方法需使用事务
//	// @Rollback(false) 标明使用完此方法后事务不回滚,true时为回滚
//	public void removeParameter_test_1() {
//
//		String originalUrl = "http://wx.1758.com";
//		String key = "a";
//
//		String expectResult = "http://wx.1758.com";
//
//		String result = UrlUtil.removeParameter(originalUrl, key);
//
//		Assert.isTrue(expectResult.equals(result), "expectResult [" + expectResult + "] not equlas [" + result + "]");
//	}
//
//	/**
//	 * 测试 @UrlUtil#removeParameter 方法 无参数，且以?结尾结尾的情况
//	 */
//	@Test
//	public void removeParameter_test_2() {
//
//		String originalUrl = "http://wx.1758.com?";
//		String key = "a";
//
//		String expectResult = "http://wx.1758.com?";
//
//		String result = UrlUtil.removeParameter(originalUrl, key);
//
//		Assert.isTrue(expectResult.equals(result), "expectResult [" + expectResult + "] not equlas [" + result + "]");
//	}
//
//	/**
//	 * 测试 @UrlUtil#removeParameter 方法 有参数，且正常结尾结尾的情况
//	 */
//	@Test
//	public void removeParameter_test_3() {
//
//		String originalUrl = "http://wx.1758.com?a=1";
//		String key = "a";
//
//		String expectResult = "http://wx.1758.com?";
//
//		String result = UrlUtil.removeParameter(originalUrl, key);
//
//		Assert.isTrue(expectResult.equals(result), "expectResult [" + expectResult + "] not equlas [" + result + "]");
//	}
//
//	/**
//	 * 测试 @UrlUtil#removeParameter 方法 有参数，且以&符结尾的情况
//	 */
//	@Test
//	public void removeParameter_test_4() {
//
//		String originalUrl = "http://wx.1758.com?a=1&";
//		String key = "a";
//
//		String expectResult = "http://wx.1758.com?";
//
//		String result = UrlUtil.removeParameter(originalUrl, key);
//
//		Assert.isTrue(expectResult.equals(result), "expectResult [" + expectResult + "] not equlas [" + result + "]");
//	}
//
//	/**
//	 * 测试 @UrlUtil#removeParameter 方法 测试重复多参数的情况，参数在前
//	 */
//	@Test
//	public void removeParameter_test_5() {
//
//		String originalUrl = "http://wx.1758.com?a=1&x=1";
//		String key = "a";
//
//		String expectResult = "http://wx.1758.com?x=1";
//
//		String result = UrlUtil.removeParameter(originalUrl, key);
//
//		Assert.isTrue(expectResult.equals(result), "expectResult [" + expectResult + "] not equlas [" + result + "]");
//	}
//
//	/**
//	 * 测试 @UrlUtil#removeParameter 方法 测试重复多参数的情况，参数在后
//	 */
//	@Test
//	public void removeParameter_test_6() {
//
//		String originalUrl = "http://wx.1758.com?x=1&a=1";
//		String key = "a";
//
//		String expectResult = "http://wx.1758.com?x=1";
//
//		String result = UrlUtil.removeParameter(originalUrl, key);
//
//		Assert.isTrue(expectResult.equals(result), "expectResult [" + expectResult + "] not equlas [" + result + "]");
//	}
//
//	/**
//	 * 测试 @UrlUtil#removeParameter 方法 测试重复多参数的情况，参数在中
//	 */
//	@Test
//	public void removeParameter_test_7() {
//
//		String originalUrl = "http://wx.1758.com?x=1&a=1&y=1";
//		String key = "a";
//
//		String expectResult = "http://wx.1758.com?x=1&y=1";
//
//		String result = UrlUtil.removeParameter(originalUrl, key);
//
//		Assert.isTrue(expectResult.equals(result), "expectResult [" + expectResult + "] not equlas [" + result + "]");
//	}
//
//}
