package com.shan.yellowpages.security.service;

import com.shan.yellowpages.base.model.paging.KhPagingResult;
import com.shan.yellowpages.base.service.IKhBaseService;
import com.shan.yellowpages.security.model.KhContactEntity;
import com.shan.yellowpages.security.model.KhContactEntityCriteria;
import com.shan.yellowpages.security.model.struct.ContactStruct;

/**
 * 联系人的信息主表的 service
 *
 * @author xuejw
 * @version 1.0
 * @date 2020-12-19 08:30:36
 */
public interface IKhContactEntityService extends IKhBaseService<KhContactEntity, KhContactEntityCriteria, Integer> {

	/**
	 * 分页查询dto
	 *
	 * @param pageNo 当前页数
	 * @param pageSize 分页大小
	 * @param criteria 条件查询辅助类
	 * @return 分页数据对象
	 */
	KhPagingResult<ContactStruct> pagingDtoByCriteria(int pageNo, int pageSize, KhContactEntityCriteria criteria) ;


	/**
	 * 分页查询dto
	 *
	 * @param pageNo 当前页数
	 * @param pageSize 分页大小
	 * @param activityId
	 * @param criteria 条件查询辅助类
	 * @return 分页数据对象
	 */
	KhPagingResult<ContactStruct> pagingDtoByActivity(int pageNo, int pageSize, int activityId, String contactName,
			KhContactEntityCriteria criteria) ;



}
