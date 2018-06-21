/**
 * 
 */
package com.wkrj.front.clinic.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wkrj.data.bean.WkrjData;

/**
 * @author linpeng
 * @date 2018年5月5日
 */
public interface WkrjWebClinicDao {
	List<Map<String,Object>> listClinics(@Param("offset")int offset,@Param("rows")int rows,@Param("data_id")String data_id);
	
	List<Map<String,Object>> searchClinics(@Param("offset")int offset,@Param("rows")int rows,@Param("clinic_name")String clinic_name);
	
	long  countClinics(@Param("clinic_name")String clinic_name,@Param("data_id")String data_id);
	
	List<Map<String,Object>> getClinicById(@Param("offset")int offset,@Param("rows")int rows,@Param("clinic_id")String clinic_id);
	
	List<Map<String, Object>> getClinicFileById(@Param("clinic_id")String clinic_id);
	
	List<WkrjData> getData();
	
	List<WkrjData> getSecondData(@Param("data_parent_id")String data_parent_id);
	
	boolean addPageviews(@Param("clinic_id")String clinic_id); 
	
	WkrjData getDataById(@Param("data_id")String data_id);
}
