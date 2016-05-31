package com.galaxyinternet.resource.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.galaxyinternet.common.controller.BaseControllerImpl;
import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.department.Department;
import com.galaxyinternet.model.resource.PlatformResource;
import com.galaxyinternet.service.DepartmentService;
import com.galaxyinternet.service.ResourceService;

/**
 * 权限资源配置
 * @author chenjianmei
 *
 */
@Controller
@RequestMapping("/galaxy/resource")
public class ResourceController extends BaseControllerImpl<PlatformResource, PlatformResource> {
	final Logger logger = LoggerFactory.getLogger(ResourceController.class);
	@Autowired
	private ResourceService resourceService;
	
	@Override
	protected BaseService<PlatformResource> getBaseService() {
		return this.resourceService;
	}

}
