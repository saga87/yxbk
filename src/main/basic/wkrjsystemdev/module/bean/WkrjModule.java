package wkrjsystemdev.module.bean;

import java.util.List;

import wkrjsystem.menu.bean.WkrjMenu;

/**
 * 功能模块表
 * 对应数据库表：wkrj_sys_module
 * @author zhaoxiaohua
 *
 */
public class WkrjModule {

	private String module_id;
	private String module_name;// 模块名称
	private int module_level;// 模块等级，是否为叶子节点0不是1是
	private String module_url;// 模块url
	private String module_parent_id;// 父模块ID
	private int module_order;// 模块顺序（1、2、3、4。。。。）
	private String module_icon;// 模块图标
	private boolean module_is_display;// 模块是否显示
	private String module_other;// 模块备注
	private List<WkrjMenu> menu;//菜单
	
	public List<WkrjMenu> getMenu() {
		return menu;
	}

	public void setMenu(List<WkrjMenu> menu) {
		this.menu = menu;
	}

	public String getModule_id() {
		return module_id;
	}

	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public int isModule_level() {
		return module_level;
	}

	public void setModule_level(int module_level) {
		this.module_level = module_level;
	}

	public String getModule_url() {
		return module_url;
	}

	public void setModule_url(String module_url) {
		this.module_url = module_url;
	}

	public String getModule_parent_id() {
		return module_parent_id;
	}

	public void setModule_parent_id(String module_parent_id) {
		this.module_parent_id = module_parent_id;
	}

	public int getModule_order() {
		return module_order;
	}

	public void setModule_order(int module_order) {
		this.module_order = module_order;
	}

	public String getModule_icon() {
		return module_icon;
	}

	public void setModule_icon(String module_icon) {
		this.module_icon = module_icon;
	}

	public boolean isModule_is_display() {
		return module_is_display;
	}

	public void setModule_is_display(boolean module_is_display) {
		this.module_is_display = module_is_display;
	}

	public String getModule_other() {
		return module_other;
	}

	public void setModule_other(String module_other) {
		this.module_other = module_other;
	}

	@Override
	public String toString() {
		return "WkrjModule [module_id=" + module_id + ", module_name="
				+ module_name + ", module_level=" + module_level
				+ ", module_url=" + module_url + ", module_parent_id="
				+ module_parent_id + ", module_order=" + module_order
				+ ", module_icon=" + module_icon + ", module_is_display="
				+ module_is_display + ", module_other=" + module_other
				+ ", menu=" + menu + "]";
	}

	
}
