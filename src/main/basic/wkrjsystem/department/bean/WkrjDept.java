package wkrjsystem.department.bean;

/**
 * 功能模块表 对应数据库表：wkrj_sys_department
 */
public class WkrjDept {

	private String dept_id;
	private String dept_order;
	private String dept_name;
	private String dept_parent_id;
	private String dept_is_leaf;
	private String dept_other;


	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_order() {
		return dept_order;
	}

	public void setDept_order(String dept_order) {
		this.dept_order = dept_order;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getDept_parent_id() {
		return dept_parent_id;
	}

	public void setDept_parent_id(String dept_parent_id) {
		this.dept_parent_id = dept_parent_id;
	}

	public String getDept_is_leaf() {
		return dept_is_leaf;
	}

	public void setDept_is_leaf(String dept_is_leaf) {
		this.dept_is_leaf = dept_is_leaf;
	}

	public String getDept_other() {
		return dept_other;
	}

	public void setDept_other(String dept_other) {
		this.dept_other = dept_other;
	}


}
