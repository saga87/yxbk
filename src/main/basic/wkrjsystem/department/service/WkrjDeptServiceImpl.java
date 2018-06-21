package wkrjsystem.department.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wkrjsystem.department.bean.WkrjDept;
import wkrjsystem.department.dao.WkrjDeptDao;
import wkrjsystem.menu.dao.WkrjMenuDao;

@Service("wkrjDeptService")
@Transactional
public class WkrjDeptServiceImpl implements WkrjDeptService {

	@Autowired
	private WkrjDeptDao dao;
	
	@Autowired
	private WkrjMenuDao menuDao;
	
	@Override
	public List<Map<String,Object>> getDeptAndSchoolGridList(String parent_id,boolean isGly,String user_dept){
		List<Map<String,Object>> newlist=new ArrayList<Map<String,Object>>();
		
		List<WkrjDept> deptList = this.dao.getDeptTree(parent_id, isGly, user_dept);
		for(WkrjDept module : deptList){
			
			Map<String,Object> map = new HashMap<String,Object>();
			Map<String,Object> attributesMap = new HashMap<String,Object>();
			
			map.put("id", module.getDept_id());
			map.put("pid", module.getDept_parent_id());
			map.put("text", module.getDept_name());
			attributesMap.put("dept_parent_id", module.getDept_parent_id());
			attributesMap.put("dept_other", module.getDept_other());
			attributesMap.put("dept_order", module.getDept_order());
			attributesMap.put("dept_id", module.getDept_id());
			attributesMap.put("dept_name", module.getDept_name());
			map.put("attributes", attributesMap);
			map.put("type", "dept");
									
			//不是叶子节点
			if(this.dao.getDeptChildCount(module.getDept_id())>0){
				map.put("isexpand", 2);
				map.put("children", this.getDeptAndSchoolGridList(module.getDept_id(),isGly,module.getDept_id()));
			}
			
			newlist.add(map);
		}
		return newlist;
	}
	
	
	@Override
	public List<Map<String, Object>> getRoleList(String parent_id,boolean isGly,String user_dept) {
		List<Map<String,Object>> newlist=new ArrayList<Map<String,Object>>();
		if(isGly){
			newlist = this.getRoleList2( parent_id, isGly, user_dept);
		}else{
			WkrjDept module=dao.getDeptNameById(user_dept);
	
			HashMap<String, Object> map=new HashMap<String,Object>();
			map.put("id", module.getDept_id());
			map.put("pid", module.getDept_parent_id());
			map.put("text", module.getDept_name());
			
			int count=dao.getDeptChildCount(user_dept);
			if(count >0){
				//该节点下有子节点
				List<Map<String, Object>> listchild=this.getRoleList2( parent_id, isGly, user_dept);
				map.put("children", listchild);
				//map.put("isexpand", true);
			}
			newlist.add(map);	
		}	
		
		return newlist;
	}	

	
	public List<Map<String,Object>> getRoleList2(String parent_id,boolean isGly,String user_dept) {
		
		List<Map<String,Object>> all = new ArrayList<Map<String,Object>>();//记录所有的module
		
		List<WkrjDept> leftMenu = this.dao.getDeptTree(parent_id, isGly, user_dept);
		
		for(WkrjDept module : leftMenu){
			
			Map<String,Object> map = new HashMap<String,Object>();
			Map<String,Object> attributesMap = new HashMap<String,Object>();
			
			map.put("id", module.getDept_id());
			map.put("pid", module.getDept_parent_id());
			map.put("text", module.getDept_name());
			attributesMap.put("dept_parent_id", module.getDept_parent_id());
			attributesMap.put("dept_other", module.getDept_other());
			attributesMap.put("dept_order", module.getDept_order());
			attributesMap.put("dept_id", module.getDept_id());
			attributesMap.put("dept_name", module.getDept_name());
			map.put("attributes", attributesMap);
			
			//不是叶子节点
			if(this.dao.getDeptChildCount(module.getDept_id())>0){
				map.put("isexpand", 2);
				map.put("children", this.getRoleList2(module.getDept_id(),isGly,module.getDept_id()));
			}
			
			all.add(map);
		}
		
		return all;
		
	}

	@Override
	public boolean addDept(WkrjDept dept) {
		
		//dept.setDept_id(dept_id);
		String maxId="01";
		if(null==dept.getDept_parent_id() || "".equals(dept.getDept_parent_id())){
			dept.setDept_parent_id("-1");
			//通过父id找到下面自己节点中最大的然后加1
			maxId = this.dao.getDeptChildMaxByPid("-1");
			
		}else{
			maxId = this.dao.getDeptChildMaxByPid(dept.getDept_parent_id());
			if("00".equals(maxId)){
				maxId = dept.getDept_parent_id()+maxId;
				dept.setDept_id(addOne(maxId));
			}
			//
		}
		//加上这句判断是为了修改时而加的
		if(null==dept.getDept_id() || "".equals(dept.getDept_id())){
			dept.setDept_id(addOne(maxId));
		}
		
		return this.dao.addDept(dept);
	}
	
	@Override
	public boolean updateDept(WkrjDept dept,String yId) {
		
		//由于id比较特殊先删除再添加
		//if(this.dao.delDept(yId)){
			return this.dao.updateDept(dept);
		//}
		
		//return false;
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
	public boolean delDept(String id) {
		return this.dao.delDept(id);
	}

	@Override
	public int getDeptChildCount(String parentId) {
		
		return this.dao.getDeptChildCount(parentId);
	}

	@Override
	public WkrjDept getDeptNameById(String deptId) {
		return this.dao.getDeptNameById(deptId);
	}


}
