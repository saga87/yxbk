package com.wkrj.front.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wkrj.front.web.dao.WkrjWebDao;
import com.wkrj.notice.bean.WkrjNotice;
import com.wkrj.notice.dao.WkrjNoticeDao;

import wkrjsystem.utils.Guid;
import wkrjsystem.utils.UtilsHelper;


/**
 * @author: wkrj_syg
 * @date:2018年3月14日 
 * 
 */
@Service("wkrjWebService")
@Transactional
public class WkrjWebServiceImpl implements WkrjWebService{
	
	@Autowired
	private WkrjWebDao DAO;
		
	@Override
	public List<Map<String, Object>> listNotice(int offset,int rows) {
		// TODO Auto-generated method stub
		return DAO.listNotice( offset, rows);
	}

	@Override
	public List<Map<String, Object>> listVideo(int offset,int rows,String video_name,
			String data_id, String speaker) {
		// TODO Auto-generated method stub
		return DAO.listVideo( offset, rows,video_name, data_id, speaker);
	}

	@Override
	public List<Map<String, Object>> listRecommendVideo(int offset,int rows) {
		// TODO Auto-generated method stub
		return DAO.listRecommendVideo( offset, rows);
	}

	@Override
	public List<Map<String, Object>> listHotBooks(int offset,int rows) {
		// TODO Auto-generated method stub
		return DAO.listHotBooks( offset, rows);
	}

	@Override
	public WkrjNotice getNotice(String notice_id) {
		// TODO Auto-generated method stub
		return DAO.getNotice(notice_id);
	}

	@Override
	public long countNotice() {
		return DAO.countNotice();
	}

	
}
