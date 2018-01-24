package com.galaxyinternet.user.service;

import com.galaxyinternet.bo.UserBo;
import com.galaxyinternet.dao.user.UserDao;
import com.galaxyinternet.framework.core.constants.UserConstant;
import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.framework.core.model.Page;
import com.galaxyinternet.framework.core.service.impl.BaseServiceImpl;
import com.galaxyinternet.framework.core.utils.PWDUtils;
import com.galaxyinternet.model.auth.AuthResult;
import com.galaxyinternet.model.department.Department;
import com.galaxyinternet.model.role.Role;
import com.galaxyinternet.model.user.User;
import com.galaxyinternet.model.user.UserRole;
import com.galaxyinternet.service.DepartmentService;
import com.galaxyinternet.service.RoleService;
import com.galaxyinternet.service.UserRoleService;
import com.galaxyinternet.service.UserService;
import com.galaxyinternet.utils.AuthRequest;
import com.galaxyinternet.utils.MessageStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.galaxyinternet.utils.ValidationUtil.throwPlatformException;

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
	private AuthRequest authReq;

	@Override
	protected BaseDao<User, Long> getBaseDao() {
		return this.userDao;
	}



	public List<User> selectViewByGBK(User user) {
		return userDao.selectViewByGBK(user);
	}



	@Override
	public Long insertUser(User user) {

		long result1 = userDao.insert(user);
		long result2 = 0l;
		UserRole userRole = new UserRole();

		if (user.getRoleId() == null) {
			throwPlatformException(MessageStatus.FIELD_NOT_ALLOWED_EMPTY, "roleId,不能为空");
		}
		// 合伙人角色
		if (user.getDepartmentId() != null && user.getRoleId().equals(3l)) {
			Department entity = new Department();
			entity.setId(user.getDepartmentId());
			entity.setManagerId(user.getId());
			departmentService.updateById(entity);
		}
		userRole.setRoleId(user.getRoleId());
		userRole.setUserId(user.getId());
		result2 = userRoleService.insertUserRole(userRole);
		return (result1 & result2);

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

	/*
	 * @Override public ResponseData<User> login(User user, HttpServletRequest
	 * request) {
	 * 
	 * ResponseData<User> responsebody = new ResponseData<User>(); String email
	 * = user.getEmail(); String password = user.getPassword();
	 * 
	 * if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
	 * responsebody.setResult(new Result(Status.ERROR, Constants.IS_UP_EMPTY,
	 * "用户名或密码不能为空！")); return responsebody; } // 获取解密后的email和password
	 * 
	 * user.setPassword(password);
	 * 
	 * user = userDao.selectOne(user); // 根据表单输入字段查询用户 if (user == null) {
	 * responsebody.setResult(new Result(Status.ERROR, Constants.IS_UP_WRONG,
	 * "用户名或密码错误！")); } else {
	 * 
	 * // 判断是否用户禁用 if (!isUserNormal(user)) { responsebody.setResult(new
	 * Result(Status.ERROR, Constants.USER_DISABLE, "用户已被禁用！")); return
	 * responsebody; } // 查询user相关字段 Department dept =
	 * getDepartmentByUserId(user.getId()); Role role =
	 * getRoleByUserId(user.getId()); if (role != null) {
	 * user.setRole(role.getName()); user.setRoleId(role.getId()); } if(dept !=
	 * null){ user.setDepartmentName(dept.getName());
	 * user.setDepartmentId(dept.getId()); } String sessionId =
	 * SessionUtils.createWebSessionId(); // 封装 user.setSessionId(sessionId);
	 * cache.set(sessionId, user); // 将sessionId存入cache
	 * request.getSession().setAttribute(Constants.SESSION_USER_KEY, user);
	 * 
	 * Header header = new Header(); header.setLoginName(user.getEmail());
	 * header.setSessionId(sessionId); header.setUserId(user.getId());
	 * 
	 * if (StringUtils.isNotBlank(role.getRoleCode())) {
	 * header.setAttachment(role.getRoleCode()); } else {
	 * header.setAttachment(""); }
	 * 
	 * responsebody.setHeader(header); responsebody.setResult(new
	 * Result(Status.OK, Constants.OPTION_SUCCESS, "登录成功！")); } return
	 * responsebody; }
	 */

	/*
	 * @Override public Page<User> queryPageList(Query query) { Page<User> page
	 * = userDao.selectPageList(query); List<User> content = page.getContent();
	 * List<Role> roleList = roleService.queryAll(); List<UserRole> userRoleList
	 * = userRoleService.queryAll(); List<Department> departList =
	 * departmentService.queryAll();
	 * 
	 * // 拼装关联数据 for (User user : content) { for (UserRole userRole :
	 * userRoleList) { // 目前一个用户对应一个角色，如果多个角色要考虑覆盖 if
	 * (user.getId().equals(userRole.getUserId())) { for (Role role:roleList) {
	 * if (role.getId().equals(userRole.getRoleId())) {
	 * user.setRole(role.getName()); user.setRoleId(role.getId()); } } } } for
	 * (Department dept : departList) { if
	 * (user.getDepartmentId().equals(dept.getId())) {
	 * user.setDepartmentName(dept.getName());
	 * 
	 * } }
	 * 
	 * }
	 * 
	 * page.setContent(content);
	 * 
	 * return page; }
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
					for (Role role : roleList) {
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
		int result1 = 1;
		UserRole userRole = new UserRole();
		if (user.getId() != null) {
			userRole.setUserId(user.getId());
			result1 = userDao.updateById(user);

		}

		if (user.getRoleId() == null) {
			throwPlatformException(MessageStatus.FIELD_NOT_ALLOWED_EMPTY, "roleId,不能为空");
		}

		userRole.setRoleId(user.getRoleId());
		userRole.setUserId(user.getId());
		long result2 = userRoleService.insertUserRole(userRole);
		return (int) (result1 & result2);
	}

	@Override
	public Department getDepartmentByUserId(Long userId) {
		User user = userDao.selectById(userId);
		if (user != null && user.getDepartmentId() != null) {
			Long deptId = user.getDepartmentId();
			return departmentService.queryById(deptId);
		} else {
			return null;
		}

	}

	@Override
	public List<User> getUserByRoleId(Long roleId) {
		UserRole userRole = new UserRole();
		userRole.setRoleId(roleId);
		 List<UserRole> queryList = userRoleService.queryList(userRole);
		 List<String> ids=new ArrayList<String>();
			 if(null!=queryList&&!queryList.isEmpty()){
			 for(UserRole ur:queryList){
				 ids.add(ur.getUserId().toString());
			 }
		 }
		if (ids != null&&!ids.isEmpty() ) {
			 List<User> selectListById = userDao.selectListById(ids);
			 for(int i=0;i<selectListById.size();i++){
				 
			 }
			return selectListById;
		} else {
			return null;
		}

	}

	@Override
	public User queryByNickName(User user) {
		return userDao.selectByNickName(user);
	}

	@Override
	public User queryByEmail(User user) {
		return userDao.selectByEmail(user);
	}

	/**
	 * 判断用户是否被禁用
	 * 
	 * @author zcy
	 * @param user
	 * @return
	 */
	@Override
	public boolean isUserNormal(User user) {
		if (UserConstant.NORMAL.equals(user.getStatus())) {
			return true;
		}
		return false;
	}

	@Override
	public int updatePwd(User query) {
        if (query!=null && query.getId()!= null && query.getPassword()!=null) {
        	AuthResult rtn = authReq.updatePwd(query.getId(), query.getPassword());
    		return rtn.isSuccess() ? 1 : 0;
        } else {
        	return 0;
        }
		
	}
	
	
	@Override
	public List<User> queryListById(List<String> idList) {
		// TODO Auto-generated method stub
		return userDao.selectListById(idList);
	}

	/* (non-Javadoc)
	 * @see com.galaxyinternet.service.UserService#queryUserByRealName(com.galaxyinternet.model.user.User)
	 */
	@Override
	public User queryUserByRealName(String realName) {
		return userDao.selectByRealName(realName);
	}

	
	//====report
	@Override
	public List<Map<String, Object>> report(Map<String, Object> params) {
		return null;
	}
	
	
	@Override
	public List<User> querytTzjlSum(Map<String, Object> params) {
		return userDao.selectTzjlSum(params);
	}
	
	@Override
	public List<User> querytUserByParams(Map<String, Object> params) {
		return userDao.selectUserByParams(params);
	}

	@Override
	public List<Map<String,Object>> queryUserDetail(Map<String,Object> params) {
		// TODO Auto-generated method stub
		
		return userDao.selectUserDetail(params);
	}
	public User queryById(Long id, boolean useAuth)
	{
		if(!useAuth)
		{
			return this.queryById(id);
		}
		return authReq.getUserById(id);
	}
}
