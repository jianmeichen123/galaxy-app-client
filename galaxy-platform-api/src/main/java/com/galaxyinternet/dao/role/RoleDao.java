package com.galaxyinternet.dao.role;

import java.util.List;

import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.model.role.Role;

public interface RoleDao extends BaseDao<Role, Long> {
	
	List<Long> selectIdByUserId(Long userID);
}
