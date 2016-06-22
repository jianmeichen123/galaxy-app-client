package com.galaxyinternet.operationMessage.handler;

import org.springframework.stereotype.Component;

import com.galaxyinternet.handler.MessageHandler;
import com.galaxyinternet.model.operationMessage.OperationMessage;
@Component
public class CreateProjectMessageHandler implements MessageHandler
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean support(OperationMessage message)
	{
		return message != null && "1".equals(message.getMessageType());
	}

	@Override
	public void handle(OperationMessage message)
	{
		StringBuffer content = new StringBuffer();
		content.append("添加了项目<a href=\"#\"class=\"blue project_name\" data-project-id=\"")
		.append(message.getProjectId())
		.append("\">")
		.append(message.getProjectName())
		.append("<a>");
		message.setContent(content.toString());
	}

	@Override
	public int getOrder()
	{
		return 1;
	}
}
