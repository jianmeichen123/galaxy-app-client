package com.galaxyinternet.resource.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxyinternet.dao.resource.RoleResourceDao;
import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.framework.core.service.impl.BaseServiceImpl;
import com.galaxyinternet.model.resource.RoleResource;
import com.galaxyinternet.service.RoleResourceService;

@Service("com.galaxyinternet.service.RoleResourceService")
public class RoleResourceServiceImpl extends BaseServiceImpl<RoleResource> implements RoleResourceService {

	@Autowired
	private RoleResourceDao roleResourceDao;
	
	@Override
	protected BaseDao<RoleResource, Long> getBaseDao() {
		// TODO Auto-generated method stub
		return roleResourceDao;
	}

}
