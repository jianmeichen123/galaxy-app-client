package com.galaxyinternet.model.department;

import com.galaxyinternet.framework.core.model.BaseEntity;

public class Department extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

    private String name;

    private String remark;

    private Long parentId;
    private Integer type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
    
    

}
