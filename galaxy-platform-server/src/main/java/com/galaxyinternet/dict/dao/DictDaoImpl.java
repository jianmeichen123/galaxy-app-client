package com.galaxyinternet.dict.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.galaxyinternet.dao.dict.DictDao;
import com.galaxyinternet.framework.core.dao.impl.BaseDaoImpl;
import com.galaxyinternet.framework.core.exception.DaoException;
import com.galaxyinternet.model.dict.BatchDictInsetParam;
import com.galaxyinternet.model.dict.Dict;

@Repository("dictDao")
public class DictDaoImpl extends BaseDaoImpl<Dict, Long>implements DictDao {

	
	@Override
	public int selectCountSame(Dict dict) {
		Assert.notNull(dict);
		try {
			return sqlSessionTemplate.selectOne(getSqlName("selectCountSame"), dict);
		} catch (Exception e) {
			throw new DaoException(String.format("查询同一parent与传入参数数据相同（in） 语句：%s", getSqlName("selectCountByParentIdAndNameOrValue")), e);
		}
	}

	@Override
	public Dict selectByParentIdAndName(Dict dict) {
		Assert.notNull(dict);
		try {
			return sqlSessionTemplate.selectOne(getSqlName("selectByParentIdAndName"), dict);
		} catch (Exception e) {
			throw new DaoException(String.format("根据parentId和名称查询！语句：%s", getSqlName("selectByParentIdAndName")), e);
		}
	}

	@Override
	public List<Dict> selectByParentId(Long parentId) {
		Assert.notNull(parentId);
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectByParentId"), parentId);
		} catch (Exception e) {
			throw new DaoException(String.format("根据parentId和名称查询！语句：%s", getSqlName("selectByParentId")), e);
		}
	}

	@Override
	public int insertInBatch(BatchDictInsetParam batchDictInsetParam) {
		Assert.notNull(batchDictInsetParam);
		try {
			return sqlSessionTemplate.insert(getSqlName("insertInBatch"), batchDictInsetParam);
		} catch (Exception e) {
			throw new DaoException(String.format("批量新增数据字典语句：%s", getSqlName("insertInBatch")), e);
		}
	}

	@Override
	public int selectCountSameIn(BatchDictInsetParam batchDictInsetParam) {
		Assert.notNull(batchDictInsetParam);
		try {
			return sqlSessionTemplate.selectOne(getSqlName("selectCountSameIn"), batchDictInsetParam);
		} catch (Exception e) {
			throw new DaoException(String.format("查询同一parent与传入参数数据相同（in） 语句：%s", getSqlName("selectCountSameIn")), e);
		}
	}

	@Override
	public Dict selectByCode(String code) {
		Assert.notNull(code);
		try {
			return sqlSessionTemplate.selectOne(getSqlName("selectByCode"), code);
		} catch (Exception e) {
			throw new DaoException(String.format("根据code查询：%s", getSqlName("selectByCode")), e);
		}
	}


	@Override
	public int selectCountInCodes(List<String> codes) {
		Assert.notNull(codes);
		try {
			return sqlSessionTemplate.selectOne(getSqlName("selectCountInCodes"), codes);
		} catch (Exception e) {
			throw new DaoException(String.format("根据codes查询：%s", getSqlName("selectCountInCodes")), e);
		}
	}

	@Override
	public int selectCountByParentId(Long parentId) {
		Assert.notNull(parentId);
		try {
			return sqlSessionTemplate.selectOne(getSqlName("selectCountByParentId"), parentId);
		} catch (Exception e) {
			throw new DaoException(String.format("根据codes查询总数：%s", getSqlName("selectCountByParentId")), e);
		}
	}

}
