package com.galaxyinternet.user.service;

import static com.galaxyinternet.utils.ValidationUtil.throwPlatformException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galaxyinternet.bo.UserBo;
import com.galaxyinternet.dao.user.UserDao;
import com.galaxyinternet.framework.cache.Cache;
import com.galaxyinternet.framework.core.constants.Constants;
import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.framework.core.model.Header;
import com.galaxyinternet.framework.core.model.Page;
import com.galaxyinternet.framework.core.model.ResponseData;
import com.galaxyinternet.framework.core.model.Result;
import com.galaxyinternet.framework.core.model.Result.Status;
import com.galaxyinternet.framework.core.service.impl.BaseServiceImpl;
import com.galaxyinternet.framework.core.utils.PWDUtils;
import com.galaxyinternet.framework.core.utils.SessionUtils;
import com.galaxyinternet.model.department.Department;
import com.galaxyinternet.model.role.Role;
import com.galaxyinternet.model.user.User;
import com.galaxyinternet.model.user.UserRole;
import com.galaxyinternet.service.DepartmentService;
import com.galaxyinternet.service.RoleService;
import com.galaxyinternet.service.UserRoleService;
import com.galaxyinternet.service.UserService;
import com.galaxyinternet.utils.MessageStatus;

@Service("com.galaxyinternet.service.UserService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	// private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private Cache cache;

	@Override
	protected BaseDao<User, Long> getBaseDao() {
		return this.userDao;
	}

	@Override
	public Long insertUser(User user) {
		String oriPwd = PWDUtils.genRandomNum(6);
		user.setOriginPassword(oriPwd);
		// 加密
		user.setPassword(PWDUtils.genernateNewPassword(oriPwd));
		return super.insert(user);
	}

	@Override
	public int resetPwd(Long userId) {
		User user = userDao.selectById(userId);
		if (user.getOriginPassword() == null) {
			throwPlatformException(MessageStatus.FIELD_NOT_ALLOWED_EMPTY, "原始密码");
		}
		
		// 加密
		user.setPassword(PWDUtils.genernateNewPassword(user.getOriginPassword()));
		return super.updateById(user);
	}

	@Override	
	public ResponseData<User> login(User user, HttpServletRequest request) {

		ResponseData<User> responsebody = new ResponseData<User>();
		String nickName = user.getNickName();
		String password = user.getPassword();
		
		if(StringUtils.isBlank(nickName)||StringUtils.isBlank(password)){
			responsebody.setResult(new Result(Status.ERROR,Constants.IS_UP_EMPTY,"用户名或密码不能为空！"));
			return responsebody;
		}
		// 获取解密后的nickName和password
	    nickName = PWDUtils.decodePasswordByBase64(nickName);
		password = PWDUtils.decodePasswordByBase64(password);

		password = PWDUtils.genernateNewPassword(password); // 重新加密password
		user.setNickName(nickName);
		user.setPassword(password);

		user = userDao.selectOne(user); // 根据表单输入字段查询用户
		if (user == null) {
			responsebody.setResult(new Result(Status.ERROR, Constants.IS_UP_WRONG,"用户名或密码错误！"));
		} else {
			String sessionId = SessionUtils.createWebSessionId(); // 封装
			user.setSessionId(sessionId);
			cache.set(sessionId, user); // 将sessionId存入cache
			request.getSession().setAttribute(Constants.SESSION_USER_KEY, user);

			Header header = new Header();
			header.setLoginName(user.getNickName());
			header.setSessionId(sessionId);
			header.setUserId(user.getId());
			responsebody.setHeader(header);
			responsebody.setResult(new Result(Status.OK, Constants.OPTION_SUCCESS, "登录成功！"));
		}
		return responsebody;
	}

	@Override
	public ResponseData<User> logout(HttpServletRequest request) {
		ResponseData<User> responsebody = new ResponseData<User>();
		String sessionId = request.getHeader(Constants.SESSION_ID_KEY);
		if(StringUtils.isBlank(sessionId)){
			responsebody.setResult(new Result(Status.ERROR, Constants.IS_SESSIONID_EMPTY,"sessionId为空！"));
			return responsebody;
		}
		request.getSession().removeAttribute(Constants.SESSION_USER_KEY); // 从本地session删除user
		cache.remove(sessionId); 																	// 从redis中删除sessionId
		responsebody.setResult(new Result(Status.OK, Constants.OPTION_SUCCESS, "退出登录"));
		return responsebody;
	}

	/*@Override
	public Page<User> queryPageList(Query query) {
		Page<User> page = userDao.selectPageList(query);
		List<User> content = page.getContent();
		List<Role> roleList = roleService.queryAll();
		List<UserRole> userRoleList = userRoleService.queryAll();
		List<Department> departList = departmentService.queryAll();

		// 拼装关联数据
		for (User user : content) {
			for (UserRole userRole : userRoleList) {
				// 目前一个用户对应一个角色，如果多个角色要考虑覆盖
				if (user.getId().equals(userRole.getUserId())) {
					for (Role role:roleList) {
						if (role.getId().equals(userRole.getRoleId())) {
							user.setRole(role.getName());
							user.setRoleId(role.getId());
						}
					}
				}
			}
			for (Department dept : departList) {
				if (user.getDepartmentId().equals(dept.getId())) {
					user.setDepartmentName(dept.getName());

				}
			}

		}

		page.setContent(content);

		return page;
	}
	*/
	@Override
	public Page<User> queryUserPageList(User query, Pageable pageable) {
		Page<User> page = userDao.selectPageList(query, pageable);
		List<User> content = page.getContent();
		List<Role> roleList = roleService.queryAll();
		List<UserRole> userRoleList = userRoleService.queryAll();
		List<Department> departList = departmentService.queryAll();

		// 拼装关联数据
		for (User user : content) {
			for (UserRole userRole : userRoleList) {
				// 目前一个用户对应一个角色，如果多个角色要考虑覆盖
				if (user.getId().equals(userRole.getUserId())) {
					for (Role role:roleList) {
						if (role.getId().equals(userRole.getRoleId())) {
							user.setRole(role.getName());
							user.setRoleId(role.getId());
						}
					}
				}
			}
			
			if (user.getDepartmentId() != null) {
				for (Department dept : departList) {
					
					if (user.getDepartmentId().equals(dept.getId())) {
						user.setDepartmentName(dept.getName());

					}
				}

			}
			
		}

		page.setContent(content);

		return page;
	}


    @Override
    @Transactional
    public int updateUser(UserBo user) {
        int result1 = userDao.updateById(user);
       
        if (user.getId()==null || user.getRoleId() == null) {
        	throwPlatformException(MessageStatus.FIELD_NOT_ALLOWED_EMPTY, "不能新建用户,用户ID");
        }
        
        UserRole userRole = new UserRole();
        userRole.setRoleId(user.getRoleId());
        userRole.setUserId(user.getId());
        long result2 = userRoleService.insertUserRole(userRole);
        return (int) (result1&result2);
    }
}
