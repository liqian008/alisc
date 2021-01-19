package com.shan.yellowpages.base.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * redis key 生成规范工具类
 * 
 * @author :xuejw
 * @version 1.0
 * @date 2019-03-05 23:24
 */
public class RedisUtil {

	private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);


	/**
	 * redis key的分隔符
	 */
	public static String REDIS_SEPERATOR = ":";

	/**
	 * Redis string 数据类型
	 */
	private static String REDIS_DATA_TYPE_STRING = "string";

	/**
	 * Redis hash 数据类型
	 */
	private static String REDIS_DATA_TYPE_HASH = "hash";

	/**
	 * Redis list 数据类型
	 */
	private static String REDIS_DATA_TYPE_LIST = "list";

	/**
	 * Redis set 数据类型
	 */
	private static String REDIS_DATA_TYPE_SET = "set";

	/**
	 * Redis zset 数据类型
	 */
	private static String REDIS_DATA_TYPE_ZSET = "zset";

	/**
	 * Redis全局命名空间
	 */
	private static String REDIS_GLOBAL_NAMESPACE = "kh";



	/**
	 * 构造string格式的key前缀，形如string:kh
	 * 
	 * @return string格式的key前缀
	 */
	public static String buildStringKeyPrefix() {
		logger.trace("[RedisUtil][buildStringKeyPrefix]entering");
		return buildKeyByType(REDIS_DATA_TYPE_STRING);
	}

	/**
	 * 构造set格式的key前缀，形如set:kh
	 *
	 * @return set格式的key前缀
	 */
	public static String buildSetKeyPrefix() {
		logger.trace("[RedisUtil][buildSetKeyPrefix]entering");
		return buildKeyByType(REDIS_DATA_TYPE_SET);
	}

	/**
	 * 构造zset格式的key前缀，形如zset:kh
	 *
	 * @return zset格式的key前缀
	 */
	public static String buildZsetKeyPrefix() {
		logger.trace("[RedisUtil][buildZsetKeyPrefix]entering");
		return buildKeyByType(REDIS_DATA_TYPE_ZSET);
	}

	/**
	 * 构造hash格式的key前缀，形如hash:kh
	 *
	 * @return hash格式的key前缀
	 */
	public static String buildHashKeyPrefix() {
		logger.trace("[RedisUtil][buildHashKeyPrefix]entering");
		return buildKeyByType(REDIS_DATA_TYPE_HASH);
	}

	/**
	 * 构造list格式的key前缀，形如list:kh
	 *
	 * @return list格式的key前缀
	 */
	public static String buildListKeyPrefix() {
		logger.trace("[RedisUtil][buildListKeyPrefix]entering");
		return buildKeyByType(REDIS_DATA_TYPE_LIST);
	}

	/**
	 * 构造规范格式的key前缀，形如xxx:kh
	 *
	 * @return key前缀
	 */
	private static String buildKeyByType(String keyType) {
		logger.trace("[RedisUtil][buildKeyByType]entering,keyType:{}", keyType);
		return keyType + REDIS_SEPERATOR + REDIS_GLOBAL_NAMESPACE;
	}

	/**
	 * key添加业务参数
	 * 
	 * @param key key前缀
	 * @param parameter 业务参数
	 * @return key值
	 */
	public static String addParameter(String key, String parameter) {
		logger.trace("[RedisUtil][addParameter]entering,key:{},parameter:{}", key, parameter);
		if (StringUtils.isBlank(key)) {
			return "";
		}
		key += REDIS_SEPERATOR;
		key += parameter;
		logger.trace("[RedisUtil][addParameter]result:{},parameter:{}", key, parameter);
		return key;
	}

	public static void main(String[] args) {
		String key = RedisUtil.buildHashKeyPrefix();
		key = RedisUtil.addParameter(key, "implicit");
		key = RedisUtil.addParameter(key, String.valueOf(6113637));
		key = RedisUtil.addParameter(key, String.valueOf(1008687));
		System.out.println("key = " + key);
	}
}
