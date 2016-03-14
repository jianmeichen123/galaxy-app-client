package com.galaxyinternet.operationMessage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxyinternet.dao.operationMessage.OperationMessageDao;
import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.framework.core.service.impl.BaseServiceImpl;
import com.galaxyinternet.model.operationMessage.OperationMessage;
import com.galaxyinternet.service.OperationMessageService;
import static com.galaxyinternet.utils.ValidationUtil.isNull;
import static com.galaxyinternet.utils.ValidationUtil.isEmptyOrMoreThan;
import static com.galaxyinternet.utils.ValidationUtil.throwPlatformException;
import static com.galaxyinternet.utils.ValidationUtil.isMoreThan;
@Service("com.galaxyinternet.service.OperationMessageService")
public class OperationMessageServiceImpl extends BaseServiceImpl<OperationMessage>implements OperationMessageService {
	//private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private OperationMessageDao operationMessageDao;
	
	@Override
	protected BaseDao<OperationMessage, Long> getBaseDao() {
		return this.operationMessageDao;
	}

	@Override
	public Long insert(OperationMessage entity) {
		isNull(OperationMessage.COMMENT,entity);
		isEmptyOrMoreThan(OperationMessage.DEPARTMENT, entity.getDepartment(), 80);
		isEmptyOrMoreThan(OperationMessage.ROLE, entity.getRole(), 50);
		isEmptyOrMoreThan(OperationMessage.TYPE, entity.getType(), 50);
		isEmptyOrMoreThan(OperationMessage.PROJECT_NAME, entity.getProjectName(), 200);
		isEmptyOrMoreThan(OperationMessage.OPERATOR, entity.getOperator(), 100);
		isEmptyOrMoreThan(OperationMessage.CONTENT, entity.getContent(), 500);
		isNull(OperationMessage.MODULE,entity.getModule());
		entity.setCreatedTime(System.currentTimeMillis());
		return operationMessageDao.insert(entity);
	}
}
