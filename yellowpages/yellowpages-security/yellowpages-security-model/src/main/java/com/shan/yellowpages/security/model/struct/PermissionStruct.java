//package com.shan.yellowpages.security.model.struct;
//
//import com.shan.yellowpages.security.model.KhAdminPermissionEntity;
//
//import java.io.Serializable;
//
///**
// * 权限的数据结构
// *
// * 复写了hashcode与equals方法，便于在集合中比较
// * @author bruce
// */
//public class PermissionStruct implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//
//	/** 数据api请求的权限类型 */
//	public static final short PERMISSION_TYPE_DATA = 0;
//	/** 路由的权限类型 */
//	public static final short PERMISSION_TYPE_ROUTER =  10;
//
//	/** 业务默认权限（sass一般用户使用） */
//	public static final short ACCESS_TYPE_NORMAL = 0;
//	/** 业务管理员的管理权限（sass admin管理员使用） */
//	public static final short ACCESS_TYPE_ADMIN = 10;
//	/** 超级管理员的业务权限（如open_app表等） */
//	public static final short ACCESS_TYPE_SUPER_NORMAL = 20;
//	/** 超级管理员的管理权限 */
//	public static final short ACCESS_TYPE_SUPER_ADMIN = 30;
//
//
//	/** 不指定url匹配时使用的level */
//	public static final short MATCH_URL_LEVEL_EMPTY =  0;
//	/** 默认level */
//	public static final short MATCH_URL_LEVEL_DEFAULT =  10;
//	/** url精准匹配时使用的固定level */
//	public static final short MATCH_URL_LEVEL_STRICT=  9999;
//
//
//	/** 权限id */
//	private int permissionId;
//	/** 权限code */
//	private String permissionCode;
//	/** 权限名称 */
//	private String name;
//	/** 权限类型 */
//	private Short type;
//
//
//
//	public PermissionStruct() {
//	}
//
//	@Deprecated
//	public PermissionStruct(int permissionId, String name, Short type) {
//		this.permissionId = permissionId;
//		this.name = name;
//		this.type = type;
//	}
//
//	public PermissionStruct(int permissionId, String permissionCode, String name, Short type) {
//		this.permissionId = permissionId;
//		this.permissionCode = permissionCode;
//		this.name = name;
//		this.type = type;
//	}
//
//	/**
//	 * 转换为struct
//	 * @param permissionEntity
//	 * @return
//	 */
//	public static PermissionStruct convert(KhAdminPermissionEntity permissionEntity) {
//		PermissionStruct result = null;
//		if(KhAdminPermissionEntity.isValid(permissionEntity)){
//			result = new PermissionStruct();
//			result.setPermissionId(permissionEntity.getId());
//			result.setName(permissionEntity.getPermissionName());
//			result.setPermissionCode(permissionEntity.getPermissionCode());
//			result.setType(result.getType());
//		}
//		return result;
//	}
//
//	public Short getType() {
//		return type;
//	}
//
//	public void setType(Short type) {
//		this.type = type;
//	}
//
//	public int getPermissionId() {
//		return permissionId;
//	}
//
//	public void setPermissionId(int permissionId) {
//		this.permissionId = permissionId;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getPermissionCode() {
//		return permissionCode;
//	}
//
//	public void setPermissionCode(String permissionCode) {
//		this.permissionCode = permissionCode;
//	}
//
//	@Override public int hashCode() {
//		return permissionId;
//	}
//
//	@Override public boolean equals(Object obj) {
//
//		boolean result = false;
//		if(obj!=null && obj instanceof PermissionStruct){
//			result = permissionId == ((PermissionStruct) obj).getPermissionId();
//		}
//		return result;
//	}
//
//	@Override public String toString() {
//		return "PermissionStruct{" + "permissionId=" + permissionId + ", permissionCode='" + permissionCode + '\''
//				+ ", name='" + name + '\'' + ", type=" + type + '}';
//	}
//
//}
//
//
//
//
//
