package com.galaxyinternet.service;

import java.util.List;

import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.resource.PlatformResource;

public interface ResourceService extends BaseService<PlatformResource> {
	/**
	 * 查询出用户所拥有的资源项
	 * @param uid
	 * @return
	 */
	List<PlatformResource> queryResourceListToUser(Long uid);
	
	public List<PlatformResource> queryUserMenus(Long userId, Long parentId);
}
