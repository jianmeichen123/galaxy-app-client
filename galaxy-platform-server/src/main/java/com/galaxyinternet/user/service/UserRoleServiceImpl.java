package com.galaxyinternet.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxyinternet.dao.user.UserRoleDao;
import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.framework.core.service.impl.BaseServiceImpl;
import com.galaxyinternet.model.user.UserRole;
import com.galaxyinternet.service.UserRoleService;

@Service("com.galaxyinternet.service.UserRoleService")
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole>implements UserRoleService {
	//private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	protected BaseDao<UserRole, Long> getBaseDao() {
		// TODO Auto-generated method stub
		return this.userRoleDao;
	}


}
