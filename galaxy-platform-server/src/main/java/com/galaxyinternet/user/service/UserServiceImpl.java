package com.galaxyinternet.user.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxyinternet.dao.user.UserDao;
import com.galaxyinternet.framework.cache.Cache;
import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.framework.core.model.Header;
import com.galaxyinternet.framework.core.model.ResponseData;
import com.galaxyinternet.framework.core.model.Result;
import com.galaxyinternet.framework.core.model.Result.Status;
import com.galaxyinternet.framework.core.service.impl.BaseServiceImpl;
import com.galaxyinternet.framework.core.utils.PWDUtils;
import com.galaxyinternet.framework.core.utils.SessionUtils;
import com.galaxyinternet.model.user.User;
import com.galaxyinternet.service.UserService;

@Service("com.galaxyinternet.service.UserService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	// private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserDao userDao;
	
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
		return super.insert(user);
	}

	@Override
	public int resetPwd(User user) {

		user.setPassword(user.getOriginPassword());
		return super.updateById(user);
	}

	@Override
	public ResponseData<User> login(User user) {

		ResponseData<User> responsebody = new ResponseData<User>();

		// 获取解密后的nickName和password 
		String nickName = PWDUtils.decodePasswordByBase64(user.getNickName());
		String password = PWDUtils.decodePasswordByBase64(user.getPassword());
		
		password = PWDUtils.genernateNewPassword(password);	//重新加密password
		user.setNickName(nickName);
		user.setPassword(password);
		
		user = userDao.selectOne(user);											// 根据表单输入字段查询用户
		if (user == null) {
			responsebody.setResult(new Result(Status.ERROR, "用户名或密码错误！"));
		} else {
			String sessionId = SessionUtils.createWebSessionId();		//封装 responsebody
			System.out.println(sessionId);
			cache.set(sessionId, user);													//将sessionId存入cache
			Header header = new Header();
			header.setLoginName(user.getNickName());
			header.setSessionId(sessionId);
			header.setUserId(user.getId());
			responsebody.setHeader(header);
			responsebody.setResult(new Result(Status.OK, "登录成功！"));
		}
		return responsebody;
	}

	
	@Override
	public ResponseData<User> logout(Header header, HttpServletRequest request){
		ResponseData<User> responsebody = new ResponseData<User>();
		String sessionId = header.getSessionId();		
		request.getSession().removeAttribute("sessionUser");				//从本地session删除user
		cache.remove(sessionId);															//从redis中删除sessionId
		responsebody.setResult(new Result(Status.OK,"退出登录"));
		return responsebody;
	}
}
