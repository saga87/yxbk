package wkrjsystemdev.roledev.bean;

/**
 * 用于管理角色和菜单表的中间表 
 * wkrj_sys_role_menu
 * @author zhaoxiaohua
 *
 */
public class WkrjRoleMenuDev {

	private String menu_id;// 菜单id
	private String role_id;// 角色id
	private String menu_order;// 菜单顺序
	
	public String getMenu_order() {
		return menu_order;
	}

	public void setMenu_order(String menu_order) {
		this.menu_order = menu_order;
	}

	public String getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

}
