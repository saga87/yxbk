package com.wkrj.notice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wkrj.notice.bean.WkrjNotice;
import com.wkrj.notice.dao.WkrjNoticeDao;

import wkrjsystem.utils.Guid;
import wkrjsystem.utils.UtilsHelper;


/**
 * @author: wkrj_syg
 * @date:2018年3月14日 
 * 
 */
@Service("wkrjNoticeService")
@Transactional
public class WkrjNoticeServiceImpl implements WkrjNoticeService{
	
	@Autowired
	private WkrjNoticeDao DAO;
		
	@Override
	public List<Map<String, Object>> listNotice(int offset,int rows) {
		// TODO Auto-generated method stub
		return DAO.listNotice( offset, rows);
	}

	@Override
	public long countNotice() {
		// TODO Auto-generated method stub
		return DAO.countNotice(  );
	}

	@Override
	public boolean addNotice(WkrjNotice notice) {
		// TODO Auto-generated method stub
		try {
			String notice_id = Guid.getGuid();
			notice.setNotice_id(notice_id);
			notice.setAddtime(UtilsHelper.getDateFormatTime());
			DAO.addNotice(notice);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateNotice(WkrjNotice notice) {
		// TODO Auto-generated method stub
		try {
			DAO.updateNotice(notice);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleNotice(String notice_id) {
		// TODO Auto-generated method stub
		try {
			String idesString [] = notice_id.split(",");
			for (int i = 0; i < idesString.length; i++) {
				String idString = idesString[i];
				DAO.deleNotice(idString);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}


	
}
