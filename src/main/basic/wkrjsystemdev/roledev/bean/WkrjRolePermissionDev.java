package wkrjsystemdev.roledev.bean;

/**
 * 用于管理角色和权限表的中间表
 * wkrj_sys_role_permission
 * @author zhaoxiaohua
 * 
 */
public class WkrjRolePermissionDev {

	private String perm_id;// 权限id
	private String role_id;// 角色id

	public String getPerm_id() {
		return perm_id;
	}

	public void setPerm_id(String perm_id) {
		this.perm_id = perm_id;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

}
