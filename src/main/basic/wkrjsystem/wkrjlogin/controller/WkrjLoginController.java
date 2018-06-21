package wkrjsystem.wkrjlogin.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import wkrjsystem.role.bean.WkrjRole;
import wkrjsystem.role.service.WkrjRoleService;
import wkrjsystem.user.bean.WkrjUser;
import wkrjsystem.utils.AjaxJson;
import wkrjsystem.utils.MD5;
import wkrjsystem.wkrjlogin.service.WkrjLonginService;


@Controller
@RequestMapping("/wkrjsystem/wkrjlogin")
public class WkrjLoginController {

	@Autowired
	private WkrjLonginService wkrjLonginService;
	
	@Autowired
	private WkrjRoleService roleService;
	
	
	@RequestMapping("checkLogin")
	@ResponseBody
	public AjaxJson checkLogin(String username,String password,HttpServletRequest req
			,HttpServletResponse response){
		
		AjaxJson json = new AjaxJson();
		WkrjUser user = new WkrjUser();
		user.setUser_name(username);
		user.setUser_password(MD5.MD5Encode(password));
		user = wkrjLonginService.findUserByNameAndPwd(user);
		if(null!=user){
			String userId = user.getUser_id();
			user = this.wkrjLonginService.getUserInfoById(userId);
			String enable = user.getUser_is_enable();
			if("1".equals(enable)){
				json.setSuccess(false);
				json.setMsg("此账号已被禁用，请联系管理员！");
				return json;
			}
			//使用权限工具进行用户登录，登录成功后跳到shiro配置的successUrl中，与下面的return没什么关系！  
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUser_name()+"_wkrj", user.getUser_password());
			SecurityUtils.getSubject().login(token);
			
				 Cookie c1 = new Cookie("username", username);  
		         Cookie c2 = new Cookie("password", password);
		         c1.setPath("/kcsp/frontpage/frontlogin.jsp");
		         c2.setPath("/kcsp/frontpage/frontlogin.jsp");
		         c1.setMaxAge(3600*24*365);  
		         c2.setMaxAge(3600*24*365);//这里设置保存这条Cookie的时间  
		         response.addCookie(c1); 
		         response.addCookie(c2);  
			 
			
			String roleId="";
			List<WkrjRole> user_role = user.getUser_role();
			if(user_role.size()>0){
				for(WkrjRole uRole:user_role){
					roleId += uRole.getRole_id();
					roleId +=",";
				}
			}
			req.getSession().setAttribute("user", user);//将user对象放入sessin中
			req.getSession().setAttribute("userCountType", user.getUser_accounttype());//将user类型放入sessin中
			req.getSession().setAttribute("userRoleId", roleId);//将role放入sessin中
			if(1==user.getUser_accounttype()){
				req.getSession().setAttribute("userPermission", roleService.getAllPermission());
			}else{
				req.getSession().setAttribute("userPermission", roleService.getRolePermissionByRoleId(roleId.substring(0, roleId.length()-1)));
			}
			json.setSuccess(true);
		}else{
			json.setSuccess(false);
			json.setMsg("用户名或者密码错误！");
		}

		return json;
	}
	
	@RequestMapping("login")
	public String login(String username,String password,HttpServletRequest request,
			HttpServletResponse response){
		
		WkrjUser user = (WkrjUser) request.getSession().getAttribute("user");
		String role_id = (String)request.getSession().getAttribute("userRoleId");
		if(null != user){
//			saveCookie(user.getUser_name(),localPwd,request,response);
			if ("1,".equals(role_id)) {
				return "redirect:/frontpage/index.jsp";
			}
			if ("2,".equals(role_id)) {
	
				return "system/index";
			}
				
		}
		return "redirect:/frontpage/frontlogin.jsp";
	}	
	
	private void saveCookie(String username, String password,HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
		 Cookie c1 = new Cookie("username", username);  
         Cookie c2 = new Cookie("password", password);  
         c1.setMaxAge(1000);  
         c2.setMaxAge(1000);//这里设置保存这条Cookie的时间  
         response.addCookie(c1); 
         response.addCookie(c2);  
	}

	@RequestMapping("/loginout")
	public String logout(HttpServletRequest req,RedirectAttributes redirectAttributes){
	
		WkrjUser user = (WkrjUser) req.getSession().getAttribute("user");
		if(null==user){
			return "redirect:/frontpage/frontlogin.jsp";
		}
		
		String role_name = user.getUser_role().get(0).getRole_name();
		
		//使用权限管理工具进行用户的退出，跳出登录，给出提示信息  
        SecurityUtils.getSubject().logout();    
        redirectAttributes.addFlashAttribute("message", "您已安全退出");    
		
		Enumeration<String> attributeNames = req.getSession().getAttributeNames();
		
		while(attributeNames.hasMoreElements()){
			req.getSession().removeAttribute(attributeNames.nextElement());
		}
		
		req.getSession().invalidate();
		
		if("普通用户".equals(role_name)){
			return "redirect:/web/login.jsp"; //普通用户的页面跳转
		}
		
		return "redirect:/frontpage/frontlogin.jsp";
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
		
		AjaxJson json = new AjaxJson();
		
		String url = request.getParameter("url");
		
		//权限校验。判断是否包含权限。
		//具体响应MyRealm。doGetAuthorizationInfo，判断是否包含此url的响应权限
		int userCountType=0;
    	if(null!=request.getSession().getAttribute("userCountType")){
    		userCountType = (Integer) request.getSession().getAttribute("userCountType");
    	}
    	if(1==userCountType){
    		json.setMsg("用户超级管理员权限");
    		json.setSuccess(true);
    	}else{
			boolean isPermitted = SecurityUtils.getSubject().isPermitted(url);
			if(isPermitted) {
				json.setSuccess(true);
			}else{
				json.setMsg("没有权限");
			}
		}
		
		return json;
	}
	
	
	@RequestMapping("updatepassword")
	@ResponseBody
	public AjaxJson updatepassword(String oldpwd,String newpwd,HttpServletRequest request){
		WkrjUser user = (WkrjUser) request.getSession().getAttribute("user");
		String userId = user.getUser_id();
		
		AjaxJson json = new AjaxJson();
		
		if(MD5.MD5Encode(oldpwd).equals(user.getUser_password())){
			
			if (wkrjLonginService.updatePassword(userId, MD5.MD5Encode(newpwd))) {
				
				json.setMsg("修改成功");
				json.setSuccess(true);
				
				return json;
			}
		}else {
			
			json.setMsg("原密码不正确");
			json.setSuccess(false);
			
			return json;
		}
		
		return json;
	}
	
}
