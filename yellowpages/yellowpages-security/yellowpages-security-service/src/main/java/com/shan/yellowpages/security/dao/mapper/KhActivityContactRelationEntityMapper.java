package com.shan.yellowpages.security.dao.mapper;

import com.shan.yellowpages.base.dao.mapper.IKhBaseMapper;
import com.shan.yellowpages.security.model.KhActivityContactRelationEntity;
import com.shan.yellowpages.security.model.KhActivityContactRelationEntityCriteria;
import org.apache.ibatis.annotations.Mapper;

/**
 * 活动-联系人关联表的DB操作mapper
 *
 * @author xuejw
 * @version 1.0
 * @date 2021-03-27 09:52:18
 */
@Mapper
public interface KhActivityContactRelationEntityMapper extends
		IKhBaseMapper<KhActivityContactRelationEntity, KhActivityContactRelationEntityCriteria, Integer> {

}