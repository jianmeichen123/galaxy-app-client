package com.galaxyinternet.bo;

import java.util.List;

import com.galaxyinternet.framework.core.model.Pagable;
import com.galaxyinternet.model.operationMessage.OperationMessage;

public class OperationMessageBo extends OperationMessage  implements Pagable{

	private static final long serialVersionUID = 1L;
	private String extendFiled;// 业务对象中扩展的字段
	private List<String> projectIds;
	
	public String getExtendFiled() {
		return extendFiled;
	}
	
	public void setExtendFiled(String extendFiled) {
		this.extendFiled = extendFiled;
	}

	protected Integer pageSize;
	protected Integer pageNum;

	@Override
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public Integer getPageSize() {
		return this.pageSize;
	}

	@Override
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;

	}

	@Override
	public Integer getPageNum() {
		return this.pageNum;
	}


	public List<String> getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(List<String> projectIds) {
		this.projectIds = projectIds;
	}

	
}
