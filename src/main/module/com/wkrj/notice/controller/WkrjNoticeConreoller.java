package com.wkrj.notice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wkrjsystem.utils.AjaxJson;
import wkrjsystem.utils.UtilsHelper;

import com.wkrj.notice.bean.WkrjNotice;
import com.wkrj.notice.service.WkrjNoticeService;

/**
 * @author: wkrj_syg
 * @date:2018年3月14日 
 * 
 */
@Controller
@RequestMapping("notice/WkrjNoticeController")
public class WkrjNoticeConreoller {

	@Autowired
	private WkrjNoticeService wkrjNoticeService;
	
	
	@RequestMapping("listNotice")
	@ResponseBody
	public Object listNotice(int pagesize, int page,String notice_code,
			String exhibition_name,String notice_name,String sortorder,String sortname){
		int offset = (page-1) * pagesize;
		long count = wkrjNoticeService.countNotice( );
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if (count>0) {
			list = wkrjNoticeService.listNotice(offset, pagesize);
			return UtilsHelper.returnMap(list, count);
		}
		return UtilsHelper.returnMap(list, list.size());
	}
	/**
	 * 添加信息
	 * @param notice
	 * @return
	 */
	@RequestMapping("addNotice")
	@ResponseBody
	public Object addNotice(HttpServletRequest request,WkrjNotice notice){
		AjaxJson json = new AjaxJson();
		if (wkrjNoticeService.addNotice(notice)) {
			json.setSuccess(true);
			json.setMsg("添加成功");
		}
		return json;
			
	}
	/**
	 * 更新信息
	 * @param notice
	 * @return
	 */
	@RequestMapping("updateNotice")
	@ResponseBody
	public Object updateNotice(WkrjNotice notice){
		AjaxJson json = new AjaxJson();
		if (wkrjNoticeService.updateNotice(notice)) {
			json.setSuccess(true);
			json.setMsg("更新成功");
		}
		return json;
		
	}
	/**
	 * 删除信息
	 * @param notice_id
	 * @return
	 */
	@RequestMapping("deleNotice")
	@ResponseBody
	public Object deleNotice(String notice_id){
		AjaxJson json = new AjaxJson();
		if (wkrjNoticeService.deleNotice(notice_id)) {
			json.setSuccess(true);
			json.setMsg("删除成功");
		}
		return json;
		
	}

}
