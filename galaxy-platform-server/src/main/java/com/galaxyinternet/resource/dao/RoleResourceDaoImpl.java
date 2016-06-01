package com.galaxyinternet.resource.dao;

import org.springframework.stereotype.Repository;

import com.galaxyinternet.dao.resource.RoleResourceDao;
import com.galaxyinternet.framework.core.dao.impl.BaseDaoImpl;
import com.galaxyinternet.model.resource.RoleResource;

@Repository("roleResourceDao")
public class RoleResourceDaoImpl extends BaseDaoImpl<RoleResource, Long> implements RoleResourceDao{
	
}
