package com.galaxyinternet.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.galaxyinternet.bo.UserBo;
import com.galaxyinternet.framework.core.model.Page;
import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.department.Department;
import com.galaxyinternet.model.role.Role;
import com.galaxyinternet.model.user.User;

/**
 * @author keifer
 */

public interface UserService extends BaseService<User> {

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
	 * 根据用户id获取角色
	 * @param userId
	 * @return
	 */
	Role getRoleByUserId(Long userId);
	
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
	 * 查询登录用户
	 * @author zcy
	 * @param user
	 * @return
	 */
	User queryUserByUP(User user);

	/**
	 * @author zcy
	 * @param user
	 * @return
	 */
	boolean isUserNormal(User user);
	
	public List<User> queryListById(List<String> idList);
	
	//导入数据用
	public User queryUserByRealName(String realName);
}
