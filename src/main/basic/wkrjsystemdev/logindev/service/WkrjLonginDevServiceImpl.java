package wkrjsystemdev.logindev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wkrjsystemdev.logindev.dao.WkrjLoginDevDao;
import wkrjsystemdev.userdev.bean.WkrjUserDev;

@Service("wkrjLonginDevService")
public class WkrjLonginDevServiceImpl implements WkrjLonginDevService {

	@Autowired
	private WkrjLoginDevDao dao;

	@Override
	public WkrjUserDev findUserByNameAndPwd(WkrjUserDev user) {
		return dao.findUserByNameAndPwd(user);
	}

	@Override
	public WkrjUserDev getUserInfoById(String id) {
		return this.dao.getUserInfoById(id);
	}

}
