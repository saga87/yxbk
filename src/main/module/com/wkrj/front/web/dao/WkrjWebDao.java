package com.wkrj.front.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wkrj.notice.bean.WkrjNotice;



/**
 * @author: wkrj_syg
 * @date:2018年3月14日 
 * 
 */
public interface WkrjWebDao {

	/**
	 * 获取系统公告列表
	 * @return
	 */
	List<Map<String,Object>> listNotice(@Param("offset")int offset,@Param("rows")int rows);
	/**
	 * 视频列表
	 * @param video_name
	 * @param data_id
	 * @param speaker
	 * @return
	 */
	List<Map<String,Object>> listVideo(@Param("offset")int offset,@Param("rows")int rows,@Param("video_name")String video_name,@Param("data_id")String data_id,@Param("speaker")String speaker);
	/**
	 * 获取推荐视频
	 * @return
	 */
	List<Map<String,Object>> listRecommendVideo(@Param("offset")int offset,@Param("rows")int rows);
	/**
	 * 获取热门图书
	 * @return
	 */
	List<Map<String,Object>> listHotBooks(@Param("offset")int offset,@Param("rows")int rows);
	
	
	WkrjNotice getNotice(@Param("notice_id")String notice_id);
	
	long countNotice();
}
