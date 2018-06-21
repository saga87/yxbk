/**
 * 
 */
package com.wkrj.front.clinic.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wkrj.data.bean.WkrjData;
import com.wkrj.front.clinic.dao.WkrjWebClinicDao;

/**
 * @author linpeng
 * @date 2018年5月5日
 */
@Service("wkrjWebClinicService")
@Transactional
public class WkrjWebClinicServiceImpl implements WkrjWebClinicService{
	@Autowired
	private WkrjWebClinicDao DAO;
	@Override
	public List<Map<String, Object>> listClinics(int offset, int rows,
			String data_id) {
		return DAO.listClinics(offset, rows, data_id);
	}

	@Override
	public List<Map<String, Object>> getClinicById(int offset, int rows,
			String clinic_id) {
		return DAO.getClinicById(offset, rows, clinic_id);
	}

	@Override
	public List<Map<String, Object>> getClinicFileById(String clinic_id) {
		return DAO.getClinicFileById(clinic_id);
	}

	@Override
	public long countClinics(String clinic_name,String data_id) {
		return DAO.countClinics(clinic_name, data_id);
	}

	@Override
	public List<WkrjData> getData() {
		return DAO.getData();
	}

	@Override
	public List<WkrjData> getSecondData(String data_parent_id) {
		return DAO.getSecondData(data_parent_id);
	}
	
	@Override
	public boolean addPageviews(String clinic_id) {
		return DAO.addPageviews(clinic_id);
	}

	
	@Override
	public WkrjData getDataById(String data_id) {
		return DAO.getDataById(data_id);
	}

	@Override
	public List<Map<String, Object>> searchClinics(int offset, int rows,
			String clinic_name) {
		// TODO Auto-generated method stub
		return DAO.searchClinics(offset, rows, clinic_name);
	}
}
