package com.galaxyinternet.model.privilege;

import com.galaxyinternet.framework.core.model.BaseEntity;

/**
 * @description 功能类,对应模块中具体按钮(CRUD)
 * @author keifer
 *
 */
public class Function extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 功能名称
	 */
	private String name;
	/**
	 * 功能的访问地址
	 */
	private String url;

	/**
	 * 所属的菜单主键
	 */
	private Long menuId;

	/**
	 * 功能的序列
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

	public Function() {
	}

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

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
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
