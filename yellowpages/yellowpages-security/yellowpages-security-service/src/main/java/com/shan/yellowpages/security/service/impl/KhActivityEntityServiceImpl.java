package com.shan.yellowpages.security.service.impl;

import com.shan.yellowpages.base.dao.mapper.IKhBaseMapper;
import com.shan.yellowpages.base.model.paging.KhPagingResult;
import com.shan.yellowpages.base.model.struct.KhCriteriaBase;
import com.shan.yellowpages.security.dao.mapper.KhActivityEntityMapper;
import com.shan.yellowpages.security.model.KhActivityEntity;
import com.shan.yellowpages.security.model.KhActivityEntityCriteria;
import com.shan.yellowpages.security.model.struct.ActivityStruct;
import com.shan.yellowpages.security.model.struct.AdminUserStruct;
import com.shan.yellowpages.security.service.IKhActivityEntityService;
import com.shan.yellowpages.security.service.IKhAdminUserEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动表的 service
 *
 * @author xuejw
 * @version 1.0
 * @date 2020-12-19 08:30:36
 */
@Service
public class KhActivityEntityServiceImpl implements IKhActivityEntityService, InitializingBean {

	private static Logger logger = LoggerFactory.getLogger(KhActivityEntityServiceImpl.class);

	@Autowired
	private KhActivityEntityMapper khActivityEntityMapper;
	@Autowired
	private IKhAdminUserEntityService khAdminUserEntityService;

	@Override
	public void afterPropertiesSet() {
		Assert.notNull(khActivityEntityMapper, "khActivityEntityMapper can't be null");
		Assert.notNull(khAdminUserEntityService, "khAdminUserEntityService can't be null");
	}

	@Override
	public IKhBaseMapper<KhActivityEntity, KhActivityEntityCriteria, Integer> getKhBaseMapper() {
		return khActivityEntityMapper;
	}

	@Override
	public KhCriteriaBase getKhCriteriaBase() {
		return new KhActivityEntityCriteria();
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
	public KhPagingResult<ActivityStruct> pagingDtoByCriteria(int pageNo, int pageSize, KhActivityEntityCriteria criteria) {
		logger.debug("[" + this.getClass().getName() + "][pagingDtoByCriteria]entering, pageNo:{},pageSize:{},criteria:{}", pageNo, pageSize, criteria);
		KhPagingResult<KhActivityEntity> pagingResult = pagingByCriteria(pageNo, pageSize, criteria);

		if(KhPagingResult.isValid(pagingResult) &&  KhPagingResult.isNotEmpty(pagingResult)){
			List<ActivityStruct> dtoDataList = new ArrayList<>();

			List<KhActivityEntity> dataList = pagingResult.getDataList();
			for(KhActivityEntity loopActivityEntity: dataList){
				ActivityStruct contactDto = ActivityStruct.convert(loopActivityEntity);
				Integer lastModUid = loopActivityEntity.getLastModUid();
				if(lastModUid!=null){
					AdminUserStruct lastModifyUser = khAdminUserEntityService.loadCachedDtoById(lastModUid);
					if(AdminUserStruct.isValid(lastModifyUser)){
						//最后操作人
						contactDto.setLastModifyUser(lastModifyUser);
					}
				}
				dtoDataList.add(contactDto);
			}
			return new KhPagingResult<ActivityStruct>(pagingResult.getPageNo(),  pagingResult.getPageSize(),  pagingResult.getTotalCount(), dtoDataList);

		}
		return KhPagingResult.EMPTY_PAGING_RESULT;
	}


}