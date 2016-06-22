package com.galaxyinternet.operationMessage.handler;

import org.springframework.stereotype.Component;

import com.galaxyinternet.handler.MessageHandler;
import com.galaxyinternet.model.operationMessage.OperationMessage;
@Component
public class CommonMessageHandler implements MessageHandler
{

	private static final long serialVersionUID = 1L;

	@Override
	public boolean support(OperationMessage message)
	{
		return message != null && message.getMessageType() != null;
	}

	@Override
	public void handle(OperationMessage message)
	{
		StringBuffer content = new StringBuffer();
		if(message.getOperator() != null)
		{
			content.append(message.getOperator());
		}
		if(message.getContent() != null)
		{
			content.append(message.getContent());
		}
		message.setContent(content.toString());
	}

	@Override
	public int getOrder()
	{
		return LOWEST_PRECEDENCE;
	}
	
	

}
