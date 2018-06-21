package wkrjsystemdev.roledev.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wkrjsystem.department.service.WkrjDeptService;
import wkrjsystem.user.bean.WkrjUser;
import wkrjsystem.utils.AjaxJson;
import wkrjsystem.utils.Guid;
import wkrjsystem.utils.SpringContextUtil;
import wkrjsystem.utils.UtilsHelper;
import wkrjsystemdev.permission.bean.WkrjPermission;
import wkrjsystemdev.roledev.bean.WkrjRoleDev;
import wkrjsystemdev.roledev.service.WkrjRoleDevService;
import wkrjsystemdev.userdev.bean.WkrjUserDev;

@Controller
@RequestMapping("wkrjsystemdev/wkrjRoleDev")
public class WkrjRoleDevController {
	
	@Autowired
	private WkrjRoleDevService roleDevService;
	
	@Autowired
	private WkrjDeptService deptService;
	
	
	@RequestMapping("getPage")
	public String getPage(){
		
		return "systemdev/role/roledev";
	}
	
//	@RequestMapping("getRoleList")
//	@ResponseBody
//	public Object getRoleList(int rows, int page,String dept_id){
//		
//		int offset = (page-1)*rows;
//		
//		List<WkrjRoleDev> list = this.roleDevService.getRoleList(offset,rows, dept_id);
//		long count = this.roleDevService.getRoleList(dept_id);
//		
//		return UtilsHelper.returnMap(list,count);
//	}
	@RequestMapping("getRoleList")
	@ResponseBody
	public Object getRoleList(HttpServletRequest request, String dept_id){
		int page=Integer.parseInt(request.getParameter("page"));

		int pagesize=Integer.parseInt(request.getParameter("pagesize"));
		int offset = (page-1)*pagesize;
		List<WkrjRoleDev> list = this.roleDevService.getRoleList(offset, pagesize, dept_id);
		long count = this.roleDevService.getRoleList(dept_id);
		
		return UtilsHelper.returnMap(list,count);
	}
	
	/**
	 * 获取所有的角色
	 * @return
	 */
	@RequestMapping("getAllRoleList")
	@ResponseBody
	public Object getAllRoleList(){
		return this.roleDevService.getAllRoleList();
	}
	
	@RequestMapping("addRole")
	@ResponseBody
	public AjaxJson addRole(WkrjRoleDev perm){
		
		AjaxJson json = new AjaxJson();
		
		perm.setRole_id(Guid.getGuid());
		perm.setRole_type("2");
		
		if(roleDevService.addRole(perm)){
			json.setSuccess(true);
			json.setMsg("保存成功");
		}
		
		return json;
	}
	
	@RequestMapping("updateRole")
	@ResponseBody
	public AjaxJson updateRole(WkrjRoleDev module){
		
		AjaxJson json = new AjaxJson();
		
		module.setRole_type("2");
		if(roleDevService.updateRole(module)){
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
				
				if(roleDevService.delRole(id_[i])){
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
		
		return roleDevService.getMenuPermission(role_id,parentId);
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
		
		if (roleDevService.setMenuPermission(menulist,permissionlist,rolerId,menu_order)) {
			
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
		
		if (roleDevService.copyRole(role_id)) {
			
			json.setMsg("复制成功");
			json.setSuccess(true);
			
		}
		
		return json;
	}
	
	/**
	 * 获取部门树
	 * @return
	 */
	@RequestMapping("getDeptTree")
	@ResponseBody
	public Object getDeptTree(HttpServletRequest request){
		
		WkrjUser user = (WkrjUser) request.getSession().getAttribute("user");
		WkrjUserDev userDev = (WkrjUserDev) request.getSession().getAttribute("userDev");
		boolean isGly = false;
		String user_dept = "";
		String roleIds = request.getSession().getAttribute("userRoleId")+"";
		String[] role_ids = roleIds.split(",");
		for (int i = 0; i < role_ids.length; i++) {
			//如果是系统管理员
			if ("2".equals(role_ids[i])) {
				isGly = true;
				break;
			}
		}
		if (user != null) {
			user_dept = user.getDept_id();
		}
		if (userDev != null) {
			user_dept = userDev.getDept_id();
		}
		deptService = (WkrjDeptService) SpringContextUtil.getBean("wkrjDeptService");
		return deptService.getRoleList("-1",isGly,user_dept);
	}
	
}
