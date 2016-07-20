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
import com.galaxyinternet.model.privilege.bo.RoleBo;
import com.galaxyinternet.model.role.Role;
import com.galaxyinternet.model.user.User;
import com.galaxyinternet.service.DepartmentService;
import com.galaxyinternet.service.RoleService;
import com.galaxyinternet.service.UserService;

/**
 * 用户相关
 * 
 * @author zhaoying
 *
 */
@Controller
@RequestMapping("/galaxy/role")
public class RoleController extends BaseControllerImpl<Role, RoleBo> {
	final Logger logger = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private Cache cache;
	
	@Autowired
	private UserService userService;
	
	@Override
	protected BaseService<Role> getBaseService() {
		return this.roleService;
	}
	/**
	 * 跳转登录
	 */
	@RequestMapping(value = "/index")
	public String index() {
		return "system/role/roleList";
	}
	
	/**
	 * 跳转登录
	 */
	@RequestMapping(value = "/roleEdit")
	public String roleEdit() {
		return "system/role/roleEdit";
	}
	
	/**
	 * 获取部门列表
	 * @author chenjianmei
	 * @return
	 */
		@ResponseBody
	@RequestMapping(value = "/roleList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<Role> roleList(HttpServletRequest request,
			@RequestBody Role role,Long type) {
		
		ResponseData<Role> responseBody = new ResponseData<Role>();
		Page<Role> pageRole=new Page<>(null, null, null);
		try {
			// 部门列表
			List<Role> roleList = null;
			if(type!=null){
			    roleList = roleService.queryAll();
			}else{
				pageRole=roleService.queryRoleList(role,new PageRequest(role.getPageNum(), role.getPageSize()));
			}
			responseBody.setPageList(pageRole);
			responseBody.setResult(new Result(Status.OK, ""));
			return responseBody;

		} catch (PlatformException e) {
			responseBody
					.setResult(new Result(Status.ERROR, "roleList faild"));

			if (logger.isErrorEnabled()) {
				logger.error("roleList ", e);
			}
		}
		return responseBody;
	}
	
	/**
	 * 新增角色
	 * @param user
	 * @param result
	 * @return
	 */
	@Token
	@ResponseBody
	@RequestMapping(value = "/addRole", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<Role> addRole( @RequestBody @Valid Role role) {
		ResponseData<Role> responseBody = new ResponseData<Role>();
	
		Result jsonResult = new Result();
		Long result=null;
		try {				
			result  = roleService.insert(role);
			
		} catch (PlatformException e) {
			
			e.printStackTrace();
		} 
		 if (result ==null||result < 1) {
			 jsonResult.addError("新增角色失败");
				responseBody.setResult(jsonResult);
			} 
		 responseBody.setResult(new Result(Status.OK, ""));
		return responseBody;
	}
	@ResponseBody
	@RequestMapping(value = "/getRoleDetail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseData<Role> getRoleDetail(Long rid) {
		
		ResponseData<Role> responseBody = new ResponseData<Role>();
		try {
			// 部门列表
			Role role=new Role();
			role = roleService.queryById(rid);
		    List<User> userByRoleId = userService.getUserByRoleId(rid);
		    if(null!=userByRoleId){
		    	role.setUserListByRid(userByRoleId);	
		    }
			responseBody.setEntity(role);
			responseBody.setResult(new Result(Status.OK, ""));
			return responseBody;

		} catch (PlatformException e) {
			responseBody
					.setResult(new Result(Status.ERROR, "role faild"));

			if (logger.isErrorEnabled()) {
				logger.error("role ", e);
			}
		}
		return responseBody;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteRole", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<User> deleteRole(@RequestBody Role role) {

			ResponseData<User> responseBody = new ResponseData<User>();
		try {

			int result = roleService.delete(role);
			responseBody.setResult(new Result(Status.OK, role));

		} catch (PlatformException e) {
			responseBody
					.setResult(new Result(Status.ERROR, "deleteRole faild"));

			if (logger.isErrorEnabled()) {
				logger.error("deleteRole ", e);
			}
		}
		
		return responseBody;
	}
	/**
	 * Ajax请求校验角色名称是否重复
	 */
	@RequestMapping(value = "checkRoleName")
	@ResponseBody
	public Map<String, Object>  checkRoleName(@RequestBody Role query) {

		Role role = roleService.queryByRoleName(query);
		Map<String, Object> map = new HashMap<String, Object>();
		if (role == null) {
			map.put("flag", false);
		} else {
			map.put("flag", true);
		}
		return map;
	}
	
	
}
