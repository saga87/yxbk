package wkrjsystem.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wkrjsystem.school.bean.WkrjSchool;
import wkrjsystem.school.dao.WkrjSchoolDao;
@Service("wkrjSchoolService")
@Transactional
public class WkrjSchoolServiceImpl implements WkrjSchoolService {

	@Autowired
	private WkrjSchoolDao dao;
	
	
	@Override
	public List<WkrjSchool> getSchoolList(int offset, int page) {
		return this.dao.getSchoolList(offset, page);
	}
	
	@Override
	public List<WkrjSchool> getAllSchoolList(String key) {
		return this.dao.getAllSchoolList();
	}
	
	@Override
	public boolean addSchool(WkrjSchool module) {
		return this.dao.addSchool(module);
	}

	@Override
	public boolean updateSchool(WkrjSchool module) {
		return this.dao.updateSchool(module);
	}

	@Override
	public boolean delSchool(String id) {
		return dao.delSchool(id);
	}

	@Override
	public long getSchoolList() {
		return dao.getSchoolListCount();
	}

	@Override
	public WkrjSchool getSchoolById(String id) {
		return this.dao.getSchoolById(id);
	}
	
}
