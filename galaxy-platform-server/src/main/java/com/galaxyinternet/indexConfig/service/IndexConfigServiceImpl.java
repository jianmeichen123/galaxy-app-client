package com.galaxyinternet.indexConfig.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxyinternet.bo.IndexConfigBo;
import com.galaxyinternet.dao.sopIndex.IndexConfigDao;
import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.framework.core.service.impl.BaseServiceImpl;
import com.galaxyinternet.model.sopIndex.IndexConfig;
import com.galaxyinternet.service.IndexConfigService;
@Service("com.galaxyinternet.service.OperationMessageService")
public class IndexConfigServiceImpl extends BaseServiceImpl<IndexConfig> implements IndexConfigService {

	@Autowired
	private IndexConfigDao indexConfigDao;
	
	@Override
	protected BaseDao<IndexConfig, Long> getBaseDao() {
		return this.indexConfigDao;
	}

	@Override
	public List<IndexConfigBo> queryConfigByResource(Map<String, Object> params) {
		return indexConfigDao.selectConfigByResource(params);
	}



}
