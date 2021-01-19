package com.shan.yellowpages.security.dao.mapper;

import com.shan.yellowpages.base.dao.mapper.IKhBaseMapper;
import com.shan.yellowpages.security.model.KhContactEntity;
import com.shan.yellowpages.security.model.KhContactEntityCriteria;
import org.apache.ibatis.annotations.Mapper;

/**
 * 联系人信息表的DB操作mapper
 *
 * @author xuejw
 * @version 1.0
 * @date 2020-12-30 20:16:19
 */
@Mapper
public interface KhContactEntityMapper extends IKhBaseMapper<KhContactEntity, KhContactEntityCriteria, Integer> {

}