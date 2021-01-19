package com.shan.yellowpages.base.dao.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shan.yellowpages.base.model.struct.KhCriteriaBase;

/**
 * @author xuejw
 */
public interface IKhBaseMapper<T extends Serializable, TCriteria extends KhCriteriaBase, ID extends Serializable> {
	/**
	 * 新写入数据库记录
	 * 
	 * @param record DB记录实体
	 * @return 插入结果：1-成功；0-失败
	 */
	int insert(T record);

	/**
	 * 动态字段,写入数据库记录
	 * 
	 * @param record DB记录实体
	 * @return 插入结果：1-成功；0-失败
	 */
	int insertSelective(T record);

	/**
	 * 强制写入数据库记录，存在就忽略当前新数据
	 * 
	 * @param record DB记录实体
	 * @return 插入结果：1-成功；0-失败
	 */
	int insertIgnore(T record);

	/**
	 * 动态字段强制写入数据库记录，存在就忽略当前新数据
	 * 
	 * @param record DB记录实体
	 * @return 插入结果：1-成功；0-失败
	 */
	int insertIgnoreSelective(T record);

	/**
	 * 强制写入数据库记录，存在就更新该记录,否则直接插入
	 * 
	 * @param record DB记录实体
	 * @return 插入结果：1-成功；0-失败
	 */
	int insertReplace(T record);

	/**
	 * 动态字段，强制写入数据库记录，存在就更新该记录,否则直接插入
	 * 
	 * @param record DB记录实体
	 * @return 插入结果：1-成功；0-失败
	 */
	int insertReplaceSelective(T record);

	/**
	 * 批量插入数据库记录
	 * 
	 * @param records DB记录实体集合
	 * @return 插入结果：>=1-成功；0-失败
	 */
	int insertBatchSelective(List<T> records);

	/**
	 * 根据主键删除数据库的记录
	 *
	 * @param id 主键id
	 * @return 删除结果：1-成功；0-失败
	 */
	int deleteByPrimaryKey(ID id);

	/**
	 * 根据主键逻辑删除数据库的记录
	 * 
	 * @param id id 主键id
	 * @return 1-成功；0-失败
	 */
	int logicalDeleteByPrimaryKey(ID id);

	/**
	 * 根据指定的条件删除数据库符合条件的记录
	 *
	 * @param example 条件查询辅助类
	 * @return 删除结果：1-成功；0-失败
	 */
	int deleteByExample(TCriteria example);

	/**
	 * 根据主键id批量删除
	 *
	 * @param idList 主键集合
	 * @return 删除结果：>=1-成功；0-失败
	 */
	int deleteBatchByPrimaryKey(List<ID> idList);

	/**
	 * 根据主键来更新符合条件的数据库记录
	 *
	 * @param record DB记录实体
	 * @return 更新结果：1-成功；0-失败
	 */
	int updateByPrimaryKey(T record);

	/**
	 * 动态字段,根据主键来更新符合条件的数据库记录
	 *
	 * @param record DB记录实体
	 * @return 更新结果：1-成功；0-失败
	 */
	int updateByPrimaryKeySelective(T record);

	/**
	 * 根据指定的条件来更新符合条件的数据库记录
	 *
	 * @param example 条件查询辅助类
	 * @param record DB实体
	 * @return 更新结果：1-成功；0-失败
	 */
	int updateByExample(@Param("record") T record, @Param("example") TCriteria example);

	/**
	 * 动态根据指定的条件来更新符合条件的数据库记录
	 *
	 * @param record DB记录实体
	 * @param example 条件查询辅助类
	 * @return 更新结果：1-成功；0-失败
	 */
	int updateByExampleSelective(@Param("record") T record, @Param("example") TCriteria example);

	/**
	 * 根据主键批量更新数据记录
	 *
	 * @param records DB记录实体集合
	 * @return 更新结果：>=1-成功；0-失败
	 */
	int updateBatchByPrimaryKeySelective(List<T> records);

	/**
	 * 根据指定的条件获取数据库记录数
	 * 
	 * @param example 条件查询辅助类
	 * @return 符合条件的记录条数
	 */
	int countByExample(TCriteria example);

	/**
	 * 根据指定主键获取一条数据库记录
	 *
	 * @param id 主键id
	 * @return 符合条件的记录
	 */
	T selectByPrimaryKey(ID id);

	/**
	 * 根据指定的条件查询符合条件的数据库记录
	 *
	 * @param example 条件查询辅助类
	 * @return 符合条件的记录集合
	 */
	List<T> selectByExample(TCriteria example);
}
