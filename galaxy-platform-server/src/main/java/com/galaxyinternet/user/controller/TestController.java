package com.galaxyinternet.user.controller;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.galaxyinternet.framework.core.model.ResponseData;
import com.galaxyinternet.framework.core.model.Result;
import com.galaxyinternet.framework.core.model.Result.Status;
import com.galaxyinternet.model.user.User;

@Controller
@RequestMapping("/galaxy/test")
public class TestController {
	final Logger logger = LoggerFactory.getLogger(TestController.class);

	@RequestMapping(value = "/href")
	public String toLogin() {
		System.err.println("Pass  authority  check  and come here!");
		return "system/login";
	}

	@ResponseBody
	@RequestMapping(value = "/ajax", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<User> login(HttpServletRequest request) {
		ResponseData<User> responsebody = new ResponseData<User>();
		responsebody.setResult(new Result(Status.OK, "111", "请求成功！"));
		return responsebody;
	}


}
