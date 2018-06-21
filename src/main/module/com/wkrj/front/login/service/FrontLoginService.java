package com.wkrj.front.login.service;

import java.util.List;
import java.util.Map;

import wkrjsystem.user.bean.WkrjUser;

/**
 * @author: wkrj_syg
 * @date:2018年5月3日 
 * 
 */
public interface FrontLoginService {

	List<Map<String, Object>> getLeftMenu(String rolerId,String userId,String root);
	
	WkrjUser findUserByNameAndPwd(WkrjUser user);
	
	WkrjUser getUserInfoById(String id);
	
	boolean updatePassword(String id,String pw);
}
