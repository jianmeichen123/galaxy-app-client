package com.galaxyinternet.model.resource;

import java.util.List;

import com.galaxyinternet.framework.core.model.PagableEntity;

public class RoleResource extends PagableEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long roleId;
    private Long resourceId;
    private Long createdUid;
    private Long updatedUid;
    
    private List<Long> roleList;
    
    public List<Long> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Long> roleList) {
		this.roleList = roleList;
	}
	public Long getRoleId() {
        return roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    public Long getResourceId() {
        return resourceId;
    }
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
    public Long getCreatedUid() {
        return createdUid;
    }
    public void setCreatedUid(Long createdUid) {
        this.createdUid = createdUid;
    }
    public Long getUpdatedUid() {
        return updatedUid;
    }
    public void setUpdatedUid(Long updatedUid) {
        this.updatedUid = updatedUid;
    }

}