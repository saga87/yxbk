package wkrjsystemdev.userdev.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wkrjsystem.station.bean.WkrjStation;
import wkrjsystem.utils.Guid;
import wkrjsystem.utils.MD5;
import wkrjsystemdev.roledev.bean.WkrjRoleDev;
import wkrjsystemdev.userdev.bean.WkrjUserDev;
import wkrjsystemdev.userdev.bean.WkrjUserRoleDev;
import wkrjsystemdev.userdev.dao.WkrjUserDevDao;

@Service("wkrjUserDevService")
@Transactional
public class WkrjUserDevServiceImpl implements WkrjUserDevService {

	@Autowired
	private WkrjUserDevDao dao;
	
	
	@Override
	public List<WkrjUserDev> getUserList(int offset, int page, String deptId, String userName) {
		return this.dao.getUserList(offset, page, deptId, userName);
	}
	
	@Override
	public List<Map<String,Object>> getRoleList() {
		
		List<Map<String,Object>> all = new ArrayList<Map<String,Object>>();//记录所有的module
		
		List<WkrjRoleDev> roleDevs = this.dao.getRoleTree();
		
		for(WkrjRoleDev roleDev : roleDevs){
			
			Map<String,Object> map = new HashMap<String,Object>();
			//Map<String,Object> attributesMap = new HashMap<String,Object>();
			
			map.put("id", roleDev.getRole_id());
			//map.put("pid", module.getDept_parent_id());
			map.put("text", roleDev.getRole_name());
			
			all.add(map);
		}
		
		return all;
		
	}
	
	@Override
	public List<Map<String,Object>> getStationList() {
		
		List<Map<String,Object>> all = new ArrayList<Map<String,Object>>();//记录所有的module
		
		List<WkrjStation> stations = this.dao.getStationTree();
		
		for(WkrjStation station : stations){
			
			Map<String,Object> map = new HashMap<String,Object>();
			
			map.put("id", station.getStation_id());
			map.put("text", station.getStation_name());
			
			all.add(map);
		}
		
		return all;
		
	}
	
	/**
	 * 检测用户名、工号以及身份证是否重复返回
	 * @param name
	 * @param gh
	 * @param sfz
	 * @param userId
	 * @return
	 */
	public long checkIsHaveNameOrGhOrSfz(String name,String gh,String sfz,String userId){
		return this.dao.checkIsHaveNameOrGhOrSfz(name, gh, sfz,userId);
	}
	
	/**
	 * 添加用户
	 * 先添加角色保存成功后再添加用户信息
	 * @param module
	 * @return
	 */
	@Override
	public String addUser(WkrjUserDev module) {
		
		//首先查询一下是否有重复的用户名、身份证号、工号，有的话用户名返回1，身份证号返回2，工号返回3正常返回0,其他返回4
		String str="4";
		String userId = Guid.getGuid();
		
		if(0<checkIsHaveNameOrGhOrSfz(module.getUser_name(),null,null,userId)){
			str="1";
			return str;
		}
		if(0<checkIsHaveNameOrGhOrSfz(null,null,module.getUser_card(),userId)){
			str="2";
			return str;
		}
		/*if(0<checkIsHaveNameOrGhOrSfz(null,module.getUser_no(),null,userId)){
			str="3";
			return str;
		}*/
		
		//密码加密
		module.setUser_password(MD5.MD5Encode(module.getUser_password()));
		module.setUser_accounttype(0);
		
		List<WkrjRoleDev> userRoles = module.getUser_role();
		boolean sus=true;
		
		if(null!=userRoles && userRoles.size()>0){
			for(WkrjRoleDev userRole:userRoles){
				
				String roleIds = userRole.getRole_id();
				if(null!=roleIds && !"".equals(roleIds)){
					
					//for(int i=0;i<roleIds.split(",").length;i++){
					for(int i=0;i<roleIds.split(",|;").length;i++){
						
						WkrjUserRoleDev ur = new WkrjUserRoleDev();
						ur.setUser_id(userId);
						//ur.setRole_id(roleIds.split(",")[i]);
						ur.setRole_id(roleIds.split(",|;")[i]);
						sus = this.dao.addUserRole(ur);
					}
					
				}
				
				if(!sus){
					return "4";
				}
			}
		}
		
		if(sus){
			
			module.setUser_id(userId);
			if(this.dao.addUser(module)){
				str="0";
			}
		}
		
		return str;
	}

	/**
	 * 先删除角色再重新添加以免重复
	 * 操作方面参考保存操作
	 */
	@Override
	public String updateUser(WkrjUserDev module) {
		
		String userId = module.getUser_id();
		String str="4";
		
		if(0<checkIsHaveNameOrGhOrSfz(module.getUser_name(),null,null,userId)){
			str="1";
			return str;
		}
		if(0<checkIsHaveNameOrGhOrSfz(null,null,module.getUser_card(),userId)){
			str="2";
			return str;
		}
//		if(0<checkIsHaveNameOrGhOrSfz(null,module.getUser_no(),null,userId)){
//			str="3";
//			return str;
//		}
		
		List<WkrjRoleDev> userRoles = module.getUser_role();
		boolean sus=true;
		
		this.dao.delUerRoleById(userId);//删除角色
		
		if(null!=userRoles && userRoles.size()>0){
			for(WkrjRoleDev userRole:userRoles){
				
				String roleIds = userRole.getRole_id();
				if(null!=roleIds && !"".equals(roleIds)){
					
					//for(int i=0;i<roleIds.split(",").length;i++){
					for(int i=0;i<roleIds.split(",|;").length;i++){
						
						WkrjUserRoleDev ur = new WkrjUserRoleDev();
						ur.setUser_id(userId);
						//ur.setRole_id(roleIds.split(",")[i]);
						ur.setRole_id(roleIds.split(",|;")[i]);
						sus = this.dao.addUserRole(ur);
					}
					
				}
				
				if(!sus){
					return "4";
				}
			}
		}
		
		if(sus){
			if(this.dao.updateUser(module)){
				str="0";
			}
		}
		
		return str;
		
	}

	/**
	 * 先删除用户角色再删除用户本身信息
	 */
	@Override
	public boolean delUser(String id) {
		
		this.dao.delUerRoleById(id);
		
		return dao.delUser(id);
	}
	
	@Override
	public boolean forbiddenUser(String id,String flag) {
		
		return dao.forbiddenUser(id,flag);
	}

	@Override
	public long getUserList(String deptId,String userName) {
		return dao.getUserListCount( deptId,userName);
	}

	@Override
	public boolean repeatPw(String id, String pw) {
		return this.dao.repeatPw(id, MD5.MD5Encode(pw));
	}

	@Override
	public WkrjUserDev findUserInfoByName(String userName,String pw) {
		return this.dao.findUserInfoByNameandPw(userName,pw);
	}
	
	@Override
	public WkrjUserDev findUserInfoByName(String userName) {
		return this.dao.findUserInfoByName(userName);
	}
	
}
