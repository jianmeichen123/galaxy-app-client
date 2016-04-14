package com.galaxyinternet.model.privilege;

import com.galaxyinternet.framework.core.model.BaseEntity;

/**
 * 模块类，如platform，sop，report
 * 
 * @author keifer
 */
public class Module extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 模块名称
	 */
	private String name;

	/**
	 * 模块标识
	 */
	private String flag;

	/**
	 * 模块对应的访问地址
	 */
	private String url;

	/**
	 * 模块的序列号
	 */
	private Integer sortNo;
	/**
	 * 创建者
	 */
	private String creator;
	/**
	 * 修改者
	 */
	private String updater;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}

	public Long getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Long updatedTime) {
		this.updatedTime = updatedTime;
	}

}