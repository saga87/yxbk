/**
 * 
 */
package com.wkrj.clinic.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wkrjsystem.utils.FileUtils;
import wkrjsystem.utils.Guid;
import wkrjsystem.utils.UtilsHelper;

import com.wkrj.clinic.bean.WkrjClinic;
import com.wkrj.clinic.bean.WkrjClinicFile;
import com.wkrj.clinic.dao.WkrjClinicDao;

/**
 * @author linpeng
 * @date 2018年4月24日
 */

@Service("wkrjClinicService")
@Transactional
public class WkrjClinicServiceImpl implements WkrjClinicService {
	
	@Autowired
	private WkrjClinicDao dao;
	
	@Override
	public List<Map<String, Object>> listClinics(int offset, int rows,String clinic_name,String data_id,String clinic_type) {
		return dao.listClinics(offset, rows,clinic_name,data_id,clinic_type);
	}

	
	@Override
	public long countClinics(String clinic_name,String data_id,String clinic_type) {
		return dao.countClinics(clinic_name,data_id,clinic_type);
	}

	
	@Override
	public boolean addClinic(WkrjClinic clinic,WkrjClinicFile clinicFile) {
		try{
			String clinic_id = Guid.getGuid();
			clinic.setClinic_id(clinic_id);
			clinic.setAddtime(UtilsHelper.getDateFormatTime());
			String path = clinicFile.getFile_path();
			if (!"".equals(path) && path != null) {
				String [] names = clinicFile.getFile_name().split(",");
				String [] paths = clinicFile.getFile_path().split(",");
				String [] types = clinicFile.getFile_type().split(",");
				for (int i = 0; i < paths.length; i++) {
					WkrjClinicFile file = new WkrjClinicFile();
					file.setClinic_id(clinic_id);
					file.setFile_id(Guid.getGuid());
					file.setFile_name(names[i]);
					file.setFile_path(paths[i]);
					file.setFile_type(types[i]);
					dao.addClinicFile(file);//添加附件
				}
			}
			dao.addClinic(clinic);//添加诊疗指南
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e);
			return false;
		}
		return true;
	}

	
	@Override
	public boolean updateClinic(WkrjClinic clinic,WkrjClinicFile clinicFile) {
		try {
			//删除与其绑定的附件，再重新添加附件
			dao.deleteClinicFile(null, clinic.getClinic_id());
			String path = clinicFile.getFile_path();
			if(!"".equals(path) && path != null){
				String [] names = clinicFile.getFile_name().split(",");
				String [] paths = clinicFile.getFile_path().split(",");
				String [] types = clinicFile.getFile_type().split(",");
				for (int i = 0; i < paths.length; i++) {
					WkrjClinicFile file = new WkrjClinicFile();
					file.setClinic_id(clinic.getClinic_id());
					file.setFile_id(Guid.getGuid());
					file.setFile_name(names[i]);
					file.setFile_path(paths[i]);
					file.setFile_type(types[i]);
					dao.addClinicFile(file);
				}
			}
			dao.updateClinic(clinic);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	@Override
	public boolean deleteClinic(HttpServletRequest request,String clinic_id) {
		try {
			String ids[] = clinic_id.split(",");
			for(int i=0;i<ids.length;i++){
				this.deleFile(request, null, ids[i]);
				dao.deleteClinic(ids[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
		return true;
	}


	
	@Override
	public List<Map<String, Object>> listFile(String file_id, String clinic_id) {
		return dao.listClinicFile(file_id, clinic_id);
	}


	
	@Override
	public boolean deleFile(HttpServletRequest request, String file_id,
			String clinic_id) {
		String ctxPath = request.getSession().getServletContext().getRealPath("/");
		if (file_id != null) {
			List<Map<String,Object>> list = dao.listClinicFile(file_id, null);
			if (list.size()>0) {
				String path = list.get(0).get("file_path").toString();
				FileUtils.lkh_delFile(String.format(ctxPath+"%s", path));
				return dao.deleteClinicFile(file_id, null);
			}
		}
		if (clinic_id != null) {
			List<Map<String,Object>> list = dao.listClinicFile(null, clinic_id);
			if (list.size()>0) {
				for (int i = 0; i < list.size(); i++) {
					String path = list.get(i).get("file_path").toString();
					FileUtils.lkh_delFile(String.format(ctxPath+"%s", path));
				}
				return dao.deleteClinicFile(null, clinic_id);
			}
		}
		return true;
	}



}
