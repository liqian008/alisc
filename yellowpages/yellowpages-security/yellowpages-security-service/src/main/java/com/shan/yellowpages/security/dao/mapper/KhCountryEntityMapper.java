package com.shan.yellowpages.security.dao.mapper;

import com.shan.yellowpages.base.dao.mapper.IKhBaseMapper;
import com.shan.yellowpages.security.model.KhCountryEntity;
import com.shan.yellowpages.security.model.KhCountryEntityCriteria;
import org.apache.ibatis.annotations.Mapper;

/**
 * 活动表的DB操作mapper
 *
 * @author xuejw
 * @version 1.0
 * @date 2022-02-25 10:44:00
 */
@Mapper
public interface KhCountryEntityMapper extends IKhBaseMapper<KhCountryEntity, KhCountryEntityCriteria, Integer> {

}