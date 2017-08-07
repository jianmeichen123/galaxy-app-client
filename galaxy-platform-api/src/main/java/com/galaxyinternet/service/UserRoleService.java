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
	List<Long> selectRoleIdByUserId(Long userID, String companyId);

	long insertUserRole(UserRole userRole);
	
	/**
	 * 根据角色ID查询相关所有用户ID
	 */
	List<Long> selectUserIdByRoleId(Long roleId);
}

