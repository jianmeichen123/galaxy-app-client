package com.galaxyinternet.service;

import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.user.User;

/**
 * @author keifer
 */
public interface UserService extends BaseService<User> {
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	Long insertUser(User user);
}

