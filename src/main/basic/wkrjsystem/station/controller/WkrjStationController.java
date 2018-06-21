package wkrjsystem.station.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wkrjsystem.station.bean.WkrjStation;
import wkrjsystem.station.service.WkrjStationService;
import wkrjsystem.utils.AjaxJson;
import wkrjsystem.utils.Guid;
import wkrjsystem.utils.UtilsHelper;

@Controller
@RequestMapping("wkrjsystem/wkrjStation")
public class WkrjStationController {
	
	@Autowired
	private WkrjStationService stationService;
	
	@RequestMapping("getPage")
	public String getPage(){
		
		return "system/station/station";
	}
	
	@RequestMapping("getAddPage")
	public String getAddPage(){
		
		return "system/station/station_add";
	}
	
	@RequestMapping("getStationList")
	@ResponseBody
//	public Object getStationList(int rows, int page){
//		
//		int offset = (page-1)*rows;
//		
//		List<WkrjStation> list = this.stationService.getStationList(offset,rows);
//		long count = this.stationService.getStationList();
//		
//		return UtilsHelper.returnMap(list,count);
//	}
	public Object getStationList(HttpServletRequest request){
		int page=Integer.parseInt(request.getParameter("page"));

		int pagesize=Integer.parseInt(request.getParameter("pagesize"));
		int offset = (page-1)*pagesize;
		List<WkrjStation> list = this.stationService.getStationList(offset, pagesize);
		long count = this.stationService.getStationList();
		
		return UtilsHelper.returnMap(list,count);
	}
	
	/**
	 * 获取所有的岗位
	 * @return
	 */
	@RequestMapping("getAllStationList")
	@ResponseBody
	public Object getAllStationList(){
		return this.stationService.getAllStationList();
	}
	
	@RequestMapping("addStation")
	@ResponseBody
	public AjaxJson addStation(WkrjStation perm){
		
		AjaxJson json = new AjaxJson();
		
		perm.setStation_id(Guid.getGuid());
		
		if(stationService.addStation(perm)){
			json.setSuccess(true);
			json.setMsg("保存成功");
		}
		
		return json;
	}
	
	@RequestMapping("updateStation")
	@ResponseBody
	public AjaxJson updateStation(WkrjStation module){
		
		AjaxJson json = new AjaxJson();
		
		if(stationService.updateStation(module)){
			json.setSuccess(true);
			json.setMsg("修改成功");
		}
		
		return json;
	}
	
	/**
	 * 删除角色，删除前需要先删除绑定的菜单和权限
	 * @param id
	 * @return
	 */
	
//	@RequiresPermissions("wkrjsystem/wkrjStation/delStation")
	@RequestMapping("delStation")
	@ResponseBody
	public AjaxJson delStation(String id){
		
		AjaxJson json = new AjaxJson();
		
		if(null!=id && !"".equals(id)){
			
			String[] id_ = id.split(",");
			
			for(int i=0;i<id_.length;i++){
				
				if(stationService.delStation(id_[i])){
					json.setSuccess(true);
					json.setMsg("删除成功");
				}
			}
		}
		
		return json;
	}
	
	
}
