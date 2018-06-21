package wkrjsystem.school.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wkrjsystem.school.bean.WkrjSchool;
import wkrjsystem.school.service.WkrjSchoolService;
import wkrjsystem.utils.AjaxJson;
import wkrjsystem.utils.Guid;
import wkrjsystem.utils.UtilsHelper;

@Controller
@RequestMapping("wkrjsystem/wkrjSchool")
public class WkrjSchoolController {
	
	@Autowired
	private WkrjSchoolService schoolService;
	
	@RequestMapping("getPage")
	public String getPage(){
		
		return "system/school/school";
	}
	
	@RequestMapping("getAddPage")
	public String getAddPage(){
		
		return "system/school/school_add";
	}
	
	@RequestMapping("getSchoolList")
	@ResponseBody
	public Object getSchoolList(HttpServletRequest request){
		int page=Integer.parseInt(request.getParameter("page"));

		int pagesize=Integer.parseInt(request.getParameter("pagesize"));
		int offset = (page-1)*pagesize;
		List<WkrjSchool> list = this.schoolService.getSchoolList(offset, pagesize);
		long count = this.schoolService.getSchoolList();
		
		return UtilsHelper.returnMap(list,count);
	}
	
	/**
	 * 获取所有的
	 * @return
	 */
	@RequestMapping("getAllSchoolList")
	@ResponseBody
	public Object getAllSchoolList(String key){
		return this.schoolService.getAllSchoolList(key);
	}
	
	@RequestMapping("addSchool")
	@ResponseBody
	public AjaxJson addSchool(WkrjSchool perm){
		
		AjaxJson json = new AjaxJson();
		
		perm.setSchool_id(Guid.getGuid());
		
		if(schoolService.addSchool(perm)){
			json.setSuccess(true);
			json.setMsg("保存成功");
		}
		
		return json;
	}
	
	@RequestMapping("updateSchool")
	@ResponseBody
	public AjaxJson updateSchool(WkrjSchool module){
		
		AjaxJson json = new AjaxJson();
		
		if(schoolService.updateSchool(module)){
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
	@RequestMapping("delSchool")
	@ResponseBody
	public AjaxJson delSchool(String id){
		
		AjaxJson json = new AjaxJson();
		
		if(null!=id && !"".equals(id)){
			
			String[] id_ = id.split(",");
			
			for(int i=0;i<id_.length;i++){
				
				if(schoolService.delSchool(id_[i])){
					json.setSuccess(true);
					json.setMsg("删除成功");
				}
			}
		}
		
		return json;
	}
	
	
}