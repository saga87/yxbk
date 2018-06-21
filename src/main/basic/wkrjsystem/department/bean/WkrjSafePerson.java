package wkrjsystem.department.bean;

/**
 * 安全管理人员表 对应数据库表：wkrj_safemanageperson
 */
public class WkrjSafePerson {

	private String dept_id;
	private String safe_person_id;
	private String safe_person_name;
	private String safe_person_tel;
	private String safe_person_phone;
	private String safe_person_email;
	public String getDept_id() {
		return dept_id;
	}
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	public String getSafe_person_id() {
		return safe_person_id;
	}
	public void setSafe_person_id(String safe_person_id) {
		this.safe_person_id = safe_person_id;
	}
	public String getSafe_person_name() {
		return safe_person_name;
	}
	public void setSafe_person_name(String safe_person_name) {
		this.safe_person_name = safe_person_name;
	}
	public String getSafe_person_tel() {
		return safe_person_tel;
	}
	public void setSafe_person_tel(String safe_person_tel) {
		this.safe_person_tel = safe_person_tel;
	}
	public String getSafe_person_phone() {
		return safe_person_phone;
	}
	public void setSafe_person_phone(String safe_person_phone) {
		this.safe_person_phone = safe_person_phone;
	}
	public String getSafe_person_email() {
		return safe_person_email;
	}
	public void setSafe_person_email(String safe_person_email) {
		this.safe_person_email = safe_person_email;
	}

}
