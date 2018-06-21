/**
 * 
 */
package com.wkrj.clinic.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wkrj.clinic.bean.WkrjClinic;
import com.wkrj.clinic.bean.WkrjClinicFile;

/**
 * @author linpeng
 * @date 2018年4月24日
 */
public interface WkrjClinicDao {
	//诊疗
	List<Map<String,Object>> listClinics(@Param("offset")int offset,@Param("rows")int rows
			,@Param("clinic_name")String clinic_name,@Param("data_id")String data_id,@Param("clinic_type")String clinic_type);
	long  countClinics(@Param("clinic_name")String clinic_name,@Param("data_id")String data_id,@Param("clinic_type")String clinic_type);
	boolean addClinic(WkrjClinic clinic);
	boolean updateClinic(WkrjClinic clinic);
	boolean deleteClinic(@Param("clinic_id") String clinic_id);
	
	//诊疗附件
	List<Map<String,Object>> listClinicFile(@Param("file_id")String file_id
					,@Param("clinic_id")String clinic_id);
	boolean addClinicFile(WkrjClinicFile clinicFile);
	boolean updateClinicFile(WkrjClinicFile clinicFile);
	boolean deleteClinicFile(@Param("file_id")String file_id,@Param("clinic_id") String clinic_id);
	
}
