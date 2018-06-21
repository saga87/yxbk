package wkrjsystem.menu.service;

import java.util.List;
import java.util.Map;

import wkrjsystemdev.module.bean.WkrjModule;


public interface WkrjMenuService {
    
	/**
	 * 获取模块菜单列表
	 * @param parentId
	 * @return
	 */
	public List<Map<String,Object>> getGridInfo(String parentId);
	
	public List<Map<String,Object>> getLeftMenuByRole(String parentId,String roleIds);
	
	public boolean addMenu(WkrjModule module);
	
	public boolean updateMenu(WkrjModule module,String yFileName);
	
	public boolean delMenu(String id,String icon);
	
	/**
	 * 判断是否还有子节点
	 * @param id
	 * @return
	 */
	public int checkIsHaveChildren(String id);
	
}
