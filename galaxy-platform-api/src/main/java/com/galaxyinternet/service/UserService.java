package com.galaxyinternet.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Pageable;

import com.galaxyinternet.bo.UserBo;
import com.galaxyinternet.framework.core.model.Header;
import com.galaxyinternet.framework.core.model.Page;
import com.galaxyinternet.framework.core.model.ResponseData;
import com.galaxyinternet.framework.core.service.BaseService;
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
	 * @param user
	 * @return
	 */
	int resetPwd(User user);

	/**
	 * 用户登录
	 * 
	 * @author zcy
	 * @param user
	 * @return
	 */
	ResponseData<User> login(User user, HttpServletRequest request);

	/**
	 * 用户注销
	 * 
	 * @param user
	 * @return
	 */
	ResponseData<User> logout(Header header, HttpServletRequest request);
	
	/**
	 * 分页查询用户
	 * @param query
	 * @param pageable
	 * @return
	 */
	Page<User> queryUserList(User query, Pageable pageable);
}
