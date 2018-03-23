package com.galaxyinternet.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.galaxyinternet.dao.department.DepartmentDao;
import com.galaxyinternet.framework.core.dao.impl.BaseDaoImpl;
import com.galaxyinternet.framework.core.exception.DaoException;
import com.galaxyinternet.model.department.Department;

@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<Department, Long>implements DepartmentDao {

	@Override
	public List<Department> queryListByType(Integer type) {
		return sqlSessionTemplate.selectList(getSqlName("queryListByType"), type);
	}
	
	@Override
	public List<Department> selectListById(List<String> idList) {
		return sqlSessionTemplate.selectList(getSqlName("selectListById"),idList);
	}
	
	public List<Department> selectView() {
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectView"));
		} catch (Exception e) {
			throw new DaoException(String.format("查询所有对象列表出错！语句：%s", getSqlName("selectView")), e);
		}
	}
	
}
