package com.galaxyinternet.resource.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxyinternet.bo.PlatformResourceBo;
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
	
	public List<PlatformResource> queryUserMenus(Long userId, Long parentId)
	{
		PlatformResourceBo query = new PlatformResourceBo();
		query.setUserId(userId);
		query.setParentId(parentId);
		query.setResourceType("1");
		query.setResourceStatus("1");
		List<PlatformResource> list = resourceDao.selectWithJoin(query);
		if(list != null && list.size()>0)
		{
			Collections.sort(list, new Comparator<PlatformResource>(){

				@Override
				public int compare(PlatformResource o1, PlatformResource o2)
				{
					if(o1 != null && o2 != null && o1.getResourceOrder() != null && o2.getResourceOrder() != null)
					{
						return o1.getResourceOrder().compareTo(o2.getResourceOrder());
					}
					return 0;
				}
				
			});
		}
		
		return list;
	}

}
