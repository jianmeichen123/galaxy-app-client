package com.galaxyinternet.service;

import javax.servlet.http.HttpServletRequest;

import com.galaxyinternet.framework.core.model.Header;
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
	ResponseData<User> login(User user);

	/**
	 * 用户注销
	 * 
	 * @param user
	 * @return
	 */
	ResponseData<User> logout(Header header, HttpServletRequest request);
}
