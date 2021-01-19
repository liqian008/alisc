package com.shan.yellowpages.security.service;

import com.shan.yellowpages.base.service.IKhBaseService;
import com.shan.yellowpages.security.model.KhCompanyEntity;
import com.shan.yellowpages.security.model.KhCompanyEntityCriteria;

/**
 * 公司主表的 service
 *
 * @author xuejw
 * @version 1.0
 * @date 2020-12-19 08:30:36
 */
public interface IKhCompanyEntityService extends IKhBaseService<KhCompanyEntity, KhCompanyEntityCriteria, Integer> {

	/**
	 * 根据名称家在
	 * @param name
	 * @return
	 */
	KhCompanyEntity loadByName(String name);

//	/**
//	 * 根据名称家在
//	 * @param name
//	 * @return
//	 */
//	KhCompanyEntity loadCacheByName(String name);
}
