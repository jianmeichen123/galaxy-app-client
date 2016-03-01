package com.galaxyinternet.bo;

import com.galaxyinternet.model.user.User;

public class UserBo extends User {

	private static final long serialVersionUID = 1L;
	private String extendFiled;// 业务对象中扩展的字段
	
	private Long roleId;// 角色Id

	public String getExtendFiled() {
		return extendFiled;
	}

	public void setExtendFiled(String extendFiled) {
		this.extendFiled = extendFiled;
	}
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
