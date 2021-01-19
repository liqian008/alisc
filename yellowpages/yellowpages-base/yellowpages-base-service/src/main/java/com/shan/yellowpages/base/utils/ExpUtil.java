package com.shan.yellowpages.base.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 经验值工具类
 * 
 * @author bruce
 * @version 1.0
 */
public class ExpUtil {

	private static Logger logger = LoggerFactory.getLogger(ExpUtil.class);

	/**
	 * 根据经验值获取相应的经验等级
	 * 
	 * @param exp 经验值
	 * @return 用户当前经验等级
	 */
	public static int calcExpLevel(long exp) {
		logger.debug("[ExpUtil][calcExpLevel] entering, exp:{}", exp);
		int expLevel = 0;
		if (exp > 0) {
			expLevel = (int) Math.sqrt(((exp * 8) / 100) + 4) - 2;
		}

		expLevel = expLevel > 0 ? expLevel : 0;

		logger.debug("[ExpUtil][calcExpLevel]result:{}, exp;{}", expLevel, exp);
		return expLevel;
	}

	/**
	 * 获取距离下一级的经验值
	 * 
	 * @param exp 经验值
	 * @return 距离下一经验等级的差值
	 */
	public static long calcNeedExp2NextLevel(long exp) {
		logger.debug("[ExpUtil][calcNeedExp2NextLevel] entering, exp:{}", exp);

		int currentExpLevel = calcExpLevel(exp);
		int nextExpLevel = currentExpLevel + 1;

		// 下一级所需经验值
		long xxx = (nextExpLevel * (nextExpLevel + 4)) * 100l; // ((nextExpLevel*nextExpLevel + (4*nextExpLevel))*100);
		double yyy = xxx / 8d;
		long nextLevelExp = (long) Math.ceil(yyy);

		long result = nextLevelExp - exp;
		logger.debug("[ExpUtil][calcNeedExp2NextLevel]result:{}, exp;{}", result, exp);
		return result;
	}

	public static void main(String[] args) {
		// System.out.println(calcExpLevel(8888888888l));
		// System.out.println(calcNeedExp2NextLevel(8888888888l));

		System.out.println(calcNeedExp2NextLevel(8888888888l));
	}

}
