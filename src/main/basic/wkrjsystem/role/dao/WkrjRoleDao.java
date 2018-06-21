package wkrjsystem.role.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import wkrjsystem.role.bean.WkrjRole;
import wkrjsystem.role.bean.WkrjRoleMenu;
import wkrjsystem.role.bean.WkrjRolePermission;
import wkrjsystemdev.permission.bean.WkrjPermission;




public interface WkrjRoleDao {
	
	public List<WkrjRole> getRoleList(@Param("offset")int offset,@Param("page") int page,@Param("role_dept")String dept_id);
	
	public List<WkrjRole> getAllRoleList();
	/**
	 * 通过角色id获取角色
	 * @param role_id
	 * @return
	 */
	public WkrjRole getRoleById(String role_id);
	
	public long getRoleListCount(@Param("role_dept")String dept_id);
	
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	public boolean addRole(WkrjRole role);
	
	/**
	 * 修改角色
	 * @param module
	 * @return
	 */
	public boolean updateRole(WkrjRole module);
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
	public List<WkrjRoleMenu> getRoleMenuByRoleId(@Param("role_id")String roleId);
	
	/**
	 * 获取菜单通过ids多个id
	 * @param ids
	 * @return
	 */
	public List<WkrjRoleMenu> getMenuByRoleIds(@Param("role_id")String ids[]);
	/**
	 * 通过角色id获取权限
	 * @param roleId
	 * @return
	 */
	public List<WkrjRolePermission> getRolePermissionByRoleId(@Param("role_id")String roleId);
	/**
	 * 获取所有的权限
	 * @return
	 */
	public List<WkrjPermission> getAllPermission();
	
	public boolean saveRoleMenu(WkrjRoleMenu roleMenu);
	public boolean delRoleMenu(String role_id);
	
	public boolean saveRolePermission(WkrjRolePermission rolePerm);
	public boolean delRolePermission(String role_id);
}
