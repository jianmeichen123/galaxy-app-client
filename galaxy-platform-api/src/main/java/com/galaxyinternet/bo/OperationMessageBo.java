package com.galaxyinternet.bo;

import java.util.List;

import com.galaxyinternet.framework.core.model.Pagable;
import com.galaxyinternet.model.operationMessage.OperationMessage;

public class OperationMessageBo extends OperationMessage  implements Pagable{

	private static final long serialVersionUID = 1L;
	private String extendFiled;// 业务对象中扩展的字段
	private List<String> projectIds;
	private Long createdTimeStart;
	private Long count;
	
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

	public Long getCreatedTimeStart() {
		return createdTimeStart;
	}

	public void setCreatedTimeStart(Long createdTimeStart) {
		this.createdTimeStart = createdTimeStart;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	/* (non-Javadoc)
	 * @see com.galaxyinternet.framework.core.model.Pagable#setDirection(java.lang.String)
	 */
	@Override
	public void setDirection(String direction) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.galaxyinternet.framework.core.model.Pagable#getDirection()
	 */
	@Override
	public String getDirection() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.galaxyinternet.framework.core.model.Pagable#setProperty(java.lang.String)
	 */
	@Override
	public void setProperty(String property) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.galaxyinternet.framework.core.model.Pagable#getProperty()
	 */
	@Override
	public String getProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
