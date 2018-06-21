package wkrjsystem.station.bean;

/**
 * 岗位模块表 对应数据库表：wkrj_sys_station
 * 
 * @author zhaoxiaohua
 * 
 */
public class WkrjStation {

	private String station_id;
	private String station_name;// 岗位名称

	public String getStation_id() {
		return station_id;
	}

	public void setStation_id(String station_id) {
		this.station_id = station_id;
	}

	public String getStation_name() {
		return station_name;
	}

	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}

}
