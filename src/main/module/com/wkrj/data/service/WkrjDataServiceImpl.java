package com.wkrj.data.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wkrjsystem.department.bean.WkrjDept;
import wkrjsystem.utils.AjaxJson;

import com.wkrj.data.bean.WkrjData;
import com.wkrj.data.dao.WkrjDataDao;



@Service("wkrjDataService")
public class WkrjDataServiceImpl implements WkrjDataService {

	@Autowired
	private WkrjDataDao dao;
	
	@Override
	public List<Map<String, Object>> getDataList(String data_parent_id) {
		List<Map<String,Object>> newlist = new ArrayList<Map<String,Object>>();
		List<WkrjData> dataList=this.dao.getDataTree(data_parent_id,null);
		for(WkrjData module : dataList){
			Map<String,Object> map = new HashMap<String, Object>();
			Map<String, Object> attributesMap = new HashMap<String, Object>();
			map.put("id", module.getData_id());
			map.put("pid", module.getData_parent_id());
			map.put("text", module.getData_name());
			attributesMap.put("data_parent_id", module.getData_parent_id());
			attributesMap.put("data_id", module.getData_id());
			attributesMap.put("data_name",module.getData_name());
			attributesMap.put("data_order",module.getData_order());
			attributesMap.put("data_show",module.getData_show());
			map.put("attributes", attributesMap);
			map.put("type", "data");
			
			//不是子节点
			if(this.dao.getDataTreeCount(module.getData_id())>0){
				map.put("isexpand", 2);
				map.put("children",this.getDataList(module.getData_id()));
				
			}
			newlist.add(map);
		}
		return newlist;
	}

	
	@Override
	public boolean addData(WkrjData data) {
		
		
		String maxId="01";
		if(null==data.getData_parent_id() || "".equals(data.getData_parent_id())){
			data.setData_parent_id("-1");
			//通过父id找到下面自己节点中最大的然后加1
			maxId = this.dao.getDataChildMaxByPid("-1");
			
		}else{
			maxId = this.dao.getDataChildMaxByPid(data.getData_parent_id());
			if("00".equals(maxId)){
				maxId = data.getData_parent_id()+maxId;
				data.setData_id(addOne(maxId));
			}
			//
		}
		//加上这句判断是为了修改时而加的
		if(null==data.getData_id() || "".equals(data.getData_id())){
			data.setData_id(addOne(maxId));
		}
		
		return dao.addData(data);
	}
	/**
	 * 在原有数字字符串的基础上加1
	 * @param testStr
	 * @return
	 */
	public String addOne(String testStr){
	    String[] strs = testStr.split("[^0-9]");//根据不是数字的字符拆分字符串
	    String numStr = strs[strs.length-1];//取出最后一组数字
	    if(numStr != null && numStr.length()>0){//如果最后一组没有数字(也就是不以数字结尾)，抛NumberFormatException异常
	        int n = numStr.length();//取出字符串的长度
	        long num = (Long.parseLong(numStr)+1L);//将该数字加一
	        String added = String.valueOf(num);
	        n = Math.min(n, added.length());
	        //拼接字符串
	        return testStr.subSequence(0, testStr.length()-n)+added;
	    }else{
	        throw new NumberFormatException();
	    }
	}
	@Override
	public boolean updateData(WkrjData data,String yId) {
		// TODO Auto-generated method stub
		return dao.updateData(data);
	}

	@Override
	public AjaxJson delData(String data_id) {
		// TODO Auto-generated method stub
		AjaxJson json = new AjaxJson();
		List<WkrjData> dataList=this.dao.getDataTree(null,data_id);
		String id = dataList.get(0).getData_parent_id();
		if ("-1".equals(id)) {
			json.setMsg("不允许删除");
			return json;
		}
		json.setSuccess(dao.delData(data_id));
		json.setMsg("删除成功");
		return json;
	}


	@Override
	public int getDataTreeCount(String data_parent_id) {
		// TODO Auto-generated method stub
		return dao.getDataTreeCount(data_parent_id);
	}

	@Override
	public WkrjData getDataNameById(String data_id) {
		// TODO Auto-generated method stub
		return dao.getDataNameById(data_id);
	}


	@Override
	public boolean updateDataShow(String data_id,String data_show) {
		// TODO Auto-generated method stub
		String idesString [] = data_id.split(",");
		for (int i = 0; i < idesString.length; i++) {
			String idString = idesString[i];
			WkrjData data= new WkrjData();
			data.setData_id(idString);
			data.setData_show(data_show);
			if(!dao.updateDataShow(data)){
				return false;
			}
		}
		return true;
	}

		

	

	
}
