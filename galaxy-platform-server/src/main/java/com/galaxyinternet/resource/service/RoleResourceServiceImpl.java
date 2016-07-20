package com.galaxyinternet.resource.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.galaxyinternet.dao.resource.ResourceDao;
import com.galaxyinternet.dao.resource.RoleResourceDao;
import com.galaxyinternet.dao.role.RoleDao;
import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.framework.core.service.impl.BaseServiceImpl;
import com.galaxyinternet.model.resource.PlatformResource;
import com.galaxyinternet.model.resource.RoleResource;
import com.galaxyinternet.model.role.Role;
import com.galaxyinternet.service.RoleResourceService;

@Service("com.galaxyinternet.service.RoleResourceService")
public class RoleResourceServiceImpl extends BaseServiceImpl<RoleResource> implements RoleResourceService {

	@Autowired
	private RoleResourceDao roleResourceDao;
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Override
	protected BaseDao<RoleResource, Long> getBaseDao() {
		// TODO Auto-generated method stub
		return roleResourceDao;
	}

	@Override
	@Transactional
	public void insertBatch(RoleResource roleResource) {
		// TODO Auto-generated method stub
		String[] resourceIds = roleResource.getResourceIds().split(",");
		
		//更新角色表
		Role role = new Role();
		role.setId(roleResource.getRoleId());
		role.setName(roleResource.getName());
		role.setDescription(roleResource.getDescription());
		roleDao.updateById(role);
		
		for(int i = 0 ;i < resourceIds.length ; i++ ){
			if(!StringUtils.isBlank(resourceIds[i])){
				//角色资源关系表
				RoleResource roleSource = new RoleResource();
				roleSource.setRoleId(roleResource.getRoleId());
				roleSource.setResourceId(Long.valueOf(resourceIds[i]));
				roleSource.setCreatedUid(roleResource.getCreatedUid());
				roleResourceDao.insert(roleSource);
				//资源表
				PlatformResource  resource = new PlatformResource();
				resource.setId(Long.valueOf(resourceIds[i]));
				resource.setResourceRange(roleResource.getResouceRange());
				resourceDao.updateByIdSelective(resource);
				
			}
		}
	}

}
