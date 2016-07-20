package com.galaxyinternet.service;

import com.galaxyinternet.framework.core.model.Page;
import com.galaxyinternet.framework.core.model.PageRequest;
import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.role.Role;

/**
 * 
 * @author chenjianmei
 *
 */
public interface RoleService extends BaseService<Role> {
	
	public Page<Role> queryRoleList(Role role,PageRequest p);
	
	public Role queryByRoleName(Role role);
}

