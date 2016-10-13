package com.galaxyinternet.indexConfig.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.galaxyinternet.bo.IndexConfigBo;
import com.galaxyinternet.dao.sopIndex.IndexConfigDao;
import com.galaxyinternet.framework.core.dao.impl.BaseDaoImpl;
import com.galaxyinternet.framework.core.exception.DaoException;
import com.galaxyinternet.model.sopIndex.IndexConfig;

@Repository("indexConfigDao")
public class IndexConfigDaoImpl extends BaseDaoImpl<IndexConfig, Long> implements IndexConfigDao {

	@Override
	public List<IndexConfigBo> selectConfigByResource(Map<String, Object> params) {
		Assert.notNull(params);
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectConfigByResource"), params);
		} catch (Exception e) {
			throw new DaoException(String.format("根据操作人查询项目id出错！语句：%s", getSqlName("selectConfigByResource")), e);
		}
	}

	
	
}