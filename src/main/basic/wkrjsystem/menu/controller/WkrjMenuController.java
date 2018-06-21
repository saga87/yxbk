package wkrjsystem.menu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import wkrjsystem.menu.service.WkrjMenuService;
import wkrjsystem.role.bean.WkrjRole;
import wkrjsystem.user.bean.WkrjUser;
import wkrjsystem.utils.AjaxJson;
import wkrjsystemdev.module.bean.WkrjModule;
import wkrjsystemdev.module.service.WkrjModuleService;

@Controller
@RequestMapping("wkrjsystem/wkrjMenu")
public class WkrjMenuController {
	
	@Autowired
	private WkrjMenuService menuService;
	
	@Autowired
	private WkrjModuleService moduleService;
	
	@RequestMapping("getPage")
	public String getPage(){
		
		return "system/menu/menu";
	}
	
	@RequestMapping("getPage.wk")
	public String getPage_wk(){
		
		return "system/menu/menu";
	}
	
	/**
	 * 获取右侧模块菜单的列表
	 * @param request
	 * @return
	 */
	@RequestMapping("getGridInfo")
	@ResponseBody
	public Object getGridInfo(HttpServletRequest request) {
		
		List<Map<String,Object>> list = moduleService.getDisplayGridInfo("-1");
		
		return list;
		
	}
	
	/**
	 * 获取左侧菜单的列表
	 * @param request
	 * @return
	 */
	@RequestMapping("getTreeInfo")
	@ResponseBody
	public Object getTreeInfo(HttpServletRequest request) {
		
		List<Map<String,Object>> list = menuService.getGridInfo("-1");
		
		return list;
		
	}
	
	@RequestMapping("addMenu")
	@ResponseBody
	public AjaxJson addMenu(WkrjModule module,String module_view){
		
		AjaxJson json = new AjaxJson();
		if("".equals(module.getMenu().get(0).getMenu_parent_id())){
			module.getMenu().get(0).setMenu_parent_id("-1");
		}
		
		module.setModule_url(module_view);
		if(menuService.addMenu(module)){
			json.setSuccess(true);
			json.setMsg("添加成功");
		}
		
		return json;
	}
	
	@RequestMapping("updateMenu")
	@ResponseBody
	public AjaxJson updateModule(WkrjModule module,String yFileName,String module_view){
		
		AjaxJson json = new AjaxJson();
		
		module.setModule_url(module_view);
		if(menuService.updateMenu(module, yFileName)){
			json.setSuccess(true);
			json.setMsg("修改成功");
		}
		
		return json;
	}
	
	@RequestMapping("delMenu")
	@ResponseBody
	public AjaxJson delMenu(String id,String icon){
		
		AjaxJson json = new AjaxJson();
		
		int count = this.menuService.checkIsHaveChildren(id);
		if(count>0){
			json.setSuccess(false);
			json.setMsg("请先删除子节点");
		}else{
			
			if(menuService.delMenu(id,icon)){
				json.setSuccess(true);
				json.setMsg("删除成功");
			}
		}
		
		return json;
	}
	
	
	
	/**
	 * 
	 * @param module_icons 上传文件的name名称
	 * @param request
	 * @param foledArrress 保存文件的路径，
	 * @return
	 */
	@RequestMapping("/uploadPic")
	@ResponseBody
	public String uploadPic(MultipartFile menu_icons,HttpServletRequest request,String foledArrress){
		return this.moduleService.uploadPic(menu_icons, request, foledArrress);
	} 
	
	/**
	 * 获取左侧菜单的列表 真正客户
	 * 通过角色来获取角色对应的菜单
	 * @param request
	 * @return
	 */
	@RequestMapping("getLeftMenu")
	@ResponseBody
	public Object getLeftMenu(HttpServletRequest request) {
		
		WkrjUser user = (WkrjUser) request.getSession().getAttribute("user");
		List<Map<String,Object>> all= new ArrayList<Map<String,Object>>();
		
		
		if(null!=user){
			
			if(1==user.getUser_accounttype()){
				all = menuService.getGridInfo("-1");
			}else{
			
				String roleIds = "";
				
				List<WkrjRole> user_role = user.getUser_role();
				if(user_role.size()>0){
					
					for(WkrjRole role:user_role){
						
						roleIds += role.getRole_id();
						roleIds +=","; 
					}
					
					if(null!=roleIds && !"".equals(roleIds)){
						
						all = menuService.getLeftMenuByRole("-1",roleIds);
					}
				}
			}
			
		}
		
		return all;
		
	}
	
}
