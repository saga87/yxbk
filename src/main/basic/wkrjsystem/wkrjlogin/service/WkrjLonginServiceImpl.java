package wkrjsystem.wkrjlogin.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wkrjsystem.user.bean.WkrjUser;
import wkrjsystem.wkrjlogin.dao.WkrjLoginDao;

@Service("wkrjLonginService")
public class WkrjLonginServiceImpl implements WkrjLonginService {

	@Autowired
	private WkrjLoginDao dao;
	
	@Override
	public List<Map<String, Object>> getLeftMenu(String rolerId, String userId,
			String root) {
		// TODO Auto-generated method stub
//		return dao.getLeftMenu(rolerId, userId, root);
		return null;
	}

	@Override
	public WkrjUser findUserByNameAndPwd(WkrjUser user) {
		return dao.findUserByNameAndPwd(user);
	}

	@Override
	public WkrjUser getUserInfoById(String id) {
		return this.dao.getUserInfoById(id);
	}

	@Override
	public boolean updatePassword(String id, String pw) {
		// TODO Auto-generated method stub
		return this.dao.updatePassword(id,pw);
	}
	

}
