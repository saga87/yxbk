package com.wkrj.front.web.controller;

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

import com.wkrj.front.web.service.WkrjWebService;
import com.wkrj.notice.bean.WkrjNotice;
import com.wkrj.notice.service.WkrjNoticeService;

/**
 * @author: wkrj_syg
 * @date:2018年5月5日 
 * 
 */
@Controller
@RequestMapping("front/web/")
public class WkrjWebConreoller {

	@Autowired
	private WkrjWebService wkrjWebService;
	
	
	@RequestMapping("listNotice")
	@ResponseBody
	public Object listNotice(int page,int rows){
		int offset = (page-1)*rows;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = wkrjWebService.listNotice(offset, rows);
		return list;
	}
	
	@RequestMapping("countNotice")
	@ResponseBody
	public Object countNotice(){
		return wkrjWebService.countNotice();
	}
	
	@RequestMapping("listRecommendVideo")
	@ResponseBody
	public Object listRecommendVideo(int page,int rows){
		int offset = (page-1)*rows;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = wkrjWebService.listRecommendVideo(offset, rows);
		return list;
	}
	
	@RequestMapping("listHotBooks")
	@ResponseBody
	public Object listHotBooks(int page,int rows){
		int offset = (page-1)*rows;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = wkrjWebService.listHotBooks(offset, rows);
		return list;
	}
	
	@RequestMapping("listVideo")
	@ResponseBody
	public Object listVideo(int page,int rows,String video_name,String data_id,String speaker){
		int offset = (page-1)*rows;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = wkrjWebService.listVideo(offset, rows, video_name, data_id, speaker);
		return list;
	}
	
	@RequestMapping("getNoticeById")
	@ResponseBody
	public Object getNotice(String notice_id){
		WkrjNotice notice = wkrjWebService.getNotice(notice_id);
		return notice;
	}
	
	
}
