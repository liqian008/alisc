package com.shan.yellowpages.security.dao.mapper;

import com.shan.yellowpages.base.dao.mapper.IKhBaseMapper;
import com.shan.yellowpages.security.model.KhCompanyEntity;
import com.shan.yellowpages.security.model.KhCompanyEntityCriteria;
import org.apache.ibatis.annotations.Mapper;

/**
 * 公司信息表的DB操作mapper
 *
 * @author xuejw
 * @version 1.0
 * @date 2020-12-30 20:16:20
 */
@Mapper
public interface KhCompanyEntityMapper extends IKhBaseMapper<KhCompanyEntity, KhCompanyEntityCriteria, Integer> {

}