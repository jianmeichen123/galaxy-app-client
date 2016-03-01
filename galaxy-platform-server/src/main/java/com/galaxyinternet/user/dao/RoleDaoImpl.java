package com.galaxyinternet.user.dao;

import org.springframework.stereotype.Repository;

import com.galaxyinternet.dao.role.RoleDao;
import com.galaxyinternet.framework.core.dao.impl.BaseDaoImpl;
import com.galaxyinternet.model.role.Role;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role, Long>implements RoleDao {

}
