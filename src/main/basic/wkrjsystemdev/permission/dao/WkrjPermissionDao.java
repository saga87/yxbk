package wkrjsystemdev.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import wkrjsystemdev.permission.bean.WkrjPermission;


public interface WkrjPermissionDao {

	public List<WkrjPermission> getPermissionList(@Param("offset")int offset, @Param("page")int page, @Param("module_id")String module_id);
	public long getPermissionListCount(String module_id);
	
	/**
	 * 添加权限
	 * @param module
	 * @return
	 */
	public boolean addPermission(WkrjPermission module);
	
	/**
	 * 检测是否已经有了权限
	 * @param perm_flag
	 * @return
	 */
	public int checkIsHavePermission(@Param("perm_flag")String perm_flag,@Param("perm_id")String id);
	/**
	 * 修改权限
	 * @param module
	 * @return
	 */
	public boolean updatePermission(WkrjPermission module);
	/**
	 * 删除权限
	 * @param id
	 * @return
	 */
	public boolean delPermission(String id);
	/**
	 * 删除权限通过模块id
	 * @param moduleId
	 * @return
	 */
	public boolean delPermissionByModuleId(String moduleId);
	/**
	 * 通过模块id获取权限
	 * @param moduleId
	 * @return
	 */
	public List<WkrjPermission> getPermissionByModuleId(String moduleId);
	/**
	 * 通过id获取权限
	 * @param permId
	 * @return
	 */
	public List<WkrjPermission> getPermissionByPermId(String permId);

}