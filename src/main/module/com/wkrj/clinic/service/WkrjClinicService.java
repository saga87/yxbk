/**
 * 
 */
package com.wkrj.clinic.service;

import java.util.List;
import java.util.Map;








import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.wkrj.clinic.bean.WkrjClinic;
import com.wkrj.clinic.bean.WkrjClinicFile;

/**
 * @author linpeng
 * @date 2018年4月24日
 */
public interface WkrjClinicService {
	List<Map<String,Object>> listClinics(int offset,int rows,String clinic_name,String data_id,String clinic_type);
	long countClinics(String clinic_name,String data_id,String clinic_type);
	boolean addClinic(WkrjClinic clinic,WkrjClinicFile clinicFile);
	boolean updateClinic(WkrjClinic clinic,WkrjClinicFile clinicFile);
	boolean deleteClinic(HttpServletRequest request,String clinic_id);
	
	/**
	 * 获取附件列表
	 * @return
	 */
	List<Map<String,Object>> listFile(@Param("file_id")String file_id,@Param("clinic_id")String clinic_id);
	/**
	 * 删除附件信息
	 * @return
	 */
	boolean deleFile(HttpServletRequest request,@Param("file_id")String file_id,@Param("clinic_id")String clinic_id);
	
	
}
