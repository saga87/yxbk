package wkrjsystemdev.userdev.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import wkrjsystem.station.bean.WkrjStation;
import wkrjsystemdev.roledev.bean.WkrjRoleDev;
import wkrjsystemdev.userdev.bean.WkrjUserDev;
import wkrjsystemdev.userdev.bean.WkrjUserRoleDev;



public interface WkrjUserDevDao {
	
	public List<WkrjUserDev> getUserList(@Param("offset")int offset,@Param("page") int page,@Param("deptId")String deptId,@Param("userName")String userName);
	
	public long getUserListCount(@Param("deptId")String deptId,@Param("userName")String userName);
	/**
	 * 检测用户名、工号以及身份证是否重复返回
	 * @param name
	 * @param gh
	 * @param sfz
	 * @return
	 */
	public long checkIsHaveNameOrGhOrSfz(@Param("name")String name,@Param("gh")String gh,@Param("sfz")String sfz,@Param("userId")String userId);
	
	/**
	 * 添加岗位
	 * @param user
	 * @return
	 */
	public boolean addUser(WkrjUserDev user);
	/**
	 * 添加用户角色
	 * @param user
	 * @return
	 */
	public boolean addUserRole(WkrjUserRoleDev userRole);
	
	/**
	 * 修改岗位
	 * @param user
	 * @return
	 */
	public boolean updateUser(WkrjUserDev user);
	/**
	 * 重置密码
	 * @param id
	 * @param pw
	 * @return
	 */
	public boolean repeatPw(@Param("user_id")String id,@Param("user_password")String pw);
	/**
	 * 删除岗位
	 * @param id
	 * @return
	 */
	public boolean delUser(String id);
	/**
	 * 禁用启用
	 * @param id
	 * @param flag
	 * @return
	 */
	public boolean forbiddenUser(@Param("user_id")String id,@Param("user_is_enable")String flag);
	/**
	 * 删除用户角色通过
	 * @param id
	 * @return
	 */
	public boolean delUerRoleById(String id);
	/**
	 * 通过用户名获取
	 * @param userName
	 * @return
	 */
	public WkrjUserDev findUserInfoByNameandPw(@Param("userName")String userName,@Param("pw")String pw);
	
	public WkrjUserDev findUserInfoByName(@Param("userName")String userName);

	public List<WkrjRoleDev> getRoleTree();

	public List<WkrjStation> getStationTree();
	
}
