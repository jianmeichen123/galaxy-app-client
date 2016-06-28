package com.galaxyinternet.user.controller;
import static com.galaxyinternet.framework.core.form.Token.TOKEN;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.galaxyinternet.bo.UserBo;
import com.galaxyinternet.common.controller.BaseControllerImpl;
import com.galaxyinternet.exception.PlatformException;
import com.galaxyinternet.framework.cache.Cache;
import com.galaxyinternet.framework.core.config.PlaceholderConfigurer;
import com.galaxyinternet.framework.core.constants.Constants;
import com.galaxyinternet.framework.core.constants.UserConstant;
import com.galaxyinternet.framework.core.form.Token;
import com.galaxyinternet.framework.core.form.TokenGenerator;
import com.galaxyinternet.framework.core.model.Page;
import com.galaxyinternet.framework.core.model.PageRequest;
import com.galaxyinternet.framework.core.model.ResponseData;
import com.galaxyinternet.framework.core.model.Result;
import com.galaxyinternet.framework.core.model.Result.Status;
import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.framework.core.utils.GSONUtil;
import com.galaxyinternet.framework.core.utils.JSONUtils;
import com.galaxyinternet.framework.core.utils.PWDUtils;
import com.galaxyinternet.framework.core.utils.mail.MailTemplateUtils;
import com.galaxyinternet.framework.core.utils.mail.SimpleMailSender;
import com.galaxyinternet.framework.core.validator.ValidatorResultHandler;
import com.galaxyinternet.model.department.Department;
import com.galaxyinternet.model.user.User;
import com.galaxyinternet.service.DepartmentService;
import com.galaxyinternet.service.UserService;

/**
 * 用户相关
 * 
 * @author zhaoying
 *
 */
@Controller
@RequestMapping("/galaxy/user")
public class UserController extends BaseControllerImpl<User, UserBo> {
	final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	@Autowired
	private Cache cache;

	@Autowired
	private DepartmentService departmentService;
	@Override
	protected BaseService<User> getBaseService() {
		return this.userService;
	}
	
	private String loginUrl;
	
	@Value("${project.home.page.url}")
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	
	public String getLoginUrl() {
		return loginUrl;
	}

	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(HttpServletRequest request,HttpServletResponse response) {
		
		return"system/user/user_list";
	}

	/**
	 * 重置密码 邮件通知
	 * 
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/resetPwd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<User> resetPwd(@RequestBody User user) {
		
		ResponseData<User> responseBody = new ResponseData<User>();
		Result result = new Result();
		int retValue = 0;
		boolean bl = false;
		try {
			retValue = userService.resetPwd(user.getId());

		} catch (PlatformException e) {

			if (logger.isErrorEnabled()) {
				logger.error("resetPwd ", e);
			}
		}
		User nUser = userService.queryById(user.getId());
		String toMail = nUser.getEmail() + Constants.MAIL_SUFFIX; // "sue_vip@126.com"; 收件人邮件地址
		//使用模板发送邮件
		String str = MailTemplateUtils.getContentByTemplate(Constants.MAIL_RESTPWD_CONTENT);
		String content = PlaceholderConfigurer.formatText(str, nUser.getRealName(),nUser.getEmail(),nUser.getOriginPassword(),this.getLoginUrl(),this.getLoginUrl());
		
		String subject = "重置密码通知";// 邮件主题
		bl = SimpleMailSender.sendHtmlMail(toMail, subject, content);
		 if (retValue < 1) {
			result.addError("密码重置失败");
			responseBody.setResult(result);
		} else if (retValue > 0 && bl== false ) {
			result.addError("邮件发送失败");
			responseBody.setResult(result);
		} else {
			responseBody.setResult(new Result(Status.OK, nUser));
		}
		
		return responseBody;
	}

	@ResponseBody
	@RequestMapping(value = "/disableUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<User> disableUser(@RequestBody User user) {

		if (StringUtils.equals(user.getStatus(), UserConstant.NORMAL)) {
			user.setStatus(UserConstant.DISABLE);
		} else {
			user.setStatus(UserConstant.NORMAL);
		}
		ResponseData<User> responseBody = new ResponseData<User>();
		try {

			userService.updateById(user);
			responseBody.setResult(new Result(Status.OK, user));

		} catch (PlatformException e) {
			responseBody
					.setResult(new Result(Status.ERROR, "disableUser faild"));

			if (logger.isErrorEnabled()) {
				logger.error("disableUser ", e);
			}
		}
		
		return responseBody;
	}

	/**
	 * 获取用户列表数据 重新组装关联数据
	 * 
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryUserList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<User> queryUserList(HttpServletRequest request,
			@RequestBody User user) {
		ResponseData<User> responseBody = new ResponseData<User>();
/*		Object obj = request.getSession().getAttribute(Constants.SESSION_USER_KEY);
		if (obj == null) {
			responseBody.setResult(
					new Result(Status.ERROR, "validate loging session failed"));
			return responseBody;
		}
*/
		try { 
			Page<User> pageUser = userService.queryUserPageList(user,new PageRequest(user.getPageNum(), user.getPageSize()));
			responseBody.setPageList(pageUser);
			responseBody.setResult(new Result(Status.OK, ""));
			return responseBody;

		} catch (PlatformException e) {
			responseBody
					.setResult(new Result(Status.ERROR, "queryUserList faild"));

			if (logger.isErrorEnabled()) {
				logger.error("queryUserList ", e);
			}
		}

		return responseBody;
	}

	/**
	 * 更新用户
	 * @param user
	 * @param result
	 * @return
	 */
	@Token
	@ResponseBody
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<UserBo> updateUser( @RequestBody @Valid UserBo user,BindingResult result) {
		ResponseData<UserBo> responseBody = new ResponseData<UserBo>();
		boolean bl = true;
		long retValue = 0;
		Result validationResult = ValidatorResultHandler.handle(result);
		if (validationResult.getStatus() == Status.ERROR) {
			responseBody.setResult(validationResult);
			return responseBody;
		}
		Result jsonResult = new Result();
		try {
			if (user.getId() != null) {
				retValue = userService.updateUser(user);
				jsonResult.setStatus(Status.OK);
				
			} else {
				String oriPwd = PWDUtils.genRandomNum(6);
				user.setOriginPassword(oriPwd);
				// 加密
				user.setPassword(PWDUtils.genernateNewPassword(oriPwd));
				user.setStatus(UserConstant.NORMAL);
				retValue = userService.insertUser(user);
				String toMail = user.getEmail() + Constants.MAIL_SUFFIX; // "sue_vip@126.com"; 收件人邮件地址
				//使用模板发送邮件
				String str = MailTemplateUtils.getContentByTemplate(Constants.MAIL_INITIALPWD_CONTENT);
				String content = PlaceholderConfigurer.formatText(str, user.getRealName(),user.getEmail(),oriPwd,this.getLoginUrl(),this.getLoginUrl());
			
				String subject = "新用户注册通知";// 邮件主题
				bl = SimpleMailSender.sendHtmlMail(toMail, subject, content);
			}
			
		} catch (PlatformException e) {

			if (logger.isErrorEnabled()) {
				logger.error("resetPwd ", e);
			}
		} 
		
		 if (retValue < 1) {
			 jsonResult.addError("新增用户失败");
				responseBody.setResult(jsonResult);
			} else if (retValue > 0 && bl== false ) {
				jsonResult.addError("邮件发送失败");
				responseBody.setResult(jsonResult);
			} else {
				jsonResult.addOK(user);
				responseBody.setResult(jsonResult);
			}
		return responseBody;
	}

	/**
	 * 获取部门列表
	 * @author zhaoying
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/departmentList", method = RequestMethod.GET)
	public ResponseData<Department> departmentList(Integer type) {
		
		ResponseData<Department> responseBody = new ResponseData<Department>();
		try {
			// 部门列表
			List<Department> deptList = null;
			if (null == type) {
				deptList = departmentService.queryAll();
			} else {
				deptList = departmentService.queryListByType(type);
			}
			responseBody.setEntityList(deptList);
			responseBody.setResult(new Result(Status.OK, ""));
			return responseBody;

		} catch (PlatformException e) {
			responseBody
					.setResult(new Result(Status.ERROR, "departmentList faild"));

			if (logger.isErrorEnabled()) {
				logger.error("departmentList ", e);
			}
		}
		return responseBody;
	}

	/**
	 * 获取用户列表
	 * @author zhaoying
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public ResponseData<User> userList(HttpServletRequest request) {
		String realName = request.getParameter("realName") ;
		User user = new User();
		if (StringUtils.isNotBlank(realName)) {
			user.setRealName(realName);
		}
		
		ResponseData<User> responseBody = new ResponseData<User>();
		try {
			// 用户列表
			Page<User> pageUser = userService.queryUserPageList(user,null);
			responseBody.setPageList(pageUser);
			responseBody.setResult(new Result(Status.OK, ""));
//			responseBody.setEntityList(userList);
			return responseBody;

		} catch (PlatformException e) {
			responseBody
					.setResult(new Result(Status.ERROR, "userList faild"));

			if (logger.isErrorEnabled()) {
				logger.error("userList ", e);
			}
		}
		return responseBody;
	}
	
	/**
	 * 获取表单提交时的token，防止重复提交
	 */
	@ResponseBody
	@RequestMapping(value = "/formtoken", method = RequestMethod.POST)
	public  Map<String, Object> fetchFormToken(HttpServletRequest request) {
		String tokenValue = TokenGenerator.getInstance().generateToken();
		request.getSession().setAttribute(tokenValue, tokenValue);
		Result result = new Result();
		result.setErrorCode(Constants.OPTION_SUCCESS);
		cache.set(tokenValue, Constants.TOKEN_IN_REDIS_TIMEOUT_SECONDS, tokenValue);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(TOKEN, tokenValue);
		map.put("result", result);
		return map;
	}
	
	/**
	 * Ajax请求校验用户名是否重复
	 */
	@RequestMapping(value = "checkNickName")
	@ResponseBody
	public Map<String, Object>  checkLoginName(@RequestBody User query) {

		User user = userService.queryByNickName(query);
		Map<String, Object> map = new HashMap<String, Object>();
		if (user == null) {
			map.put("flag", false);
		} else {
			map.put("flag", true);
		}
		return map;
	}
	
	/**
	 * Ajax请求校验邮箱是否重复
	 */
	@RequestMapping(value = "checkEmail")
	@ResponseBody
	public Map<String, Object>  checkEmail(@RequestBody User query) {

		User user = userService.queryByEmail(query);
		Map<String, Object> map = new HashMap<String, Object>();
		if (user == null) {
			map.put("flag", false);
		} else {
			map.put("flag", true);
		}
		return map;
	}
	
	@RequestMapping(value = "test")
	@ResponseBody
	public Map<String, Object>  test(HttpServletRequest request) {

		String json = JSONUtils.getBodyString(request);
		User query = GSONUtil.fromJson(json, User.class);
		User user = userService.queryByEmail(query);
		Map<String, Object> map = new HashMap<String, Object>();
		if (user == null) {
			map.put("flag", false);
		} else {
			map.put("flag", true);
		}
		return map;
	}
	
}
