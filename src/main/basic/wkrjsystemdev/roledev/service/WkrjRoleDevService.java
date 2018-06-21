package wkrjsystemdev.roledev.service;

import java.util.List;
import java.util.Map;

import wkrjsystemdev.roledev.bean.WkrjRoleDev;
import wkrjsystemdev.roledev.bean.WkrjRoleMenuDev;


public interface WkrjRoleDevService {
    
	
	public List<WkrjRoleDev> getRoleList(int offset, int page,String dept_id);
	
	public List<WkrjRoleDev> getAllRoleList();
	
	public long getRoleList(String dept_id);
	
	public boolean addRole(WkrjRoleDev module);
	
	public boolean updateRole(WkrjRoleDev module);
	
	public boolean delRole(String id);
	
	/**
	 * 获取菜单权限列表
	 * @param role_id
	 * @return
	 */
	public List<Map<String,Object>> getMenuPermission(String role_id,String parentId);
	/**
	 * 保存权限
	 * @param menulist
	 * @param permissionlist
	 * @param roler_id
	 * @param menu_order菜单顺序
	 * @return
	 */
	public boolean setMenuPermission(String menulist,String permissionlist,String roler_id,String menu_order);
	/**
	 * 拷贝角色
	 * @param role_id
	 * @param role_name
	 * @return
	 */
	public boolean copyRole(String role_id);
	/**
	 * 通过角色id获取菜单
	 * @param roleId
	 * @return
	 */
	public List<WkrjRoleMenuDev> getRoleMenuByRoleId(String roleId);
	/**
	 * 获取角色权限通过角色id
	 * @param id
	 * @return
	 */
	public String getRolePermissionByRoleId(String id);
	/**
	 * 获取所有的权限
	 * @return
	 */
	public String getAllPermission();

}
