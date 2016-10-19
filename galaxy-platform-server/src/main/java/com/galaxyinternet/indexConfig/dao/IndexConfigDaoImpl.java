package com.galaxyinternet.indexConfig.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.galaxyinternet.bo.IndexConfigBo;
import com.galaxyinternet.dao.sopIndex.IndexConfigDao;
import com.galaxyinternet.framework.core.constants.SqlId;
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
	
	
	@Override
	@Transactional
	public int updateByResourceId(IndexConfig entity) {
		Assert.notNull(entity);
		UpdatedTime(entity);
		try {
			return sqlSessionTemplate.update(getSqlName("updateByResourceId"), entity);
		} catch (Exception e) {
			throw new DaoException(String.format("根据resourceIdID更新对象出错！语句：%s", getSqlName("updateByResourceId")), e);
		}
	}
	private final void UpdatedTime(IndexConfig entity) {
		entity.setUpdatedTime(new Date().getTime());
	}

	
	
}