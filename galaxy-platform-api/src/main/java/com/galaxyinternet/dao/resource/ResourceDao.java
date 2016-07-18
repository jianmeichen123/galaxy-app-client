package com.galaxyinternet.dao.resource;

import java.util.List;

import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.model.resource.PlatformResource;

public interface ResourceDao extends BaseDao<PlatformResource, Long> {
	
	/**
	 * 查询出用户所拥有的资源项
	 * @param uid
	 * @return
	 */
	List<PlatformResource> selectResourceListToUser(Long uid);

}
