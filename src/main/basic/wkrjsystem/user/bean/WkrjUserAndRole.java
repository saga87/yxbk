package wkrjsystem.user.bean;

import wkrjsystem.role.bean.WkrjRole;

/**
 * 岗位模块表 对应数据库表：wkrj_sys_user_role
 * 
 * @author zhaoxiaohua
 * 
 */
public class WkrjUserAndRole {

	private WkrjUser user;
	private WkrjRole role;

	public WkrjUser getUser() {
		return user;
	}

	public void setUser(WkrjUser user) {
		this.user = user;
	}

	public WkrjRole getRole() {
		return role;
	}

	public void setRole(WkrjRole role) {
		this.role = role;
	}

}
