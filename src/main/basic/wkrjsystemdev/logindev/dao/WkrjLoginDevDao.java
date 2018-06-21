package wkrjsystemdev.logindev.dao;

import wkrjsystemdev.userdev.bean.WkrjUserDev;

public interface WkrjLoginDevDao {

	WkrjUserDev findUserByNameAndPwd(WkrjUserDev user);
	
	WkrjUserDev getUserInfoById(String userId);
}
