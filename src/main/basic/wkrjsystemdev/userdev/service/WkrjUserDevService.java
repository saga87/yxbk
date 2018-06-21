package wkrjsystemdev.userdev.service;

import java.util.List;
import java.util.Map;

import wkrjsystemdev.userdev.bean.WkrjUserDev;


public interface WkrjUserDevService {
    
	
	public List<WkrjUserDev> getUserList(int offset, int page, String deptId, String userName);
	
	public WkrjUserDev findUserInfoByName(String userName,String pw);
	
	public WkrjUserDev findUserInfoByName(String userName);
	
	public long getUserList(String deptId,String userName);
	
	public String addUser(WkrjUserDev module);
	
	public String updateUser(WkrjUserDev module);
	
	public boolean delUser(String id);
	
	public boolean repeatPw(String id,String pw);
	
	public boolean forbiddenUser(String id,String flag);
	
	public List<Map<String,Object>> getRoleList();

	public List<Map<String, Object>> getStationList();
	

}
