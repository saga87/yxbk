package wkrjsystem.role.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wkrjsystem.role.bean.WkrjRole;
import wkrjsystem.role.service.WkrjRoleService;
import wkrjsystem.utils.AjaxJson;
import wkrjsystem.utils.Guid;
import wkrjsystem.utils.UtilsHelper;

@Controller
@RequestMapping("wkrjsystem/wkrjRole")
public class WkrjRoleController {
	
	@Autowired
	private WkrjRoleService roleService;
	
	
	@RequestMapping("getPage")
	public String getPage(){
		
		return "system/role/role";
	}
	
	@RequestMapping("getRoleList")
	@ResponseBody
	public Object getRoleList(int pagesize, int page,String dept_id){
		
		int offset = (page-1)*pagesize;
		
		List<WkrjRole> list = this.roleService.getRoleList(offset,pagesize, dept_id);
		long count = this.roleService.getRoleList(dept_id);
		
		return UtilsHelper.returnMap(list,count);
	}
	
	/**
	 * 获取所有的角色
	 * @return
	 */
	@RequestMapping("getAllRoleList")
	@ResponseBody
	public Object getAllRoleList(){
		return this.roleService.getAllRoleList();
	}
	
	@RequestMapping("addRole")
	@ResponseBody
	public AjaxJson addRole(WkrjRole perm){
		
		AjaxJson json = new AjaxJson();
		
		perm.setRole_id(Guid.getGuid());
		perm.setRole_type("2");
		
		if(roleService.addRole(perm)){
			json.setSuccess(true);
			json.setMsg("保存成功");
		}
		
		return json;
	}
	
	@RequestMapping("updateRole")
	@ResponseBody
	public AjaxJson updateRole(WkrjRole module){
		
		AjaxJson json = new AjaxJson();
		
		module.setRole_type("2");
		if(roleService.updateRole(module)){
			json.setSuccess(true);
			json.setMsg("修改成功");
		}
		
		return json;
	}
	
	/**
	 * 删除角色，删除前需要先删除绑定的菜单和权限
	 * @param id
	 * @return
	 */
	@RequestMapping("delRole")
	@ResponseBody
	public AjaxJson delRole(String id){
		
		AjaxJson json = new AjaxJson();
		
		if(null!=id && !"".equals(id)){
			
			String[] id_ = id.split(",");
			
			for(int i=0;i<id_.length;i++){
				
				if(roleService.delRole(id_[i])){
					json.setSuccess(true);
					json.setMsg("删除成功");
				}
			}
		}
		
		return json;
	}
	
	/**
	 * 获取所有的菜单
	 * @return
	 */
	@RequestMapping("getMenuPermission")
	@ResponseBody
	public Object getMenuPermission(String role_id,String parentId){
		
		if(null==parentId || "".equals(parentId)){
			parentId="-1";
		}
		
		return roleService.getMenuPermission(role_id,parentId);
	}
	
	/**
	 * 保存权限
	 * @param menulist
	 * @param permissionlist
	 * @param rolerId
	 * @return
	 */
	@RequestMapping("setMenuPermission")
	@ResponseBody
    public AjaxJson setMenuPermission(String menulist,String permissionlist,String rolerId,String menu_order){
		
		AjaxJson json = new AjaxJson();
		json.setMsg("保存失败");
		
		if (roleService.setMenuPermission(menulist,permissionlist,rolerId,menu_order)) {
			
			json.setMsg("保存成功");
			json.setSuccess(true);
			
		}
	   return json;
   }
	
	@RequestMapping("copyRole")
	@ResponseBody
	public AjaxJson copyRole(String role_id){
		
		AjaxJson json = new AjaxJson();
		json.setMsg("复制失败");
		
		if (roleService.copyRole(role_id)) {
			
			json.setMsg("复制成功");
			json.setSuccess(true);
			
		}
		
		return json;
	}
	
}
