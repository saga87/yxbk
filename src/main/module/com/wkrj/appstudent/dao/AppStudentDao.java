package com.wkrj.appstudent.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wkrj.appstudent.bean.AppStudent;

public interface AppStudentDao {

	//public boolean addUser(AppUser module);

	public List<AppStudent> getStudentList(@Param("offset") int offset,@Param("page") int page);

	public long getStudentListCount();

	public boolean addStudent(AppStudent model);

	public boolean delStudent(@Param("id") String id);

	public long checkSNum(@Param("studentNum") String studentNum);

	public boolean updateStudent(AppStudent model);

	public boolean updateAppStudent(AppStudent model);

	public List<Map<String, Object>> getStudentInfo(String student_id);
	
}
