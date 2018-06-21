package wkrjsystem.menu.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wkrjsystem.menu.bean.WkrjMenu;
import wkrjsystem.menu.dao.WkrjMenuDao;
import wkrjsystem.role.bean.WkrjRoleMenu;
import wkrjsystem.role.dao.WkrjRoleDao;
import wkrjsystem.utils.Guid;
import wkrjsystemdev.module.bean.WkrjModule;
import wkrjsystemdev.module.dao.WkrjModuleDao;

@Service("wkrjMenuService")
@Transactional
public class WkrjMenuServiceImpl implements WkrjMenuService {

	@Autowired
	private WkrjMenuDao dao;
	@Autowired
	private WkrjModuleDao moduleDao; 
	
	@Autowired
	private WkrjRoleDao roleDao;
	
	//路径
	private String realPath= getClass().getClassLoader().getResource("/").getPath()+ "../../system/icons/";

	@Override
	public List<Map<String,Object>> getGridInfo(String parentId) {
		
		List<Map<String,Object>> all = new ArrayList<Map<String,Object>>();//记录所有的menu
		
		List<WkrjMenu> leftMenu = this.dao.getAllMenu(parentId);
		
		for(WkrjMenu menu : leftMenu){
			
			Map<String,Object> map = new HashMap<String,Object>();
			Map<String,Object> attrMap = new HashMap<String,Object>();
			
			map.put("id", menu.getMenu_id());
			map.put("menu_id", menu.getMenu_id());
			map.put("menu_url", menu.getMenu_url());
			map.put("name", menu.getMenu_name());
			map.put("text", menu.getMenu_name());
			map.put("menu_name", menu.getMenu_name());
			map.put("menu_order", menu.getMenu_order());
			map.put("menu_is_display", (true==menu.isMenu_is_display()?"隐藏":"显示"));
			map.put("iconCls", menu.getMenu_icon());
			if(null!=menu.getMenu_icon() && !"".equals(menu.getMenu_icon()) && !"".equals(menu.getMenu_icon()))
			map.put("icon", "system/icons/"+menu.getMenu_icon()+".png");
			map.put("menu_icon", menu.getMenu_icon());
			map.put("menu_other", menu.getMenu_other());
			map.put("menu_parent_id", menu.getMenu_parent_id());
			map.put("menu_level", menu.isMenu_level());
			
			attrMap.put("menu_url", menu.getMenu_url());
			attrMap.put("iconCls", menu.getMenu_icon());
			attrMap.put("menu_parent_id", menu.getMenu_parent_id());
			attrMap.put("menu_other", menu.getMenu_other());
			attrMap.put("menu_order", menu.getMenu_order());
			attrMap.put("menu_is_display", menu.isMenu_is_display());
			attrMap.put("wkrjModule", this.moduleDao.getWkrjModuleById(menu.getModule_id()));
			map.put("attributes", attrMap);
			
			//不是叶子节点
			if(this.dao.getAllMenuChildCount(menu.getMenu_id())>0){
				//map.put("state", "closed");
				map.put("children", this.getGridInfo(menu.getMenu_id()));
			}
			
			all.add(map);
		}
		
		return all;
	}

	@Override
	public boolean addMenu(WkrjModule module) {
		
		WkrjMenu m = module.getMenu().get(0);
		m.setMenu_id(Guid.getGuid());
		m.setModule_id(module.getModule_id());
		m.setMenu_url(module.getModule_url());
		
//		m.setMenu_icon(copyFile(module.getModule_icon()));
		
		return this.dao.addMenu(m);
	}

	@Override
	public boolean updateMenu(WkrjModule module,String yFileName) {
		
		//如果图标未被使用则删除
		if(!"".equals(yFileName) && !this.dao.checkeIsHaveUseIcon(yFileName) && !this.dao.checkeIsHaveUseIconModule(yFileName)){
			this.delIcon(yFileName);
		}
		
		WkrjMenu m = module.getMenu().get(0);
		m.setModule_id(module.getModule_id());
		m.setMenu_url(module.getModule_url());
		
		return dao.updateMenu(m);
	}

	@Override
	public int checkIsHaveChildren(String id) {
		return this.dao.getGridInfoChildCount(id);
	}

	@Override
	public boolean delMenu(String id, String icon) {
		
		try{
		
			if(dao.delMenu(id)){
				//如果图标还被其他模块使用，则不删除图标
				if(!this.dao.checkeIsHaveUseIcon(icon) && !this.dao.checkeIsHaveUseIconModule(icon)){
					return this.delIcon(icon);
				}else{
					return true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * 删除图标物理地址
	 * @param icon
	 * @return
	 */
	public boolean delIcon(String icon) {
			
		//删除本地附件
		String sPath = this.realPath+icon+".png";
		File file = new File(sPath);  
		   // 路径为文件且不为空则进行删除  
		if (file.isFile() && file.exists()) {  
			return file.delete();  
		}
		return true;
	}
	
	@Override
	public List<Map<String,Object>> getLeftMenuByRole(String parentId,String roleIds) {
		
		List<Map<String,Object>> all = new ArrayList<Map<String,Object>>();//记录所有的menu
		
		if(null!=roleIds && !"".equals(roleIds)){
			
			String roleChildMenuId="";//记录菜单拥有的子节点的id
			String roleMenuId="";//记录角色所拥有的菜单的id
			
			List<WkrjRoleMenu> roleMenus = this.roleDao.getMenuByRoleIds(roleIds.split(","));
			
			if(roleMenus.size()>0){
			
				for(WkrjRoleMenu roleMenu:roleMenus){
					roleMenuId += roleMenu.getMenu_id();
					roleMenuId +=","; 
				}
				
				for(WkrjRoleMenu roleMenu:roleMenus){
					
				List<WkrjMenu> menus = this.dao.getMenuById(roleMenu.getMenu_id(),parentId);
				
				if(menus.size()>0){
					
					for(WkrjMenu menu : menus){
					
						Map<String,Object> map = new HashMap<String,Object>();
						Map<String,Object> attributesMap = new HashMap<String,Object>();
						
						map.put("id", menu.getMenu_id());
						map.put("text", menu.getMenu_name());
						map.put("iconCls", menu.getMenu_icon());
						attributesMap.put("menu_url", menu.getMenu_url());
						attributesMap.put("iconCls", menu.getMenu_icon());
						map.put("attributes", attributesMap);
						
						//判断是否包含，子节点
						List<WkrjMenu> gridInfo = this.dao.checkNodeisLeaf(menu.getMenu_id());
						if(gridInfo.size()>0){
							
							//进入后需要判断，子节点是否被赋予了权限
							for(WkrjMenu grid:gridInfo){
								
								roleChildMenuId = grid.getMenu_id();
								roleChildMenuId +=","; 
								
								if(roleMenuId.indexOf(roleChildMenuId)>=0){
									
									//map.put("state", "closed");
									map.put("children", this.getLeftMenuByRole(menu.getMenu_id(),roleIds));
								}
								
							}
							
						}
						
						all.add(map);
						
					}
						
				}
				
			}
		}
			
	}
		
		return all;
	}
	
}
