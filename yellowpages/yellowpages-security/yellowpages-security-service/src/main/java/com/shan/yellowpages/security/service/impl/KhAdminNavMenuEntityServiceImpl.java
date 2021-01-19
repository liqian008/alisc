package com.shan.yellowpages.security.service.impl;

import com.shan.yellowpages.base.dao.mapper.IKhBaseMapper;
import com.shan.yellowpages.base.exception.IKhErrorCode;
import com.shan.yellowpages.base.exception.KhRuntimeException;
import com.shan.yellowpages.base.model.struct.KhCriteriaBase;
import com.shan.yellowpages.base.model.tree.struct.KhTreeStruct;
import com.shan.yellowpages.security.dao.mapper.KhAdminNavMenuEntityMapper;
import com.shan.yellowpages.security.model.KhAdminNavMenuEntity;
import com.shan.yellowpages.security.model.KhAdminNavMenuEntityCriteria;
import com.shan.yellowpages.security.model.struct.NavMenuStruct;
import com.shan.yellowpages.security.service.IKhAdminNavMenuEntityService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理后台权限表的 service
 *
 * @author xuejw
 * @version 1.0
 * @date 2019-12-06 10:22:48
 */
@Service
public class KhAdminNavMenuEntityServiceImpl implements IKhAdminNavMenuEntityService, InitializingBean {

	private static Logger logger = LoggerFactory.getLogger(KhAdminNavMenuEntityServiceImpl.class);

	@Autowired
	private KhAdminNavMenuEntityMapper khAdminNavMenuEntityMapper;
//	@Autowired
//	private IKhAdminPermissionEntityService khAdminPermissionEntityService;

	@Override
	public void afterPropertiesSet() {
		Assert.notNull(khAdminNavMenuEntityMapper, "khAdminNavMenuEntityMapper can't be null");
//		Assert.notNull(khAdminPermissionEntityService, "khAdminPermissionEntityService can't be null");
	}

	@Override
	public IKhBaseMapper<KhAdminNavMenuEntity, KhAdminNavMenuEntityCriteria, Integer> getKhBaseMapper() {
		return khAdminNavMenuEntityMapper;
	}

	@Override
	public KhCriteriaBase getKhCriteriaBase() {
		return new KhAdminNavMenuEntityCriteria();
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

		if(id<0){
			throw new KhRuntimeException(IKhErrorCode.SYSTEM_PARAM_ERROR, "不能删除根节点", null);
		}

		//删除子结点
		KhAdminNavMenuEntityCriteria criteria = new KhAdminNavMenuEntityCriteria();
		criteria.createCriteria().andParentIdEqualTo(id);
		getKhBaseMapper().deleteByExample(criteria);

		//删除本节点
		int result = getKhBaseMapper().deleteByPrimaryKey(id);
		return result;
	}



	/**
	 * 指定权限code是否存在
	 * @return
	 */
	@Override public boolean codeExists(String code) {

		KhAdminNavMenuEntityCriteria criteria = new KhAdminNavMenuEntityCriteria();
		KhAdminNavMenuEntityCriteria.Criteria subCriteria = criteria.createCriteria();
		subCriteria.andMenuCodeEqualTo(code);

		List<KhAdminNavMenuEntity> dataList = queryByCriteria(criteria);

		boolean result = dataList!=null && dataList.size()>0;
		return result;
	}



	/**
	 * 获取指定导航类型的菜单列表（抛弃根结点）
	 * @param navType 导航类型（0左导航，1上导航，目前只支持0）
	 * @param accessType
	 * @param loadPermissionInfo
	 * @return
	 */
	@Override
	public List<NavMenuStruct> getMenuList(short navType, Short accessType, boolean loadPermissionInfo){

		logger.debug("[getMenuList] entering, navType:{}", navType);

		List<NavMenuStruct> result = new ArrayList<>();

		//查询db中的菜单项列表
		KhAdminNavMenuEntityCriteria criteria = new KhAdminNavMenuEntityCriteria();

		KhAdminNavMenuEntityCriteria.Criteria subCriteria = criteria.createCriteria();
		subCriteria.andNavTypeEqualTo(navType);
//		if(accessType!=null){
//			subCriteria.andAccessTypeEqualTo(accessType);
//		}
//		if(!loadPermissionInfo){
//			//任务非superadmin操作，需要指定状态
//			subCriteria.andStatusEqualTo(StatusEnum.ENABLE.getStatus());
//		}

		criteria.setOrderByClause(" sort asc");

		//获取全部菜单
		List<KhAdminNavMenuEntity> fullMenuList = queryByCriteria(criteria);


		if(CollectionUtils.isNotEmpty(fullMenuList)){
			for(KhAdminNavMenuEntity menuItem: fullMenuList){

				if(menuItem.getParentId()==NavMenuStruct.ROOT_ID){
					//一级菜单
					NavMenuStruct level1Menu = new NavMenuStruct();
					level1Menu.setId(menuItem.getId());
					level1Menu.setParentId(menuItem.getParentId());
					level1Menu.setTitle(menuItem.getTitle());
					level1Menu.setMenuCode(menuItem.getMenuCode());
					level1Menu.setIconUrl(menuItem.getIconUrl());

//					//是否加载权限对象
//					int permissionId = menuItem.getPermissionId();
//					level1Menu.setPermissionId(menuItem.getPermissionId());
//					if(permissionId>0 && loadPermissionInfo){
//						PermissionStruct permissionStruct = loadPermissionStruct(permissionId);
//						level1Menu.setPermissionStruct(permissionStruct);
//					}

					//添加子节点
					result.add(level1Menu);
				}
			}
		}

		///遍历一级菜单
		List<NavMenuStruct> level1MenuList = result;
		if(CollectionUtils.isNotEmpty(level1MenuList)){
			for(KhTreeStruct levelMenu: level1MenuList){
				NavMenuStruct level1MenuStructItem = (NavMenuStruct) levelMenu;
				//获取一级菜单的菜单id
				int menuId = level1MenuStructItem.getId();

				for(KhAdminNavMenuEntity fullMenuItem: fullMenuList){
//
					if(fullMenuItem.getParentId()==menuId){
						//二级菜单
						NavMenuStruct level2Menu = new NavMenuStruct();
						level2Menu.setId(fullMenuItem.getId());
						level2Menu.setParentId(menuId);
//						level2Menu.setPermissionId(fullMenuItem.getPermissionId());
						level2Menu.setTitle(fullMenuItem.getTitle());
						level2Menu.setIconUrl(fullMenuItem.getIconUrl());
//						level2Menu.setKey(fullMenuItem.getKeyCode());
						level2Menu.setMenuCode(fullMenuItem.getMenuCode());

//						//是否加载权限对象
//						int permissionId = fullMenuItem.getPermissionId();
//						level2Menu.setPermissionId(fullMenuItem.getPermissionId());
//						if(permissionId>0 && loadPermissionInfo){
//							PermissionStruct permissionStruct = loadPermissionStruct(permissionId);
//							level2Menu.setPermissionStruct(permissionStruct);
//						}

						//在一级菜单下添加子节点（二级菜单）
						level1MenuStructItem.addChild(level2Menu);
					}
				}
			}
		}

		logger.info("[getMenuList]result:{}, navType:{}", result, navType);

		return result;
	}


//	/**
//	 * 加载permissionStruct
//	 * @param permissionId
//	 * @return
//	 */
//	private PermissionStruct loadPermissionStruct(int permissionId) {
//		KhAdminPermissionEntity permissionEntity = khAdminPermissionEntityService.loadById(permissionId);
//		//		KhAdminPermissionEntity permissionEntity = khAdminPermissionEntityService.loadCacheById(permissionId);
//		return PermissionStruct.convert(permissionEntity);
//	}
}