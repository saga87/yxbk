package wkrjsystem.department.service;

import java.util.List;
import java.util.Map;

import wkrjsystem.department.bean.WkrjDept;


public interface WkrjDeptService {
    
	/**
	 * 获取角色
	 * @param parent_id
	 * @param isGly
	 * @param user_dept
	 * @return
	 */
	public List<Map<String,Object>> getRoleList(String parent_id,boolean isGly,String user_dept);
	
	/**
	 * 获取组织机构，含学校
	 * @param parent_id
	 * @param isGly
	 * @param user_dept
	 * @return
	 */
	public List<Map<String,Object>> getDeptAndSchoolGridList(String parent_id,boolean isGly,String user_dept);
	
	/**
	 * 添加组织机构
	 * @param dept
	 * @return
	 */
	public boolean addDept(WkrjDept dept);
	
	/**
	 * 修改组织机构
	 * @param dept
	 * @return
	 */
	public boolean updateDept(WkrjDept dept,String yId);
	
	/**
	 * 删除组织机构
	 * @param id
	 * @return
	 */
	public boolean delDept(String id);
	
	/**
	 * 判断是否还有子节点，如果大于0说明有
	 * @param parentId
	 * @return
	 */
	public int getDeptChildCount(String parentId);
	
	/**
	 * 根据id获取组织机构名称
	 * @param deptId
	 * @return
	 */
	public WkrjDept getDeptNameById(String deptId);

	
}
