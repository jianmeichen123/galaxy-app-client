package com.galaxyinternet.model.privilege.bo;

import java.util.ArrayList;
import java.util.List;

import com.galaxyinternet.model.privilege.Function;
import com.galaxyinternet.model.privilege.Menu;

/**
 * @description 菜单类
 * @author keifer
 *
 */
public class MenuBo extends Menu {

	private static final long serialVersionUID = 1L;
	private List<Function> functions;

	public MenuBo() {
		this.functions = new ArrayList<Function>(0);
	}

	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

}