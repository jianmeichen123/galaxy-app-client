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
	
	/**
	 * 用户  获取  首页可显示项
	 * userRole 中用户关联的 resourceId
	 * indexConfig 中 首页配置的 resourceId
	 * 2者交集  
	 * 
	 * @param params key:roleIds 用户角色集合
	 */
	@Override
	public List<IndexConfigBo> queryUserIndexModel(Map<String, Object> params) {
		params.put("indexDivConfig", 1);
		return indexConfigDao.selectUserIndexModel(params);
	}
	
	
	/**
	 * 管理员 获取  模版已配置项
	 */
	@Override
	public List<IndexConfigBo> queryConfigResource(Map<String, Object> params) {
		return indexConfigDao.selectConfigByResource(params);
	}

	/**
	 * 管理员 获取 可配置项
	 */
	@Override
	public List<IndexConfigBo> queryAvailableConfig(Map<String, Object> params) {
		List<IndexConfigBo> result = indexConfigDao.selectAvailableConfig(params);
		return result==null||result.isEmpty()?new ArrayList<IndexConfigBo>():result;
	}

}
