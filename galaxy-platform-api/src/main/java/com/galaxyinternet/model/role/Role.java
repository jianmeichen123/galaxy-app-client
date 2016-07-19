package com.galaxyinternet.model.role;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.galaxyinternet.framework.core.model.BaseEntity;
import com.galaxyinternet.framework.core.model.PagableEntity;
import com.galaxyinternet.model.user.User;

/**
 * 角色
 * 
 * @author zhaoying
 *
 */
public class Role extends PagableEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * name
	 */
	private String name;
	/**
	 * role_code
	 */
	private String roleCode;
	
	private Integer applicationPlatform;
	
	private Long userId;
	/**
	 * description
	 */
	private String description;
	/**
	 * sort
	 */
	private Integer sort;
	/**
	 * disabled
	 */
	private Integer disabled;
	
	private List<User> userListByRid;

	public void setName(String name) {

		if (StringUtils.isNotBlank(name)) {
			name = name.trim();
		}
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setRoleCode(String roleCode) {

		if (StringUtils.isNotBlank(roleCode)) {
			roleCode = roleCode.trim();
		}
		this.roleCode = roleCode;
	}

	public String getRoleCode() {
		return this.roleCode;
	}

	public Integer getApplicationPlatform() {
		return applicationPlatform;
	}

	public void setApplicationPlatform(Integer applicationPlatform) {
		this.applicationPlatform = applicationPlatform;
	}

	public void setDescription(String description) {

		if (StringUtils.isNotBlank(description)) {
			description = description.trim();
		}
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setSort(Integer sort) {

		this.sort = sort;
	}

	public Integer getSort() {
		return this.sort;
	}

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String toString() {
		return new StringBuffer().append("id=").append(getId()).append(",").append("name=").append(getName())
				.append(",").append("roleCode=").append(getRoleCode()).append(",").append("description=")
				.append(getDescription()).append(",").append("sort=").append(getSort()).append(",").append("disabled=")
				.append(getDisabled()).append(",").toString();
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	public List<User> getUserListByRid() {
		return userListByRid;
	}

	public void setUserListByRid(List<User> userListByRid) {
		this.userListByRid = userListByRid;
	}
	
	
}
