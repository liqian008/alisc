package com.shan.yellowpages.security.service.impl;

import com.shan.yellowpages.base.dao.mapper.IKhBaseMapper;
import com.shan.yellowpages.base.model.struct.KhCriteriaBase;
import com.shan.yellowpages.security.dao.mapper.KhActivityContactRelationEntityMapper;
import com.shan.yellowpages.security.model.KhActivityContactRelationEntity;
import com.shan.yellowpages.security.model.KhActivityContactRelationEntityCriteria;
import com.shan.yellowpages.security.model.KhActivityEntityCriteria;
import com.shan.yellowpages.security.service.IKhActivityContactRelationEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 活动联系人关联表的 service
 *
 * @author xuejw
 * @version 1.0
 * @date 2020-12-19 08:30:36
 */
@Service
public class KhActivityContactRelationEntityServiceImpl implements IKhActivityContactRelationEntityService, InitializingBean {

	private static Logger logger = LoggerFactory.getLogger(KhActivityContactRelationEntityServiceImpl.class);

	@Autowired
	private KhActivityContactRelationEntityMapper khActivityContactRelationEntityMapper;

	@Override
	public void afterPropertiesSet() {
		Assert.notNull(khActivityContactRelationEntityMapper, "khActivityContactRelationEntityMapper can't be null");
	}

	@Override
	public IKhBaseMapper<KhActivityContactRelationEntity, KhActivityContactRelationEntityCriteria, Integer> getKhBaseMapper() {
		return khActivityContactRelationEntityMapper;
	}

	@Override
	public KhCriteriaBase getKhCriteriaBase() {
		return new KhActivityEntityCriteria();
	}




}