/**
 * 
 */
package com.wkrj.front.video.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wkrj.data.bean.WkrjData;
import com.wkrj.front.video.service.WkrjWebVideoService;

/**
 * @author linpeng
 * @date 2018年5月7日
 */
@Controller
@RequestMapping("front/web/video")
public class WkrjWebVideoController {
	
	@Autowired
	private WkrjWebVideoService wkrjWebService;
	
	@RequestMapping("listNewVideo")
	@ResponseBody
	public Object listNewVideo(int page,int rows){
		int offset = (page-1)*rows;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = wkrjWebService.listNewVideo(offset, rows);
		return list;
	}
	
	
	@ResponseBody
	@RequestMapping("getVideoData")
	public Object getVideoData(){
		List<WkrjData> list = wkrjWebService.getVideoData();
		return list;
	}
	
	@ResponseBody
	@RequestMapping("getVideoSecondData")
	public Object getVideoSecondData(String data_parent_id){
		List<WkrjData> list = wkrjWebService.getVideoSecondData(data_parent_id);
		return list;
	}
	
	@RequestMapping("listVideos")
	@ResponseBody
	public Object listVideos(int page,int rows,String data_id){
		int offset = (page-1)*rows;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = wkrjWebService.listVideos(offset,rows,data_id);
		return list;
	}
	
	
	@RequestMapping("searchVideos")
	@ResponseBody
	public Object searchVideos(int page,int rows,String video_name){
		int offset = (page-1)*rows;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = wkrjWebService.searchVideos(offset,rows,video_name);
		return list;
	}
	
	
	@RequestMapping("countVideos")
	@ResponseBody
	public Object countVideos(String video_name,String data_id){
		return wkrjWebService.countVideos(video_name,data_id);
	}
	
	@RequestMapping("getVideoById")
	@ResponseBody
	public Object getVideoById(String video_id){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = wkrjWebService.getVideoById(video_id);
		return list;
	}
	
	
	@RequestMapping("getVideoFileById")
	@ResponseBody
	public Object getVideoFileById(String video_id){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = wkrjWebService.getVideoFileById(video_id);
		return list;
	}
	
	
	@RequestMapping("countRecomVideo")
	@ResponseBody
	public Object countRecomVideo(){
		return wkrjWebService.countRecomVideo();
	}
	
	@ResponseBody
	@RequestMapping("addPlayTime")
	public Object addPlayTime(String video_id){
		return wkrjWebService.addPlayTime(video_id);
	}
	
}
