package com.galaxyinternet.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.galaxyinternet.dao.role.RoleDao;
import com.galaxyinternet.framework.core.constants.SqlId;
import com.galaxyinternet.framework.core.dao.impl.BaseDaoImpl;
import com.galaxyinternet.model.role.Role;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role, Long>implements RoleDao {

	@Override
	public List<Long> selectIdByUserId(Long userID) {
		return sqlSessionTemplate.selectList(getSqlName(SqlId.SQL_SELECT_ID_BY_USERID), userID);
	}
}
