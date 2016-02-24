package com.galaxyinternet.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxyinternet.dao.user.UserDao;
import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.framework.core.service.impl.BaseServiceImpl;
import com.galaxyinternet.framework.core.utils.PWDUtils;
import com.galaxyinternet.model.user.User;
import com.galaxyinternet.service.UserService;

@Service("com.galaxyinternet.service.UserService")
public class UserServiceImpl extends BaseServiceImpl<User>implements UserService {
	//private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserDao userDao;

	@Override
	protected BaseDao<User, Long> getBaseDao() {
		return this.userDao;
	}
	
	@Override
	public Long insertUser(User user) {
		String oriPwd = PWDUtils.genRandomNum(6);
		user.setOriginPassword(oriPwd);
		return super.insert(user);
	}
}
