package wkrjsystemdev.userdev.controller;

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
import wkrjsystem.utils.AjaxJson;
import wkrjsystem.utils.SpringContextUtil;
import wkrjsystem.utils.UtilsHelper;
import wkrjsystemdev.userdev.bean.WkrjUserDev;
import wkrjsystemdev.userdev.service.WkrjUserDevService;

@Controller
@RequestMapping("wkrjsystemdev/wkrjUserDev")
public class WkrjUserDevController {
	
	@Autowired
	private WkrjUserDevService userService;
	
	@Autowired
	private WkrjDeptService deptService;
	
	@Autowired
	private WkrjStationService stationService;
	
	@RequestMapping("getPage")
	public String getPage(){
		return "systemdev/user/userdev";
	}
	
	@RequestMapping("getUserList")
	@ResponseBody
//	public Object getUserList(int rows, int page,String deptId,HttpServletRequest req){
//		
//		String userName = req.getParameter("userName");
//		
//		int offset = (page-1)*rows;
//		
//		List<WkrjUserDev> list = this.userService.getUserList(offset,rows,deptId,userName);
//		long count = this.userService.getUserList(deptId,userName);
//		
//		return UtilsHelper.returnMap(list,count);
//	}
	public Object getUserList(HttpServletRequest request, String deptId){
		int page=Integer.parseInt(request.getParameter("page"));
		String userName = request.getParameter("userName");
		int pagesize=Integer.parseInt(request.getParameter("pagesize"));
		int offset = (page-1)*pagesize;
		List<WkrjUserDev> list = this.userService.getUserList(offset, pagesize, deptId, userName);
		long count = this.userService.getUserList(deptId, userName);
		
		return UtilsHelper.returnMap(list,count);
	}
	
	@RequestMapping("getRoleTree")
	@ResponseBody
	public Object getRoleTree(){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			List<Map<String,Object>> list = userService.getRoleList();
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
			List<Map<String,Object>> list = userService.getStationList();
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
	public AjaxJson addUser(WkrjUserDev perm){
		
		AjaxJson json = new AjaxJson();
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
	public AjaxJson updateUser(WkrjUserDev module){
		
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
		
		deptService = (WkrjDeptService) SpringContextUtil.getBean("wkrjDeptService");
		
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
		
		stationService = (WkrjStationService) SpringContextUtil.getBean("wkrjStationService");
		
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
	
	/**
	 * 获取所有的岗位
	 * @return
	 */
	@RequestMapping("getAllStationList")
	@ResponseBody
	public Object getAllStationList(){
		
		stationService = (WkrjStationService)SpringContextUtil.getBean("wkrjStationService");
		
		return stationService.getAllStationList();
	}
	
}
