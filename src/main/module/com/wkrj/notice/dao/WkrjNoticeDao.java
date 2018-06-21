package com.wkrj.notice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wkrj.notice.bean.WkrjNotice;



/**
 * @author: wkrj_syg
 * @date:2018年3月14日 
 * 
 */
public interface WkrjNoticeDao {

	/**
	 * 获取列表
	 * @param offset
	 * @param rows
	 * @return
	 */
	List<Map<String,Object>> listNotice(@Param("offset")int offset,@Param("rows")int rows);
	/**
	 * 
	 * @return
	 */
	long  countNotice();
	/**
	 * 添加信息
	 * @param notice
	 * @return
	 */
	boolean addNotice(WkrjNotice notice);
	/**
	 * 更新信息
	 * @param notice
	 * @return
	 */
	boolean updateNotice(WkrjNotice notice);
	/**
	 * 删除信息
	 * @param notice_id
	 * @return
	 */
	boolean deleNotice(@Param("notice_id")String notice_id);
	
}
