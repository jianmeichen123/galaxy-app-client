package com.galaxyinternet.indexConfig.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.galaxyinternet.bo.IndexConfigBo;
import com.galaxyinternet.dao.sopIndex.IndexConfigDao;
import com.galaxyinternet.framework.core.dao.impl.BaseDaoImpl;
import com.galaxyinternet.framework.core.exception.DaoException;
import com.galaxyinternet.model.sopIndex.IndexConfig;

@Repository("indexConfigDao")
public class IndexConfigDaoImpl extends BaseDaoImpl<IndexConfig, Long> implements IndexConfigDao {

	@Override
	public List<IndexConfigBo> selectConfigByResource(Map<String, Object> params) {
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectConfigByResource"), params);
		} catch (Exception e) {
			throw new DaoException(String.format("管理员查询页面配置信息出错！语句：%s", getSqlName("selectConfigByResource")), e);
		}
	}

	
	@Override
	public List<IndexConfigBo> selectAvailableConfig(Map<String, Object> params) {
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectAvailableConfig"), params);
		} catch (Exception e) {
			throw new DaoException(String.format("管理员查询页面可配置信息出错！语句：%s", getSqlName("selectAvailableConfig")), e);
		}
	}


	@Override
	public List<IndexConfigBo> selectUserIndexModel(Map<String,Object> params) {
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectUserIndexModel"), params);
		} catch (Exception e) {
			throw new DaoException(String.format("用户查询页面可显示模块出错！语句：%s", getSqlName("selectUserIndexModel")), e);
		}
	}


	
	
}