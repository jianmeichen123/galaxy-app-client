package com.galaxyinternet.operationMessage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.galaxyinternet.bo.OperationMessageBo;
import com.galaxyinternet.common.controller.BaseControllerImpl;
import com.galaxyinternet.exception.PlatformException;
import com.galaxyinternet.framework.core.model.ResponseData;
import com.galaxyinternet.framework.core.model.Result;
import com.galaxyinternet.framework.core.model.Result.Status;
import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.operationMessage.OperationMessage;
import com.galaxyinternet.service.OperationMessageService;

@Controller
@RequestMapping("/galaxy/operationMessage")
public class OperationMessageController extends BaseControllerImpl<OperationMessage, OperationMessageBo> {
	
	final Logger logger = LoggerFactory.getLogger(OperationMessageController.class);
	
	@Autowired
	private OperationMessageService operationMessageService;
	
	@Autowired
	com.galaxyinternet.framework.cache.Cache cache;
	
	@Override
	protected BaseService<OperationMessage> getBaseService() {
		return this.operationMessageService;
	}
	
	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<OperationMessage> insert(@RequestBody OperationMessage operationMessage) {
		ResponseData<OperationMessage> responseBody = new ResponseData<OperationMessage>();
		Result result = new Result();
		try {
			operationMessageService.insert(operationMessage);
			result.setStatus(Status.OK);
			responseBody.setEntity(operationMessage);
		} catch (PlatformException e){
			result.addError(e.getMessage(), e.getCode()+"");
			logger.error("新增错误",e);
		} catch (Exception e) {
			result.addError("系统错误");
			logger.error("新增错误",e);
		}
		responseBody.setResult(result);
		return responseBody;
	}

	
}