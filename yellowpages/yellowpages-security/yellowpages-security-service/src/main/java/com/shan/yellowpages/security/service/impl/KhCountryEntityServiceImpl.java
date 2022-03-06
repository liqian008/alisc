package com.shan.yellowpages.security.service.impl;

import com.shan.yellowpages.base.dao.mapper.IKhBaseMapper;
import com.shan.yellowpages.base.model.struct.KhCriteriaBase;
import com.shan.yellowpages.security.dao.mapper.KhCountryEntityMapper;
import com.shan.yellowpages.security.model.KhCountryEntity;
import com.shan.yellowpages.security.model.KhCountryEntityCriteria;
import com.shan.yellowpages.security.service.IKhCountryEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * 活动表的 service
 *
 * @author xuejw
 * @version 1.0
 * @date 2022-02-25 10:44:01
 */
@Service("khCountryEntityService")
public class KhCountryEntityServiceImpl implements IKhCountryEntityService, InitializingBean {

	private static Logger logger = LoggerFactory.getLogger(KhCountryEntityServiceImpl.class);

	@Resource
	private KhCountryEntityMapper khCountryEntityMapper;

	@Override
	public void afterPropertiesSet() {
		Assert.notNull(khCountryEntityMapper, "khCountryEntityMapper can't be null");
	}

	@Override
	public IKhBaseMapper<KhCountryEntity, KhCountryEntityCriteria, Integer> getKhBaseMapper() {
		return khCountryEntityMapper;
	}

	@Override
	public KhCriteriaBase getKhCriteriaBase() {
		return new KhCountryEntityCriteria();
	}

	@Override public List<KhCountryEntity> getCountryList() {
		KhCountryEntityCriteria criteria = new KhCountryEntityCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause("sort asc, code asc");
		List<KhCountryEntity> result = khCountryEntityMapper.selectByExample(null);
		return result;
	}
}