/**
 * 
 */
package com.wkrj.front.clinic.service;

import java.util.List;
import java.util.Map;

import com.wkrj.data.bean.WkrjData;

/**
 * @author linpeng
 * @date 2018年5月5日
 */
public interface WkrjWebClinicService {
	List<Map<String,Object>> listClinics(int offset,int rows,String data_id);
	
	List<Map<String,Object>> searchClinics(int offset,int rows,String clinic_name);
	
	List<Map<String,Object>> getClinicById(int offset,int rows,String clinic_id);
	
	List<Map<String,Object>> getClinicFileById(String clinic_id);
	
	
	long countClinics(String clinic_name,String data_id); 
	
	List<WkrjData> getData();
	
	List<WkrjData> getSecondData(String data_parent_id);
	
	boolean addPageviews(String clinic_id);
	
	WkrjData getDataById(String data_id);
	
}
