package wkrjsystem.user.bean;

/**
 * 岗位模块表 对应数据库表：wkrj_sys_user_role
 * 
 * @author zhaoxiaohua
 * 
 */
public class WkrjUserRole {

	private String user_id;//用户Id
	private String role_id;// 角色Id

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	@Override
	public String toString() {
		return "WkrjUserRole [user_id=" + user_id + ", role_id=" + role_id
				+ "]";
	}
	
	

}
