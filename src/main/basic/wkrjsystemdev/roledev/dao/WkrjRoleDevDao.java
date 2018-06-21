package wkrjsystemdev.roledev.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import wkrjsystemdev.permission.bean.WkrjPermission;
import wkrjsystemdev.roledev.bean.WkrjRoleDev;
import wkrjsystemdev.roledev.bean.WkrjRoleMenuDev;
import wkrjsystemdev.roledev.bean.WkrjRolePermissionDev;




public interface WkrjRoleDevDao {
	
	public List<WkrjRoleDev> getRoleList(@Param("offset")int offset,@Param("page") int page,@Param("role_dept")String dept_id);
	
	public List<WkrjRoleDev> getAllRoleList();
	/**
	 * 通过角色id获取角色
	 * @param role_id
	 * @return
	 */
	public WkrjRoleDev getRoleById(String role_id);
	
	public long getRoleListCount(@Param("role_dept")String dept_id);
	
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	public boolean addRole(WkrjRoleDev role);
	
	/**
	 * 修改角色
	 * @param module
	 * @return
	 */
	public boolean updateRole(WkrjRoleDev module);
	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	public boolean delRole(String id);
	/**
	 * 通过角色id获取菜单
	 * @param roleId
	 * @return
	 */
	public List<WkrjRoleMenuDev> getRoleMenuByRoleId(@Param("role_id")String roleId);
	
	/**
	 * 获取菜单通过ids多个id
	 * @param ids
	 * @return
	 */
	public List<WkrjRoleMenuDev> getMenuByRoleIds(@Param("role_id")String ids[]);
	/**
	 * 通过角色id获取权限
	 * @param roleId
	 * @return
	 */
	public List<WkrjRolePermissionDev> getRolePermissionByRoleId(@Param("role_id")String roleId);
	/**
	 * 获取所有的权限
	 * @return
	 */
	public List<WkrjPermission> getAllPermission();
	
	public boolean saveRoleMenu(WkrjRoleMenuDev roleMenu);
	public boolean delRoleMenu(String role_id);
	
	public boolean saveRolePermission(WkrjRolePermissionDev rolePerm);
	public boolean delRolePermission(String role_id);
}
