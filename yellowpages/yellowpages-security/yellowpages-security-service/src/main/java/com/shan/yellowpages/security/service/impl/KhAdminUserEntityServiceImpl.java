package com.shan.yellowpages.security.service.impl;

import com.shan.yellowpages.base.dao.mapper.IKhBaseMapper;
import com.shan.yellowpages.base.exception.IKhErrorCode;
import com.shan.yellowpages.base.exception.KhException;
import com.shan.yellowpages.base.exception.KhRuntimeException;
import com.shan.yellowpages.base.model.struct.KhCriteriaBase;
import com.shan.yellowpages.security.dao.mapper.KhAdminUserEntityMapper;
import com.shan.yellowpages.security.model.KhAdminUserEntity;
import com.shan.yellowpages.security.model.KhAdminUserEntityCriteria;
import com.shan.yellowpages.security.model.struct.AdminUserStruct;
import com.shan.yellowpages.security.service.IKhAdminUserEntityService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理后台用户表的 service
 *
 * @author xuejw
 * @version 1.0
 * @date 2019-11-28 14:47:31
 */
@Service
public class KhAdminUserEntityServiceImpl implements IKhAdminUserEntityService, InitializingBean {

	private static Logger logger = LoggerFactory.getLogger(KhAdminUserEntityServiceImpl.class);

	@Autowired
	private KhAdminUserEntityMapper khAdminUserEntityMapper;

	@Override
	public void afterPropertiesSet() {
		Assert.notNull(khAdminUserEntityMapper, "khAdminUserEntityMapper can't be null");
	}

	@Override
	public IKhBaseMapper<KhAdminUserEntity, KhAdminUserEntityCriteria, Integer> getKhBaseMapper() {
		return khAdminUserEntityMapper;
	}

	@Override
	public KhCriteriaBase getKhCriteriaBase() {
		return new KhAdminUserEntityCriteria();
	}


	private Map<Integer, AdminUserStruct> cachedUserDtoMap = new ConcurrentHashMap<>();

	/**
	 * @param id
	 * @return
	 */
	@Override
	public AdminUserStruct loadCachedDtoById(int id) {
		AdminUserStruct result = cachedUserDtoMap.get(id);
		if(result==null){
			KhAdminUserEntity entity = loadById(id);
			result = AdminUserStruct.convert(entity);
			if(result!=null){
				cachedUserDtoMap.put(id, result);
			}

		}
		return result;
	}


	/**
	 * 是否需要脱敏
	 */
	@Override public boolean needDataMask(){
		return true;
	}

	/**
	 * 处理单条数据
	 * 目前用于脱敏（如：用户密码，手机号等），不向外层暴露
	 */
	@Override public void dataMask(KhAdminUserEntity t){
		if(t!=null){
			//隐藏密码
			t.setPassword(null);
		}
	}

	/**
	 * 处理多条数据
	 * 目前用于脱敏（如：用户密码，手机号等），不向外层暴露
	 */
	@Override public void dataMask(List<KhAdminUserEntity> tList){
		if(tList!=null && tList.size()>0){
			for(KhAdminUserEntity loopItem: tList){
				dataMask(loopItem);
			}
		}
	}



	/**
	 * 保存并返回实体对象 通常在rpc（对象序列化）后，实体对象的变更无法传递回调用方时使用
	 *
	 * 务必需保留此方法，子业务目前有根据此方法名做切面
	 *
	 * @param entity DB记录实体
	 * @return KhResponseResult
	 */
	@Override
	public int save(KhAdminUserEntity entity) {

		if(entity==null){
			throw new KhRuntimeException(IKhErrorCode.SYSTEM_PARAM_ERROR);
		}

		String password = entity.getPassword();
		//对密码进行加密
		String encrypedPassword = encryptedPassword(password);
		entity.setPassword(encrypedPassword);

		int result = getKhBaseMapper().insertSelective(entity);
		return result;
	}


	/**
	 * 保存并返回实体对象 通常在rpc（对象序列化）后，实体对象的变更无法传递回调用方时使用
	 *
	 * 务必需保留此方法，子业务目前有根据此方法名做切面
	 *
	 * @param entity DB记录实体
	 * @return KhResponseResult
	 */
	@Override
	public KhAdminUserEntity saveAndReturn(KhAdminUserEntity entity) {
		save(entity);
		KhAdminUserEntity result = entity;
		return result;
	}

	/**
	 * 按主键id删除
	 *
	 * @param id 主键id
	 * @return 删除结果：1-成功；0-失败
	 */
	@Override
	public int deleteById(Integer id) {
		logger.debug("[" + this.getClass().getName() + "][deleteById]entering, id:{}", id);
		return getKhBaseMapper().deleteByPrimaryKey(id);
	}

	/**
	 * 按条件删除
	 *
	 * @param criteria 条件查询辅助类
	 * @return 删除结果：1-成功；0-失败
	 */
	@Override
	public  int deleteByCriteria(KhAdminUserEntityCriteria criteria) {
		logger.debug("[" + this.getClass().getName() + "][deleteByCriteria]entering, criteria:{}", criteria);
		return getKhBaseMapper().deleteByExample(criteria);
	}

	/**
	 * 批量根据主键id批量删除
	 *
	 * @param idList 主键集合
	 * @return 删除结果：>=1-成功；0-失败
	 */
	@Override
	public int deleteBatchById(List<Integer> idList) {
		logger.debug("[" + this.getClass().getName() + "][deleteBatchById]entering, idList:{}",
				CollectionUtils.size(idList));
		return getKhBaseMapper().deleteBatchByPrimaryKey(idList);
	}




	@Override
	public int changePassword(int userId, String oldPassword, String newPassword) throws KhException {

		KhAdminUserEntityCriteria criteria = new KhAdminUserEntityCriteria();
		criteria.setLimitRows(1);

		String encrypedPassword = encryptedPassword(oldPassword);

		criteria.createCriteria().andIdEqualTo(userId).andPasswordEqualTo(encrypedPassword);
		List<KhAdminUserEntity> dataList = queryByCriteria(criteria);

		KhAdminUserEntity loadResult = CollectionUtils.size(dataList) == 1 ? dataList.get(0) : null;
		if (!KhAdminUserEntity.isValid(loadResult)) {
			throw new KhException(IKhErrorCode.SYSTEM_PARAM_ERROR, "用户名密码不匹配");
		}

		KhAdminUserEntity khAdminUserEntity = new KhAdminUserEntity();
		String encryptedPassword = encryptedPassword(newPassword);
		khAdminUserEntity.setPassword(encryptedPassword);
		khAdminUserEntity.setId(userId);

		int result = updateById(khAdminUserEntity);
		return result;
	}



	@Override
	public int adminResetPassword(int userId, String newPassword) {
		KhAdminUserEntity khAdminUserEntity = new KhAdminUserEntity();

		KhAdminUserEntity entity = loadById(userId);
		if(!KhAdminUserEntity.isValid(entity)){
			throw new KhRuntimeException(IKhErrorCode.SYSTEM_PARAM_ERROR, "要处理的用户不存在", null);
		}

		String encryptedPassword = encryptedPassword(newPassword);
		khAdminUserEntity.setPassword(encryptedPassword);

		KhAdminUserEntityCriteria criteria = new KhAdminUserEntityCriteria();
		criteria.createCriteria().andIdEqualTo(userId);
		return updateByCriteria(khAdminUserEntity, criteria);
	}

	/**
	 * 密码加密
	 * @param newPassword
	 * @return
	 */
	private String encryptedPassword(String newPassword) {
		return DigestUtils.md5Hex(newPassword);
	}

	@Override
	public KhAdminUserEntity userAuthentication(String username, String password) throws KhException {

		if (StringUtils.isBlank(username)) {
			throw new KhException(IKhErrorCode.SYSTEM_PARAM_ERROR, "用户名不能为空");
		}
		if (StringUtils.isBlank(password)) {
			throw new KhException(IKhErrorCode.SYSTEM_PARAM_ERROR, "密码不能为空");
		}

		KhAdminUserEntityCriteria criteria = new KhAdminUserEntityCriteria();
		criteria.setLimitRows(1);

		String encrypedPassword = encryptedPassword(password);

		criteria.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(encrypedPassword);
		List<KhAdminUserEntity> dataList = queryByCriteria(criteria);

		KhAdminUserEntity result = CollectionUtils.size(dataList) == 1 ? dataList.get(0) : null;

		if (result==null) {
			throw new KhException(IKhErrorCode.SYSTEM_PARAM_ERROR, "用户名密码不匹配");
		}
		//标记最后一次登录
		updateLastLoginTime(result.getId(), new Date());
		//抹掉密码，避免泄漏
		result.setPassword(null);

		return result;
	}


//	@Override
//	public KhAdminUserEntity loadUserByUsername(String username) {
//		KhAdminUserEntityCriteria criteria = new KhAdminUserEntityCriteria();
//		criteria.setLimitRows(1);
//		criteria.createCriteria().andUsernameEqualTo(username);
//		List<KhAdminUserEntity> dataList = queryByCriteria(criteria);
//		return CollectionUtils.size(dataList) == 1 ? dataList.get(0) : null;
//	}

	/**
	 * 标记最后一次登录
	 * @param userId
	 * @param lastLoginTime
	 * @return
	 */
	private int updateLastLoginTime(int userId, Date lastLoginTime) {
		KhAdminUserEntity updated = new KhAdminUserEntity();
		updated.setId(userId);
		updated.setLastLoginTime(lastLoginTime);
		int result = updateById(updated);
		return result;
	}

}