package com.wkrj.video.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wkrj.data.bean.WkrjData;
import com.wkrj.video.bean.WkrjVideo;
import com.wkrj.video.bean.WkrjVideoFile;
import com.wkrj.video.dao.WkrjVideoDao;

import wkrjsystem.utils.FileUtils;
import wkrjsystem.utils.Guid;
import wkrjsystem.utils.UtilsHelper;


/**
 * @author: wkrj_syg
 * @date:2018年3月14日 
 * 
 */
@Service("wkrjVideoService")
@Transactional
public class WkrjVideoServiceImpl implements WkrjVideoService{
	
	@Autowired
	private WkrjVideoDao DAO;
		
	@Override
	public List<Map<String, Object>> listVideo(int offset,int rows,String video_name,String data_id) {
		// TODO Auto-generated method stub
		return DAO.listVideo( offset, rows, video_name,null, data_id);
	}

	@Override
	public long countVideo(String video_name,String data_id) {
		// TODO Auto-generated method stub
		return DAO.countVideo(  video_name , data_id);
	}

	@Override
	public boolean addVideo(WkrjVideo video,WkrjVideoFile filevideo) {
		// TODO Auto-generated method stub
		try {
			String video_id = Guid.getGuid();
			if ("1".equals(video.getRecommend())) {
				video.setRecommendtime(UtilsHelper.getDateFormatTime());
			}
			video.setVideo_id(video_id);
			video.setAddtime(UtilsHelper.getDateFormatTime());
			String path = filevideo.getFile_path();
			if (!"".equals(path) && path != null) {
				String [] names = filevideo.getFile_name().split(",");
				String [] paths = filevideo.getFile_path().split(",");
				String [] types = filevideo.getFile_type().split(",");
				for (int i = 0; i < paths.length; i++) {
					WkrjVideoFile file = new WkrjVideoFile();
					file.setVideo_id(video_id);
					file.setFile_id(Guid.getGuid());
					file.setFile_name(names[i]);
					file.setFile_path(paths[i]);
					file.setFile_type(types[i]);
					DAO.addVideoFile(file);
				}
			}
			DAO.addVideo(video);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateVideo(WkrjVideo video,WkrjVideoFile filevideo) {
		// TODO Auto-generated method stub
		try {
			//先删除数据之前的附件信息重新添加
			DAO.deleVideoFile(null, video.getVideo_id());
			String path = filevideo.getFile_path();
			if (!"".equals(path) && path != null) {
				String [] names = filevideo.getFile_name().split(",");
				String [] paths = filevideo.getFile_path().split(",");
				String [] types = filevideo.getFile_type().split(",");
				for (int i = 0; i < paths.length; i++) {
					WkrjVideoFile file = new WkrjVideoFile();
					file.setVideo_id(video.getVideo_id());
					file.setFile_id(Guid.getGuid());
					file.setFile_name(names[i]);
					file.setFile_path(paths[i]);
					file.setFile_type(types[i]);
					DAO.addVideoFile(file);
				}
			}
			if ("1".equals(video.getRecommend())) {
				video.setRecommendtime(UtilsHelper.getDateFormatTime());
			}else{
				video.setRecommendtime("");
			}
			DAO.updateVideo(video);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleVideo(HttpServletRequest request,String video_id) {
		// TODO Auto-generated method stub
		try {
			String idesString [] = video_id.split(",");
			for (int i = 0; i < idesString.length; i++) {
				String idString = idesString[i];
				this.deleFile(request, null, idString);
				DAO.deleVideo(idString);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public List<Map<String, Object>> listFile(String file_id, String video_id) {
		// TODO Auto-generated method stub
		return DAO.listVideoFile(file_id,video_id);
	}

	@Override
	public boolean deleFile(HttpServletRequest request, String file_id,
			String video_id) {
		// TODO Auto-generated method stub
		String ctxPath = request.getSession().getServletContext().getRealPath("/");
		if (file_id != null) {
			List<Map<String,Object>> list = DAO.listVideoFile(file_id, null);
			if (list.size()>0) {
				for (int i = 0; i < list.size(); i++) {
					String path = list.get(i).get("file_path").toString();
					FileUtils.lkh_delFile(String.format(ctxPath+"%s", path));
					if(!DAO.deleVideoFile(file_id, null)){
						return false;
					}
				}
				return true;
			}
		}
		if (video_id != null) {
			List<Map<String,Object>> booklist = DAO.listVideo(0, 10, null, video_id, null);
			if (booklist.get(0).containsKey("coverpath")) {
				String coverpath = booklist.get(0).get("coverpath").toString();
				FileUtils.lkh_delFile(String.format(ctxPath+"%s", coverpath));
			}
			List<Map<String,Object>> filelist = DAO.listVideoFile(null, video_id);
			if (filelist.size()>0) {
				for (int i = 0; i < filelist.size(); i++) {
					String path = filelist.get(i).get("file_path").toString();
					FileUtils.lkh_delFile(String.format(ctxPath+"%s", path));
				}
				return DAO.deleVideoFile(null, video_id);
			}
		}
		return true;
	}

	@Override
	public boolean updateRecommend(String video_id,String recommend) {
		// TODO Auto-generated method stub
		String idesString [] = video_id.split(",");
		for (int i = 0; i < idesString.length; i++) {
			String idString = idesString[i];
			WkrjVideo video = new WkrjVideo();
			video.setRecommend(recommend);
			video.setVideo_id(idString);
			video.setRecommendtime(UtilsHelper.getDateFormatTime());
			if(!DAO.updateRecommend(video)){
				return false;
			}
		}
		return true;
	}


	
}
