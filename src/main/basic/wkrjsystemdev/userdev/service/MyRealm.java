package wkrjsystemdev.userdev.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import wkrjsystem.role.bean.WkrjRole;
import wkrjsystem.role.service.WkrjRoleService;
import wkrjsystem.user.bean.WkrjUser;
import wkrjsystem.user.service.WkrjUserService;
import wkrjsystem.wkrjlogin.service.WkrjLonginService;
import wkrjsystemdev.logindev.service.WkrjLonginDevService;
import wkrjsystemdev.roledev.bean.WkrjRoleDev;
import wkrjsystemdev.roledev.service.WkrjRoleDevService;
import wkrjsystemdev.userdev.bean.WkrjUserDev;

public class MyRealm extends AuthorizingRealm {

	@Autowired
    private WkrjUserDevService userService;  
	
	@Autowired
	private WkrjRoleDevService roleDevService;
	
	@Autowired
	private WkrjLonginDevService wkrjLonginDevService;
	
	@Autowired
	private WkrjUserService userService_;  
	
	@Autowired
	private WkrjRoleService roleService;
	
	@Autowired
	private WkrjLonginService wkrjLonginService;
    
    /** 
     * 权限认证 
     */  
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {  
        //获取登录时输入的用户名  
        String loginName=(String) principalCollection.getPrimaryPrincipal();
        
        if(loginName.indexOf("_wkrj")>=0){
        	//普通用户判断
        	
        	loginName = loginName.substring(0, loginName.indexOf("_wkrj"));
	        WkrjUser user=userService_.findUserInfoByName(loginName);  
	        user = this.wkrjLonginService.getUserInfoById(user.getUser_id());
	        
	        if(user!=null){  
	            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）  
	            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();  
	            //用户的角色集合  
	            List<WkrjRole> user_role = user.getUser_role();
	            String roleId="";
	            Set<String> set = new HashSet<String>();
	            
	            if(user_role.size()>0){
	            	
	            	for(WkrjRole uRole:user_role){
						roleId += uRole.getRole_id();
						roleId +=",";
						
						set.add(uRole.getRole_name());
					}
	            	
	            	roleId = roleId.substring(0, roleId.length()-1);
	            }
	            
	            if(1==user.getUser_accounttype()){
	            	info.addStringPermission(roleService.getAllPermission());
	            }else{
	            	info.addStringPermission(roleService.getRolePermissionByRoleId(roleId));
	            }
	            
//	            Set<WkrjRole> set  = new HashSet<WkrjRole>();
	            info.addRole(roleId);
	            info.setRoles(set);
	        
	            return info;  
	        }  
        	
        }else{
        	//开发管理员判断
	        WkrjUserDev user=userService.findUserInfoByName(loginName);  
	        user = this.wkrjLonginDevService.getUserInfoById(user.getUser_id());
	        
	        if(user!=null){  
	            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）  
	            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();  
	            //用户的角色集合  
	            List<WkrjRoleDev> user_role = user.getUser_role();
	            String roleId="";
	            Set<String> set = new HashSet<String>();
	            
	            if(user_role.size()>0){
	            	
	            	for(WkrjRoleDev uRole:user_role){
						roleId += uRole.getRole_id();
						roleId +=",";
						
						set.add(uRole.getRole_name());
					}
	            	
	            	roleId = roleId.substring(0, roleId.length()-1);
	            }
	            
	            if(1==user.getUser_accounttype()){
	            	info.addStringPermission(roleDevService.getAllPermission());
	            }else{
	            	info.addStringPermission(roleDevService.getRolePermissionByRoleId(roleId));
	            }
	        
	            info.addRole(roleId);
	            
	            info.setRoles(set);
	            
	            return info;  
	        }  
        }
        return null;  
    }
	
    /** 
     * 登录认证; 
     */  
    @Override  
    protected AuthenticationInfo doGetAuthenticationInfo(  
            AuthenticationToken authenticationToken) throws AuthenticationException {  
        //UsernamePasswordToken对象用来存放提交的登录信息  
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;  
        
        //查出是否有此用户  
        String password = new String((char[])token.getPassword());//获取密码
        
        if(token.getUsername().indexOf("_wkrj")>=0){
        	
        	String uName = token.getUsername().substring(0, token.getUsername().indexOf("_wkrj"));
        	
        	WkrjUser user=userService_.findUserInfoByName(uName,password);  
            if(user!=null){  
                //若存在，将此用户存放到登录认证info中  
                return new SimpleAuthenticationInfo(user.getUser_name()+"_wkrj", user.getUser_password(), ByteSource.Util.bytes(user.getUser_password()),getName());  
            }  
        	
        }else{
        	
            WkrjUserDev user=userService.findUserInfoByName(token.getUsername(),password);  
            if(user!=null){  
                //若存在，将此用户存放到登录认证info中  
                return new SimpleAuthenticationInfo(user.getUser_name(), user.getUser_password(), ByteSource.Util.bytes(user.getUser_password()),getName());  
            }  
        	
        }

        return null;  
    }  
	
}
