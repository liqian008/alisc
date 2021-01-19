package com.shan.yellowpages.base.service;

import com.shan.yellowpages.base.dao.mapper.IKhBaseMapper;
import com.shan.yellowpages.base.exception.IKhErrorCode;
import com.shan.yellowpages.base.exception.KhRuntimeException;
import com.shan.yellowpages.base.model.paging.KhPagingFallloadResult;
import com.shan.yellowpages.base.model.paging.KhPagingResult;
import com.shan.yellowpages.base.model.paging.KhTailFallloadResult;
import com.shan.yellowpages.base.model.struct.KhCriteriaBase;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

/**
 * crud操作等基础的 BaseService
 *
 * @author xuejw
 */
@SuppressWarnings("unused")
public interface IKhBaseService<T extends Serializable, TCriteria extends KhCriteriaBase, ID extends Serializable> {

	Logger logger = LoggerFactory.getLogger(IKhBaseService.class);

	/**
	 * 是否需要脱敏，默认false
	 */
	default boolean needDataMask(){
		return false;
	}

	/**
	 * 处理单条数据
	 * 目前用于脱敏（如：用户密码，手机号等），不向外层暴露
	 */
	default void dataMask(T t){
		return;
	}

	/**
	 * 处理多条数据
	 * 目前用于脱敏（如：用户密码，手机号等），不向外层暴露
	 */
	default void dataMask(List<T> tList){
		return;
	}


	/**
	 * 获取base mapper
	 * 
	 * @return base mapper
	 */
	IKhBaseMapper<T, TCriteria, ID> getKhBaseMapper();

	/**
	 * 获取 KhCriteriaBase，供排序等需要实例化对象时使用
	 * 
	 * @return KhCriteriaBase
	 */
	KhCriteriaBase getKhCriteriaBase();

	/**
	 * 保存数据记录
	 * 
	 * @param entity DB记录实体
	 * @return 保存结果：1-成功；0-失败
	 */
	default int save(T entity) {
		logger.debug("[" + this.getClass().getName() + "][save]entering, entity:{}", entity);
		return getKhBaseMapper().insertSelective(entity);
	}

	/**
	 * 保存并返回实体对象，通常在rpc（对象序列化）后，实体对象的变更无法传递回调用方时使用
	 * 
	 * @param entity DB记录实体
	 * @return 保存后的实体对象
	 */
	default T saveAndReturn(T entity) {
		logger.debug("[" + this.getClass().getName() + "][saveAndReturn]entering, entity:{}", entity);
		T result = null;
		int operateResult = save(entity);
		if (operateResult > 0) {
			result = entity;

			if(needDataMask()){
				//如果需要脱敏
				dataMask(result);
			}

		}
		return result;
	}

	/**
	 * 强制写入数据库记录，存在就忽略当前新数据
	 *
	 * @param entity DB记录实体
	 * @return 插入结果：1-成功；0-失败
	 */
	default int saveIgnore(T entity) {
		logger.debug("[" + this.getClass().getName() + "][saveIgnore]entering, entity:{}", entity);
		return getKhBaseMapper().insertIgnoreSelective(entity);
	}

	/**
	 * 强制写入数据库记录，存在就更新该记录,否则直接插入
	 *
	 * @param entity DB记录实体
	 * @return 插入结果：1-成功；0-失败
	 */
	default int saveReplace(T entity) {
		logger.debug("[" + this.getClass().getName() + "][saveReplace]entering, entity:{}", entity);
		return getKhBaseMapper().insertReplace(entity);
	}

	/**
	 * 批量插入
	 *
	 * @param list DB记录实体集合，注意：要求每一个对象传入参数必须相同，否则无法插入
	 * @return 插入结果：>=1-成功；0-失败
	 */
	default int saveBatch(List<T> list) {
		logger.debug("[" + this.getClass().getName() + "][saveBatch]entering, list:{}", CollectionUtils.size(list));
		return getKhBaseMapper().insertBatchSelective(list);
	}

	/**
	 * 按主键id删除
	 *
	 * @param id 主键id
	 * @return 删除结果：1-成功；0-失败
	 */
	default int deleteById(ID id) {
		logger.debug("[" + this.getClass().getName() + "][deleteById]entering, id:{}", id);
		return getKhBaseMapper().deleteByPrimaryKey(id);
	}

	/**
	 * 按主键id逻辑删除
	 *
	 * @param id 主键id
	 * @return 删除结果：1-成功；0-失败
	 */
	default int logicalDeleteById(ID id) {
		logger.debug("[" + this.getClass().getName() + "][logicalDeleteById]entering, id:{}", id);
		return getKhBaseMapper().logicalDeleteByPrimaryKey(id);
	}

	/**
	 * 按条件删除
	 *
	 * @param criteria 条件查询辅助类
	 * @return 删除结果：1-成功；0-失败
	 */
	default int deleteByCriteria(TCriteria criteria) {
		logger.debug("[" + this.getClass().getName() + "][deleteByCriteria]entering, criteria:{}", criteria);
		return getKhBaseMapper().deleteByExample(criteria);
	}

	/**
	 * 批量根据主键id批量删除
	 * 
	 * @param idList 主键集合
	 * @return 删除结果：>=1-成功；0-失败
	 */
	default int deleteBatchById(List<ID> idList) {
		logger.debug("[" + this.getClass().getName() + "][deleteBatchById]entering, idList:{}",
				CollectionUtils.size(idList));
		return getKhBaseMapper().deleteBatchByPrimaryKey(idList);
	}

	/**
	 * 根据主键更新
	 * 
	 * @param entity DB记录实体
	 * @return 更新结果：1-成功；0-失败
	 */
	default int updateById(T entity) {
		logger.debug("[" + this.getClass().getName() + "][updateById]entering, entity:{}", entity);
		return getKhBaseMapper().updateByPrimaryKeySelective(entity);
	}

	/**
	 * 根据条件更新
	 * 
	 * @param entity DB记录实体
	 * @param criteria 查询条件辅助类
	 * @return 更新结果：1-成功；0-失败
	 */
	default int updateByCriteria(T entity, TCriteria criteria) {
		logger.debug("[" + this.getClass().getName() + "][updateByCriteria]entering, entity:{},criteria:{}", entity,
				criteria);
		return getKhBaseMapper().updateByExampleSelective(entity, criteria);
	}

	/**
	 * 批量根据主键更新
	 * 
	 * @param list DB记录实体集合
	 * @return 更新结果：>=1-成功；0-失败
	 */
	default int updateBatchById(List<T> list) {
		logger.debug("[" + this.getClass().getName() + "][updateBatchById]entering, list:{}",
				CollectionUtils.size(list));
		return getKhBaseMapper().updateBatchByPrimaryKeySelective(list);
	}

	/**
	 * 按条件count
	 * 
	 * @param criteria 条件查询辅助类
	 * @return 符合条件的记录条数
	 */
	default int countByCriteria(TCriteria criteria) {
		logger.debug("[" + this.getClass().getName() + "][countByCriteria]entering, criteria:{}", criteria);
		return getKhBaseMapper().countByExample(criteria);
	}

	/**
	 * 按id加载
	 * 
	 * @param id 主键id
	 * @return 符合条件的记录
	 */
	default T loadById(ID id) {
		logger.debug("[" + this.getClass().getName() + "][loadById]entering, id:{}", id);
		T result = getKhBaseMapper().selectByPrimaryKey(id);

		if(needDataMask()){
			//如果需要脱敏
			dataMask(result);
		}
		return result;
	}

	/**
	 * 按条件查询记录
	 * 
	 * @param criteria 条件查询辅助类
	 * @return 符合条件的记录集合
	 */
	default List<T> queryByCriteria(TCriteria criteria) {
		logger.debug("[" + this.getClass().getName() + "][queryByCriteria]entering, criteria:{}", criteria);
		List<T> result = getKhBaseMapper().selectByExample(criteria);
		if(needDataMask()){
			//如果需要脱敏
			dataMask(result);
		}
		return result;
	}

	/**
	 * 查询所有记录，注意效率问题
	 * 
	 * @return 记录集合
	 */
	default List<T> queryAll() {
		logger.debug("[" + this.getClass().getName() + "][queryAll]entering");
		List<T> result = queryAllAndSort(null);
		if(needDataMask()){
			//如果需要脱敏
			dataMask(result);
		}
		return result;
	}

	/**
	 * 查询所有（支持排序），注意效率问题
	 * 
	 * @param orderByClause 排序条件(注意需要以空格开头)
	 * @return 排序记录集合
	 */
	@SuppressWarnings("unchecked")
	default List<T> queryAllAndSort(String orderByClause) {
		logger.debug("[" + this.getClass().getName() + "][queryAllAndSort]entering, orderByClause:{}", orderByClause);
		KhCriteriaBase khCriteriaBase = getKhCriteriaBase();
		khCriteriaBase.setOrderByClause(orderByClause);
		List<T> result = queryByCriteria((TCriteria) khCriteriaBase);

		if(needDataMask()){
			//如果需要脱敏
			dataMask(result);
		}
		return result;
	}

	/**
	 * 分页查询
	 * 
	 * @param pageNo 当前页数
	 * @param pageSize 分页大小
	 * @param criteria 条件查询辅助类
	 * @return 分页数据对象
	 */
	default KhPagingResult<T> pagingByCriteria(int pageNo, int pageSize, TCriteria criteria) {
		logger.debug("[" + this.getClass().getName() + "][pagingByCriteria]entering, pageNo:{},pageSize:{},criteria:{}",
				pageNo, pageSize, criteria);
		// 确保pageNo合法
		pageNo = pageNo <= 0 ? 1 : pageNo;
		// 确保pageSize合法
		pageSize = verifyPageSize(pageSize);

		int offset = (pageNo - 1) * pageSize;

		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);
		int count = countByCriteria(criteria);
		List<T> dataList = queryByCriteria(criteria);

		if(needDataMask()){
			//如果需要脱敏
			dataMask(dataList);
		}

		return new KhPagingResult<>(pageNo, pageSize, count, dataList);
	}

	/**
	 * 瀑布流查询，TODO 暂未实现
	 *
	 * @param pageNo 当前页数
	 * @param pageSize 当前页数
	 * @param tCriteria 条件查询辅助类
	 * @return 瀑布流分页数据对象
	 */
	default KhPagingFallloadResult<T> fallloadByCriteria(int pageNo, int pageSize, TCriteria tCriteria) {
		throw new KhRuntimeException(IKhErrorCode.SYSTEM_ERROR, "fallloadByCriteria 方法没有被覆写！");
	}

	/**
	 * 瀑布流查询
	 *
	 * @param tailFlag 尾数据标记
	 * @param pageSize 分页大小
	 * @param criteria 条件查询辅助类
	 * @return 瀑布流数据对象
	 */
	default KhTailFallloadResult<T> tailFallloadByCriteria(long tailFlag, int pageSize, TCriteria criteria) {
		logger.debug(
				"[" + this.getClass().getName()
						+ "][tailFallloadByCriteria]entering,tailFlag:{},pageSize:{},criteria:{}",
				tailFlag, pageSize, criteria);
		pageSize = verifyPageSize(pageSize);
		// 额外多取一条，用于判断是否有下一页
		int queryPageSize = pageSize + 1;
		// Criteria设置限制大小，这里默认子类会将tailFlag条件添加到tCriteria中，否则查询结果不正确
		criteria.setLimitRows(queryPageSize);
		List<T> list = queryByCriteria(criteria);

		if(needDataMask()){
			//如果需要脱敏
			dataMask(list);
		}

		boolean hasNext = false;
		if (CollectionUtils.size(list) > pageSize) {
			hasNext = true;
			// 数据有下一页标记，获取最新的尾标，交由业务层控制，一般获取数据id即可
			tailFlag = genNewTailFlag(list.get(pageSize));
			// 移除多取的一条的数据（用于判断是否存在下一页）
			list.remove(pageSize);
		} else {
			// 无下一页，返回实际列表大小，尾标记设置为-1。
			pageSize = list.size();
			tailFlag = -1;
		}
		return new KhTailFallloadResult<>(pageSize, hasNext, tailFlag, list);
	}

	/**
	 * 校验page size
	 * 
	 * @param pageSize 分页大小
	 * @return 校验之后的分页大小
	 */
	default int verifyPageSize(int pageSize) {
		logger.debug("[" + this.getClass().getName() + "][verifyPageSize]entering, pageSize:{}", pageSize);
		// 确保pageSize合法;
		pageSize = pageSize <= 0 || pageSize > 50 ? 50 : pageSize;
		return pageSize;
	}

	/**
	 * 生成新的尾标，供瀑布流查询方法使用
	 * 
	 * @param entity 实体对象
	 * @return 新的尾标，一般取实体的id即可
	 */
	default long genNewTailFlag(T entity) {
		throw new KhRuntimeException(IKhErrorCode.SYSTEM_ERROR, "genNewTailFlag 方法没有被覆写！");
	}

	/**
	 * 缓存方式，根据主键id加载对象，需要每个service单独实现，否则缓存不生效
	 * 
	 * @param id 主键id
	 * @return 缓存中的实体
	 */
	default T loadCacheById(ID id) {
		throw new KhRuntimeException(IKhErrorCode.SYSTEM_ERROR, "loadCacheById 方法没有被覆写！");
	}

	/**
	 * 根据主键id，逐出缓存
	 *
	 * @param id 主键id
	 * @return 执行结果
	 */
	default boolean evictCacheById(ID id) {
		throw new KhRuntimeException(IKhErrorCode.SYSTEM_ERROR, "evictCacheById 方法没有被覆写！");
	}
}
