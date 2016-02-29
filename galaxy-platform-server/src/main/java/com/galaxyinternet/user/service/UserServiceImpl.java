package com.galaxyinternet.user.service;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.galaxyinternet.dao.user.UserDao;
import com.galaxyinternet.framework.core.constants.SqlId;
import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.framework.core.model.ResponseData;
import com.galaxyinternet.framework.core.model.Result;
import com.galaxyinternet.framework.core.model.Result.Status;
import com.galaxyinternet.framework.core.service.impl.BaseServiceImpl;
import com.galaxyinternet.framework.core.utils.PWDUtils;
import com.galaxyinternet.framework.core.utils.AESUtil;
import com.galaxyinternet.framework.core.utils.Base64Util;
import com.galaxyinternet.framework.core.utils.Md5Utils;
import com.galaxyinternet.model.user.User;
import com.galaxyinternet.service.UserService;

@Service("com.galaxyinternet.service.UserService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	// private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserDao userDao;

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

		// 获取解密后的nickName和password password用MD5再加密
		try {
			String nickName = Base64Util.decode(user.getNickName());
			String password = Base64Util.decode(user.getPassword());
			password = Md5Utils.getMD5Str(password);
			user.setNickName(nickName);
			user.setPassword(password);
		} catch (UnsupportedEncodingException e) {
			// TODO
		}

		// 根据表单输入字段查询用户
		user = userDao.selectOne(user);
		if (user == null) {
			responsebody.setResult(new Result(Status.ERROR, "用户名或密码错误！"));
		} else {
			responsebody.setResult(new Result(Status.OK, "登录成功！"));
		}
		responsebody.setEntity(user);
		return responsebody;
	}
}
