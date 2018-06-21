package wkrjsystem.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import wkrjsystem.role.bean.WkrjRole;
import wkrjsystem.user.bean.WkrjUser;
import wkrjsystem.user.bean.WkrjUserRole;

public interface WkrjUserDao {
	/**
	 * 获取用户列表
	 * @param offset
	 * @param page
	 * @param deptId
	 * @return
	 */
	public List<WkrjUser> getUserList(@Param("offset")int offset,@Param("page") int page,
			@Param("deptId")String deptId,@Param("userName")String userName
			,@Param("isGly")boolean isGly,@Param("user_dept")String user_dept);
	/**
	 * 获取用户数量
	 * @param offset
	 * @param page
	 * @param deptId
	 * @return
	 */
	public long getUserListCount(@Param("deptId")String deptId,@Param("userName")String userName,
			@Param("isGly")boolean isGly,@Param("user_dept")String user_dept);
	/**
	 * 检测用户名、工号以及身份证是否重复返回
	 * @param name
	 * @param gh
	 * @param sfz
	 * @return
	 */
	public long checkIsHaveNameOrGhOrSfz(@Param("name")String name,@Param("gh")String gh,@Param("sfz")String sfz,@Param("userId")String userId);
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public boolean addUser(WkrjUser user);
	/**
	 * 添加用户角色
	 * @param user
	 * @return
	 */
	public boolean addUserRole(WkrjUserRole userRole);
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	public boolean updateUser(WkrjUser user);
	/**
	 * 重置密码
	 * @param id
	 * @param pw
	 * @return
	 */
	public boolean repeatPw(@Param("user_id")String id,@Param("user_password")String pw);
	/**
	 * 删除用户
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
	 * 删除用户角色
	 * @param id
	 * @return
	 */
	public boolean delUerRoleById(String id);
	/**
	 * 通过用户名查询用户信息
	 * @param userName
	 * @return
	 */
	public WkrjUser findUserInfoByName(@Param("userName")String userName);
	/**
	 * 通过用户名和密码查询用户信息
	 * @param userName
	 * @return
	 */
	public WkrjUser findUserInfoByNameandPw(@Param("userName")String userName,@Param("pw")String pw);
	/**
	 * 获取用户角色通过用户id
	 * @param user_id
	 * @return
	 */
	public List<WkrjRole> getRoleListByUserId(String user_id);
	/**
	 * 获取角色信息
	 * @param 
	 * @return
	 */
	public List<WkrjRole> getRoleTree(@Param("role_level")String role_level);

}
