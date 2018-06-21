package wkrjsystem.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wkrjsystem.role.bean.WkrjRole;
import wkrjsystem.user.bean.WkrjUser;
import wkrjsystem.user.bean.WkrjUserRole;
import wkrjsystem.user.dao.WkrjUserDao;
import wkrjsystem.utils.Guid;
import wkrjsystem.utils.MD5;

@Service("wkrjUserService")
@Transactional
public class WkrjUserServiceImpl implements WkrjUserService {

	@Autowired
	private WkrjUserDao dao;
	
	
	@Override
	public List<WkrjUser> getUserList(int offset, int page, String deptId, String userName, boolean isGly, String user_dept) {
		return this.dao.getUserList(offset, page, deptId, userName,  isGly,  user_dept);
	}
	
	@Override
	public List<Map<String,Object>> getRoleList(String role_level) {
		
		List<Map<String,Object>> all = new ArrayList<Map<String,Object>>();//记录所有的module
		
		List<WkrjRole> roles = this.dao.getRoleTree(role_level);
		
		for(WkrjRole role : roles){
			
			Map<String,Object> map = new HashMap<String,Object>();
			//Map<String,Object> attributesMap = new HashMap<String,Object>();
			
			map.put("id", role.getRole_id());
			//map.put("pid", module.getDept_parent_id());
			map.put("text", role.getRole_name());
			
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
	public String addUser(WkrjUser module) {
		
		//首先查询一下是否有重复的用户名、身份证号、工号，有的话用户名返回1，身份证号返回2，工号返回3正常返回0,其他返回4
		String str="4";
		String userId = Guid.getGuid();
		
		if(0<checkIsHaveNameOrGhOrSfz(module.getUser_name(),null,null,userId)){
			str="1";
			return str;
		}
//		if(0<checkIsHaveNameOrGhOrSfz(null,null,module.getUser_card(),userId)){
//			str="2";
//			return str;
//		}
		/*if(0<checkIsHaveNameOrGhOrSfz(null,module.getUser_no(),null,userId)){
			str="3";
			return str;
		}*/
		
		//密码加密
		module.setUser_password(MD5.MD5Encode(module.getUser_password()));
		module.setUser_accounttype(0);
		
		List<WkrjRole> userRoles = module.getUser_role();
		boolean sus=true;
		
		if(null!=userRoles && userRoles.size()>0){
			for(WkrjRole userRole:userRoles){
				
				String roleIds = userRole.getRole_id();
				if(null!=roleIds && !"".equals(roleIds)){
					
					//for(int i=0;i<roleIds.split(",").length;i++){
					for(int i=0;i<roleIds.split(",|;").length;i++){						
						WkrjUserRole ur = new WkrjUserRole();
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
	public String updateUser(WkrjUser module) {
		
		String userId = module.getUser_id();
		String str="4";
		
		if(0<checkIsHaveNameOrGhOrSfz(module.getUser_name(),null,null,userId)){
			str="1";
			return str;
		}
//		if(0<checkIsHaveNameOrGhOrSfz(null,null,module.getUser_card(),userId)){
//			str="2";
//			return str;
//		}
		/*if(0<checkIsHaveNameOrGhOrSfz(null,module.getUser_no(),null,userId)){
			str="3";
			return str;
		}*/
		
		List<WkrjRole> userRoles = module.getUser_role();
		boolean sus=true;
		
		this.dao.delUerRoleById(userId);//删除角色
		
		if(null!=userRoles && userRoles.size()>0){
			for(WkrjRole userRole:userRoles){
				
				String roleIds = userRole.getRole_id();
				if(null!=roleIds && !"".equals(roleIds)){
					
					//for(int i=0;i<roleIds.split(",").length;i++){
					for(int i=0;i<roleIds.split(",|;").length;i++){
						
						WkrjUserRole ur = new WkrjUserRole();
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
	public long getUserList(String deptId,String userName,boolean isGly,String user_dept) {
		return dao.getUserListCount( deptId,userName, isGly,user_dept);
	}

	@Override
	public boolean repeatPw(String id, String pw) {
		return this.dao.repeatPw(id, MD5.MD5Encode(pw));
	}
	
	@Override
	public WkrjUser findUserInfoByName(String userName) {
		return this.dao.findUserInfoByName(userName);
	}

	@Override
	public WkrjUser findUserInfoByName(String userName, String pw) {
		return this.dao.findUserInfoByNameandPw(userName,pw);
	}
	
	@Override
	public List<WkrjRole> getRoleListByUserId(String user_id) {
		return this.dao.getRoleListByUserId(user_id);
	}

}
