package com.shan.yellowpages.base.utils.math;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 该算法要求概率和必须为1,否则计算结果有误<br>
 * 算法参考:http://www.cnblogs.com/zqiguoshang/p/5885455.html<br>
 * 代码参考: https://www.lanhusoft.com/Article/421.html
 * 
 * @author :xuejw
 * @create 2017-11-21 11:05
 * @Modified By:
 *
 */
public class AliasMethod implements Serializable{

	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(AliasMethod.class);

	/** 随机数 */
	private static Random random = new Random();
	/** 别名表 */
	private  int[] alias = {};
	/** 概率修正表 */
	private  double[] probabilities = {};

	/**
	 * 为保证可序列化，必须有无参构造函数，实际是不使用的
	 *
	 */
	public AliasMethod() {
	}

	/**
	 * 构造一个新的Alias方法从离散分布中抽样，并根据概率分布返回结果。
	 * 
	 * 给定一个与结果0,1，...，n-1相对应的概率列表，这个构造函数创建从这个分布中有效抽样的概率和别名表。
	 *
	 * @param probabilities 概率列表 总和为1
	 */
	public AliasMethod(List<Double> probabilities) {
		this(probabilities, random);
	}

	/**
	 * 构造一个新的Alias方法,从离散分布中抽样，并根据概率分布返回结果。
	 *
	 * @param probabilityList 概率列表 总和为1
	 * @param random 随机对象
	 */
	public AliasMethod(List<Double> probabilityList, Random random) {
		// 数据校验
		if (probabilityList == null || random == null) {
			throw new NullPointerException();
		}
		if (probabilityList.size() == 0) {
			throw new IllegalArgumentException("概率list不可为空！");
		}
		// 概率和别名初始化
		probabilities = new double[probabilityList.size()];
		alias = new int[probabilityList.size()];
		//this.random = random;
		//校验概率列表和是否为1
		double sumRate = 0D;
		for (Double prob : probabilityList) {
			sumRate = MathArithUtil.add(sumRate, prob);
		}
		if (sumRate != 1) {
			throw new IllegalArgumentException("概率和不为1！");
		}

		// 计算平均概率
		final double average = MathArithUtil.div(1.0, probabilityList.size());
		// 概率list需创建一个副本，因为之后会修改该list
		probabilityList = new ArrayList<Double>(probabilityList);

		// 创建两个堆栈，存放概率值的下标
		Deque<Integer> small = new ArrayDeque<Integer>();
		Deque<Integer> large = new ArrayDeque<Integer>();

		// 按照概率是否大于平均值，分为两个队列
		for (int i = 0; i < probabilityList.size(); ++i) {
			if (probabilityList.get(i) >= average) {
				large.add(i);
			} else {
				small.add(i);
			}
		}

		// 处理数组，使得每个数组的概率值均为平均值
		while (!small.isEmpty() && !large.isEmpty()) {
			// 得到小概率和大概率的下标
			int less = small.removeLast();
			int more = large.removeLast();
			// 概率列表按照规则每个概率值乘以列表大小
			probabilities[less] = probabilityList.get(less) * probabilityList.size();
			alias[less] = more;

			// 把大概率值补充到小概率表中，减少的值为小概率值补充至平均值的差值,并修正大概率值为减少之后的值，
			probabilityList.set(more, (MathArithUtil
					.sub(MathArithUtil.add(probabilityList.get(more), probabilityList.get(less)), average)));

			// 如果新概率小于平均值，则将其添加到小列表中，不再分割，否则将其添加到大列表中，再次分割，直至分割完成
			if (probabilityList.get(more) >= average) {
				large.add(more);
			} else {
				small.add(more);
			}
		}

		// 执行到这里，意味着剩下的probabilityList都是平均值,此时若队列还有数据，则需要补充其所对应的概率表为1
		while (!small.isEmpty()) {
			probabilities[small.removeLast()] = 1.0;
		}
		while (!large.isEmpty()) {
			probabilities[large.removeLast()] = 1.0;
		}
	}

	/**
	 * 获取随机值
	 * 
	 * @return 概率列表中的第几个概率
	 */
	public int next() {
		logger.trace("[AliasMethod][next] entering");
		// 先生成一个等概率的随机数组下标
		int column = new Random().nextInt(probabilities.length);
		// 然后产生一个(0,1]随机数，判断值是否在概率表中，不再则为其他概率值
		boolean coinToss =  new Random().nextDouble() < probabilities[column];
		int result = coinToss ? column : alias[column];
		logger.debug("[AliasMethod][next]result:{}", result);
		return result;
	}

	public static void main(String[] args) {
		List<Double> probabilityList = new ArrayList<>();
		probabilityList.add(0.005);
		probabilityList.add(0.15);
		probabilityList.add(0.001);
		probabilityList.add(0.294000);
		probabilityList.add(0.550000);
		Map<Integer, Integer> result = new HashMap<>();

		AliasMethod aliasMethod = new AliasMethod(probabilityList);
		for (int i = 0; i < 5000; i++) {
			int a = aliasMethod.next();
			Integer value = result.get(a) == null ? 0 : result.get(a);
			result.put(a, value + 1);
		}
		System.out.println("result = " + result);
	}
}
