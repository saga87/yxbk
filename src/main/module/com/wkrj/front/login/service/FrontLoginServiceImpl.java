package com.wkrj.front.login.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wkrj.front.login.dao.FrontLoginDao;

import wkrjsystem.user.bean.WkrjUser;

/**
 * @author: wkrj_syg
 * @date:2018年5月3日 
 * 
 */
@Service("frontLoginService")
@Transactional
public class FrontLoginServiceImpl implements FrontLoginService{

	@Autowired
	private FrontLoginDao dao;
	
	@Override
	public List<Map<String, Object>> getLeftMenu(String rolerId, String userId,
			String root) {
		// TODO Auto-generated method stub
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
