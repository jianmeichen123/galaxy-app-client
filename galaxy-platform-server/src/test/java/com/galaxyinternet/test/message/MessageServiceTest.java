package com.galaxyinternet.test.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.galaxyinternet.model.operationMessage.OperationMessage;
import com.galaxyinternet.service.OperationMessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/server_ctx.xml" })
public class MessageServiceTest
{
	@Autowired
	private OperationMessageService service;
	
	@Test
	public void insertTest()
	{
		OperationMessage entity = new OperationMessage();
		entity.setDepartment("dept1");
		entity.setOperatorDepartmentId(1L);;
		entity.setBelongDepartmentId(2L);
		entity.setRole("Role1");
		entity.setOperator("Operator 1");
		entity.setOperatorId(2L);
		entity.setType("type1");
		entity.setProjectId(3L);
		entity.setProjectName("project name");
		entity.setContent("content1");
		entity.setMessageType("1.1");
		service.insert(entity);
	}
}
