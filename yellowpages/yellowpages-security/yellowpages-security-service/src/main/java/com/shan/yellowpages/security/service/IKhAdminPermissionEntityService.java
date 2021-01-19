//package com.shan.yellowpages.security.service;
//
//import com.shan.yellowpages.base.service.IKhBaseService;
//import com.shan.yellowpages.security.model.KhAdminPermissionEntity;
//import com.shan.yellowpages.security.model.KhAdminPermissionEntityCriteria;
//
///**
// * 管理后台权限表的 service
// *
// * @author xuejw
// * @version 1.0
// * @date 2019-12-06 10:22:47
// */
//public interface IKhAdminPermissionEntityService extends
//		IKhBaseService<KhAdminPermissionEntity, KhAdminPermissionEntityCriteria, Integer> {
//
//
//	/**
//	 * 指定权限code是否存在
//	 * @return
//	 */
//	boolean codeExists(String permissionCode);
//
//
//
//
//
//
//	//	/**
////	 * 获取指定角色的所有权限
////	 *
////	 * @param adminRoleId 角色id
////	 * @param status 状态
////	 * @return 角色集合
////	 */
////	List<KhAdminPermissionEntity> queryPermissionsByRoleId(int adminRoleId, Short status);
//
////	/**
////	 * 根据用户id查询其权限列表
////	 * @param adminUserId
////	 * @param type
////	 * @param status
////	 * @return
////	 */
////	List<KhAdminPermissionEntity> queryPermissionsByAdminUserId(int adminUserId, Short type, Short status);
//
//
//
////	/**
////	 * 获取指定状态的超级管理员权限列表
////	 *
////	 * @param status 状态（小于0则查询所有）
////	 * @return 资源列表
////	 */
////	List<KhAdminPermissionEntity> querySuperadminPermissions(Short status);
//
//
//
////	/**
////	 * 获取指定状态的权限列表
////	 *
////	 *
////	 * @param accessType
////	 * @param status 状态（小于0则查询所有）
////	 * @return 资源列表
////	 */
////	List<KhAdminPermissionEntity> queryPermissions(Short accessType, Short status);
//
//
//
////	/**
////	 * 查询角色id对应的权限列表
////	 * @param roleId
////	 * @param status
////	 * @return
////	 */
////	List<KhAdminPermissionEntity> queryPermissionsByRoleId(Integer roleId, Short status);
////
////
////
////
////
////	/**
////	 * 获取组装好的权限分类数据
////	 * @return
////	 */
////	List<CategoryStruct<KhSecurityRelationStruct>> getCategoriedPemissions(Short accessType, Short status);
//
//
//}
