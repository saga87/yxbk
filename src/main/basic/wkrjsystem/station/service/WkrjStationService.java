package wkrjsystem.station.service;

import java.util.List;

import wkrjsystem.station.bean.WkrjStation;


public interface WkrjStationService {
    
	
	public List<WkrjStation> getStationList(int offset, int page);
	
	public List<WkrjStation> getAllStationList();
	
	public WkrjStation getStationById(String id);
	
	public long getStationList();
	
	public boolean addStation(WkrjStation module);
	
	public boolean updateStation(WkrjStation module);
	
	public boolean delStation(String id);
	

}
