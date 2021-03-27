package com.shan.yellowpages.security.dao.mapper;

import com.shan.yellowpages.base.dao.mapper.IKhBaseMapper;
import com.shan.yellowpages.security.model.KhContactEntity;
import com.shan.yellowpages.security.model.KhContactEntityCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 联系人信息表的DB操作mapper
 *
 * @author xuejw
 * @version 1.0
 * @date 2020-12-30 20:16:19
 */
@Mapper
public interface KhContactEntityMapper extends IKhBaseMapper<KhContactEntity, KhContactEntityCriteria, Integer> {

	/**
	 * 根据指定的条件查询符合条件的数据库记录
	 *
	 * @param activityId
	 * @param contactName 名称
	 * @param example 条件查询辅助类
	 * @return 符合条件的记录集合
	 */
	List<KhContactEntity> queryByActivity(@Param("activityId") int activityId, @Param("contactName") String contactName, @Param("example") KhContactEntityCriteria example);

	/**
	 * 查询个数
	 * @param activityId
	 * @param contactName 名称
	 * @param example 条件查询辅助类
	 * @return 符合条件的记录集合
	 */
	Integer countByActivity(@Param("activityId") int activityId, @Param("contactName") String contactName, @Param("example") KhContactEntityCriteria example);


	/**
	 * 查询个数
	 */
	Integer test();





}