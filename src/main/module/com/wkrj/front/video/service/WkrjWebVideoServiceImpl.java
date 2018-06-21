/**
 * 
 */
package com.wkrj.front.video.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wkrj.data.bean.WkrjData;
import com.wkrj.front.video.dao.WkrjWebVideoDao;

/**
 * @author linpeng
 * @date 2018年5月7日
 */
@Service("wkrjWebVideoService")
@Transactional
public class WkrjWebVideoServiceImpl implements WkrjWebVideoService{

	@Autowired
	private WkrjWebVideoDao DAO;
	
	@Override
	public List<Map<String, Object>> listNewVideo(int offset, int rows) {
		return DAO.listNewVideo(offset,rows);
	}

	@Override
	public List<WkrjData> getVideoData() {
		return DAO.getVideoData();
	}

	@Override
	public List<WkrjData> getVideoSecondData(String data_parent_id) {
		return DAO.getVideoSecondData(data_parent_id);
	}

	@Override
	public List<Map<String, Object>> listVideos(int offset, int rows,
			String data_id) {
		// TODO Auto-generated method stub
		return DAO.listVideos(offset, rows, data_id);
	}

	@Override
	public List<Map<String, Object>> getVideoById(String video_id) {
		// TODO Auto-generated method stub
		return DAO.getVideoById(video_id);
	}

	@Override
	public List<Map<String, Object>> getVideoFileById(String video_id) {
		// TODO Auto-generated method stub
		return DAO.getVideoFileById(video_id);
	}

	@Override
	public long countVideos(String video_name, String data_id) {
		// TODO Auto-generated method stub
		return DAO.countVideos(video_name, data_id);
	}

	@Override
	public long countRecomVideo() {
		// TODO Auto-generated method stub
		return DAO.countRecomVideo();
	}

	@Override
	public boolean addPlayTime(String video_id) {
		// TODO Auto-generated method stub
		return DAO.addPlayTime(video_id);
	}

	@Override
	public List<Map<String, Object>> searchVideos(int offset, int rows,
			String video_name) {
		return DAO.searchVideos(offset, rows, video_name);
	}
	
}
