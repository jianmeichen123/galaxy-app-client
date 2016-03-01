package com.galaxyinternet.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.galaxyinternet.dao.user.UserRoleDao;
import com.galaxyinternet.framework.core.dao.impl.BaseDaoImpl;
import com.galaxyinternet.model.user.UserRole;

@Repository("userRoleDao")
public class UserRoleDaoImpl extends BaseDaoImpl<UserRole, Long>implements UserRoleDao {

	@Override
	public List<Long> selectRoleIdByUserId(Long userID) {
		return sqlSessionTemplate.selectList(getSqlName("selectRoleIdByUserId"), userID);
	}

}
