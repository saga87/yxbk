package wkrjsystemdev.module.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import wkrjsystem.utils.AjaxJson;
import wkrjsystem.utils.Guid;
import wkrjsystemdev.module.bean.WkrjModule;
import wkrjsystemdev.module.service.WkrjModuleService;
import wkrjsystemdev.roledev.bean.WkrjRoleDev;
import wkrjsystemdev.userdev.bean.WkrjUserDev;

@Controller
@RequestMapping("wkrjsystemdev/wkrjModule")
public class WkrjModuleController {
	
	@Autowired
	private WkrjModuleService moduleService;
	
	@RequestMapping("getPage")
	public String getPage(){
		return "systemdev/module/module";
	}
	
	@RequestMapping("getAddPage")
	public String getAddPage(){
		
		return "system/module/module_add";
	}
	
	/**
	 * 获取左侧菜单的列表
	 * @param request
	 * @return
	 */
	@RequestMapping("getGridInfo")
	@ResponseBody
	public Object getGridInfo(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			List<Map<String,Object>> list = moduleService.getGridInfo("-1");
			
			map.put("Rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
		
	}

	
	/**
	 * 获取左侧菜单的列表
	 * @param request
	 * @return
	 */
	@RequestMapping("getGridInfoNew")
	@ResponseBody
	public Object getGridInfoNew(HttpServletRequest request) {
		try {
			List<Map<String,Object>> list = moduleService.getGridInfo("-1");
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 获取左侧菜单的列表 开发人员用的
	 * @param request
	 * @return
	 */
	@RequestMapping("getLeftMenu.wk")
	@ResponseBody
	public Object getLeftMenu(HttpServletRequest request) {
		
		WkrjUserDev user = (WkrjUserDev) request.getSession().getAttribute("userDev");
		
		List<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
		if(1==user.getUser_accounttype()){
			l = moduleService.getLeftMenu("-1");
		}else{
			String roleIds = "";
			
			List<WkrjRoleDev> user_role = user.getUser_role();
			if(user_role.size()>0){
				
				for(WkrjRoleDev role:user_role){
					
					roleIds += role.getRole_id();
					roleIds +=","; 
				}
				
				if(null!=roleIds && !"".equals(roleIds)){
					
					l = moduleService.getLeftMenuByRole("-1",roleIds);
				}
			}
		}
		
		return l;
		
	}
	
	@RequestMapping("addModule")
	@ResponseBody
	public AjaxJson addModule(WkrjModule module){
		
		AjaxJson json = new AjaxJson();
		
		module.setModule_id(Guid.getGuid());
		if(0==module.isModule_level() && (null==module.getModule_parent_id() || "".equals(module.getModule_parent_id()))){
			module.setModule_parent_id("-1");
		}
		
		if(moduleService.addModule(module)){
			json.setSuccess(true);
			json.setMsg("保存成功");
		}
		
		return json;
	}
	
	@RequestMapping("updateModule")
	@ResponseBody
	public AjaxJson updateModule(WkrjModule module,String yFileName){
		
		AjaxJson json = new AjaxJson();
		
		if(moduleService.updateModule(module,yFileName)){
			json.setSuccess(true);
			json.setMsg("修改成功");
		}
		
		return json;
	}
	
	@RequestMapping("delModule")
	@ResponseBody
	public AjaxJson delModule(String id,String icon){
		
		AjaxJson json = new AjaxJson();
		
		int count = this.moduleService.checkIsHaveChildren(id);
		if(count>0){
			json.setSuccess(false);
			json.setMsg("请先删除子节点");
		}else{
			try{
				if(moduleService.delModule(id,icon)){
					json.setSuccess(true);
					json.setMsg("删除成功");
				}
			}catch(Exception e){
				if(e.getStackTrace()[0].getClassName().indexOf("SQLErrorCodeSQLExceptionTranslator")>=0){
					json.setMsg("删除失败,已被其他模块使用");
				}else{
					e.printStackTrace();
				}
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
	public String uploadPic(MultipartFile module_icons,HttpServletRequest request,String foledArrress){
		return this.moduleService.uploadPic(module_icons, request, foledArrress);
	} 
	
}
