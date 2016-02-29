package com.galaxyinternet.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.galaxyinternet.bo.UserBo;
import com.galaxyinternet.common.controller.BaseControllerImpl;
import com.galaxyinternet.framework.core.model.ResponseData;
import com.galaxyinternet.framework.core.model.Result;
import com.galaxyinternet.framework.core.model.Result.Status;
import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.framework.core.utils.mail.SimpleMailSender;
import com.galaxyinternet.model.user.User;
import com.galaxyinternet.service.UserService;

@Controller
@RequestMapping("/galaxy/user")
public class UserController extends BaseControllerImpl<User, UserBo> {
	final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	// @Autowired
	// UserRepository userRepository;

	@Autowired
	com.galaxyinternet.framework.cache.Cache cache;

	@Override
	protected BaseService<User> getBaseService() {
		return this.userService;
	}

	/**
	 * 重置密码 邮件通知
	 * 
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/resetPwd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<User> resetPwd(User user) {
		userService.resetPwd(user);
		String toMail = user.getEmail(); // "sue_vip@126.com"; 收件人邮件地址
		String content = "<html>" + "<head></head>" + "<body>" + "<div align=center>"
				+ "	<a href=http://localhost:8000/controller/vcs/login/toLogin target=_blank>"
				+ "您好，您密码已重置，请点击地址：http://localhost:8000/controller/vcs/login/toLogin  登陆 " + "	</a>" + "</div>"
				+ "</body>" + "</html>";// 邮件内容
		String subject = "重置密码通知";// 邮件主题
		SimpleMailSender.sendHtmlMail(toMail, subject, content);
		ResponseData<User> responseBody = new ResponseData<User>();
		responseBody.setResult(new Result(Status.OK, user));
		return responseBody;
	}

}
