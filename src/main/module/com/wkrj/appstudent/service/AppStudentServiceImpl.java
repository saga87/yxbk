package com.wkrj.appstudent.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wkrj.appstudent.bean.AppStudent;
import com.wkrj.appstudent.dao.AppStudentDao;

@Service("studentService")
@Transactional
public class AppStudentServiceImpl implements AppStudentService {

	@Autowired
	private AppStudentDao dao;




	@Override
	public List<AppStudent> getStudentList(int offset, int page) {
		// TODO Auto-generated method stub
		return dao.getStudentList(offset,page);
	}


	@Override
	public long getStudentListCount() {
		// TODO Auto-generated method stub
		return dao.getStudentListCount();
	}


	@Override
	public boolean addStudent(AppStudent model) {
		// TODO Auto-generated method stub
		return dao.addStudent(model);
	}


	@Override
	public boolean delStudent(String id) {
		// TODO Auto-generated method stub
		return dao.delStudent(id);
	}


	@Override
	public long checkSNum(String studentNum) {
		// TODO Auto-generated method stub
		return dao.checkSNum(studentNum);
	}


	@Override
	public boolean updateStudent(AppStudent model) {
		// TODO Auto-generated method stub
		return dao.updateStudent(model);
	}


	@Override
	public boolean updateAppStudent(AppStudent model) {
		// TODO Auto-generated method stub
		return dao.updateAppStudent(model);
	}


	@Override
	public List<Map<String, Object>> getStudentInfo(String student_id) {


		
		return dao.getStudentInfo(student_id);
	}
	
}
