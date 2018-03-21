package com.galaxyinternet.service;

import com.galaxyinternet.bo.UserBo;
import com.galaxyinternet.framework.core.model.Page;
import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.department.Department;
import com.galaxyinternet.model.user.User;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author keifer
 */

public interface UserService extends BaseService<User> {

	/**
	 * careerline = 1 的用户按name首字母排序
	 *
	 * @param user
	 * @return
	 */
	List<User> selectViewByGBK(User user);

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	Long insertUser(User user);
	
	/**
	 * 更新用户
	 * 
	 * @param user
	 * @return
	 */
	int updateUser(UserBo user);

	/**
	 * 重置密码
	 * 
	 * @param userId
	 * @return
	 */
	int resetPwd(Long userId);

	/**
	 * 用户更改密码
	 * @param query
	 * @return
	 */
    int updatePwd(User query);
	
	/**
	 * 分页查询用户
	 * @param query
	 * @param pageable
	 * @return
	 */
	Page<User> queryUserPageList(User query, Pageable pageable);
	
	/**
	 * 根据用户ID查询部门
	 * @param userId
	 * @return
	 */
	Department getDepartmentByUserId(Long userId);
	
	/**
	 * 查询用户昵称 重复校验用
	 * @author zhaoying
	 * @param user
	 * @return
	 */
	User queryByNickName(User user);
	
	/**
	 * 查询用户昵称 重复校验用
	 * @author zhaoying
	 * @param user
	 * @return
	 */
	User queryByEmail(User user);

	/**
	 * @author zcy
	 * @param user
	 * @return
	 */
	boolean isUserNormal(User user);
	
	public List<User> queryListById(List<String> idList);
	
	//导入数据用
	public User queryUserByRealName(String realName);
	
	public List<Map<String, Object>> report(Map<String,Object> params);

	List<User> querytTzjlSum(Map<String, Object> params);

	List<User> querytUserByParams(Map<String, Object> params);
	
	public List<User> getUserByRoleId(Long roleId);
	
	/**
	 * 关联查询用户的详细信息 包括名字,角色,email,部门
	 * @param map(条件查询)
	 * 	userId 
		email
		roleId
		departmentId
	 * @return
	 */
	public List<Map<String,Object>> queryUserDetail(Map<String,Object> params);
	
	
	public User queryById(Long id, boolean useAuth);
	
	public List<User> selectView(User user);
}
