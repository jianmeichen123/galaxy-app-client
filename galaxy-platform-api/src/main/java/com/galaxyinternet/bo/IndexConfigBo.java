package com.galaxyinternet.bo;

import com.galaxyinternet.model.sopIndex.IndexConfig;

public class IndexConfigBo extends IndexConfig {

	private static final long serialVersionUID = 1L;
	private String extendFiled;// 业务对象中扩展的字段
	
	private Boolean resourceIdNullFilter;  //true:过滤掉  resourceId 为 null 的结果集
	private String resourceMark;
    private String resourceName;
	
    
    
    
	public Boolean getResourceIdNullFilter() {
		return resourceIdNullFilter;
	}

	public void setResourceIdNullFilter(Boolean resourceIdNullFilter) {
		this.resourceIdNullFilter = resourceIdNullFilter;
	}

	public String getResourceMark() {
		return resourceMark;
	}

	public void setResourceMark(String resourceMark) {
		this.resourceMark = resourceMark;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getExtendFiled() {
		return extendFiled;
	}

	public void setExtendFiled(String extendFiled) {
		this.extendFiled = extendFiled;
	}
	
}
