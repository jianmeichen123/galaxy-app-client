package com.galaxyinternet.service;

import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.operationMessage.OperationMessage;

public interface OperationMessageService extends BaseService<OperationMessage> {

	//Page<OperationMessage> queryPageList(OperationMessageBo query, Pageable pageable);

	
	Long selectCount(OperationMessage query);
	
}
