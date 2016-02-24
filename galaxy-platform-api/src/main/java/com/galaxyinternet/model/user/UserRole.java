package com.galaxyinternet.model.user;

import com.galaxyinternet.framework.core.model.BaseEntity;

public class UserRole extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userId;
	
	private Long roleId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	

}
