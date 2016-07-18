package com.galaxyinternet.resource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxyinternet.dao.resource.ResourceDao;
import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.framework.core.service.impl.BaseServiceImpl;
import com.galaxyinternet.model.resource.PlatformResource;
import com.galaxyinternet.service.ResourceService;
@Service("com.galaxyinternet.service.ResourceService")
public class ResourceServiceImpl extends BaseServiceImpl<PlatformResource> implements ResourceService
{
	@Autowired
	private ResourceDao resourceDao;
	@Override
	protected BaseDao<PlatformResource, Long> getBaseDao()
	{
		return resourceDao;
	}
	
	
	@Override
	public List<PlatformResource> queryResourceListToUser(Long uid) {
		return resourceDao.selectResourceListToUser(uid);
	}

}
