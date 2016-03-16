package com.galaxyinternet.service;

import org.springframework.data.domain.Pageable;

import com.galaxyinternet.bo.OperationMessageBo;
import com.galaxyinternet.framework.core.model.Page;
import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.operationMessage.OperationMessage;

public interface OperationMessageService extends BaseService<OperationMessage> {

	Page<OperationMessage> queryPageList(OperationMessageBo query, Pageable pageable);

	
	Long selectCount(OperationMessage query);
}
