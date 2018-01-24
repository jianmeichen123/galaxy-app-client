package com.galaxyinternet.user.dao;

import com.galaxyinternet.dao.user.UserDao;
import com.galaxyinternet.framework.core.dao.impl.BaseDaoImpl;
import com.galaxyinternet.framework.core.utils.BeanUtils;
import com.galaxyinternet.model.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
	
	
	
	//== report
	@Override
	public List<User> selectTzjlSum(Map<String, Object> params) {
		return sqlSessionTemplate.selectList(getSqlName("selectTzjlSum"),params);
	}

	@Override
	public List<User> selectUserByParams(Map<String, Object> params) {
		return sqlSessionTemplate.selectList(getSqlName("selectUserByParams"),params);
	}

	@Override
	public List<Map<String,Object>> selectUserDetail(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(getSqlName("selectUserDetail"),params);
	}

	@Override
	public List<Map<String, Object>> getResources(Map<String, Object> params)
	{
		return sqlSessionTemplate.selectList(getSqlName("getResources"),params);
	}

	@Override
	public List<User> selectView(User user)
	{
		Map<String,Object> params = BeanUtils.toMap(user);
		return sqlSessionTemplate.selectList(getSqlName("selectView"),params);
	}

	@Override
	public List<User> selectViewByGBK(User user)
	{
		Map<String,Object> params = BeanUtils.toMap(user);
		return sqlSessionTemplate.selectList(getSqlName("selectViewByGBK"),params);
	}
	
}
