package com.galaxyinternet.service;

import java.util.List;
import java.util.Map;

import com.galaxyinternet.bo.IndexConfigBo;
import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.sopIndex.IndexConfig;

public interface IndexConfigService extends BaseService<IndexConfig> {
	
	List<IndexConfigBo> queryConfigByResource(Map<String,Object> params);
}
