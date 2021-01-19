package com.shan.yellowpages.security.model.struct;

import com.shan.yellowpages.base.model.tree.struct.KhTreeStruct;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 菜单结构
 * @author bruce
 */
@Data
@NoArgsConstructor
public class NavMenuStruct extends KhTreeStruct<Integer> {

	private static final long serialVersionUID = 1L;

	/** 左导航类型 */
	public static final short NAV_TYPE_SIDEBAR_LEFT = 0;

	/** 根节点的id */
	public static final int ROOT_ID = 0;

	/** icon地址 */
	private String iconUrl;
	/** 菜单名称 */
	private String title;
	/** 菜单code */
	private String menuCode;

//	private PermissionStruct permissionStruct;
	//	/** 该菜单对应的权限id */
	//	private int permissionId;

	public NavMenuStruct(Integer parentId, Integer id, List<KhTreeStruct<Integer>> children) {
		super(parentId, id, children);
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	/**
	 * 判断是否合法
	 *
	 * @param entity
	 */
	public static boolean isValid(NavMenuStruct entity) {
		boolean result = false;
		if (entity != null ){
			result = true;
		}
		return result;
	}

}