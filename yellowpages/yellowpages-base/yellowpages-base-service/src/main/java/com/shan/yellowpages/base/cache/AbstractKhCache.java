//package com.shan.yellowpages.base.cache;
//
//import com.shan.yellowpages.base.exception.KhCacheNotExistException;
//import com.shan.yellowpages.base.model.cache.struct.KhRedisZsetStruct;
//import org.apache.commons.collections4.CollectionUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.redis.core.DefaultTypedTuple;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ZSetOperations;
//
//import java.util.*;
//
///**
// * cache抽象基类
// * @author bruce
// */
//public abstract class AbstractKhCache {
//
//	private static final Logger logger = LoggerFactory.getLogger(AbstractKhCache.class);
//
//	/** 空数据的占位符，默认为空串 */
//	protected static final String DEFAULT_ZSET_PLACEHOLDER_MEMBER = "";
//	/** 空数据的占位符value，默认为-1 */
//	protected static final int DEFAULT_ZSET_PLACEHOLDER_SCORE = -1;
//
//	/**
//	 * 检查key是否存在
//	 * @param redisTemplate
//	 * @param key
//	 * @throws KhCacheNotExistException
//	 */
//	protected void checkKeyExists(StringRedisTemplate redisTemplate, String key) throws KhCacheNotExistException {
//		if(!redisTemplate.hasKey(key)){
//			throw new KhCacheNotExistException();
//		}
//	}
//
//
//	/**
//	 * 添加到zset(单条记录)
//	 * 会检查member是否存在（存在则不再添加，应用场景：关注操作，已经关注过不能重复关注）
//	 *
//	 * @param ignoreMemberExists 是否忽略member存在的情况
//	 * @param key
//	 * @param member
//	 * @param score
//	 * @return
//	 * @throws KhCacheNotExistException
//	 */
//	protected int add2Zset(StringRedisTemplate redisTemplate, boolean ignoreMemberExists, String key, String member, double score) throws KhCacheNotExistException {
//		logger.debug("[AbstractKhCache][add2Zset]entering, key:{}, member:{}, score:{}", key, member, score);
//
//		checkKeyExists(redisTemplate, key);
//
//		//默认返回成功
//		int result = 1;
//		ZSetOperations<String, String> zop = redisTemplate.opsForZSet();
//		if(ignoreMemberExists){
//			//直接添加（更新）， 忽略member存在的情况
//			zop.add(key, member, score);
//		}else{
//			//member存在，则不继续添加
//			Double previousScore = zop.score(key, member);
//			if(previousScore==null){
//				//不存在，才允许重复添加
//				zop.add(key, member, score);
//
//			}
//		}
//		return result;
//	}
//
//	/**
//	 * 批量添加到zset
//	 * 多条记录，不检查member是否存在的情况，简单粗暴的添加
//	 * @param key
//	 * @param batchData 批数据
//	 * @return
//	 * @throws KhCacheNotExistException
//	 */
//	protected int batchAdd2Zset(StringRedisTemplate redisTemplate, String key, Set<KhRedisZsetStruct> batchData) throws KhCacheNotExistException {
//		logger.debug("[AbstractKhCache][batchAdd2Zset]entering, key:{}, batchData:{}", key, batchData);
//		int result = 0;
//		//校验参数是否合法
//		if(CollectionUtils.isNotEmpty(batchData)){
//			checkKeyExists(redisTemplate, key);
//			ZSetOperations<String, String> zop = redisTemplate.opsForZSet();
//			Set<ZSetOperations.TypedTuple<String>> tupleSet = new HashSet<>();
//			for(KhRedisZsetStruct loopItem: batchData){
//				tupleSet.add(new DefaultTypedTuple(String.valueOf(loopItem.getMember()), loopItem.getScore()));
//			}
//			Long opResult= zop.add(key, tupleSet);
//			result = opResult==null?0:opResult.intValue();
//		}
//		return result;
//	}
//
//
//	/**
//	 * 从zset中批量移除
//	 *
//	 * @param key
//	 * @param members
//	 * @return
//	 * @throws KhCacheNotExistException
//	 */
//	protected int delFromZset(StringRedisTemplate redisTemplate, String key, String... members)
//			throws KhCacheNotExistException {
//		logger.debug("[AbstractKhCache][delFromZset]entering, key:{}, member:{}", key, members);
//
//		int result = 0;
//		checkKeyExists(redisTemplate, key);
//
//		ZSetOperations<String, String> zop = redisTemplate.opsForZSet();
//		Long opResult = zop.remove(key, members);
//
//		result = opResult==null?0:opResult.intValue();
//		return result;
//	}
//
//
//	/**
//	 * 通用逻辑，对zset的size进行计数
//	 *
//	 * 会设置placeholder的value
//	 *
//	 * @param redisTemplate
//	 * @param key
//	 * @return
//	 * @throws KhCacheNotExistException
//	 */
//	protected long countZset(StringRedisTemplate redisTemplate, String key) throws KhCacheNotExistException {
//		logger.debug("[AbstractKhCache][countZset]entering, key:{}", key);
//
//		checkKeyExists(redisTemplate, key);
//
//		ZSetOperations<String, String> zop = redisTemplate.opsForZSet();
//		//避开占位符的value
//		Long size = zop.count(key,  DEFAULT_ZSET_PLACEHOLDER_SCORE+1 , Long.MAX_VALUE);
//		int result = size==null?0:size.intValue();
//		return result;
//	}
//
//
//	/**
//	 * 判断zset中是否存在
//	 *
//	 * 会设置placeholder的value
//	 *
//	 * @param redisTemplate
//	 * @param key
//	 * @return
//	 * @throws KhCacheNotExistException
//	 */
//	protected boolean zsetMemberExists(StringRedisTemplate redisTemplate, String key, String member) throws KhCacheNotExistException {
//		logger.debug("[AbstractKhCache][zsetMemberExists]entering, key:{}, member:{}", key, member);
//
//		checkKeyExists(redisTemplate, key);
//		ZSetOperations<String, String> zop = redisTemplate.opsForZSet();
//
//		Double score = zop.score(key, member);
//		boolean result = score!=null;
//		logger.debug("[AbstractKhCache][zsetMemberExists]result:{}, key:{}, member:{}",result, key, member);
//		return result;
//	}
//
//
//
//
//	/**
//	 * 正序方式获取指定数量的业务数据
//	 * 下标参数 start 和 end 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
//	 * @param key
//	 * @param startIndex
//	 * @param endIndex
//	 * @return
//	 * @throws KhCacheNotExistException
//	 */
//	protected Set<KhRedisZsetStruct> rangeZsetData(StringRedisTemplate redisTemplate, String key, int startIndex, int endIndex)
//			throws KhCacheNotExistException {
//		logger.debug("[AbstractKhCache][rangeZsetData]entering, key:{}, startIndex:{}, endIndex:{}", key,
//				startIndex, endIndex);
//
//		checkKeyExists(redisTemplate, key);
//
//		ZSetOperations<String, String> zop = redisTemplate.opsForZSet();
//		Set<KhRedisZsetStruct> result = new LinkedHashSet<>();
//		Set<ZSetOperations.TypedTuple<String>> tupleSet = zop.rangeWithScores(key, startIndex, endIndex);
//		if(tupleSet!=null && tupleSet.size()>0){
//			for(ZSetOperations.TypedTuple<String> tuple: tupleSet){
//				String member = tuple.getValue();
//				Double score = tuple.getScore();
//				KhRedisZsetStruct struct = new KhRedisZsetStruct(key, member, score);
//				result.add(struct);
//			}
//		}
//		logger.info("[AbstractKhCache][rangeZsetData]result:{}, key:{}, startIndex:{}, endIndex:{}", result, key,
//				startIndex, endIndex);
//		return result;
//	}
//
//	/**
//	 * 瀑布流分页方式加载数据
//	 * @param key
//	 * @param orderAsc 是否正序，目前代码只支持倒序，尚不支持正序
//	 * @param tailId
//	 * @param pageSize
//	 * @return
//	 * @throws KhCacheNotExistException
//	 */
//	protected List<KhRedisZsetStruct> rangeZsetDataWithSocre(StringRedisTemplate redisTemplate, boolean orderAsc, String key, Long tailId, int pageSize) throws KhCacheNotExistException {
//		logger.debug("[AbstractKhCache][rangeZsetData] entering, key:{}, tailId:{}, pageSize:{}", key, tailId, pageSize);
//		List<KhRedisZsetStruct> result =  null;
//
//		checkKeyExists(redisTemplate, key);
//
//		ZSetOperations<String, String> zop = redisTemplate.opsForZSet();
//
//		Set<ZSetOperations.TypedTuple<String>> tupleSet = null;
//		long min = 0L;
//		long max = Long.MAX_VALUE;
//
//		if(orderAsc){
//			//正序
//			min = tailId==null || tailId<=0?0:tailId;
//			tupleSet = zop.rangeByScoreWithScores(key, min, max, 0, pageSize);
//		}else{
//			//倒序
//			max = tailId==null || tailId<=0?Long.MAX_VALUE:tailId;
//			tupleSet = zop.reverseRangeByScoreWithScores(key, min, max, 0, pageSize);
//		}
//		logger.info("[AbstractKhCache][rangeZsetData]tupleSet:{}, key:{}, min:{}, max:{}", tupleSet, key, min, max);
//
//		List<KhRedisZsetStruct> dataList = null;
//		if (CollectionUtils.isNotEmpty(tupleSet)) {
//			dataList = new ArrayList<>();
//			for (ZSetOperations.TypedTuple<String> tuple : tupleSet) {
//				Double score = tuple.getScore();
//				String member = tuple.getValue();
//				KhRedisZsetStruct item = new KhRedisZsetStruct(key, member, score);
//				dataList.add(item);
//			}
//			result = dataList;
//		}
//		logger.info("[AbstractKhCache][rangeZsetData]result:{}, key:{}, tailId:{}, pageSize:{}, key:{}", result, key, tailId, pageSize, key);
//		return result;
//	}
//
//
//
//
//	/**
//	 * 设置zset列表
//	 * 空数据情况下，需要设置占位符，防止击穿
//	 *
//	 * @param redisTemplate
//	 * @param key
//	 * @param cacheDataList
//	 * @return
//	 */
//	protected int setZsetList(StringRedisTemplate redisTemplate, String key, List<KhRedisZsetStruct> cacheDataList) {
//
//		logger.debug("[AbstractKhCache][setZsetList]entering, key:{}, cacheDataList:{}", key,
//				cacheDataList!=null?cacheDataList.size():0);
//
//		int result = 0;
//		ZSetOperations<String, String> zop = redisTemplate.opsForZSet();
//
//		//设置前，先清除指定key下所有的数据
//		redisTemplate.delete(key);
//		if (CollectionUtils.isNotEmpty(cacheDataList)) {
//			Set<ZSetOperations.TypedTuple<String>> tupleSet = new HashSet<ZSetOperations.TypedTuple<String>>();
//			for (KhRedisZsetStruct itemLoop : cacheDataList) {
//				tupleSet.add(new DefaultTypedTuple(String.valueOf(itemLoop.getMember()), (double) itemLoop.getScore()));
//			}
//			Long operateResult = zop.add(key, tupleSet);
//			result = operateResult!= null?operateResult.intValue():0;
//		}else{
//			//空数据集，增加占位符（score为-1）
//			zop.add(key, DEFAULT_ZSET_PLACEHOLDER_MEMBER, DEFAULT_ZSET_PLACEHOLDER_SCORE);
//		}
//		return result;
//	}
//
//
//	/**
//	 * 降zset中数据，转换为kh的业务对象list
//	 * @param key
//	 * @param tupleSet redis中的datatSet
//	 * @return
//	 */
//	protected List<KhRedisZsetStruct> conver2RedisZsetList(String key, Set<ZSetOperations.TypedTuple<String>> tupleSet) {
//		List<KhRedisZsetStruct> dataList = null;
//		if (CollectionUtils.isNotEmpty(tupleSet)) {
//			dataList = new ArrayList<>();
//			for (ZSetOperations.TypedTuple<String> tuple : tupleSet) {
//				Double score = tuple.getScore();
//				String member = tuple.getValue();
//				KhRedisZsetStruct item = new KhRedisZsetStruct(key, member, score);
//				dataList.add(item);
//			}
//		}
//		return dataList;
//	}
//
//
//}
//
//
//
//
//
//
//