package com.shan.yellowpages.security.dao.mapper;

import com.shan.yellowpages.base.dao.mapper.IKhBaseMapper;
import com.shan.yellowpages.security.model.KhAdminNavMenuEntity;
import com.shan.yellowpages.security.model.KhAdminNavMenuEntityCriteria;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理后台权限表的DB操作mapper
 *
 * @author xuejw
 * @version 1.0
 * @date 2020-03-09 23:22:07
 */
@Mapper
public interface KhAdminNavMenuEntityMapper extends
		IKhBaseMapper<KhAdminNavMenuEntity, KhAdminNavMenuEntityCriteria, Integer> {

}