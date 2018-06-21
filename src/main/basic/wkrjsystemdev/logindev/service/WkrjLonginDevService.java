package wkrjsystemdev.logindev.service;

import wkrjsystemdev.userdev.bean.WkrjUserDev;

public interface WkrjLonginDevService {
    
	WkrjUserDev findUserByNameAndPwd(WkrjUserDev user);
	
	WkrjUserDev getUserInfoById(String id);
	

}
