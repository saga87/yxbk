package wkrjsystemdev.permission.service;

import java.util.List;

import wkrjsystemdev.permission.bean.WkrjPermission;


public interface WkrjPermissionService {

	public List<WkrjPermission> getPermissionList(int offset, int page, String module_id);
	
	public long getPermissionListCount(String module_id);
	
	public boolean addPermission(WkrjPermission module);
	/**
	 * 检测是否已经有了权限
	 * @param perm_flag
	 * @return
	 */
	public boolean checkIsHavePermission(String perm_flag,String id);
	
	public boolean updatePermission(WkrjPermission module);
	
	public boolean delPermission(String id,String icon);
	
	public boolean delPermissionByModuleId(String moduleId);

}