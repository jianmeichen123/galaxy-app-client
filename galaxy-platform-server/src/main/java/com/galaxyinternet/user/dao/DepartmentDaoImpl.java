package com.galaxyinternet.user.dao;

import org.springframework.stereotype.Repository;

import com.galaxyinternet.dao.department.DepartmentDao;
import com.galaxyinternet.framework.core.dao.impl.BaseDaoImpl;
import com.galaxyinternet.model.department.Department;

@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<Department, Long>implements DepartmentDao {
}
