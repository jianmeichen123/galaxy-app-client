package com.galaxyinternet.dao.sopIndex;

import java.util.List;
import java.util.Map;

import com.galaxyinternet.bo.IndexConfigBo;
import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.model.sopIndex.IndexConfig;

public interface IndexConfigDao extends BaseDao<IndexConfig, Long>{
	
	List<IndexConfigBo> selectConfigByResource(Map<String,Object> params);

}