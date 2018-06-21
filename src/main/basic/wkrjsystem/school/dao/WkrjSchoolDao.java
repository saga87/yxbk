package wkrjsystem.school.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import wkrjsystem.school.bean.WkrjSchool;
public interface WkrjSchoolDao {
	
	public List<WkrjSchool> getSchoolList(@Param("offset")int offset,@Param("page") int page);
	
	public WkrjSchool getSchoolById(String id);
	
	public List<WkrjSchool> getAllSchoolList();
	
	public long getSchoolListCount();
	
	/**
	 * 添加岗位
	 * @param school
	 * @return
	 */
	public boolean addSchool(WkrjSchool school);
	
	/**
	 * 修改岗位
	 * @param school
	 * @return
	 */
	public boolean updateSchool(WkrjSchool school);
	/**
	 * 删除岗位
	 * @param id
	 * @return
	 */
	public boolean delSchool(String id);
	
}
