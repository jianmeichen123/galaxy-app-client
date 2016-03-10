package com.galaxyinternet.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxyinternet.dao.role.RoleDao;
import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.framework.core.service.impl.BaseServiceImpl;
import com.galaxyinternet.model.role.Role;
import com.galaxyinternet.service.RoleService;

@Service("com.galaxyinternet.service.RoleService")
public class RoleServiceImpl extends BaseServiceImpl<Role>implements RoleService {
	//private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RoleDao roleDao;

	@Override
	protected BaseDao<Role, Long> getBaseDao() {
		return this.roleDao;
	}

}
