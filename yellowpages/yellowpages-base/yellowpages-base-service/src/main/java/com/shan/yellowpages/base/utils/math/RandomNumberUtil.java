/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.shan.yellowpages.base.utils.math;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机数的一些应用
 * 
 * @author liqian
 * @version 1.0
 * 
 */
public class RandomNumberUtil {

	/** 默认salt */
	public static final String RANDOM_SALT = "khaan";

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(RandomNumberUtil.class);


	/**
	 * 获得0-9的随机数(数字)
	 *
	 * @return 0-9的随机数
	 */
	public static String getRandomDigital() {
		return String.valueOf(getRandomNo(10));
	}

	/**
	 * 获得随机字符a-z
	 * 
	 * @return 随机字符a-z
	 */
	public static char getRandomChar() {
		int ch = 97 + getRandomNo(26);
		return (char) ch;
	}

	/**
	 * 生成随机数串(只包含数字)
	 *
	 * @param num 数字个数
	 * @return 随机数字字符串
	 */
	public static String generateRandomCodeNum(int num) {
		StringBuilder code = new StringBuilder();
		for (int i = 0; i < num; i++) {
			code.append(getRandomDigital());
		}
		return code.toString();
	}

	/**
	 * 生成随机字母串
	 * 
	 * @param num 随机串长度
	 * @return 随机串
	 */
	public static String generateRandomCodeChar(int num) {
		StringBuilder code = new StringBuilder();
		for (int i = 0; i < num; i++) {
			code.append(getRandomChar());
		}
		return code.toString();
	}

	/**
	 * 生成指定长度的随机字符串（包含数字和字符）
	 * 
	 * @param length 随即串长度
	 * @return 随机串
	 */
	public static String getRandomString(int length) {
		StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyz");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();

		for (int i = 0; i < length; i++) {
			sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
	}

	/**
	 * 高效获取指定范围的随机数，左闭右开
	 *
	 * @param min 最小值
	 * @param max 最大值
	 * @return 随机数
	 */
	public static int getRandomNo(int min, int max) {
		int result = ThreadLocalRandom.current().nextInt(max - min + 1) + min;
		logger.trace("[RandomNumberUtil][getRandomNo]result:{}, min:{}, max:{}", result, min, max);
		return result;
	}

	/**
	 * 高效获取随机数 (0~bound]，左闭右开
	 *
	 * @param bound 范围
	 * @return 随机数
	 */
	public static int getRandomNo(int bound) {
		int result = ThreadLocalRandom.current().nextInt(bound);
		logger.trace("[RandomNumberUtil][getRandomNo]result:{}, bound:{}", result, bound);
		return result;
	}

	/**
	 *
	 * 根据总数分割个数及限定区间进行数据随机处理 数列浮动阀值为0.85
	 *
	 * @link https://blog.csdn.net/donghaiming111/article/details/86676919
	 * @param total - 被分割的总数
	 * @param splitNum - 分割的个数
	 * @param min - 单个数字下限
	 * @param max - 单个数字上限
	 * @return - 返回符合要求的数字列表
	 */
	public static List<Integer> genRandomList(int total, int splitNum, int min, int max) {
		return genRandomList(total, splitNum, min, max, 0.5f);
	}

	/**
	 * 根据总数分割个数及限定区间进行数据随机处理，思想为线段切割法
	 *
	 * @param total - 被分割的总数
	 * @param splitNum - 分割的个数
	 * @param min - 单个数字下限
	 * @param max - 单个数字上限
	 * @param thresh - 数列浮动阀值，即每个值偏移平均线的范围[0.0, 1.0]
	 * @return 随机队列
	 */
	public static List<Integer> genRandomList(int total, int splitNum, int min, int max, float thresh) {
		assert total >= splitNum * min && total <= splitNum * max;
		assert thresh >= 0.0f && thresh <= 1.0f;
		// 平均分配
		int average = total / splitNum;
		List<Integer> list = new ArrayList<>(splitNum);
		int rest = total - average * splitNum;
		for (int i = 0; i < splitNum; i++) {
			if (i < rest) {
				list.add(average + 1);
			} else {
				list.add(average);
			}
		}
		// 如果浮动阀值为0则不进行数据随机处理
		if (thresh == 0) {
			return list;
		}
		// 根据阀值进行数据随机处理
		for (int i = 0; i < splitNum - 1; i++) {
			int nextIndex = i + 1;
			// 当前元素值
			int itemThis = list.get(i);
			// 下一个元素值
			int itemNext = list.get(nextIndex);
			// 当前元素是否小于下一个元素
			boolean isLt = itemThis < itemNext;
			// 获取下限
			int rangeLower = isLt ? max - itemThis : itemThis - min;
			// 获取上限
			int rangeUpper = isLt ? itemNext - min : max - itemNext;
			// 根据浮动系数获得最终浮动范围，因为变化的值不能超过上下限
			int rangeFinal = (int) Math.ceil(thresh * (Math.min(rangeLower, rangeUpper) + 1));
			// 在上下限中获取随机值
			int randOfRange = getRandomNo(rangeFinal);

			// int randomAddCoefficient = isLt ? 1 : -1;
			// 随机修改相邻的两个元素值，使用随机值进行增减
			int randomAddCoefficient = ThreadLocalRandom.current().nextBoolean() ? 1 : -1;
			list.set(i, list.get(i) + randomAddCoefficient * randOfRange);
			list.set(nextIndex, list.get(nextIndex) + (-randomAddCoefficient) * randOfRange);
		}
		return list;
	}

	/**
	 * 模拟微信抢红包算法
	 * <p>
	 * 二倍均值法,剩余红包金额为M，剩余人数为N，那么有如下公式：
	 * </p>
	 * <p>
	 * 每次抢到的金额 = 随机区间 （0， M / N X 2）
	 * </p>
	 * <p>
	 * 保证了每次随机金额的平均值是相等的，
	 * </p>
	 * <p>
	 * 缺点是不是真正的随机，除了最后一个人，每个人获得的金额小于人均金额的2倍
	 * </p>
	 *
	 * @link https://blog.csdn.net/qq_35830949/article/details/80708333
	 * @param totalAmount 总金额
	 * @param totalPeopleNum 总人数
	 * @return 微信红包分配方案
	 */
	public static List<Integer> divideRedPackage(Integer totalAmount, Integer totalPeopleNum) {
		List<Integer> amountList = new ArrayList<>();
		Integer restAmount = totalAmount;
		Integer restPeopleNum = totalPeopleNum;
		Random random = new Random();
		for (int i = 0; i < totalPeopleNum - 1; i++) {
			// 随机范围：[1，剩余人均金额的两倍)，左闭右开
			int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
			restAmount -= amount;
			restPeopleNum--;
			amountList.add(amount);
		}
		amountList.add(restAmount);
		return amountList;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			String a = generateRandomCodeChar(4);
			System.out.println("a = " + a);
		}
	}

}
