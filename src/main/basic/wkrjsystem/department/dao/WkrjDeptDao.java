package wkrjsystem.department.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import wkrjsystem.department.bean.WkrjDept;

public interface WkrjDeptDao {
	
	/**
	 * 获取组织机构树
	 * @param parentId
	 * @return
	 */
	public List<WkrjDept> getDeptTree(@Param("parent_id")String parent_id,@Param("isGly")boolean isGly,@Param("user_dept")String user_dept);
	
	/**
	 * 判断是否还有子节点，如果大于0说明有
	 * @param parentId
	 * @return
	 */
	public int getDeptChildCount(String parentId);
	
	public String getDeptChildMaxByPid(String parentId);
	
	/**
	 * 添加组织机构
	 * @param dept
	 * @return
	 */
	public boolean addDept(WkrjDept dept);
	
	/**
	 * 删除组织机构
	 * @param id
	 * @return
	 */
	public boolean delDept(String id);
	
	/**
	 * 通过id获取组织机构名称
	 * @param deptId
	 * @return
	 */
	public WkrjDept getDeptNameById(String deptId);
	
	/**
	 * 修改组织机构
	 * @param dept
	 * @return
	 */
	public boolean updateDept(WkrjDept dept);

	
}
