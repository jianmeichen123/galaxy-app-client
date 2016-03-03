package com.galaxyinternet.service;

import java.util.List;

import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.user.UserRole;

/**
 * 
 * @author zhaoying
 *
 */
public interface UserRoleService extends BaseService<UserRole> {
	/**
	 * 根据用户id获取角色列表
	 * @author zhaoying
	 * @param userID
	 * @return
	 */
	List<Long> selectRoleIdByUserId(Long userID);

	long insertUserRole(UserRole userRole);
}

