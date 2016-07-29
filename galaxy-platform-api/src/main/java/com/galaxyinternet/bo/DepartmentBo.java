package com.galaxyinternet.bo;

/**
 * 部门查询条件
 * 
 * @author wangkun
 *
 */
public class DepartmentBo {
	
	/**
	 * 主键
	 */
	private long id=-1;
	/**
	 * 部门名称，模糊查询
	 */
	private String name="-1";
	/**
	 * 上级部门编号
	 */
	private long parentId=-1;
	/**
	 * 部门类型标识
	 * 1. 事业线
	 * 2. 职能部门
	 */
	private Integer type=-1;
	/**
	 * 合伙人，关联user主键
	 */
	private long managerId=-1;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public long getManagerId() {
		return managerId;
	}
	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}

}
