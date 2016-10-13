package com.galaxyinternet.service;

import java.util.List;
import java.util.Map;

import com.galaxyinternet.bo.IndexConfigBo;
import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.sopIndex.IndexConfig;

public interface IndexConfigService extends BaseService<IndexConfig> {
	
	List<IndexConfigBo> queryConfigResource(Map<String,Object> params);

	List<IndexConfigBo> queryAvailableConfig(Map<String, Object> params);

	void saveIndexConfig(IndexConfig indexConfig, List<IndexConfig> indexConfigList);

}
