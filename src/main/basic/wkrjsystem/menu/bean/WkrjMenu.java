package wkrjsystem.menu.bean;

/**
 * 功能模块表 对应数据库表：wkrj_sys_menu
 * 
 * @author zhaoxiaohua
 * 
 */
public class WkrjMenu {

	private String menu_id;
	private String module_id;
	private String menu_name;// 模块名称
	private boolean menu_level;// 模块等级，是否为叶子节点0不是1是
	private String menu_url;// 模块url
	private String menu_parent_id;// 父模块ID
	private int menu_order;// 模块顺序（1、2、3、4。。。。）
	private String menu_icon;// 模块图标
	private boolean menu_is_display;// 模块是否显示
	private String menu_other;// 模块备注

	
	public String getModule_id() {
		return module_id;
	}

	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}

	public String getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public boolean isMenu_level() {
		return menu_level;
	}

	public void setMenu_level(boolean menu_level) {
		this.menu_level = menu_level;
	}

	public String getMenu_url() {
		return menu_url;
	}

	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}

	public String getMenu_parent_id() {
		return menu_parent_id;
	}

	public void setMenu_parent_id(String menu_parent_id) {
		this.menu_parent_id = menu_parent_id;
	}

	public int getMenu_order() {
		return menu_order;
	}

	public void setMenu_order(int menu_order) {
		this.menu_order = menu_order;
	}

	public String getMenu_icon() {
		return menu_icon;
	}

	public void setMenu_icon(String menu_icon) {
		this.menu_icon = menu_icon;
	}

	public boolean isMenu_is_display() {
		return menu_is_display;
	}

	public void setMenu_is_display(boolean menu_is_display) {
		this.menu_is_display = menu_is_display;
	}

	public String getMenu_other() {
		return menu_other;
	}

	public void setMenu_other(String menu_other) {
		this.menu_other = menu_other;
	}

	@Override
	public String toString() {
		return "WkrjMenu [menu_id=" + menu_id + ", module_id=" + module_id
				+ ", menu_name=" + menu_name + ", menu_level=" + menu_level
				+ ", menu_url=" + menu_url + ", menu_parent_id="
				+ menu_parent_id + ", menu_order=" + menu_order
				+ ", menu_icon=" + menu_icon + ", menu_is_display="
				+ menu_is_display + ", menu_other=" + menu_other + "]";
	}

	
}
