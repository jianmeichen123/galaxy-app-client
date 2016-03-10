package com.galaxyinternet.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		return this.userRoleDao;
	}

	@Override
	public List<Long> selectRoleIdByUserId(Long userID) {
		return userRoleDao.selectRoleIdByUserId(userID);
	}

	@Override
	@Transactional
	public long insertUserRole(UserRole userRole) {
		UserRole role =  userRoleDao.selectByUserId(userRole.getUserId());
		if (role != null) {
			role.setRoleId(userRole.getRoleId());
			return userRoleDao.updateById(role);
		} else {
			return userRoleDao.insertUserRole(userRole);
		}
	}
}
