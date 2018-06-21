package wkrjsystem.department.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wkrjsystem.department.bean.WkrjDept;
import wkrjsystem.department.service.WkrjDeptService;
import wkrjsystem.user.bean.WkrjUser;
import wkrjsystem.utils.AjaxJson;
import wkrjsystem.utils.Guid;
import wkrjsystem.utils.UtilsHelper;
import wkrjsystemdev.userdev.bean.WkrjUserDev;

@Controller
@RequestMapping("wkrjsystem/wkrjDept")
public class WkrjDeptController {
	
	@Autowired
	private WkrjDeptService deptService;
	
	
	@RequestMapping("getPage")
	public String getPage(){
		
		return "system/dept/dept";
	}
	
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
		} else if (userDev != null) {
			user_dept = userDev.getDept_id();
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			List<Map<String,Object>> list = deptService.getRoleList("-1",isGly,user_dept);
			
			return list;
			//map.put("Rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("getDeptAndSchoolGridList")
	@ResponseBody
	public Object getDeptAndSchoolGridList(HttpServletRequest request){
		
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
		} else if (userDev != null) {
			user_dept = userDev.getDept_id();
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			List<Map<String,Object>> list = deptService.getDeptAndSchoolGridList("-1",isGly,user_dept);
			map.put("Rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("getDeptGridList")
	@ResponseBody
	public Object getDeptGridList(HttpServletRequest request){
		
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
		} else if (userDev != null) {
			user_dept = userDev.getDept_id();
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			List<Map<String,Object>> list = deptService.getRoleList("-1",isGly,user_dept);
			map.put("Rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
		
	@RequestMapping("addDept")
	@ResponseBody
	public AjaxJson addDept(WkrjDept dept){
		
		AjaxJson json = new AjaxJson();
		
		if(deptService.addDept(dept)){
			json.setSuccess(true);
			json.setMsg("保存成功");
		}
		
		return json;
	}
	
	@RequestMapping("updateDept")
	@ResponseBody
	public AjaxJson updateDept(WkrjDept dept,String yId){
		
		AjaxJson json = new AjaxJson();
		
		if(deptService.updateDept(dept,yId)){
			json.setSuccess(true);
			json.setMsg("修改成功");
		}
		
		return json;
	}
	
	@RequestMapping("delDept")
	@ResponseBody
	public AjaxJson delDept(String id){
		
		AjaxJson json = new AjaxJson();
		
		//首先判断是否还有孩子
		if(deptService.getDeptChildCount(id)<=0){
			if(deptService.delDept(id)){
				json.setSuccess(true);
				json.setMsg("删除成功");
			}
		}else{
			json.setMsg("请先删除子节点");
		}
		
		return json;
	}	
}
