package com.galaxyinternet.dao.department;

import java.util.List;

import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.model.department.Department;

public interface DepartmentDao extends BaseDao<Department, Long> {
	
	List<Department> queryListByType(Integer type);
	
	public List<Department> selectListById(List<String> idList);
}
