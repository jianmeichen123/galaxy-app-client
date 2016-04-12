package com.galaxyinternet.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxyinternet.dao.department.DepartmentDao;
import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.framework.core.service.impl.BaseServiceImpl;
import com.galaxyinternet.model.department.Department;
import com.galaxyinternet.service.DepartmentService;

@Service("com.galaxyinternet.service.DepartmentService")
public class DepartmentServiceImpl extends BaseServiceImpl<Department>implements DepartmentService {
	//private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private DepartmentDao departmentDao;

	@Override
	protected BaseDao<Department, Long> getBaseDao() {
		return this.departmentDao;
	}

	@Override
	public List<Department> queryListByType(Integer type) {
		return departmentDao.queryListByType(type);
	}

	@Override
	public List<Department> queryListById(List<String> idList) {
		// TODO Auto-generated method stub
		return departmentDao.selectListById(idList);
	}
	
	
	
	
}
