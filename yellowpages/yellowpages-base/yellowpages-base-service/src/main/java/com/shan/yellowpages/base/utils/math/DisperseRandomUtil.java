package com.shan.yellowpages.base.utils.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 离散算法工具类
 * <p>
 * 整体思想: <br>
 * 概率比例组成累加和集合,原始数据如:0.1,0.2,0.3,0.4,构造区间为 [0.1, 0.3, 0.6, 1],<br>
 * 然后产生一个随机数加入其中并排序,随机数落在哪个区间,就表示对应的概率事件发生,<br>
 * 如0.25,排序后为[0.1, 0.25, 0.3,0.6,1],表示0.2对应的概率事件发生,<br>
 * 返回0.25的索引, 该索引就是原始概率集合中的索引。
 * </p>
 * 
 * @author xuejw
 * @version 1.0
 */
public class DisperseRandomUtil {

	private static Logger logger = LoggerFactory.getLogger(DisperseRandomUtil.class);

	/**
	 * 离散方式获取随机概率的索引值
	 *
	 * @param probabilityList 概率集合
	 * @return 索引值
	 */
	public static int getProbability(List<Double> probabilityList) {
		logger.trace("[DisperseRandomUtil][getProbability] entering");
		int result = -1;
		if (probabilityList != null && !probabilityList.isEmpty()) {
			// 计算概率总和
			Double sum = 0D;
			for (Double prob : probabilityList) {
				sum += prob;
			}
			if (sum >= 0) {
				// 每一个概率所占比例
				double rate = 0D;
				List<Double> sortRateList = new ArrayList<>();
				for (Double prob : probabilityList) {
					rate += prob;
					// 构建一个比例区段组成的集合(概率和可以不为1)
					sortRateList.add(MathArithUtil.div(rate, sum));
				}
				// 随机生成一个随机数，并排序
				double random = Math.random();
				sortRateList.add(random);
				Collections.sort(sortRateList);
				// 返回该随机数在比例集合中的索引
				result = sortRateList.indexOf(random);
			}
		}
		logger.debug("[DisperseRandomUtil][getProbability] result:{}", result);
		return result;
	}
}