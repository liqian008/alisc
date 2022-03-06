package com.shan.yellowpages.security.service;

import com.shan.yellowpages.base.service.IKhBaseService;
import com.shan.yellowpages.security.model.KhCountryEntity;
import com.shan.yellowpages.security.model.KhCountryEntityCriteria;

import java.util.List;

/**
 * 活动表的 service
 *
 * @author xuejw
 * @version 1.0
 * @date 2022-02-25 10:44:01
 */
public interface IKhCountryEntityService extends IKhBaseService<KhCountryEntity, KhCountryEntityCriteria, Integer> {

	/**
	 * 获取国家列表
	 * @return
	 */
	List<KhCountryEntity> getCountryList();

}
