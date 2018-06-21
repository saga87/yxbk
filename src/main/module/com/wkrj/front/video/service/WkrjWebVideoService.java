/**
 * 
 */
package com.wkrj.front.video.service;

import java.util.List;
import java.util.Map;

import com.wkrj.data.bean.WkrjData;

/**
 * @author linpeng
 * @date 2018年5月7日
 */
public interface WkrjWebVideoService {
	List<Map<String,Object>> listNewVideo(int offset,int rows);
	
	List<WkrjData> getVideoData();
	
	List<WkrjData> getVideoSecondData(String data_parent_id);
	
	List<Map<String,Object>> listVideos(int offset,int rows,String data_id);
	
	List<Map<String,Object>> searchVideos(int offset,int rows,String video_name);
	
	
	List<Map<String,Object>> getVideoById(String video_id);
	
	List<Map<String,Object>> getVideoFileById(String video_id);
	
	long countVideos(String video_name,String data_id); 
	
	long countRecomVideo();
	
	boolean addPlayTime(String video_id);
	
	
}
