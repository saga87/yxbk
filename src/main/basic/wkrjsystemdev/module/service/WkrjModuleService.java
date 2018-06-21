package wkrjsystemdev.module.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import wkrjsystemdev.module.bean.WkrjModule;


public interface WkrjModuleService {
    
	/**
	 * 获取系统左侧菜单列表
	 * @param parentId
	 * @return
	 */
	public List<Map<String,Object>> getLeftMenu(String parentId);
	
	public List<Map<String,Object>> getLeftMenuByRole(String parentId,String roleIds);
	
	/**
	 * 获取模块菜单列表
	 * @param parentId
	 * @return
	 */
	public List<Map<String,Object>> getGridInfo(String parentId);
	/**
	 * 只是显示不隐藏的菜单
	 * @param parentId
	 * @return
	 */
	public List<Map<String,Object>> getDisplayGridInfo(String parentId);
	
	public boolean addModule(WkrjModule module);
	
	public boolean updateModule(WkrjModule module,String yFileName);
	
	public boolean delModule(String id,String icon);
	
	public String uploadPic(MultipartFile module_icons,HttpServletRequest request,String foledArrress);
	/**
	 * 判断是否还有子节点
	 * @param id
	 * @return
	 */
	public int checkIsHaveChildren(String id);
}
