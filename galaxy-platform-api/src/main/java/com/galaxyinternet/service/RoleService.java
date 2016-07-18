package com.galaxyinternet.service;

import java.util.List;

import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.role.Role;

/**
 * 
 * @author zhaoying
 *
 */
public interface RoleService extends BaseService<Role> {
	
	public List<Role>queryRoleList();
}

