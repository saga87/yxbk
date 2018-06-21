package com.wkrj.video.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wkrj.video.bean.WkrjVideo;
import com.wkrj.video.bean.WkrjVideoFile;


/**
 * @author: wkrj_syg
 * @date:2018年3月14日 
 * 
 */
public interface WkrjVideoDao {

	/**
	 * 获取列表
	 * @param offset
	 * @param rows
	 * @return
	 */
	List<Map<String,Object>> listVideo(@Param("offset")int offset,@Param("rows")int rows,@Param("video_name")String video_name,
			@Param("video_id")String video_id,@Param("data_id")String data_id);
	/**
	 * 
	 * @return
	 */
	long  countVideo(@Param("video_name")String video_name,@Param("data_id")String data_id);
	/**
	 * 添加信息
	 * @param video
	 * @return
	 */
	boolean addVideo(WkrjVideo video);
	/**
	 * 更新信息
	 * @param video
	 * @return
	 */
	boolean updateVideo(WkrjVideo video);
	/**
	 * 设置推荐视频
	 * @param video
	 * @return
	 */
	boolean updateRecommend(WkrjVideo video);
	/**
	 * 删除信息
	 * @param video_id
	 * @return
	 */
	boolean deleVideo(@Param("video_id")String video_id);
	
	/**
	 * 获取附件列表
	 * @param video_id
	 * @return
	 */
	List<Map<String,Object>> listVideoFile(@Param("file_id")String file_id,@Param("video_id")String video_id);

	/**
	 * 添加附件信息
	 * @param videoFile
	 * @return
	 */
	boolean addVideoFile(WkrjVideoFile videoFile);
	/**
	 * 更新附件信息
	 * @param videoFile
	 * @return
	 */
	boolean updateVideoFile(WkrjVideoFile videoFile);
	/**
	 * 删除附件信息
	 * @param video_id
	 * @param file_id
	 * @return
	 */
	boolean deleVideoFile(@Param("file_id")String file_id,@Param("video_id")String video_id);
	
}
