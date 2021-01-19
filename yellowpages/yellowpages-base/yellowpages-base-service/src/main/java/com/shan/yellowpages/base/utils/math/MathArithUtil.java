package com.shan.yellowpages.base.utils.math;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精 确的浮点数运算，包括加减乘除和四舍五入。
 * 
 * @author bruce
 * @version 1.0
 */
public class MathArithUtil {

	private static Logger logger = LoggerFactory.getLogger(MathArithUtil.class);

	/** 默认除法运算精度 10位小数 */
	private static final int DEF_DIV_SCALE = 10;

	/**
	 * 0.0数字格式
	 */
	public static final ThreadLocal<DecimalFormat> df1 = new ThreadLocal<DecimalFormat>() {
		protected DecimalFormat initialValue() {
			return new DecimalFormat("0.0");
		}

	};

	/**
	 * 0.00格式数字
	 */
	public static final ThreadLocal<DecimalFormat> df2 = new ThreadLocal<DecimalFormat>() {
		protected DecimalFormat initialValue() {
			return new DecimalFormat("0.00");
		}
	};

	// 这个类不能实例化
	private MathArithUtil() {
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1 被加数
	 * @param v2 加数
	 * @return 两个参数的和
	 */
	public static BigDecimal add2decimal(double v1, double v2) {
		logger.trace("[MathArithUtil][add2decimal] entering, v1: {},v2:{}", v1, v2);
		BigDecimal b1 = BigDecimal.valueOf(v1);
		BigDecimal b2 = BigDecimal.valueOf(v2);
		BigDecimal result = b1.add(b2);
		logger.trace("[MathArithUtil][add2decimal] result:{}, v1: {},v2:{}", result, v1, v2);
		return result;
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1 被加数
	 * @param v2 加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		logger.trace("[MathArithUtil][add] entering, v1: {},v2:{}", v1, v2);
		return add2decimal(v1, v2).doubleValue();
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1 被减数
	 * @param v2 减数
	 * @return 两个参数的差
	 */
	public static BigDecimal sub2decimal(double v1, double v2) {
		logger.trace("[MathArithUtil][sub2decimal] entering, v1: {},v2:{}", v1, v2);
		BigDecimal b1 = BigDecimal.valueOf(v1);
		BigDecimal b2 = BigDecimal.valueOf(v2);
		BigDecimal result = b1.subtract(b2);
		logger.trace("[MathArithUtil][sub2decimal] result:{}, v1: {},v2:{}", result, v1, v2);
		return result;
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1 被减数
	 * @param v2 减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		logger.trace("[MathArithUtil][sub] entering, v1: {},v2:{}", v1, v2);
		return sub2decimal(v1, v2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1 被乘数
	 * @param v2 乘数
	 * @return 两个参数的积
	 */
	public static BigDecimal mul2decimal(double v1, double v2) {
		logger.trace("[MathArithUtil][mul2decimal] entering, v1: {},v2:{}", v1, v2);
		BigDecimal b1 = BigDecimal.valueOf(v1);
		BigDecimal b2 = BigDecimal.valueOf(v2);
		BigDecimal result = b1.multiply(b2);
		logger.trace("[MathArithUtil][mul2decimal] result:{}, v1: {},v2:{}", result, v1, v2);
		return result;
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1 被乘数
	 * @param v2 乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {
		logger.trace("[MathArithUtil][mul] entering, v1: {},v2:{}", v1, v2);
		return mul2decimal(v1, v2).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1 被除数
	 * @param v2 除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1 被除数
	 * @param v2 除数
	 * @return 两个参数的商
	 */
	public static BigDecimal div2decimal(double v1, double v2) {
		logger.trace("[MathArithUtil][div] entering, v1: {},v2:{}", v1, v2);
		return div2decimal(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1 被除数
	 * @param v2 除数
	 * @param scale 表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		logger.trace("[MathArithUtil][div] entering, v1: {},v2:{},scale:{}", v1, v2, scale);
		return div2decimal(v1, v2, scale).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1 被除数
	 * @param v2 除数
	 * @param scale 表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static BigDecimal div2decimal(double v1, double v2, int scale) {
		logger.trace("[MathArithUtil][div2decimal] entering, v1: {},v2:{},scale:{}", v1, v2, scale);
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = BigDecimal.valueOf(v1);
		BigDecimal b2 = BigDecimal.valueOf(v2);
		BigDecimal result = b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
		logger.trace("[MathArithUtil][div2decimal] result:{}, v1: {},v2:{}", result, v1, v2);
		return result;
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v 需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		logger.trace("[MathArithUtil][round] entering, v: {},scale:{}", v, scale);
		return round2Decimal(v, scale).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v 需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static BigDecimal round2Decimal(double v, int scale) {
		logger.trace("[MathArithUtil][round2Decimal] entering, v: {},scale:{}", v, scale);
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = BigDecimal.valueOf(v);
		BigDecimal one = new BigDecimal("1");
		BigDecimal result = b.divide(one, scale, BigDecimal.ROUND_HALF_UP);
		logger.trace("[MathArithUtil][round2Decimal] result:{},v: {},scale:{}", result, v, scale);
		return result;
	}

	/**
	 * 判断给定的是否是整数
	 * 
	 * @param val
	 * @return boolean
	 */
	public static boolean isInteger(double val) {
		logger.trace("[MathArithUtil][isInteger] entering, val: {}", val);
		boolean result = false;
		if (val % 1 == 0) {
			result = true;
		}
		logger.trace("[MathArithUtil][isInteger] result:{}, val: {}", result, val);
		return result;
	}

	/**
	 * 计算百分率
	 * @param val1 被除数
	 * @param val2 除数
	 * @param minValue
	 * @param maxValue
	 * @return
	 */
	public static double calcPercent(Double val1, Double val2, Double minValue, Double maxValue) {
		return calcPercent(val1, val2, 2, minValue, maxValue);
	}


	/**
	 * 计算百分率
	 * @param val1 被除数
	 * @param val2 除数
	 * @param scale 小数点后位数
	 * @param minValue
	 * @param maxValue
	 * @return
	 */
	public static double calcPercent(Double val1, Double val2, int scale, Double minValue, Double maxValue) {
		logger.trace("[MathArithUtil][toRate] entering, val1: {},val2:{}", val1, val2);

		double result = 0d;
		if (val1 != null && val2 != null && val2 != 0) {
			result = div(val1*100, val2, scale);
		}

		if(minValue!=null && result<minValue){
			result = minValue;
		}

		if(maxValue!=null && result>maxValue){
			result = maxValue;
		}

		return result;
	}




	/**
	 * 展示计算出来的百分率,默认为 -
	 * 
	 * @param val1 被除数
	 * @param val2 除数
	 * @return
	 */
	public static String toRate(Double val1, Double val2) {
		logger.trace("[MathArithUtil][toRate] entering, val1: {},val2:{}", val1, val2);
		return toRate(val1, val2, "-");
	}

	/**
	 * 展示计算出来的百分率,默认为 -
	 * 
	 * @param val1 被除数
	 * @param val2 除数
	 * @param defaultValue 默认值
	 * @return
	 */
	public static String toRate(Double val1, Double val2, String defaultValue) {
		logger.trace("[MathArithUtil][toRate] entering, val1: {},val2:{},defaultValue:{}", val1, val2, defaultValue);

		if (val1 != null && val2 != null && val2 != 0) {
			double rate = 100 * (val1 / val2);
			defaultValue = df2.get().format(rate);
		}
		logger.trace("[MathArithUtil][toRate] result:{}, val1: {},val2:{}", defaultValue, val1, val2);
		return defaultValue;
	}



	/**
	 * 展示计算出来的百分率,默认为 -
	 * 
	 * @param val1 被除数
	 * @param val2 除数
	 * @param defaultValue 默认值
	 * @return
	 */
	public static String toDivResult(Double val1, Double val2, String defaultValue) {
		logger.trace("[MathArithUtil][toDivResult] entering, val1: {},val2:{},defaultValue:{}", val1, val2,
				defaultValue);
		if (val1 != null && val2 != null && val2 != 0) {
			double rate = (val1 / val2);
			defaultValue = df2.get().format(rate);
		}
		logger.trace("[MathArithUtil][toRate] toDivResult:{}, val1: {},val2:{}", defaultValue, val1, val2);
		return defaultValue;
	}

	/**
	 * 正态分布方式，生成数值（包含正负值）
	 * 
	 * @param x 控制数据集中程度（0，+∞），越小越集中
	 *
	 * @return 正态分布结果
	 */
	public static BigDecimal gaussianCalc(double x) {
		return gaussianCalc(x, null, null);
	}

	/**
	 * 正态分布方式，生成数值（金额），默认集中在0轴附近
	 * 
	 * @param x 控制数据集中程度（0，+∞），越小越集中
	 * @param min 最小值
	 * @param max 最大值
	 * @return
	 */
	public static BigDecimal gaussianCalc(double x, BigDecimal min, BigDecimal max) {
		return gaussianCalc(x, 0, min, max);
	}

	/**
	 * 正态分布方式，生成数值
	 * 
	 * @param x 控制数据集中程度（0，+∞），越小越集中
	 * @param u 控制生成的数值集中在哪个数值附近
	 * @param min 最小默认值
	 * @param max 最大默认值
	 * @return
	 */
	public static BigDecimal gaussianCalc(double x, double u, BigDecimal min, BigDecimal max) {
		BigDecimal fee;
		Random random = new Random();
		// 使用正太分布得到随机金额，Math.sqrt(x)表示分布集中程度，
		// 函数曲线下68.268949%的概率在平均值左右的一个x范围内
		// 95.449974%的面积在平均值左右两个标准差2x的范围内
		fee = new BigDecimal(Math.sqrt(x) * random.nextGaussian() + u);

		// TODO 负数的处理 得到的结果有负数

		if (u > 0) {// x轴右侧
			if (fee.doubleValue() < 0) {// 小于0
				fee = new BigDecimal(0 + fee.doubleValue() - u - u);
			}
		} else {// TODO x轴左侧
		}

		fee = fee.setScale(1, BigDecimal.ROUND_HALF_UP);

		if (min != null && fee.compareTo(min) < 0) {
			return min;
		}
		if (max != null && fee.compareTo(max) > 0) {
			return max;
		}
		logger.trace("[MathArithUtil][gaussianCalc]result:{}, x:{}, u:{}, min:{}, max:{}", fee, x, u, min, max);
		return fee;
	}

	// public static void main(String[] args) {
	// for(int i=0;i<100;i++){
	// System.out.println(gaussianCalc(1, 3,new BigDecimal(0.5), new
	// BigDecimal(5)));
	// }
	// }

	// public static void main(String[] args) {
	// System.out.println(0.001+0.005);
	// System.out.println(add(0.005, 0.001));
	//
	// System.out.println(1.0-0.42);
	// System.out.println(sub(1.0, 0.42));
	//
	// System.out.println(4.015*100);
	// System.out.println(mul(4.015, 100));
	//
	//
	// System.out.println(123.3/100);
	// System.out.println(div(123.3, 100));
	//
	//
	//// double a = 0.05;
	//// double b= 0.01;
	//// System.out.println(a+b);
	//
	//
	// }

}