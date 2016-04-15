package com.galaxyinternet.dao.user;

import java.util.List;

import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.model.user.User;

public interface UserDao extends BaseDao<User, Long> {
	
	User selectByNickName(User user);
	User selectByEmail(User user);
	User selectByRealName(String realName);
	public List<User> selectListById(List<String> idList);
	
}
