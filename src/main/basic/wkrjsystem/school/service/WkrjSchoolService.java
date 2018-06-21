package wkrjsystem.school.service;
import java.util.List;
import wkrjsystem.school.bean.WkrjSchool;
public interface WkrjSchoolService {
    
	
	public List<WkrjSchool> getSchoolList(int offset, int page);
	
	public List<WkrjSchool> getAllSchoolList(String key);
	
	public WkrjSchool getSchoolById(String id);
	
	public long getSchoolList();
	
	public boolean addSchool(WkrjSchool module);
	
	public boolean updateSchool(WkrjSchool module);
	
	public boolean delSchool(String id);
	

}
