/*package com.galaxyinternet.test.dict;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.galaxyinternet.model.dict.Dict;
import com.galaxyinternet.service.DictService;

public class DictServiceTest {

	
	private DictService dictService;
	
	@SuppressWarnings("resource")
	@Before
	public void getService(){
		if(dictService == null){
			ApplicationContext applicationContext  = new ClassPathXmlApplicationContext("classpath:spring/server_ctx.xml");
			dictService = applicationContext.getBean(DictService.class);
		}
		
	}
	
	@Test
	public void insert() throws Exception {
		Dict dict = new Dict();
		dict.setParentCode("fileWorktype");
		dict.setName("测试");
		dictService.insert(dict);
	}
	
	
	@Test
	public void updateByCode() throws Exception {
		Dict dict = new Dict();
		dict.setCode("meetingType:3");
		dict.setName("测试");
		dictService.updateByCode(dict);
	}
	
	
	@Test
	public void selectByCode() throws Exception {
		Dict dict = dictService.selectByCode("meetingType:3");
		System.out.println(dict.toString());
	}
	
	@Test
	public void selectByParentCode() throws Exception {
		List<Dict> dicts = dictService.selectByParentCode("meetingType");
		System.out.println(dicts.size());
	}
}
*/