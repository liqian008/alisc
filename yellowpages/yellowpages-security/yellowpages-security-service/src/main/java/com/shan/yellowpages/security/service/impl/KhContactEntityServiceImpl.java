package com.shan.yellowpages.security.service.impl;

import com.shan.yellowpages.base.dao.mapper.IKhBaseMapper;
import com.shan.yellowpages.base.model.paging.KhPagingResult;
import com.shan.yellowpages.base.model.struct.KhCriteriaBase;
import com.shan.yellowpages.security.dao.mapper.KhContactEntityMapper;
import com.shan.yellowpages.security.model.KhContactEntity;
import com.shan.yellowpages.security.model.KhContactEntityCriteria;
import com.shan.yellowpages.security.model.struct.AdminUserStruct;
import com.shan.yellowpages.security.model.struct.ContactStruct;
import com.shan.yellowpages.security.service.IKhAdminUserEntityService;
import com.shan.yellowpages.security.service.IKhContactEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 联系人的信息主表的 service
 *
 * @author xuejw
 * @version 1.0
 * @date 2020-12-19 08:30:36
 */
@Service
public class KhContactEntityServiceImpl implements IKhContactEntityService, InitializingBean {

	private static Logger logger = LoggerFactory.getLogger(KhContactEntityServiceImpl.class);

	@Autowired
	private KhContactEntityMapper khContactEntityMapper;
	@Autowired
	private IKhAdminUserEntityService khAdminUserEntityService;

	@Override
	public void afterPropertiesSet() {
		Assert.notNull(khContactEntityMapper, "khContactEntityMapper can't be null");
		Assert.notNull(khAdminUserEntityService, "khAdminUserEntityService can't be null");
	}

	@Override
	public IKhBaseMapper<KhContactEntity, KhContactEntityCriteria, Integer> getKhBaseMapper() {
		return khContactEntityMapper;
	}

	@Override
	public KhCriteriaBase getKhCriteriaBase() {
		return new KhContactEntityCriteria();
	}



	/**
	 * 分页查询
	 *
	 * @param pageNo 当前页数
	 * @param pageSize 分页大小
	 * @param criteria 条件查询辅助类
	 * @return 分页数据对象
	 */
	@Override
	public KhPagingResult<ContactStruct> pagingDtoByCriteria(int pageNo, int pageSize, KhContactEntityCriteria criteria) {
		logger.debug("[" + this.getClass().getName() + "][pagingDtoByCriteria]entering, pageNo:{}, pageSize:{}, criteria:{}", pageNo, pageSize, criteria);


		KhPagingResult<KhContactEntity> pagingResult = pagingByCriteria(pageNo, pageSize, criteria);

		if(KhPagingResult.isValid(pagingResult) &&  KhPagingResult.isNotEmpty(pagingResult)){
			List<ContactStruct> dtoDataList = new ArrayList<>();

			List<KhContactEntity> dataList = pagingResult.getDataList();
			for(KhContactEntity loopContactEntity: dataList){
				ContactStruct contactDto = ContactStruct.convert(loopContactEntity);
				Integer lastModUid = loopContactEntity.getLastModUid();
				if(lastModUid!=null){
					AdminUserStruct lastModifyUser = khAdminUserEntityService.loadCachedDtoById(lastModUid);
					if(AdminUserStruct.isValid(lastModifyUser)){
						//最后操作人
						contactDto.setLastModifyUser(lastModifyUser);
					}
				}
				dtoDataList.add(contactDto);
			}
			return new KhPagingResult<ContactStruct>(pagingResult.getPageNo(),  pagingResult.getPageSize(),  pagingResult.getTotalCount(), dtoDataList);

		}
		return KhPagingResult.EMPTY_PAGING_RESULT;
	}


	/**
	 * 分页查询
	 *
	 * @param pageNo 当前页数
	 * @param pageSize 分页大小
	 * @param activityId
	 * @param contactName
	 * @param criteria 条件查询辅助类
	 * @return 分页数据对象
	 */
	@Override
	public KhPagingResult<ContactStruct> pagingDtoByActivity(int pageNo, int pageSize, int activityId, String contactName, KhContactEntityCriteria criteria) {
		logger.debug("[" + this.getClass().getName() + "][pagingDtoByActivity]entering, pageNo:{},pageSize:{},criteria:{}", pageNo, pageSize, criteria);

//		Integer count = khContactEntityMapper.test();
		Integer count = khContactEntityMapper.countByActivity(activityId, contactName);
		count = count==null?0:count;

		// 确保pageNo合法
		pageNo = pageNo <= 0 ? 1 : pageNo;
		// 确保pageSize合法
		pageSize = verifyPageSize(pageSize);

		int offset = (pageNo - 1) * pageSize;

		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);

		List<KhContactEntity> dataList= khContactEntityMapper.queryByActivity(activityId, contactName, criteria);

		KhPagingResult<KhContactEntity> pagingResult = new KhPagingResult(pageNo, pageSize, count, dataList);

		if(KhPagingResult.isValid(pagingResult) &&  KhPagingResult.isNotEmpty(pagingResult)){
			List<ContactStruct> dtoDataList = new ArrayList<>();
			for(KhContactEntity loopContactEntity: dataList){
				ContactStruct contactDto = ContactStruct.convert(loopContactEntity);
				Integer lastModUid = loopContactEntity.getLastModUid();
				if(lastModUid!=null){
					AdminUserStruct lastModifyUser = khAdminUserEntityService.loadCachedDtoById(lastModUid);
					if(AdminUserStruct.isValid(lastModifyUser)){
						//最后操作人
						contactDto.setLastModifyUser(lastModifyUser);
					}
				}
				dtoDataList.add(contactDto);
			}
			return new KhPagingResult<>(pagingResult.getPageNo(), pagingResult.getPageSize(), pagingResult.getTotalCount(), dtoDataList);

		}
		return KhPagingResult.EMPTY_PAGING_RESULT;
	}
}