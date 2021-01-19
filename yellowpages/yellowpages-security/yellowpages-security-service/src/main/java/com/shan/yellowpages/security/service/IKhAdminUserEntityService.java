package com.shan.yellowpages.security.service;

import com.shan.yellowpages.base.exception.KhException;
import com.shan.yellowpages.base.service.IKhBaseService;
import com.shan.yellowpages.security.model.KhAdminUserEntity;
import com.shan.yellowpages.security.model.KhAdminUserEntityCriteria;
import com.shan.yellowpages.security.model.struct.AdminUserStruct;

/**
 * 管理后台用户表的 service
 *
 * @author xuejw
 * @version 1.0
 * @date 2019-11-28 14:47:31
 */
public interface IKhAdminUserEntityService extends IKhBaseService<KhAdminUserEntity, KhAdminUserEntityCriteria, Integer> {

	/**
	 *
	 * @param id
	 * @return
	 */
	AdminUserStruct loadCachedDtoById(int id);



	/**
	 * 用户自行修改密码
	 *
	 * @param userId 用户id
	 * @param oldPassword 用户旧密码
	 * @param newPassword 用户新密码
	 * @return 修改结果
	 */
	int changePassword(int userId, String oldPassword, String newPassword) throws KhException;


	/**
	 * 管理员根据用户id，直接修改用户密码
	 *
	 * @param userId
	 * @param newPassword
	 * @return
	 */
	int adminResetPassword(int userId, String newPassword);


	/**
	 * 验证用户名密码
	 * @param username
	 * @param password
	 * @return
	 * @throws KhException TODO 自定义异常用户名不存在，账户密码不匹配
	 */
	KhAdminUserEntity userAuthentication(String username, String password) throws KhException;



	//	/**
//	 * 根据用户名查询用户信息
//	 *
//	 * 方法设计不合理，不应该直接返回用户对象（会暴露用户的密码）
//	 * 使用userAuthentication方法进行替代
//	 *
//	 * @param username 用户名称
//	 * @return {@link KhAdminUserEntity}
//	 */
//	@Deprecated
//	KhAdminUserEntity loadUserByUsername(String username);

}
