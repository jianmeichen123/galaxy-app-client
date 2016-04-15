package com.galaxyinternet.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.galaxyinternet.dao.user.UserDao;
import com.galaxyinternet.framework.core.dao.impl.BaseDaoImpl;
import com.galaxyinternet.model.department.Department;
import com.galaxyinternet.model.user.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User, Long>implements UserDao {

	@Override
	public User selectByNickName(User user) {
		return sqlSessionTemplate.selectOne(getSqlName("selectByNickName"),
				user);
	}

	@Override
	public User selectByEmail(User user) {
		return sqlSessionTemplate.selectOne(getSqlName("selectByEmail"), user);
	}
	
	@Override
	public List<User> selectListById(List<String> idList) {
		return sqlSessionTemplate.selectList(getSqlName("selectListById"),idList);
	}

	/* (non-Javadoc)
	 * @see com.galaxyinternet.dao.user.UserDao#selectByRealName(com.galaxyinternet.model.user.User)
	 */
	@Override
	public User selectByRealName(String realName) {
		return sqlSessionTemplate.selectOne(getSqlName("selectByRealName"),
				realName);
	}
}
