package com.shan.yellowpages.security.service;

import com.shan.yellowpages.base.service.IKhBaseService;
import com.shan.yellowpages.security.model.KhActivityContactRelationEntity;
import com.shan.yellowpages.security.model.KhActivityContactRelationEntityCriteria;

/**
 * 活动表的 service
 *
 * @author xuejw
 * @version 1.0
 * @date 2020-12-19 08:30:36
 */
public interface IKhActivityContactRelationEntityService extends IKhBaseService<KhActivityContactRelationEntity, KhActivityContactRelationEntityCriteria, Integer> {

	int deleteByContactId(int contactId);

	//	/**
//	 * 分页查询dto
//	 *
//	 * @param pageNo 当前页数
//	 * @param pageSize 分页大小
//	 * @param criteria 条件查询辅助类
//	 * @return 分页数据对象
//	 */
//	KhPagingResult<ActivityStruct> pagingDtoByCriteria(int pageNo, int pageSize, KhActivityEntityCriteria criteria) ;

}
