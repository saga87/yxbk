package wkrjsystemdev.permission.bean;

public class WkrjPermission {

	private String perm_id;// 权限ID
	private String module_id;// 模块ID
	private String perm_name;// 权限名称
	private String perm_flag;// 权限标记
	private String perm_icon;// 权限图标
	private String perm_icon_show;// 权限图标

	public String getPerm_icon_show() {
		return perm_icon_show;
	}

	public void setPerm_icon_show(String perm_icon_show) {
		this.perm_icon_show = perm_icon_show;
	}

	public String getPerm_id() {
		return perm_id;
	}

	public void setPerm_id(String perm_id) {
		this.perm_id = perm_id;
	}

	public String getModule_id() {
		return module_id;
	}

	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}

	public String getPerm_name() {
		return perm_name;
	}

	public void setPerm_name(String perm_name) {
		this.perm_name = perm_name;
	}

	public String getPerm_flag() {
		return perm_flag;
	}

	public void setPerm_flag(String perm_flag) {
		this.perm_flag = perm_flag;
	}

	public String getPerm_icon() {
		return perm_icon;
	}

	public void setPerm_icon(String perm_icon) {
		this.perm_icon = perm_icon;
	}

}
