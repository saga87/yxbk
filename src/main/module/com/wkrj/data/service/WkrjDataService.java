package com.wkrj.data.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import wkrjsystem.user.bean.WkrjUser;
import wkrjsystem.utils.AjaxJson;

import com.wkrj.data.bean.WkrjData;

public interface WkrjDataService {
	/**
	 * 添加数据
	 */
	public boolean addData(WkrjData data);
	/**
	 * 更新数据
	 */
	public boolean updateData(WkrjData data,String yId);
	/**
	 * 更新是否首页显示
	 * @param data
	 * @return
	 */
	public boolean updateDataShow(String data_id,String data_show);
	/**
	 * 删除数据
	 */
	public AjaxJson delData(String data_id);
	/**
	 * 判断是否还有子节点，如果大于0说明有
	 */
	public int getDataTreeCount(String data_parent_id);
	

	/**
	 * 根据id获取组织机构名称
	 */
	public WkrjData getDataNameById(String data_id);
	/**
	 * 获取组织机构
	 */
	public List<Map<String,Object>> getDataList(String data_parent_id);
	
	
}
