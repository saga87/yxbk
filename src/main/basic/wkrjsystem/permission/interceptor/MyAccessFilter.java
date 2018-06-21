package wkrjsystem.permission.interceptor;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

public class MyAccessFilter extends AccessControlFilter { 
	
    private String unauthorizedUrl = "/403.jsp";  
    private String loginUrl = "/frontpage/frontlogin.jsp";  
    
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {  
        return false;//跳到onAccessDenied处理  
    }  
  
    @Override  
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {  
        Subject subject = getSubject(request, response);  
        if (subject.getPrincipal() == null) {//表示没有登录，重定向到登录页面  
            saveRequest(request);  
            WebUtils.issueRedirect(request, response, loginUrl);  
        } else {  
        	
        	HttpServletRequest req = (HttpServletRequest)request;
        	
        	int userCountType=0;
        	if(null!=req.getSession().getAttribute("userCountType")){
        		userCountType = (Integer) req.getSession().getAttribute("userCountType");
        	}
        	
        	if(null!=req.getSession().getAttribute("userCountTypeDev")){
        		userCountType = (Integer) req.getSession().getAttribute("userCountTypeDev");
        	}
        	
        	if(1==userCountType){
        		return true;
        	}
        	String url = req.getServletPath();
    		url = url.substring(1, url.length());
    		
        	boolean isPermitted = SecurityUtils.getSubject().isPermitted(url);
        	
        	if(!isPermitted){
        		
        		WebUtils.issueRedirect(request, response, unauthorizedUrl);  
        		
        	}else{
        		return true;
        	}
        	
           
        }  
        return false;  
    }  
}   