package com.galaxyinternet.model.privilege.bo;

import java.util.ArrayList;
import java.util.List;

import com.galaxyinternet.model.role.Role;

/**
 * 模块类，如platform，sop，report
 * 
 * @author keifer
 */
public class RoleBo extends Role {

	private static final long serialVersionUID = 1L;

	private List<MenuBo> resources;

	public RoleBo() {
		this.resources = new ArrayList<MenuBo>(0);
	}

	public List<MenuBo> getResources() {
		return resources;
	}

	public void setResources(List<MenuBo> resources) {
		this.resources = resources;
	}

}