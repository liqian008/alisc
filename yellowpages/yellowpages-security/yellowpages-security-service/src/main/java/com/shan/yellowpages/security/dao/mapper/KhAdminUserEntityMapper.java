package com.shan.yellowpages.security.dao.mapper;

import com.shan.yellowpages.base.dao.mapper.IKhBaseMapper;
import com.shan.yellowpages.security.model.KhAdminUserEntity;
import com.shan.yellowpages.security.model.KhAdminUserEntityCriteria;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理后台用户表的DB操作mapper
 *
 * @author xuejw
 * @version 1.0
 * @date 2020-02-21 22:28:09
 */
@Mapper
public interface KhAdminUserEntityMapper extends IKhBaseMapper<KhAdminUserEntity, KhAdminUserEntityCriteria, Integer> {

}