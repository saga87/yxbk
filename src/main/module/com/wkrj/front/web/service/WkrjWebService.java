package com.wkrj.front.web.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wkrj.notice.bean.WkrjNotice;



/**
 * @author: wkrj_syg
 * @date:2018年3月14日 
 * 
 */
public interface WkrjWebService {

	/**
	 * 获取列表
	 * @param offset
	 * @param rows
	 * @return
	 */
	List<Map<String,Object>> listNotice(int offset,int rows);
	/**
	 * 视频列表
	 * @param video_name
	 * @param data_id
	 * @param speaker
	 * @return
	 */
	List<Map<String,Object>> listVideo(int offset,int rows,@Param("video_name")String video_name,@Param("data_id")String data_id,@Param("speaker")String speaker);
	/**
	 * 获取推荐视频
	 * @return
	 */
	List<Map<String,Object>> listRecommendVideo(int offset,int rows);
	/**
	 * 获取热门图书
	 * @return
	 */
	List<Map<String,Object>> listHotBooks(int offset,int rows);
	
	/**
	 * 获取公告
	 * @param notice_id
	 * @param notice_title
	 * @return
	 */
	WkrjNotice getNotice(String notice_id);

	long countNotice();

}
