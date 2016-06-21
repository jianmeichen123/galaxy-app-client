package com.galaxyinternet.dao.user;

import java.util.List;

import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.model.user.UserRole;

public interface UserRoleDao extends BaseDao<UserRole, Long> {
	List<Long> selectRoleIdByUserId(Long userID);
	
	Long insertUserRole(UserRole userRole);
	UserRole selectByUserId(Long userId);
	/**
	 * 根据角色ID查询相关所有用户ID
	 */
	List<Long> selectUserIdByRoleId(Long roleId);
}
