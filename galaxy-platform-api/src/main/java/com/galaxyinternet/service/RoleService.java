package com.galaxyinternet.service;

import java.util.List;

import com.galaxyinternet.framework.core.model.Page;
import com.galaxyinternet.framework.core.model.PageRequest;
import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.role.Role;

/**
 * 
 * @author zhaoying
 *
 */
public interface RoleService extends BaseService<Role> {
	
	public Page<Role> queryRoleList(Role role,PageRequest p);
}

