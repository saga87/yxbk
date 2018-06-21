package com.wkrj.appstudent.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wkrjsystem.utils.AjaxJson;
import wkrjsystem.utils.Guid;
import wkrjsystem.utils.UtilsHelper;

import com.wkrj.appstudent.bean.AppStudent;
import com.wkrj.appstudent.service.AppStudentService;

@Controller
@RequestMapping("wkrj/appstudent")
public class AppStudentController {
	
	@Autowired
	private AppStudentService studentService;
	/*@Autowired
	private AppWeightService appweightService;*/
	
	/**
	 * mobile
	 * 修改学生信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("updateAppStudent")
	@ResponseBody
	public AjaxJson updateAppStudent(AppStudent model,HttpServletRequest request){	
		AjaxJson json = new AjaxJson();		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (studentService.updateAppStudent(model)) {
			/*appweightService.addWeight(Guid.getGuid(),model.getStudent_id(),model.getWeight(),sdf.format(new Date()));*/
			json.setSuccess(true);
			json.setMsg("修改成功");
		}else {
			json.setSuccess(false);
			json.setMsg("修改失败");
		}
		return json;
	}
	
	
	
	/**
	 * 查询学生列表
	 * @param request
	 * @return
	 */
	@RequestMapping("getStudentList")
	@ResponseBody
	public Object getStudentList(HttpServletRequest request){
		int page=Integer.parseInt(request.getParameter("page"));

		int pagesize=Integer.parseInt(request.getParameter("pagesize"));
		int offset = (page-1)*pagesize;
		List<AppStudent> list = this.studentService.getStudentList(offset, pagesize);
		long count = this.studentService.getStudentListCount();
		
		return UtilsHelper.returnMap(list,count);
	}
		
	/**
	 * 增加学生信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("addStudent")
	@ResponseBody
	public AjaxJson addStudent(AppStudent model,HttpServletRequest request){	
		AjaxJson json = new AjaxJson();		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long count = studentService.checkSNum(model.getStudent_num());	
		if (count==1) {
			json.setSuccess(false);
			json.setMsg("此学号已存在");
		}else {
			model.setInput_time(sdf.format(new Date()));
			model.setStudent_id(Guid.getGuid());
		if (studentService.addStudent(model)) {
			json.setSuccess(true);
			json.setMsg("添加成功");
		}else {
			json.setSuccess(false);
			json.setMsg("添加失败");
		}		
		}
		return json;
	}
	/**
	 * 修改学生信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("updateStudent")
	@ResponseBody
	public AjaxJson updateStudent(AppStudent model,HttpServletRequest request){	
		AjaxJson json = new AjaxJson();		
		String birthdate = request.getParameter("birthdate");
		model.setCs_date(birthdate);
		if (studentService.updateStudent(model)) {
			json.setSuccess(true);
			json.setMsg("修改成功");
		}else {
			json.setSuccess(false);
			json.setMsg("修改失败");
		}
		return json;
	}
	/**
	 * 删除学生
	 * @param student_id
	 * @return
	 */
	@RequestMapping("delStudent")
	@ResponseBody
	public AjaxJson delStudent(String id){	
		AjaxJson json = new AjaxJson();
		if (studentService.delStudent(id)) {
			json.setSuccess(true);
			json.setMsg("删除成功");
		}else {
			json.setSuccess(false);
			json.setMsg("删除失败");
		}		
		return json;
	}
	
	
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("getStudentInfo")
	@ResponseBody
	public Object getStudentInfo(HttpServletRequest request , String student_id){

		List<Map<String, Object>> list = this.studentService.getStudentInfo(student_id);

		
		return list;
	}
	
}

