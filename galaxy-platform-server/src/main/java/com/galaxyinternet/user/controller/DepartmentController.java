package com.galaxyinternet.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.galaxyinternet.common.controller.BaseControllerImpl;
import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.department.Department;
import com.galaxyinternet.service.DepartmentService;

/**
 * 部门相关
 * @author zhaoying
 *
 */
@Controller
@RequestMapping("/galaxy/dept")
public class DepartmentController extends BaseControllerImpl<Department, Department> {
	final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	@Autowired
	private DepartmentService departmentService;
	
	@Override
	protected BaseService<Department> getBaseService() {
		return this.departmentService;
	}

}
