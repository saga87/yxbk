package wkrjsystemdev.userdev.bean;

import wkrjsystemdev.roledev.bean.WkrjRoleDev;

/**
 * 岗位模块表 对应数据库表：wkrj_sys_user_role_dev
 * 
 * @author zhaoxiaohua
 * 
 */
public class WkrjUserAndRoleDev {

	private WkrjUserDev user;
	private WkrjRoleDev role;

	public WkrjUserDev getUser() {
		return user;
	}

	public void setUser(WkrjUserDev user) {
		this.user = user;
	}

	public WkrjRoleDev getRole() {
		return role;
	}

	public void setRole(WkrjRoleDev role) {
		this.role = role;
	}

}
