//package com.shan.yellowpages.base.utils;
//
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.net.URL;
//
//import com.github.stuxuhai.jpinyin.PinyinException;
//import com.github.stuxuhai.jpinyin.PinyinFormat;
//import com.github.stuxuhai.jpinyin.PinyinHelper;
//
///**
// * 汉字转换为拼音工具类
// *
// * @author xuejw
// */
//public class PinyinUtils {
//
//	static {
//		try {
//			URL systemResource = PinyinUtils.class.getResource("mutil_pinyin_hlmy.dict");
//			PinyinHelper.addMutilPinyinDict(systemResource.getPath());
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 测试main方法
//	 *
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// 转为首字母
//		System.out.println(PinyinUtils.toFirstChar("盛世-2"));
//		System.out.println(PinyinUtils.toFirstChar("重聚！"));
//		System.out.println(PinyinUtils.toFirstChar("音乐H5"));
//		System.out.println(PinyinUtils.toFirstChar("《快乐》"));
//		System.out.println(PinyinUtils.toFirstChar("真机抓娃娃-测试"));
//		System.out.println(PinyinUtils.toFirstChar("在线夺宝[h5小游戏]"));
//		System.out.println(PinyinUtils.toFirstChar("黑暗轮回：破坏神之王"));
//		System.out.println(PinyinUtils.toFirstChar("鸣人の羁绊"));
//		System.out.println(PinyinUtils.toFirstChar("进化吧！战车"));
//		System.out.println(PinyinUtils.toPinyin("九州降魔录"));
//	}
//
//	/**
//	 * 中文字符串首字母解析
//	 * @param chinese 中文
//	 * @return 中文字符串首字母
//	 */
//	public static String toFirstChar(String chinese) {
//		String result = "";
//		chinese = chinese.replaceAll("\\pP|\\pS", "");
//		try {
//			result = PinyinHelper.getShortPinyin(chinese);
//		} catch (PinyinException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
//
//	/**
//	 * 中文变拼音
//	 * @param chinese 中文
//	 * @return 拼音
//	 */
//	public static String toPinyin(String chinese) {
//		String result = "";
//		chinese = chinese.replaceAll("\\pP|\\pS", "");
//		try {
//			result = PinyinHelper.convertToPinyinString(chinese, "", PinyinFormat.WITHOUT_TONE);
//		} catch (PinyinException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
//}