package com.wkrj.data.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;






import wkrjsystem.department.bean.WkrjDept;

import com.wkrj.data.bean.WkrjData;


public interface WkrjDataDao {
	/**
	 * 添加数据
	 */
	public boolean addData(WkrjData data);
	/**
	 * 更新数据
	 */
	public boolean updateData(WkrjData data);
	/**
	 * 更新是否首页显示
	 * @param data
	 * @return
	 */
	public boolean updateDataShow(WkrjData data);
	/**
	 * 删除数据
	 */
	public boolean delData(String data_id);
	/**
	 * 获取数据字典列表
	 */
	public List<WkrjData> getDataTree(@Param("data_parent_id")String data_parent_id,@Param("data_id")String data_id);
	
	/**
	 * 判断是否还有子节点，如果大于0说明有
	 */
	public int getDataTreeCount(String data_parent_id);
	
	String getDataChildMaxByPid(String parentId);
	
	/**
	 * 获取组织机构名称
	 */
	public WkrjData getDataNameById(String data_id);
	
}
