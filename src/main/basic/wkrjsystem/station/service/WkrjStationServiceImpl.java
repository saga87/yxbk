package wkrjsystem.station.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wkrjsystem.station.bean.WkrjStation;
import wkrjsystem.station.dao.WkrjStationDao;

@Service("wkrjStationService")
@Transactional
public class WkrjStationServiceImpl implements WkrjStationService {

	@Autowired
	private WkrjStationDao dao;
	
	
	@Override
	public List<WkrjStation> getStationList(int offset, int page) {
		return this.dao.getStationList(offset, page);
	}
	
	@Override
	public List<WkrjStation> getAllStationList() {
		return this.dao.getAllStationList();
	}
	
	@Override
	public boolean addStation(WkrjStation module) {
		return this.dao.addStation(module);
	}

	@Override
	public boolean updateStation(WkrjStation module) {
		return this.dao.updateStation(module);
	}

	@Override
	public boolean delStation(String id) {
		return dao.delStation(id);
	}

	@Override
	public long getStationList() {
		return dao.getStationListCount();
	}

	@Override
	public WkrjStation getStationById(String id) {
		return this.dao.getStationById(id);
	}
	
}
