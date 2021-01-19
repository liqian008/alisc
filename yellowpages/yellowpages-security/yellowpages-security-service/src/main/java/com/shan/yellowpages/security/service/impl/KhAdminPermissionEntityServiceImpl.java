//package com.shan.yellowpages.security.service.impl;
//
//import com.shan.yellowpages.base.dao.mapper.IKhBaseMapper;
//import com.shan.yellowpages.base.exception.IKhErrorCode;
//import com.shan.yellowpages.base.exception.KhRuntimeException;
//import com.shan.yellowpages.base.model.struct.KhCriteriaBase;
//import com.shan.yellowpages.security.dao.mapper.KhAdminPermissionEntityMapper;
//import com.shan.yellowpages.security.model.KhAdminPermissionEntity;
//import com.shan.yellowpages.security.model.KhAdminPermissionEntityCriteria;
//import com.shan.yellowpages.security.model.struct.PermissionStruct;
//import com.shan.yellowpages.security.service.IKhAdminPermissionEntityService;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.Assert;
//
//import java.util.List;
//
///**
// * 管理后台权限表的 service
// *
// * @author xuejw
// * @version 1.0
// * @date 2019-12-06 10:22:47
// */
//@Service("khAdminPermissionEntityService")
//public class KhAdminPermissionEntityServiceImpl implements IKhAdminPermissionEntityService, InitializingBean {
//
//	private static Logger logger = LoggerFactory.getLogger(KhAdminPermissionEntityServiceImpl.class);
//
//	@Autowired
//	private KhAdminPermissionEntityMapper khAdminPermissionEntityMapper;
////	@Autowired
////	private IKhAdminRolePermissionEntityService khAdminRolePermissionEntityService;
////	@Autowired
////	private IKhAdminCategoryEntityService khAdminCategoryEntityService;
//
//
//	@Override
//	public void afterPropertiesSet() {
//		Assert.notNull(khAdminPermissionEntityMapper, "khAdminPermissionEntityMapper can't be null");
////		Assert.notNull(khAdminRolePermissionEntityService, "khAdminRolePermissionEntityService can't be null");
////		Assert.notNull(khAdminCategoryEntityService, "khAdminCategoryEntityService can't be null");
//	}
//
//	@Override
//	public IKhBaseMapper<KhAdminPermissionEntity, KhAdminPermissionEntityCriteria, Integer> getKhBaseMapper() {
//		return khAdminPermissionEntityMapper;
//	}
//
//	@Override
//	public KhCriteriaBase getKhCriteriaBase() {
//		return new KhAdminPermissionEntityCriteria();
//	}
//
//
//	/**
//	 * 指定权限code是否存在
//	 * @return
//	 */
//	@Override public boolean codeExists(String permissionCode) {
//
//		KhAdminPermissionEntityCriteria criteria = new KhAdminPermissionEntityCriteria();
//		KhAdminPermissionEntityCriteria.Criteria subCriteria = criteria.createCriteria();
//		subCriteria.andPermissionCodeEqualTo(permissionCode);
//
//		List<KhAdminPermissionEntity> dataList = queryByCriteria(criteria);
//
//		boolean result = dataList!=null && dataList.size()>0;
//		return result;
//	}
//
//
//
//	/**
//	 * 保存并返回实体对象 通常在rpc（对象序列化）后，实体对象的变更无法传递回调用方时使用
//	 *
//	 * 务必需保留此方法，子业务目前有根据此方法名做切面
//	 *
//	 *
//	 * @param entity DB记录实体
//	 * @return
//	 */
//	@Override
//	public int save(KhAdminPermissionEntity entity) {
//		if(entity==null){
//			throw new KhRuntimeException(IKhErrorCode.SYSTEM_PARAM_ERROR);
//		}
//		//对数据做必要的整理，处理属性等
//		populateEntity(entity);
//
//		int result = getKhBaseMapper().insertSelective(entity);
//		return result;
//	}
//
//	/**
//	 * 保存并返回实体对象 通常在rpc（对象序列化）后，实体对象的变更无法传递回调用方时使用
//	 *
//	 * 务必需保留此方法，子业务目前有根据此方法名做切面
//	 *
//	 * @param entity DB记录实体
//	 * @return
//	 */
//	@Override
//	public KhAdminPermissionEntity saveAndReturn(KhAdminPermissionEntity entity) {
//		save(entity);
//		KhAdminPermissionEntity result = entity;
//		return result;
//	}
//
//
//	/**
//	 * 根据主键更新
//	 *
//	 * @param entity DB记录实体
//	 * @return 更新结果：1-成功；0-失败
//	 */
//	@Override
//	public int updateById(KhAdminPermissionEntity entity) {
//		logger.debug("[" + this.getClass().getName() + "][updateById]entering, entity:{}", entity);
//
//		//对数据做必要的整理，处理属性等
//		populateEntity(entity);
//
//		int result = getKhBaseMapper().updateByPrimaryKeySelective(entity);
//		return result;
//	}
//
//
//
//
//
//
//
//
////	/**
////	 * 查询指定用户拥有的权限列表
////	 * @param adminUserId
////	 * @param type 权限类型
////	 * @param status
////	 * @return
////	 */
////	@Override public List<KhAdminPermissionEntity> queryPermissionsByAdminUserId(int adminUserId, Short type,
////			Short status) {
////
////		logger.debug("[KhAdminPermissionEntityServiceImpl][queryPermissionsByAdminUserId]entering, adminUserId:{}, type:{}, status:{}", adminUserId, type, status);
////
////		List<KhAdminPermissionEntity> result = khAdminPermissionEntityMapper.queryPermissionsByAdminUserId(adminUserId, type, status);
//////		List<KhAdminPermissionEntity> result = null;
//////		if(adminUserId==1){
//////			//TODO 如何判断超级管理员
//////			KhAdminPermissionEntityCriteria criteria = new KhAdminPermissionEntityCriteria();
//////			criteria.createCriteria().andStatusEqualTo(status);
//////			result = queryByCriteria(criteria);
//////		}else{
//////			//非超级管理员
//////			result = khAdminPermissionEntityMapper.queryPermissionsByAdminUserId(adminUserId, type, status);
//////		}
////		return result;
////	}
//
////	@Override
////	public List<KhAdminPermissionEntity> queryPermissions(Short accessType, Short status) {
////		logger.debug("[queryPermissions] entering, accessType:{}, status:{}", accessType, status);
////		KhAdminPermissionEntityCriteria criteria =  new KhAdminPermissionEntityCriteria();
////		KhAdminPermissionEntityCriteria.Criteria subCriteria = criteria.createCriteria();
////
////		if (accessType != null) {
////			subCriteria.andAccessTypeEqualTo(accessType);
////		}
////		if (status != null) {
////			subCriteria.andStatusEqualTo(status);
////		}
////		List<KhAdminPermissionEntity> adminPermissionList = queryByCriteria(criteria);
////		logger.info("[queryPermissions] result:{}, status:{}", CollectionUtils.size(adminPermissionList),
////				status);
////		return adminPermissionList;
////	}
//
////	@Override
////	public List<KhAdminPermissionEntity> queryPermissionsByRoleId(Integer roleId, Short status) {
////
////		KhAdminRolePermissionEntityCriteria criteria = new KhAdminRolePermissionEntityCriteria();
////		criteria.createCriteria().andRoleIdEqualTo(roleId);
////		List<KhAdminRolePermissionEntity> rolePermissionsList = khAdminRolePermissionEntityService.queryByCriteria(criteria);
////		if (rolePermissionsList != null && rolePermissionsList.size() > 0) {
////			// 此处未使用联合查询，而是使用了两次查询（考虑是后台系统，所以忽视效率问题）
////			List<Integer> resourceIdList = new ArrayList<>();
////			for (KhAdminRolePermissionEntity rolePermission : rolePermissionsList) {
////				resourceIdList.add(rolePermission.getPermissionId());
////			}
////			if (resourceIdList.size() > 0) {
////				KhAdminPermissionEntityCriteria resourceCriteria = new KhAdminPermissionEntityCriteria();
////				KhAdminPermissionEntityCriteria.Criteria coreCriteria = resourceCriteria.createCriteria()
////						.andIdIn(resourceIdList);
////
////				if (status != null) {
////					coreCriteria.andStatusEqualTo(status);
////				}
////				return queryByCriteria(resourceCriteria);
////			}
////		}
////		return null;
////	}
////
////	/**
////	 * 获取组装好的权限分类数据
////	 * @return
////	 */
////	@Override
////	public List<CategoryStruct<KhSecurityRelationStruct>> getCategoriedPemissions(Short accessType, Short status){
////
////		List<CategoryStruct<KhSecurityRelationStruct>> result = new ArrayList<CategoryStruct<KhSecurityRelationStruct>>();
////
////		//查询所有的权限
////		List<KhAdminPermissionEntity> permissionEntityList = queryPermissions(null, null);
////		//查询所有的分组
////		KhAdminCategoryEntityCriteria criteria=  new KhAdminCategoryEntityCriteria();
////		KhAdminCategoryEntityCriteria.Criteria subCriteria =  criteria.createCriteria();
////		if(status!=null){
////			subCriteria.andStatusEqualTo(status);
////		}
////		List<KhAdminCategoryEntity> categoryList = khAdminCategoryEntityService.queryByCriteria(criteria);
////		if(CollectionUtils.isNotEmpty(categoryList)){
////			for(KhAdminCategoryEntity loopItem: categoryList){
////				CategoryStruct categoryStruct = new CategoryStruct();
////				int categoryId = loopItem.getId();
////				categoryStruct.setName(loopItem.getName());
////				categoryStruct.setCategoryId(categoryId);
////
////				List<KhSecurityRelationStruct> relationList = new ArrayList<>();
////				//遍历permissionStructList, 符合的数据，就组装到该分类下
////				if(CollectionUtils.isNotEmpty(permissionEntityList)) {
////
////					Iterator<KhAdminPermissionEntity> iterator = permissionEntityList.iterator();
////					while(iterator.hasNext()){
////						KhAdminPermissionEntity loopPermissionItem = iterator.next();
////						if(loopPermissionItem.getCategoryId()!=null && loopPermissionItem.getCategoryId()==categoryId){
////
////							//添加到xx中，并从原list中删除
////							iterator.remove();
////
////							KhSecurityRelationStruct relationStruct = new KhSecurityRelationStruct();
////							relationStruct.setId(loopPermissionItem.getId());
////							relationStruct.setName(loopPermissionItem.getPermissionName());
////							relationStruct.setCheck(KhSecurityRelationStruct.CHECKED);
////							relationList.add(relationStruct);
////						}
////					}
////				}
////				categoryStruct.setDataList(relationList);
////				result.add(categoryStruct);
////			}
////
////			//剩余的数据，归到未分类的组中
////			CategoryStruct unCategoriedStruct = new CategoryStruct();
////			unCategoriedStruct.setCategoryId(CategoryStruct.UNCATEGORIED);
////			unCategoriedStruct.setName("未分组");
////			if(CollectionUtils.isNotEmpty(permissionEntityList)) {
////
////				List<KhSecurityRelationStruct> relationList = new ArrayList<>();
////				for(KhAdminPermissionEntity permissionLoopItem: permissionEntityList){
////
////					KhSecurityRelationStruct relationStruct = new KhSecurityRelationStruct();
////					relationStruct.setId(permissionLoopItem.getId());
////					relationStruct.setName(permissionLoopItem.getPermissionName());
////					relationStruct.setCheck(KhSecurityRelationStruct.CHECKED);
////					relationList.add(relationStruct);
////				}
////				unCategoriedStruct.setDataList(relationList);
////			}
////			result.add(unCategoriedStruct);
////
////		}
////		return result;
////
////	}
//
//
//
//	/**
//	 * 对数据做必要的整理，处理属性等
//	 * @param entity
//	 */
//	private void populateEntity(KhAdminPermissionEntity entity) {
//
//		if(entity !=null && entity.getType()!=null){
//			//需要处理type
//
//			//处理属性间的逻辑
//			if(entity.getType()== PermissionStruct.PERMISSION_TYPE_ROUTER){
//				//路由权限，设置默认level
//				entity.setMatchUrlLevel(PermissionStruct.MATCH_URL_LEVEL_EMPTY);
//			}else{
//				//api权限，TODO 根据路由登记设置level
//				String matchUrl = entity.getMatchUrl();
//				if(StringUtils.containsIgnoreCase(matchUrl, "*")){
//					//模糊模式
//					int countMatchers = StringUtils.countMatches(matchUrl, "/");
//					if(countMatchers>0){
//						//根据当前url，自动判断其层级
//						int urlLevel = countMatchers *  PermissionStruct.MATCH_URL_LEVEL_DEFAULT;
//						entity.setMatchUrlLevel((short) urlLevel);
//					}
//				}else{
//					//严格模式
//					entity.setMatchUrlLevel(PermissionStruct.MATCH_URL_LEVEL_STRICT);
//				}
//			}
//
//
//		}
//
//	}
//
//
//
//
//}