package com.galaxyinternet.dao.user;

import java.util.List;
import java.util.Map;

import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.model.user.User;

public interface UserDao extends BaseDao<User, Long> {
	
	User selectByNickName(User user);
	User selectByEmail(User user);
	User selectByRealName(String realName);
	public List<User> selectListById(List<String> idList);
	
	public List<User> selectTzjlSum(Map<String, Object> params);
	List<User> selectUserByParams(Map<String, Object> params);
	
	/**
	 * 关联查询用户的详细信息 包括名字,角色,email,部门
	 * @param query
	 * @return
	 */
	public List<Map<String,Object>> selectUserDetail(Map<String,Object> params);
}
