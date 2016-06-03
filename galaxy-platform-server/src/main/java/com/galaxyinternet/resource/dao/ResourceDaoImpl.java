package com.galaxyinternet.resource.dao;

import org.springframework.stereotype.Repository;

import com.galaxyinternet.dao.resource.ResourceDao;
import com.galaxyinternet.framework.core.dao.impl.BaseDaoImpl;
import com.galaxyinternet.model.resource.PlatformResource;
@Repository("resourceDao")
public class ResourceDaoImpl extends BaseDaoImpl<PlatformResource, Long> implements ResourceDao {
	
}
