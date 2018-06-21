package wkrjsystem.menu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import wkrjsystem.menu.bean.WkrjMenu;



public interface WkrjMenuDao {
	
	/**
	 * 判断是否还有子节点，如果大于0说明有
	 * 隐藏菜单不会出现
	 * @param parentId
	 * @return
	 */
	public int getGridInfoChildCount(String parentId);
	/**
	 * 判断是否还有子节点，如果大于0说明有
	 * 隐藏菜单会出现
	 * @param parentId
	 * @return
	 */
	public int getAllMenuChildCount(String parentId);
	
	/**
	 * 获取模块菜单列表
	 * 隐藏菜单不会出现
	 * @param parentId
	 * @return
	 */
	public List<WkrjMenu> getGridInfo(String parentId);
	/**
	 * 获取模块菜单列表
	 * 隐藏菜单会出现
	 * @param parentId
	 * @return
	 */
	public List<WkrjMenu> getAllMenu(String parentId);
	
	public List<WkrjMenu> checkNodeisLeaf(String parentId);
	/**
	 * 获取菜单模块通过id
	 * @param id
	 * @param parentId
	 * @return
	 */
	public List<WkrjMenu> getMenuById(@Param("menu_id")String id,@Param("parentId")String parentId);
	
	/**
	 * 添加模块
	 * @param module
	 * @return
	 */
	public boolean addMenu(WkrjMenu module);
	/**
	 * 修改模块
	 * @param module
	 * @return
	 */
	public boolean updateMenu(WkrjMenu module);
	/**
	 * 删除模块
	 * @param id
	 * @return
	 */
	public boolean delMenu(String id);
	
	/**
	 * 判断是否图标还被占用着 wkrj_sys_menu
	 * @param icon
	 * @return
	 */
	public boolean checkeIsHaveUseIcon(String icon);
	/**
	 * 判断是否图标还被占用着 wkrj_sys_module
	 * @param icon
	 * @return
	 */
	public boolean checkeIsHaveUseIconModule(String icon);
	
}
