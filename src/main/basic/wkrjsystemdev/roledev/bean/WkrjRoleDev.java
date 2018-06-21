package wkrjsystemdev.roledev.bean;

/**
 * 功能模块表 对应数据库表：wkrj_sys_role_dev
 * 
 * @author zhaoxiaohua
 * 
 */
public class WkrjRoleDev {

	private String role_id;
	private String role_name;// 角色名称
	private String role_type;// 角色类型1、开发管理员 2、普通管理员 默认是普通管理员
	private String role_dept;// 角色所在部门
	private String role_other;// 角色备注

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getRole_type() {
		return role_type;
	}

	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}

	public String getRole_dept() {
		return role_dept;
	}

	public void setRole_dept(String role_dept) {
		this.role_dept = role_dept;
	}

	public String getRole_other() {
		return role_other;
	}

	public void setRole_other(String role_other) {
		this.role_other = role_other;
	}

	@Override
	public String toString() {
		return "WkrjRole [role_id=" + role_id + ", role_name=" + role_name
				+ ", role_type=" + role_type + ", role_dept=" + role_dept
				+ ", role_other=" + role_other + "]";
	}
	

}
