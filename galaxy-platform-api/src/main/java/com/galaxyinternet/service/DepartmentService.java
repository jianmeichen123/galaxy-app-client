package com.galaxyinternet.service;

import java.util.List;

import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.department.Department;

/**
 * 
 * @author zhaoying
 *
 */
public interface DepartmentService extends BaseService<Department> {

	/**
	 * 根据类别查询列表
	 * @author zhaoying
	 * @param type
	 * @return
	 */
	List<Department> queryListByType(Integer type);
	
	
}

