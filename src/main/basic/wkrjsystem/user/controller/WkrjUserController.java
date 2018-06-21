package wkrjsystem.user.controller;

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
import wkrjsystem.station.bean.WkrjStation;
import wkrjsystem.station.service.WkrjStationService;
import wkrjsystem.user.bean.WkrjUser;
import wkrjsystem.user.service.WkrjUserService;
import wkrjsystem.utils.AjaxJson;
import wkrjsystem.utils.SpringContextUtil;
import wkrjsystem.utils.UtilsHelper;
import wkrjsystemdev.userdev.bean.WkrjUserDev;
import wkrjsystemdev.userdev.service.WkrjUserDevService;

@Controller
@RequestMapping("wkrjsystem/wkrjUser")
public class WkrjUserController {
	
	@Autowired
	private WkrjUserService userService;
	
	@Autowired
	private WkrjUserDevService userdevService;
	
	@Autowired
	private WkrjDeptService deptService;
	
	@Autowired
	private WkrjStationService stationService;
	
	
	@RequestMapping("getPage")
	public String getPage(){
		return "system/user/user";
	}
	
	@RequestMapping("getUserList")
	@ResponseBody
	public Object getUserList(HttpServletRequest request, String deptId){
		int page=Integer.parseInt(request.getParameter("page"));
		String userName = request.getParameter("userName");
		int pagesize=Integer.parseInt(request.getParameter("pagesize"));
		int offset = (page-1)*pagesize;
		
		WkrjUser sessionUser = (WkrjUser) request.getSession().getAttribute("user");
		WkrjUserDev userDev = (WkrjUserDev) request.getSession().getAttribute("userDev");
		boolean isGly = false;
		//String user_id = "";
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
		if (sessionUser != null) {
			//user_id = sessionUser.getUser_id();
			user_dept = sessionUser.getDept_id();
		} else if (userDev != null) {
			//user_id = userDev.getUser_id();
			user_dept = userDev.getDept_id();
		}
		 
		
		List<WkrjUser> list = this.userService.getUserList(offset, pagesize, deptId, userName, isGly, user_dept);
		for(WkrjUser user:list){
			//获取角色信息
			user.setUser_role(this.userService.getRoleListByUserId(user.getUser_id()));
		}
		long count = this.userService.getUserList(deptId, userName, isGly, user_dept);
		
		return UtilsHelper.returnMap(list,count);
	}
	
	
	
	@RequestMapping("getRoleTree")
	@ResponseBody
	public Object getRoleTree(HttpServletRequest request, String role_level){
		Map<String,Object> map = new HashMap<String, Object>();
		WkrjUser sessionUser = (WkrjUser) request.getSession().getAttribute("user");
		WkrjUserDev userDev = (WkrjUserDev) request.getSession().getAttribute("userDev");
		String user_dept = "";
		if (sessionUser != null) {
			user_dept = sessionUser.getDept_id();
		} else if (userDev != null) {
			user_dept = userDev.getDept_id();
		}
		//如果部门id为四位，限定市级角色
		if (user_dept.length() == 4) {
			role_level = "1";
		}
		//如果部门id为六位，限定县级角色
		if (user_dept.length() == 6) {
			role_level = "2";
		}
		//如果部门id为八位，限定乡镇角色
		if (user_dept.length() == 8) {
			role_level = "3";
		}
		try {
			List<Map<String,Object>> list = userService.getRoleList(role_level);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping("getStationTree")
	@ResponseBody
	public Object getStationTree(){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			List<Map<String,Object>> list = userdevService.getStationList();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 * 添加用户注意事项
	 * 1、用户名不能重复
	 * 2、身份证不能重复
	 * 3、工号不能重复
	 * @param perm
	 * @return
	 */
	@RequestMapping("addUser")
	@ResponseBody
	public AjaxJson addUser(WkrjUser perm,HttpServletRequest request){
		
		AjaxJson json = new AjaxJson();
		
		WkrjUser user = (WkrjUser) request.getSession().getAttribute("user");
		WkrjUserDev userDev = (WkrjUserDev) request.getSession().getAttribute("userDev");
		
		String user_id = "";
		String user_dept = "";
		
		if (user != null) {
			user_id = user.getUser_id();
			user_dept = user.getDept_id();
		} else if (userDev != null) {
			user_id = userDev.getUser_id();
			user_dept = userDev.getDept_id();
		}
		
		if(!"".equals(user_id)){
			perm.setInputuserdept(user_dept);
		}
		
		String res = userService.addUser(perm);
		
		if("0".equals(res)){
			json.setSuccess(true);
			json.setMsg("保存成功");
		}else if("1".equals(res)){
			json.setMsg("用户名已经存在");
		}else if("2".equals(res)){
			json.setMsg("身份证已经存在");
		}else if("3".equals(res)){
			json.setMsg("工号已经存在");
		}
		
		return json;
	}
	
	@RequestMapping("updateUser")
	@ResponseBody
	public AjaxJson updateUser(WkrjUser module){
		
		AjaxJson json = new AjaxJson();
		String res = userService.updateUser(module);
		
		if("0".equals(res)){
			json.setSuccess(true);
			json.setMsg("修改成功");
		}else if("1".equals(res)){
			json.setMsg("用户名已经存在");
		}else if("2".equals(res)){
			json.setMsg("身份证已经存在");
		}else if("3".equals(res)){
			json.setMsg("工号已经存在");
		}
		
		return json;
	}
	
	@RequestMapping("forbiddenUser")
	@ResponseBody
	public AjaxJson forbiddenUser(String  id,String flag){
		
		AjaxJson json = new AjaxJson();
		
		if(userService.forbiddenUser(id,flag)){
			json.setSuccess(true);
			json.setMsg("操作成功");
		}
		
		return json;
	}
	
	/**
	 * 删除角色，删除前需要先删除绑定的菜单和权限
	 * @param id
	 * @return
	 */
	@RequestMapping("delUser")
	@ResponseBody
	public AjaxJson delUser(String id){
		
		AjaxJson json = new AjaxJson();
		
		if(null!=id && !"".equals(id)){
			
			String[] id_ = id.split(",");
			
			for(int i=0;i<id_.length;i++){
				
				if(userService.delUser(id_[i])){
					json.setSuccess(true);
					json.setMsg("删除成功");
				}
			}
		}
		
		return json;
	}
	
	/**
	 * 获取部门名称通过id
	 * @param deptId
	 * @return
	 */
	@RequestMapping("getDeptNameById")
	@ResponseBody
	public AjaxJson getDeptNameById(String deptId){
		
		AjaxJson json = new AjaxJson();
		WkrjDept dept = deptService.getDeptNameById(deptId);
		
		json.setObj(dept);
		json.setSuccess(true);
		json.setMsg("返回成功");
		
		return json;
	}
	
	/**
	 * 获取岗位名称通过id
	 * @param stationId
	 * @return
	 */
	@RequestMapping("getStationById")
	@ResponseBody
	public AjaxJson getStationById(String stationId){
		
		AjaxJson json = new AjaxJson();
		WkrjStation station = stationService.getStationById(stationId);
		
		json.setObj(station);
		json.setSuccess(true);
		json.setMsg("返回成功");
		
		return json;
	}
		
	
	/**
	 * 重置密码123
	 * @param id
	 * @return
	 */
	@RequestMapping("repeatPw")
	@ResponseBody
	public AjaxJson repeatPw(String id){
	
		AjaxJson json = new AjaxJson();
		
		if(this.userService.repeatPw(id, "123")){
			json.setSuccess(true);
			json.setMsg("重置成功");
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
		
		deptService = (WkrjDeptService) SpringContextUtil.getBean("wkrjDeptService");
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
		return deptService.getRoleList("-1",isGly,user_dept);
	}
	
	/**
	 * 获取所有的岗位
	 * @return
	 */
	@RequestMapping("getAllStationList")
	@ResponseBody
	public Object getAllStationList(){
		return stationService.getAllStationList();
	}
}
