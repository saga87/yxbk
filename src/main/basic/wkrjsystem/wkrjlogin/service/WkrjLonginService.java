package wkrjsystem.wkrjlogin.service;

import java.util.List;
import java.util.Map;

import wkrjsystem.role.bean.WkrjRolePermission;
import wkrjsystem.user.bean.WkrjUser;
public interface WkrjLonginService {
    
	List<Map<String, Object>> getLeftMenu(String rolerId,String userId,String root);
	
	WkrjUser findUserByNameAndPwd(WkrjUser user);
	
	WkrjUser getUserInfoById(String id);
	
	boolean updatePassword(String id,String pw);
	

}
