package wkrjsystemdev.module.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import wkrjsystemdev.module.bean.WkrjModule;


public interface WkrjModuleDao {
	
	/**
	 * 获取系统左侧菜单列表
	 * @param parentId
	 * @return
	 */
	public List<WkrjModule> getLeftMenu(String parentId);
	/**
	 * 获取所有的菜单包括隐藏的
	 * @param parentId
	 * @return
	 */
	public List<WkrjModule> getAllModule(String parentId);
	/**
	 * 判断是否还有子节点，如果大于0说明有
	 * @param parentId
	 * @return
	 */
	public int getLeftMenuChildCount(String parentId);
	/**
	 * 获取所有的菜单包括隐藏的
	 * @param parentId
	 * @return
	 */
	public int getAllModuleChildCount(String parentId);
	
	public List<WkrjModule> checkNodeisLeaf(String parentId);
	/**
	 * 判断是否还有子节点，如果大于0说明有
	 * @param parentId
	 * @return
	 */
	public int getGridInfoChildCount(String parentId);
	/**
	 * 判断是否还有子节点，如果大于0说明有  不包括隐藏的
	 * @param parentId
	 * @return
	 */
	public int getDisplayGridInfoChildCount(String parentId);
	
	/**
	 * 获取模块菜单列表 
	 * @param parentId
	 * @return
	 */
	public List<WkrjModule> getGridInfo(String parentId);
	/**
	 * 获取模块菜单列表 隐藏的不显示
	 * @param parentId
	 * @return
	 */
	public List<WkrjModule> getDisplayGridInfo(String parentId);
	/**
	 * 通过id获取WkrjModule
	 * @param id
	 * @return
	 */
	public WkrjModule getWkrjModuleById(String id);
	
	/**
	 * 添加模块
	 * @param module
	 * @return
	 */
	public boolean addModule(WkrjModule module);
	/**
	 * 修改模块
	 * @param module
	 * @return
	 */
	public boolean updateModule(WkrjModule module);
	/**
	 * 删除模块
	 * @param id
	 * @return
	 */
	public boolean delModule(String id);
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
	
	/**
	 * 获取菜单模块通过id
	 * @param id
	 * @param parentId
	 * @return
	 */
	public List<WkrjModule> getMenuById(@Param("menu_id")String id,@Param("parentId")String parentId);
	
}
