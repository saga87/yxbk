package wkrjsystem.wkrjlogin.dao;

import org.apache.ibatis.annotations.Param;

import wkrjsystem.user.bean.WkrjUser;

public interface WkrjLoginDao {

	
	WkrjUser findUserByNameAndPwd(WkrjUser user);
	
	WkrjUser getUserInfoById(String userId);
	
	public boolean updatePassword(@Param("id")String id,@Param("pw") String pw);
}
