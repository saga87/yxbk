package com.wkrj.data.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wkrjsystem.utils.AjaxJson;

import com.wkrj.data.bean.WkrjData;
import com.wkrj.data.service.WkrjDataService;

/**
 * 数据字典维护
 * @author Administrator
 *
 */
@Controller
@RequestMapping("data/WkrjDataController")
public class WkrjDataController {
	@Autowired
	private WkrjDataService service;
	
	/**
	*添加数据字典
	 */
	@RequestMapping("addData")
	@ResponseBody
	public AjaxJson addData(WkrjData data){
		AjaxJson json = new AjaxJson();
		if(service.addData(data)){
			json.setSuccess(true);
			json.setMsg("添加成功！");
			
		}
		return json;
	}
	/**
	 * 更新数据字典
	 */
	@RequestMapping("updateData")
	@ResponseBody
	public AjaxJson updateData(WkrjData data,String yId){
		AjaxJson json = new AjaxJson();
		if(service.updateData(data, yId)){
			json.setSuccess(true);
			json.setMsg("修改成功！");
		}
		return json;
	}
	/**
	 * 删除数据字典
	 */
	@RequestMapping("delData")
	@ResponseBody
	public AjaxJson delData(String id){
		AjaxJson json = new AjaxJson();
		json = service.delData(id);
		return json;
	}
	/**
	 * 获取结构树
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getDataTree")
	public Object getDataTree(){
		String data_parent_id = "-1";
		List<Map<String,Object>> list = service.getDataList(data_parent_id);
		return list;
		
	}
	/**
	 * 获取数据字典组织结构树
	 */
	@RequestMapping("getDataList")
	@ResponseBody
	public Object getDataList(){
		String data_parent_id = "-1";
		Map<String,Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list =  service.getDataList(data_parent_id);
		map.put("Rows", list);
		return map;
		
	}
	
	/**
	 * 设置是否首页显示
	 * @param data
	 * @return
	 */
	@RequestMapping("updateDataShow")
	@ResponseBody
	public AjaxJson updateDataShow(String data_id,String data_show){
		AjaxJson json = new AjaxJson();
		if(service.updateDataShow( data_id, data_show)){
			json.setSuccess(true);
			json.setMsg("修改成功！");
		}
		return json;
	}
}
