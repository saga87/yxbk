package wkrjsystem.permission.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import wkrjsystem.user.bean.WkrjUser;
import wkrjsystemdev.userdev.bean.WkrjUserDev;

import com.google.gson.Gson;

public class PermissionInterceptor extends HandlerInterceptorAdapter{
 
	private List<String> excludeUrls;//额外的排除参数
	
	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}



	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String url = request.getServletPath();
		url = url.substring(1, url.length());
		
		if(url.indexOf(".wk")>=0){
			return true;
		}
		WkrjUserDev userDev = (WkrjUserDev) request.getSession().getAttribute("userDev"); 
		String user_permissions = (String) request.getSession().getAttribute("userPermission"); 
		
		if(null!=userDev){
			if(1==userDev.getUser_accounttype()){
				return true;
			}else{
				if ((user_permissions.indexOf(url) > -1)) {
					return true;
				}
				
			}
		}
		
		
		WkrjUser user = (WkrjUser) request.getSession().getAttribute("user"); 
		user_permissions = (String) request.getSession().getAttribute("userPermission"); 
		
		if(null!=user){
			if(1==user.getUser_accounttype()){
				return true;
			}else{
				if ((user_permissions.indexOf(url) > -1)) {
					return true;
				}
				
			}
		}
		
		if(excludeUrls.contains(url)){
			return true;
		}
		
		response.setContentType("text/plain;charset=UTF-8");
		
 		Map<String,Object> m = new HashMap<String,Object>();
 		m.put("success", false);
 		m.put("msg", "没有权限");
 
 		response.getWriter().print(new Gson().toJson(m));
//        if(false == aflag){
// 
//            if (fireAuthority.resultType() == ResultTypeEnum.page) {
//                //传统的登录页面              
//                StringBuilder sb = new StringBuilder();
//                sb.append(request.getContextPath());
//                sb.append("/oprst.jsp?oprst=false&opmsg=").append(URLEncoder.encode(ControllerProperty.NOT_HAVE_AUTHORITY,"utf-8"));
//                response.sendRedirect(sb.toString());
//            } else if (fireAuthority.resultType() == ResultTypeEnum.json) {
//                //ajax类型的登录提示
//                response.setCharacterEncoding("utf-8");
//                response.setContentType("text/html;charset=UTF-8");
//                OutputStream out = response.getOutputStream();
//                PrintWriter pw = new PrintWriter(new OutputStreamWriter(out,"utf-8"));
//                pw.println("{\"result\":false,\"code\":12,\"errorMessage\":\""+ControllerProperty.NOT_HAVE_AUTHORITY+"\"}");
//                pw.flush();
//                pw.close();
//            }
// 
//            return false;
// 
//        }
		
		
		return false;
	}
	
	 @Override    
	    public void postHandle(HttpServletRequest request,    
	            HttpServletResponse response, Object handler,    
	            ModelAndView modelAndView) throws Exception {
		 
	         if(modelAndView != null){      
	        	//在此可以放入想要返回的数据
	            // modelAndView.addObject("msg", "登录超时");    
	         } 
	    }    
	    
	    /**  
	     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等   
	     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()  
	     */    
	    @Override    
	    public void afterCompletion(HttpServletRequest request,    
	            HttpServletResponse response, Object handler, Exception ex)    
	            throws Exception {    
	    }    
	  

	
}
