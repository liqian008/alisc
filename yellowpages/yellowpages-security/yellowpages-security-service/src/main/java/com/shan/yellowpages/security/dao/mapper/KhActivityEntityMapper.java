package com.shan.yellowpages.security.dao.mapper;

import com.shan.yellowpages.base.dao.mapper.IKhBaseMapper;
import com.shan.yellowpages.security.model.KhActivityEntity;
import com.shan.yellowpages.security.model.KhActivityEntityCriteria;
import org.apache.ibatis.annotations.Mapper;

/**
 * 活动表的DB操作mapper
 *
 * @author xuejw
 * @version 1.0
 * @date 2021-01-02 10:57:59
 */
@Mapper
public interface KhActivityEntityMapper extends IKhBaseMapper<KhActivityEntity, KhActivityEntityCriteria, Integer> {

}