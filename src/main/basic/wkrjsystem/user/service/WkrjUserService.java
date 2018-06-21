package wkrjsystem.user.service;

import java.util.List;
import java.util.Map;

import wkrjsystem.role.bean.WkrjRole;
import wkrjsystem.user.bean.WkrjUser;


public interface WkrjUserService {
	public List<WkrjUser> getUserList(int offset, int page,String deptId,String userName,boolean isGly,String user_dept);
	
	public long getUserList(String deptId,String userName,boolean isGly,String user_dept);
	
	public WkrjUser findUserInfoByName(String userName);
	
	public WkrjUser findUserInfoByName(String userName,String pw);
	
	public String addUser(WkrjUser module);
	
	public String updateUser(WkrjUser module);
	
	public boolean delUser(String id);
	
	public boolean repeatPw(String id,String pw);
	
	public boolean forbiddenUser(String id,String flag);
	
	public List<WkrjRole> getRoleListByUserId(String user_id);

	public List<Map<String, Object>> getRoleList(String role_level);
	
}
