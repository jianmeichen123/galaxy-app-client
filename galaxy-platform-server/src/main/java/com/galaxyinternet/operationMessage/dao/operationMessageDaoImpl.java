package com.galaxyinternet.operationMessage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.galaxyinternet.dao.operationMessage.OperationMessageDao;
import com.galaxyinternet.framework.core.dao.impl.BaseDaoImpl;
import com.galaxyinternet.framework.core.exception.DaoException;
import com.galaxyinternet.model.operationMessage.OperationMessage;

@Repository("operationMessageDao")
public class operationMessageDaoImpl extends BaseDaoImpl<OperationMessage, Long> implements OperationMessageDao {

	@Override
	public List<String> selectProjecIdsByOperatorId(Long operator) {
		Assert.notNull(operator);
		try {
			return sqlSessionTemplate.selectList(getSqlName("selectProjecIdsByOperatorId"), operator);
		} catch (Exception e) {
			throw new DaoException(String.format("根据操作人查询项目id出错！语句：%s", getSqlName("selectProjecIdsByOperator")), e);
		}
	}
	
}