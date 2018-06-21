package wkrjsystemdev.logindev.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import wkrjsystem.utils.AjaxJson;
import wkrjsystem.utils.MD5;
import wkrjsystemdev.logindev.service.WkrjLonginDevService;
import wkrjsystemdev.roledev.bean.WkrjRoleDev;
import wkrjsystemdev.roledev.service.WkrjRoleDevService;
import wkrjsystemdev.userdev.bean.WkrjUserDev;


@Controller
@RequestMapping("wkrjsystemdev/wkrjLoginDev")
public class WkrjLoginDevController {

	@Autowired
	private WkrjLonginDevService wkrjLonginDevService;
	
	@Autowired
	private WkrjRoleDevService roleDevService;
	
	@RequestMapping("checkLogin")
	@ResponseBody
	public AjaxJson checkLogin(String username,String password,HttpServletRequest req){
		
		AjaxJson json = new AjaxJson();
		
		WkrjUserDev user = new WkrjUserDev();
		user.setUser_name(username);
		user.setUser_password(MD5.MD5Encode(password));
		
		user = wkrjLonginDevService.findUserByNameAndPwd(user);
		
		if(null!=user){
			
			String userId = user.getUser_id();
			user = this.wkrjLonginDevService.getUserInfoById(userId);
			
			//使用权限工具进行用户登录，登录成功后跳到shiro配置的successUrl中，与下面的return没什么关系！  
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUser_name(), user.getUser_password());
			SecurityUtils.getSubject().login(token);
			
			/*//如果身份不是开发管理员用户不允许登录
			if(1!=user.getUser_accounttype()){
				json.setMsg("您不能登陆此系统");
				json.setSuccess(false);
				return json;
			}*/
			
			String roleId="";
			
			List<WkrjRoleDev> user_role = user.getUser_role();
			
			if(user_role.size()>0){
				for(WkrjRoleDev uRole:user_role){
					roleId += uRole.getRole_id();
					roleId +=",";
				}
			}
			
			if(user_role.size()<=0 && 1!=user.getUser_accounttype()){
				json.setMsg("没有分配任何角色");
				json.setSuccess(false);
			}else{ 
			
				req.getSession().setAttribute("userDev", user);//将user对象放入sessin中
				req.getSession().setAttribute("userCountTypeDev", user.getUser_accounttype());//将user类型放入sessin中
				req.getSession().setAttribute("userRoleId", roleId);//将role放入sessin中
//				if(1==user.getUser_accounttype()){
//					req.getSession().setAttribute("userPermissionDev", roleDevService.getAllPermission());
//				}else{
//					req.getSession().setAttribute("userPermissionDev", roleDevService.getRolePermissionByRoleId(roleId.substring(0, roleId.length()-1)));
//				}
			}
			
			json.setSuccess(true);
		}
		
		return json;
	}
	
	@RequestMapping("login")
	public String login(String username,String password,HttpServletRequest req){
		
		WkrjUserDev user = (WkrjUserDev) req.getSession().getAttribute("userDev");
		
		if(null != user){
			return "systemdev/developer";
		}
		
		return "redirect:/systemdev/developer.jsp";
	}
	
	@RequestMapping("loginout")
	public String logout(HttpServletRequest req,RedirectAttributes redirectAttributes){
	
		WkrjUserDev user = (WkrjUserDev) req.getSession().getAttribute("userDev");
		if(null==user){
			return "redirect:/systemdev/developer.jsp";
		}
		
		//使用权限管理工具进行用户的退出，跳出登录，给出提示信息  
        SecurityUtils.getSubject().logout();    
        redirectAttributes.addFlashAttribute("message", "您已安全退出");    
		
		Enumeration<String> attributeNames = req.getSession().getAttributeNames();
		
		while(attributeNames.hasMoreElements()){
			req.getSession().removeAttribute(attributeNames.nextElement());
		}
		
		req.getSession().invalidate();
		
		return "redirect:/systemdev/developer.jsp";
	}
	
	/**
	 * 权限判断
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="permissionsCheck")
    @ResponseBody
	private AjaxJson permissionsCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//Define and throw a dedicated exception instead of using a generic one.
		
		AjaxJson json = new AjaxJson();
		
		String url = request.getParameter("url");
		
		int userCountType=0;
    	
    	
    	if(null!=request.getSession().getAttribute("userCountTypeDev")){
    		userCountType = (Integer) request.getSession().getAttribute("userCountTypeDev");
    	}
    	
    	if(1==userCountType){
    		json.setMsg("开发人员权限");
    		json.setSuccess(true);
    	}else{
			//权限校验。判断是否包含权限。
			//具体响应MyRealm。doGetAuthorizationInfo，判断是否包含此url的响应权限
			boolean isPermitted = SecurityUtils.getSubject().isPermitted(url);
			if(isPermitted) {
				json.setSuccess(true);
			}else{
				json.setMsg("没有权限");
			}
		}
		
		return json;
	}
	
}
