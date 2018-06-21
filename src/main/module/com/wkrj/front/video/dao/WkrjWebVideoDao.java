/**
 * 
 */
package com.wkrj.front.video.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wkrj.data.bean.WkrjData;

/**
 * @author linpeng
 * @date 2018年5月7日
 */
public interface WkrjWebVideoDao {
	List<Map<String,Object>> listNewVideo(@Param("offset")int offset,@Param("rows")int rows);
	
	List<WkrjData> getVideoData();
	
	List<WkrjData> getVideoSecondData(@Param("data_parent_id")String data_parent_id);
	
	List<Map<String, Object>> listVideos(@Param("offset")int offset,@Param("rows")int rows,@Param("data_id")String data_id);
	
	List<Map<String, Object>> searchVideos(@Param("offset")int offset,@Param("rows")int rows,@Param("video_name")String video_name);
	
	List<Map<String,Object>> getVideoById(@Param("video_id")String video_id);
	
	List<Map<String,Object>> getVideoFileById(@Param("video_id")String video_id);
	
	long countVideos(@Param("video_name") String video_name,@Param("data_id")String data_id); 
	
	long countRecomVideo();
	
	boolean addPlayTime(@Param("video_id")String video_id); 
}
