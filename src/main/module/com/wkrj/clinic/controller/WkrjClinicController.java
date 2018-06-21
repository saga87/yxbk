/**
 * 
 */
package com.wkrj.clinic.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wkrjsystem.utils.AjaxJson;
import wkrjsystem.utils.FileUtils;
import wkrjsystem.utils.UtilsHelper;

import com.wkrj.clinic.bean.WkrjClinic;
import com.wkrj.clinic.bean.WkrjClinicFile;
import com.wkrj.clinic.service.WkrjClinicService;
import com.wkrj.data.service.WkrjDataService;

/**
 * @author linpeng
 * @date 2018年4月24日
 */

@Controller
@RequestMapping("clinic/WkrjClinicController")
public class WkrjClinicController {

	@Autowired
	private WkrjClinicService clinicService;

	@Autowired
	private WkrjDataService wkrjDataService;

	@RequestMapping("listClinics")
	@ResponseBody
	public Object listClinics(int pagesize, int page, String clinic_name,String data_id,String clinic_type) {
		int offset = (page - 1) * pagesize;
		long count = clinicService.countClinics(clinic_name,data_id,clinic_type);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (count > 0) {
			list = clinicService.listClinics(offset, pagesize, clinic_name,data_id,clinic_type);
			return UtilsHelper.returnMap(list, count);
		}
		return UtilsHelper.returnMap(list, list.size());
	}

	@RequestMapping("listClinicFile")
	@ResponseBody
	public Object listClinicFile(String clinic_id) {
		return clinicService.listFile(null, clinic_id);
	}

	@RequestMapping("addClinic")
	@ResponseBody
	public Object addClinic(WkrjClinic clinic, WkrjClinicFile clinicFile) {
		AjaxJson json = new AjaxJson();
		if (clinicService.addClinic(clinic, clinicFile)) {
			json.setSuccess(true);
			json.setMsg("添加成功");
		}
		return json;
	}

	@RequestMapping("updateClinic")
	@ResponseBody
	public Object updateClinic(WkrjClinic clinic, WkrjClinicFile clinicFile) {
		AjaxJson json = new AjaxJson();
		if (clinicService.updateClinic(clinic, clinicFile)) {
			json.setSuccess(true);
			json.setMsg("更新成功");
		}
		return json;
	}

	@RequestMapping("deleteClinic")
	@ResponseBody
	public Object deleteClinic(HttpServletRequest request, String clinic_id) {
		AjaxJson json = new AjaxJson();
		if (clinicService.deleteClinic(request, clinic_id)) {
			json.setSuccess(true);
			json.setMsg("删除成功");
		}
		return json;
	}

	@RequestMapping("uploadFile")
	@ResponseBody
	public Object uploadFile(HttpServletRequest request) {
		List<Map<String, String>> list = FileUtils.lkh_uploadFile(request,
				"clinic");
		return list;
	}

	@RequestMapping("deleteFile")
	@ResponseBody
	public Object deleteFile(HttpServletRequest request, String path) {
		AjaxJson json = new AjaxJson();
		String ctxPath = request.getSession().getServletContext()
				.getRealPath("/");
		if (!"".equals(path) && path != null) {
			FileUtils.lkh_delFile(String.format(ctxPath + "%s", path));
			json.setSuccess(true);
			json.setMsg("删除成功");
			return json;
		}
		return json;
	}

	@RequestMapping("getData")
	@ResponseBody
	public Object getDataType() {
		List<Map<String, Object>> list = wkrjDataService.getDataList("02");
		return list;
	}

}
