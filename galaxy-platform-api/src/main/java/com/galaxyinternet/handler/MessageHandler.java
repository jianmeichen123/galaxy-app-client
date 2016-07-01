package com.galaxyinternet.handler;

import java.io.Serializable;

import org.springframework.core.Ordered;

import com.galaxyinternet.model.operationMessage.OperationMessage;

public interface MessageHandler extends Ordered,Serializable
{
	public boolean support(OperationMessage message);
	public OperationMessage handle(OperationMessage message);
}
