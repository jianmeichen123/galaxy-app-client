package com.galaxyinternet.dao.operationMessage;

import java.util.List;

import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.model.operationMessage.OperationMessage;

public interface OperationMessageDao extends BaseDao<OperationMessage, Long>{

	
	List<String> selectProjecIdsByOperatorId(Long operator);
	
}
