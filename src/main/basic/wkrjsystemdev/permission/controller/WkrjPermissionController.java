package wkrjsystemdev.permission.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import wkrjsystem.utils.AjaxJson;
import wkrjsystem.utils.Guid;
import wkrjsystem.utils.UtilsHelper;
import wkrjsystemdev.module.service.WkrjModuleService;
import wkrjsystemdev.permission.bean.WkrjPermission;
import wkrjsystemdev.permission.service.WkrjPermissionService;

@Controller
@RequestMapping("wkrjsystemdev/wkrjPermission")
public class WkrjPermissionController {

	@Autowired
	private WkrjPermissionService service;
	
	@Autowired
	private WkrjModuleService moduleService;
	
	@RequestMapping("getPermissionList")
	@ResponseBody
	public Object getPermissionList(String module_id, HttpServletRequest request){
		int page=Integer.parseInt(request.getParameter("page"));
		int pagesize=Integer.parseInt(request.getParameter("pagesize"));
		int offset = (page-1)*pagesize;
		List<WkrjPermission> list = this.service.getPermissionList(offset, pagesize, module_id);
		long count = this.service.getPermissionListCount(module_id);
		
		return UtilsHelper.returnMap(list,count);
	}
	
	
	@RequestMapping("addPermission")
	@ResponseBody
	public AjaxJson addPermission(WkrjPermission perm){
		
		AjaxJson json = new AjaxJson();
		
		String id = Guid.getGuid();
		perm.setPerm_id(id);
		if(!service.checkIsHavePermission(perm.getPerm_flag(),null)){
			
			if(service.addPermission(perm)){
				json.setSuccess(true);
				json.setMsg("保存成功");
			}
		}else{
			json.setSuccess(false);
			json.setMsg("该权限已存在");
		}
		
		return json;
	}
	
	@RequestMapping("updatePermission")
	@ResponseBody
	public AjaxJson updatePermission(WkrjPermission module){
		
		AjaxJson json = new AjaxJson();
		
		if(!service.checkIsHavePermission(module.getPerm_flag(),module.getPerm_id())){
		
			if(service.updatePermission(module)){
				json.setSuccess(true);
				json.setMsg("修改成功");
			}
		}else{
			json.setSuccess(false);
			json.setMsg("该权限已存在");
		}
		return json;
	}
	
	@RequestMapping("delPermission")
	@ResponseBody
	public AjaxJson delPermission(String id,String icon){
		
		AjaxJson json = new AjaxJson();
		
		if(null!=id && !"".equals(id)){
			
			String[] id_ = id.split(",");
			String[] icon_ = icon.split(",");
			
			for(int i=0;i<id_.length;i++){
				
				if(service.delPermission(id_[i],icon_[i])){
					json.setSuccess(true);
					json.setMsg("删除成功");
				}
			}
		}
		
		return json;
	}
	
	/**
	 * 
	 * @param module_icons 上传文件的name名称
	 * @param request
	 * @param foledArrress 保存文件的路径
	 * @return
	 */
	@RequestMapping("/uploadPic")
	@ResponseBody
	public String uploadPic(MultipartFile module_icons,HttpServletRequest request,String foledArrress){
		return this.moduleService.uploadPic(module_icons, request, foledArrress);
	} 
	

}
