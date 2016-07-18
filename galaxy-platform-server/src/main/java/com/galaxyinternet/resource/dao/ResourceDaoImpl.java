package com.galaxyinternet.resource.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.galaxyinternet.dao.resource.ResourceDao;
import com.galaxyinternet.framework.core.dao.impl.BaseDaoImpl;
import com.galaxyinternet.framework.core.exception.DaoException;
import com.galaxyinternet.model.resource.PlatformResource;
@Repository("resourceDao")
public class ResourceDaoImpl extends BaseDaoImpl<PlatformResource, Long> implements ResourceDao {

	@Override
	public List<PlatformResource> selectResourceListToUser(Long uid) {
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectResourceListToUser"), uid);
		} catch (Exception e) {
			throw new DaoException(String.format("查询用户拥有的资源项出错！语句：%s", getSqlName("selectResourceListToUser")), e);
		}
	}
	
}
