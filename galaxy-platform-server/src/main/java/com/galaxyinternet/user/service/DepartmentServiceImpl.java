package com.galaxyinternet.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxyinternet.dao.department.DepartmentDao;
import com.galaxyinternet.framework.cache.Cache;
import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.framework.core.service.impl.BaseServiceImpl;
import com.galaxyinternet.model.department.Department;
import com.galaxyinternet.platform.constant.PlatformConst;
import com.galaxyinternet.service.DepartmentService;

@Service("com.galaxyinternet.service.DepartmentService")
public class DepartmentServiceImpl extends BaseServiceImpl<Department>implements DepartmentService,InitializingBean{
	//private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired
	private Cache cache;

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
	
	@Override
	public void afterPropertiesSet() throws Exception
	{
		List<Department> list = queryAll();
		Map<Long,Department> map=new HashMap<Long,Department>();
		if(null!=list){
			for(Department d: list){
				map.put(d.getId(),d);
			}
		}
		cache.set(PlatformConst.REQUEST_DEPARTMENT, map);
	
	}
	
	
	
}
