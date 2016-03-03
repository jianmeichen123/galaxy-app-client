package com.galaxyinternet.utils;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

public class InitAppliacaion implements InitializingBean, ServletContextAware{

	@Autowired
	private DictUtil dictUtil;
	
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		dictUtil.initDictKV();
	}

}
