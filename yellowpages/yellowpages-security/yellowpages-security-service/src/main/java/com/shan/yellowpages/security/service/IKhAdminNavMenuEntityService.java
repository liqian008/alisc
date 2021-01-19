package com.shan.yellowpages.security.service;

import com.shan.yellowpages.base.service.IKhBaseService;
import com.shan.yellowpages.security.model.KhAdminNavMenuEntity;
import com.shan.yellowpages.security.model.KhAdminNavMenuEntityCriteria;
import com.shan.yellowpages.security.model.struct.NavMenuStruct;

import java.util.List;

/**
 * 管理后台权限表的 service
 *
 * @author xuejw
 * @version 1.0
 * @date 2019-12-06 10:22:48
 */
public interface IKhAdminNavMenuEntityService extends
		IKhBaseService<KhAdminNavMenuEntity, KhAdminNavMenuEntityCriteria, Integer> {

	/**
	 * 指定权限code是否存在
	 * @return
	 */
	boolean codeExists(String code);



	/**
	 * 获取指定导航类型的菜单列表（抛弃根结点）
	 * @param navType 导航类型（0左导航，1上导航，目前只支持0）
	 * @param accessType
	 * @param loadPermissionInfo 是否加载权限对象
	 * @return
	 */
	List<NavMenuStruct> getMenuList(short navType, Short accessType, boolean loadPermissionInfo);

}
