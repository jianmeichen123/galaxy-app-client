package com.galaxyinternet.model.privilege.bo;

import java.util.ArrayList;
import java.util.List;

import com.galaxyinternet.framework.core.mongodb.MongoEntity;

public class PrivilegeBo extends MongoEntity<String> {

	private static final long serialVersionUID = 1L;

	/* 用户id */
	private Long userId;
	/* 用户具有的角色id */
	private List<RoleBo> roles;

	public PrivilegeBo() {
		this.roles = new ArrayList<RoleBo>(0);
	}

	public List<RoleBo> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleBo> roles) {
		this.roles = roles;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/*
	 * mongdb单条记录的唯一主键：如果为空，mongo会自己生产。推荐使用IdGenerator.generateId(PrivilegeBo.
	 * class)生产
	 */
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
}
