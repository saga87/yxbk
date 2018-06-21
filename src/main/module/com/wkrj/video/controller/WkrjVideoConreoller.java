package com.wkrj.video.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wkrjsystem.department.service.WkrjDeptService;
import wkrjsystem.user.bean.WkrjUser;
import wkrjsystem.utils.AjaxJson;
import wkrjsystem.utils.FileUtils;
import wkrjsystem.utils.UtilsHelper;
import wkrjsystemdev.userdev.bean.WkrjUserDev;

import com.mysql.fabric.xmlrpc.base.Array;
import com.wkrj.data.service.WkrjDataService;
import com.wkrj.video.bean.WkrjVideo;
import com.wkrj.video.bean.WkrjVideoFile;
import com.wkrj.video.service.WkrjVideoService;

/**
 * @author: wkrj_syg
 * @date:2018年3月14日 
 * 
 */
@Controller
@RequestMapping("video/WkrjVideoController")
public class WkrjVideoConreoller {

	@Autowired
	private WkrjVideoService wkrjVideoService;
	
	@Autowired
	private WkrjDataService wkrjDataService;
	
	@RequestMapping("listVideo")
	@ResponseBody
	public Object listVideo(int pagesize, int page,String video_name,String sortorder,String sortname,String data_id){
		int offset = (page-1) * pagesize;
		long count = wkrjVideoService.countVideo( video_name , data_id);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if (count>0) {
			list = wkrjVideoService.listVideo(offset, pagesize, video_name, data_id);
			return UtilsHelper.returnMap(list, count);
		}
		return UtilsHelper.returnMap(list, list.size());
	}
	
	@RequestMapping("listVideoFile")
	@ResponseBody
	public Object listVideoFile(String video_id){
		return wkrjVideoService.listFile(null, video_id);
	}
	/**
	 * 添加信息
	 * @param video
	 * @return
	 */
	@RequestMapping("addVideo")
	@ResponseBody
	public Object addVideo(HttpServletRequest request,WkrjVideo video,WkrjVideoFile filevideo){
		AjaxJson json = new AjaxJson();
		if (wkrjVideoService.addVideo(video, filevideo)) {
			json.setSuccess(true);
			json.setMsg("添加成功");
		}
		return json;
			
	}
	/**
	 * 更新信息
	 * @param video
	 * @return
	 */
	@RequestMapping("updateVideo")
	@ResponseBody
	public Object updateVideo(WkrjVideo video,WkrjVideoFile filevideo){
		AjaxJson json = new AjaxJson();
		if (wkrjVideoService.updateVideo(video, filevideo)) {
			json.setSuccess(true);
			json.setMsg("更新成功");
		}
		return json;
		
	}
	/**
	 * 设置推荐视频
	 * @param video_id
	 * @param recommend
	 * @return
	 */
	@RequestMapping("updateRecommend")
	@ResponseBody
	public Object updateRecommend(String video_id,String recommend) {
		AjaxJson json = new AjaxJson();
		if (wkrjVideoService.updateRecommend(video_id, recommend)) {
			json.setSuccess(true);
			json.setMsg("设置成功");
		}
		return json;
		
	}
	/**
	 * 删除信息
	 * @param video_id
	 * @return
	 */
	@RequestMapping("deleVideo")
	@ResponseBody
	public Object deleVideo(HttpServletRequest request,String video_id,String path){
		AjaxJson json = new AjaxJson();
		if (wkrjVideoService.deleVideo( request,video_id)) {
			json.setSuccess(true);
			json.setMsg("删除成功");
		}
		return json;
		
	}

	@RequestMapping("uploadFile")
	@ResponseBody
	public Object uploadFile(HttpServletRequest request){
		List<Map<String, String>> list = FileUtils.lkh_uploadFile(request, "video");
		return list;
	}
	
	@RequestMapping("deleFile")
	@ResponseBody
	public Object deleFile(HttpServletRequest request,String path){
		AjaxJson json = new AjaxJson();
		String ctxPath = request.getSession().getServletContext().getRealPath("/");
		if(!"".equals(path) && path != null){
			FileUtils.lkh_delFile(String.format(ctxPath+"%s", path));
			json.setSuccess(true);
			json.setMsg("删除成功");
			return json;
		}
		return json;
	}
	/**
	 * 获取数据字典
	 * @param data_parent_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getDataTree")
	public Object getDataTree(String data_parent_id ){
		List<Map<String,Object>> list = wkrjDataService.getDataList(data_parent_id);
		return list;
		
	}
}
