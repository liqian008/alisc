package com.shan.yellowpages.security.service.impl;

import com.shan.yellowpages.base.dao.mapper.IKhBaseMapper;
import com.shan.yellowpages.base.model.struct.KhCriteriaBase;
import com.shan.yellowpages.security.dao.mapper.KhCompanyEntityMapper;
import com.shan.yellowpages.security.model.KhCompanyEntity;
import com.shan.yellowpages.security.model.KhCompanyEntityCriteria;
import com.shan.yellowpages.security.service.IKhCompanyEntityService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 公司主表的 service
 *
 * @author xuejw
 * @version 1.0
 * @date 2020-12-19 08:30:36
 */
@Service
public class KhCompanyEntityServiceImpl implements IKhCompanyEntityService, InitializingBean {

	private static Logger logger = LoggerFactory.getLogger(KhCompanyEntityServiceImpl.class);

	@Autowired
	private KhCompanyEntityMapper khCompanyEntityMapper;

	@Override
	public void afterPropertiesSet() {
		Assert.notNull(khCompanyEntityMapper, "khCompanyEntityMapper can't be null");
	}

	@Override
	public IKhBaseMapper<KhCompanyEntity, KhCompanyEntityCriteria, Integer> getKhBaseMapper() {
		return khCompanyEntityMapper;
	}

	@Override
	public KhCriteriaBase getKhCriteriaBase() {
		return new KhCompanyEntityCriteria();
	}

	@Override public KhCompanyEntity loadByName(String name) {

		KhCompanyEntityCriteria criteria = new KhCompanyEntityCriteria();
		criteria.createCriteria().andNameEqualTo(StringUtils.trim(name));
		criteria.setOrderByClause(" id asc");
		criteria.setLimitRows(1);

		List<KhCompanyEntity> dataList = queryByCriteria(criteria);

		KhCompanyEntity result = null;
		if(CollectionUtils.isNotEmpty(dataList)){
			result = dataList.get(0);
		}
		return result;
	}

//	private Map<String, KhCompanyEntity > companyMap = new HashMap<>();
//	@Override public KhCompanyEntity loadCacheByName(String name) {
//
//		KhCompanyEntity cache = companyMap.get(name);
//		if(KhCompanyEntity.isValid(cache)){
//			cache = loadByName(name);
////			companyMap.put(name, cache);
//		}
//		return cache;
//	}
}