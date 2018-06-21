package com.wkrj.video.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.wkrj.video.bean.WkrjVideo;
import com.wkrj.video.bean.WkrjVideoFile;


/**
 * @author: wkrj_syg
 * @date:2018年3月14日 
 * 
 */
public interface WkrjVideoService {

	/**
	 * 获取列表
	 * @param offset
	 * @param rows
	 * @return
	 */
	List<Map<String,Object>> listVideo(int offset,int rows,String video_name,String data_id);
	/**
	 * 获取信息数量
	 * @return
	 */
	long  countVideo(String video_name,String data_id);
	/**
	 * 添加信息
	 * @param video
	 * @return
	 */
	boolean addVideo(WkrjVideo video,WkrjVideoFile filevideo);
	/**
	 * 更新信息
	 * @param video
	 * @return
	 */
	boolean updateVideo(WkrjVideo video,WkrjVideoFile filevideo);
	/**
	 * 设置推荐视频
	 * @param video
	 * @return
	 */
	boolean updateRecommend(String video_id,String recommend);
	/**
	 * 删除信息
	 * @param video_id
	 * @return
	 */
	boolean deleVideo(HttpServletRequest request,String video_id);

	/**
	 * 获取附件列表
	 * @param notice_id
	 * @return
	 */
	List<Map<String,Object>> listFile(@Param("file_id")String file_id,@Param("video_id")String video_id);
	/**
	 * 删除附件信息
	 * @param notice_id
	 * @return
	 */
	boolean deleFile(HttpServletRequest request,@Param("file_id")String file_id,@Param("video_id")String video_id);

}
