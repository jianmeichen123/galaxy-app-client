package com.galaxyinternet.operationMessage.dao;

import org.springframework.stereotype.Repository;

import com.galaxyinternet.dao.operationMessage.OperationMessageDao;
import com.galaxyinternet.framework.core.dao.impl.BaseDaoImpl;
import com.galaxyinternet.model.operationMessage.OperationMessage;

@Repository("operationMessageDao")
public class operationMessageDaoImpl extends BaseDaoImpl<OperationMessage, Long> implements OperationMessageDao {

}