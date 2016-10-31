package com.galaxyinternet.service;

import java.util.List;
import java.util.Map;

import com.galaxyinternet.bo.IndexConfigBo;
import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.sopIndex.IndexConfig;

public interface IndexConfigService extends BaseService<IndexConfig> {
	
	List<IndexConfigBo> queryConfigResource(Map<String,Object> params);

	List<IndexConfigBo> queryAvailableConfig(Map<String, Object> params);
	/**
	 * 用户  获取  首页可显示项
	 * userRole 中用户关联的 resourceId
	 * indexConfig 中 首页配置的 resourceId
	 * 2者交集  
	 * 
	 * @param params key:roleIds 用户角色集合
	 */
	List<IndexConfigBo> queryUserIndexModel(Map<String,Object> params);
	
	
	
	 int updateByResourceId(IndexConfig indexConfig);
	 
	 public List<IndexConfig> selectIndexConfigDesc();

}
