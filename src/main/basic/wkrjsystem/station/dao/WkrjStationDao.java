package wkrjsystem.station.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import wkrjsystem.station.bean.WkrjStation;



public interface WkrjStationDao {
	
	public List<WkrjStation> getStationList(@Param("offset")int offset,@Param("page") int page);
	
	public WkrjStation getStationById(String id);
	
	public List<WkrjStation> getAllStationList();
	
	public long getStationListCount();
	
	/**
	 * 添加岗位
	 * @param station
	 * @return
	 */
	public boolean addStation(WkrjStation station);
	
	/**
	 * 修改岗位
	 * @param station
	 * @return
	 */
	public boolean updateStation(WkrjStation station);
	/**
	 * 删除岗位
	 * @param id
	 * @return
	 */
	public boolean delStation(String id);
	
}
