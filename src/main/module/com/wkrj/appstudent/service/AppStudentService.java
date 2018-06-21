package com.wkrj.appstudent.service;

import java.util.List;
import java.util.Map;

import com.wkrj.appstudent.bean.AppStudent;


public interface AppStudentService {
	public List<AppStudent> getStudentList(int offset, int page);


	public long getStudentListCount();

 
	public boolean addStudent(AppStudent model);


	public boolean delStudent(String id);


	public long checkSNum(String studentNum);


	public boolean updateStudent(AppStudent model);


	public boolean updateAppStudent(AppStudent model);


	public List<Map<String, Object>> getStudentInfo(String student_id);


	
	
}
