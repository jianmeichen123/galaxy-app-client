package com.galaxyinternet.model.privilege;

import com.galaxyinternet.framework.core.model.BaseEntity;

/**
 * @description 菜单类
 * @author keifer
 *
 */
public class Menu extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 功能菜单名称
	 */
	private String name;

	/**
	 * 功能对应的访问地址
	 */
	private String url;

	/**
	 * 菜单备注说明
	 */
	private String remark;

	/**
	 * 父级菜单
	 */
	private Long parentId;

	/**
	 * 菜单的层次结构，默认以s-开头，s-1开始
	 */
	private String structure;

	/**
	 * 菜单的序列
	 */
	private Integer sortNo;

	/**
	 * 系统模块标记(对应后台服务器endpoint.properties配置文件的key或是唯一标示即可)
	 */
	private String moduleFlag;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getModuleFlag() {
		return moduleFlag;
	}

	public void setModuleFlag(String moduleFlag) {
		this.moduleFlag = moduleFlag;
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