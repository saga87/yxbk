package com.wkrj.front.login.dao;

import org.apache.ibatis.annotations.Param;

import wkrjsystem.user.bean.WkrjUser;

/**
 * @author: wkrj_syg
 * @date:2018年5月3日 
 * 
 */
public interface FrontLoginDao {

	WkrjUser findUserByNameAndPwd(WkrjUser user);
	
	WkrjUser getUserInfoById(String userId);
	
	public boolean updatePassword(@Param("id")String id,@Param("pw") String pw);
}
