package com.galaxyinternet.indexConfig.service;

import java.util.ArrayList;
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
@Service("com.galaxyinternet.service.IndexConfigService")
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

	@Override
	public List<IndexConfigBo> queryAvailableConfig(Map<String, Object> params) {
		List<IndexConfigBo> result = indexConfigDao.selectAvailableConfig(params);
		return result==null||result.isEmpty()?new ArrayList<IndexConfigBo>():result;
	}

	@Override
	public void saveIndexConfig(IndexConfig indexConfig, List<IndexConfig> indexConfigList) {
		List<IndexConfig> toSave = new ArrayList<IndexConfig>();
		for(IndexConfig iCon : indexConfigList){
			indexConfig.setSorting(iCon.getShapeType());
			indexConfig.setResourceId(iCon.getResourceId());
			indexConfig.setStyleCss(iCon.getStyleCss());
			indexConfig.setShapeType(iCon.getShapeType());
			toSave.add(iCon);
		}
		indexConfigDao.insertInBatch(toSave);
	}



}
