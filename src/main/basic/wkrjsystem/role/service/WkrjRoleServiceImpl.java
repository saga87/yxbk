package wkrjsystem.role.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wkrjsystem.menu.bean.WkrjMenu;
import wkrjsystem.menu.dao.WkrjMenuDao;
import wkrjsystem.role.bean.WkrjRole;
import wkrjsystem.role.bean.WkrjRoleMenu;
import wkrjsystem.role.bean.WkrjRolePermission;
import wkrjsystem.role.dao.WkrjRoleDao;
import wkrjsystem.utils.Guid;
import wkrjsystemdev.permission.bean.WkrjPermission;
import wkrjsystemdev.permission.dao.WkrjPermissionDao;

@Service("wkrjRoleService")
@Transactional
public class WkrjRoleServiceImpl implements WkrjRoleService {

	@Autowired
	private WkrjRoleDao dao;

	@Autowired
	private WkrjMenuDao menuDao;
	
	@Autowired
	private WkrjPermissionDao permDao;
	
	
	@Override
	public List<WkrjRole> getRoleList(int offset, int page, String dept_id) {
		return this.dao.getRoleList(offset, page, dept_id);
	}

	@Override
	public long getRoleList(String dept_id) {
		return this.dao.getRoleListCount(dept_id);
	}
	
	@Override
	public boolean addRole(WkrjRole module) {
		return this.dao.addRole(module);
	}

	@Override
	public boolean updateRole(WkrjRole module) {
		return this.dao.updateRole(module);
	}

	@Override
	public boolean delRole(String id) {
		
		boolean sus=true;
		
		//删除菜单
		if(this.dao.getRoleMenuByRoleId(id).size()>0){
			sus=this.dao.delRoleMenu(id);
			if(false==sus)return false;
		}
		//删除权限
		if(this.dao.getRolePermissionByRoleId(id).size()>0){
			sus=this.dao.delRolePermission(id);
			if(false==sus)return false;
		}
		
		if(sus){
			return dao.delRole(id);
		}
		
		return false;
		
		
	}

	@Override
	public List<Map<String, Object>> getMenuPermission(String role_id,String parentId) {
		
		List<Map<String,Object>> all = new ArrayList<Map<String,Object>>();//记录所有的menu
		
		List<WkrjMenu> allMenu = this.menuDao.getAllMenu(parentId);//获取所有的菜单通过parentId
		List<WkrjRoleMenu> allRoleMenu = this.dao.getRoleMenuByRoleId(role_id);//获取角色菜单
		
		for(WkrjMenu menu : allMenu){
			
			Map<String,Object> map = new HashMap<String,Object>();
			Map<String,Object> attrMap = new HashMap<String,Object>();
			
			map.put("id", menu.getMenu_id());
			map.put("menu_url", menu.getMenu_url());
			map.put("name", menu.getMenu_name());
			map.put("text", menu.getMenu_name());
			map.put("iconCls", menu.getMenu_icon());
			
			attrMap.put("menu_url", menu.getMenu_url());
			attrMap.put("menu_id", menu.getMenu_id());
			attrMap.put("menu_parent_id", menu.getMenu_parent_id());
			attrMap.put("menu_other", menu.getMenu_other());
			attrMap.put("menu_order", menu.getMenu_order());
			attrMap.put("menu_is_display", menu.isMenu_is_display());
			map.put("attributes", attrMap);
			
			//获取所有的权限通过模块Id
			List<WkrjPermission> allWkrjPermission = permDao.getPermissionByModuleId(menu.getModule_id());
			List<Map<String,Object>> listPermissionMap = new ArrayList<Map<String,Object>>();
			
			if(allWkrjPermission.size()>0){
				
				for(WkrjPermission perm:allWkrjPermission){
					
					Map<String,Object> permissionMap = new HashMap<String,Object>();
					Map<String,Object> permAttrMap = new HashMap<String,Object>();
					
					permissionMap.put("id", perm.getPerm_id());
					permissionMap.put("text", perm.getPerm_name());
					permissionMap.put("iconCls", perm.getPerm_icon());
					
					permAttrMap.put("permid", perm.getPerm_id());
					permissionMap.put("attributes",permAttrMap);
					
					//通过角色id获取对应的权限
					List<WkrjRolePermission> listRolePermission = this.dao.getRolePermissionByRoleId(role_id);
					if(listRolePermission.size()>0 && (null!=role_id && !"".equals(role_id))){
						
						for(WkrjRolePermission rolePerm :listRolePermission){
							
							if(perm.getPerm_id().equals(rolePerm.getPerm_id())){
								permissionMap.put("ischecked", true);
							}
						}
						
					}
					
					listPermissionMap.add(permissionMap);
					
				}
				
				map.put("children", listPermissionMap);
			}
			
			
			//不是叶子节点
			if(this.menuDao.getAllMenuChildCount(menu.getMenu_id())>0){
				//map.put("state", "closed");
				
				if(allRoleMenu.size()>0 && (null!=role_id && !"".equals(role_id))){
					
					for(WkrjRoleMenu roleMenu : allRoleMenu){
						//角色中有的模块则被选中
						if(roleMenu.getMenu_id().equals(menu.getMenu_id())){
							map.put("ischecked", true);
						}
					}
					
				}
				
				map.put("children", this.getMenuPermission(role_id,menu.getMenu_id()));
			}else{
				
				if(allRoleMenu.size()>0 && (null!=role_id && !"".equals(role_id))){
					
					for(WkrjRoleMenu roleMenu : allRoleMenu){
						//角色中有的模块则被选中
						if(roleMenu.getMenu_id().equals(menu.getMenu_id())){
							map.put("ischecked", true);
						}
					}
					
				}
				
			}
			
			all.add(map);
		}
		
		return all;
		
	}

	@Override
	public boolean setMenuPermission(String menulist, String permissionlist,
			String roler_id,String menu_order) {
		
		boolean sus=false;
		
		//首先删除原先的菜单和权限，以免冲突
		this.dao.delRoleMenu(roler_id);
		this.dao.delRolePermission(roler_id);
		
		//保存菜单
		if(!"".equals(menulist)){
			
			for(int i=0;i<menulist.split(",").length;i++){
				
				WkrjRoleMenu roleMenu = new WkrjRoleMenu();
				roleMenu.setMenu_id(menulist.split(",")[i]);
				roleMenu.setRole_id(roler_id);
				roleMenu.setMenu_order(menu_order.split(",")[i]);
				sus = this.dao.saveRoleMenu(roleMenu);
				if(sus==false){
					return false;
				}
			}
			
		}else{
			sus=true;
		}
		
		//保存权限
		if(!"".equals(permissionlist)){
			
			for(int i=0;i<permissionlist.split(",").length;i++){
				
				WkrjRolePermission rolePerm = new WkrjRolePermission();
				rolePerm.setPerm_id(permissionlist.split(",")[i]);
				rolePerm.setRole_id(roler_id);
				
				sus = this.dao.saveRolePermission(rolePerm);
				if(sus==false){
					return false;
				}
			}
			
		}else{
			sus=true;
		}
		
		return sus;
	}

	@Override
	public boolean copyRole(String role_id) {
		
		//首先复制角色的列表然后再复制角色的权限
		WkrjRole role = this.dao.getRoleById(role_id);
		String id = Guid.getGuid();
		role.setRole_id(id);
		
		boolean sus=true;
		
		if(this.dao.addRole(role)){
			
			//添加菜单
			List<WkrjRoleMenu> roleMenu = dao.getRoleMenuByRoleId(role_id);
			if(roleMenu.size()>0){
				for(WkrjRoleMenu rm:roleMenu){
					rm.setRole_id(id);
					if(!this.dao.saveRoleMenu(rm)){
						sus=false;
						return sus;
					}
				}
			}
			
			//添加权限
			List<WkrjRolePermission> rolePerm = dao.getRolePermissionByRoleId(role_id);
			if(rolePerm.size()>0){
				for(WkrjRolePermission rp:rolePerm){
					rp.setRole_id(id);
					if(!this.dao.saveRolePermission(rp)){
						sus=false;
						return sus;
					}
				}
			}
			
			
		}else{
			sus=false;
		}
		
		return sus;
	}

	@Override
	public List<WkrjRole> getAllRoleList() {
		return this.dao.getAllRoleList();
	}

	@Override
	public List<WkrjRoleMenu> getRoleMenuByRoleId(String roleId) {
		return this.dao.getRoleMenuByRoleId(roleId);
	}

	@Override
	public String getRolePermissionByRoleId(String role_id) {
		
		String permStr="";
		
		if(null!=role_id && !"".equals(role_id)){
			
			for(int i=0;i<role_id.split(",").length;i++){
				//通过角色id获取对应的权限
				List<WkrjRolePermission> listRolePermission = this.dao.getRolePermissionByRoleId(role_id.split(",")[i]);
				if(listRolePermission.size()>0){
					
					for(WkrjRolePermission rolePerm :listRolePermission){
						
						List<WkrjPermission> perms = permDao.getPermissionByPermId(rolePerm.getPerm_id());
						if(perms.size()>0){
							
							for(WkrjPermission perm :perms){
								permStr += perm.getPerm_flag();
								permStr +=",";
							}
							
						}
					}
				}
			}
		}
		
		if(!"".equals(permStr)){
			permStr = permStr.substring(0, permStr.length()-1);
		}
		
		return permStr;
	}
	
	@Override
	public String getAllPermission() {
		
		String permStr="";
		
		
		//通过角色id获取对应的权限
		List<WkrjPermission> listRolePermission = this.dao.getAllPermission();
		if(listRolePermission.size()>0){
			
			for(WkrjPermission perm :listRolePermission){
				
					permStr += perm.getPerm_flag();
					permStr +=",";
					
			}
		}
		
		if(!"".equals(permStr)){
			permStr = permStr.substring(0, permStr.length()-1);
		}
		
		return permStr;
	}
	
	
}
