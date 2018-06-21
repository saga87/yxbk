/**
 * 
 */
package com.wkrj.front.clinic.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wkrj.data.bean.WkrjData;
import com.wkrj.front.clinic.service.WkrjWebClinicService;

/**
 * @author linpeng
 * @date 2018年5月5日
 */
@Controller
@RequestMapping("front/web/clinic")
public class WkrjWebClinicController {

	@Autowired
	private WkrjWebClinicService wkrjWebService;
	
	@RequestMapping("listClinics")
	@ResponseBody
	public Object listClinics(int page,int rows,String data_id){
		int offset = (page-1)*rows;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = wkrjWebService.listClinics(offset, rows,data_id);
		return list;
	}
	
	
	@RequestMapping("searchClinics")
	@ResponseBody
	public Object searchClinics(int page,int rows,String clinic_name){
		int offset = (page-1)*rows;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = wkrjWebService.searchClinics(offset, rows,clinic_name);
		return list;
	}
	
	
	
	@RequestMapping("countClinics")
	@ResponseBody
	public Object countClinics(String clinic_name,String data_id){
		return wkrjWebService.countClinics(clinic_name,data_id);
	}
	
	
	
	
	@RequestMapping("getClinicById")
	@ResponseBody
	public Object getClinicById(int page,int rows,String clinic_id){
		int offset = (page-1)*rows;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(null == clinic_id ||"".equals(clinic_id)){
			return list;
		}
		list = wkrjWebService.getClinicById(offset, rows, clinic_id);
		return list;
	}
	
	
	@RequestMapping("getClinicFileById")
	@ResponseBody
	public Object getClinicFileById(String clinic_id){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = wkrjWebService.getClinicFileById(clinic_id);
		return list;
	}
	
	@ResponseBody
	@RequestMapping("getData")
	public Object getData(){
		List<WkrjData> list = wkrjWebService.getData();
		return list;
	}
	
	@ResponseBody
	@RequestMapping("getSecondData")
	public Object getSecondData(String data_parent_id){
		List<WkrjData> list = wkrjWebService.getSecondData(data_parent_id);
		return list;
	}
	
	
	@ResponseBody
	@RequestMapping("getDataById")
	public Object getDataById(String data_id){
		WkrjData data = wkrjWebService.getDataById(data_id);
		return data;
	}
	
	@ResponseBody
	@RequestMapping("addPageviews")
	public Object addPageviews(String clinic_id){
		return wkrjWebService.addPageviews(clinic_id);
	}
	
}
