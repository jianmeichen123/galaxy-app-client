package com.galaxyinternet.bo;

import com.galaxyinternet.model.operationMessage.OperationMessage;

public class OperationMessageBo extends OperationMessage{

	private static final long serialVersionUID = 1L;
	private String extendFiled;// 业务对象中扩展的字段
	public String getExtendFiled() {
		return extendFiled;
	}
	public void setExtendFiled(String extendFiled) {
		this.extendFiled = extendFiled;
	}
}
