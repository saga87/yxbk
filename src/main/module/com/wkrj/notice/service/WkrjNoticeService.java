package com.wkrj.notice.service;

import java.util.List;
import java.util.Map;

import com.wkrj.notice.bean.WkrjNotice;



/**
 * @author: wkrj_syg
 * @date:2018年3月14日 
 * 
 */
public interface WkrjNoticeService {

	/**
	 * 获取列表
	 * @param offset
	 * @param rows
	 * @return
	 */
	List<Map<String,Object>> listNotice(int offset,int rows);
	/**
	 * 获取信息数量
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
	boolean deleNotice(String notice_id);



}
