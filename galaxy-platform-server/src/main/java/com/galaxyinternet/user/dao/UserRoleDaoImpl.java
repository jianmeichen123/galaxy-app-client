package com.galaxyinternet.user.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.galaxyinternet.dao.user.UserRoleDao;
import com.galaxyinternet.framework.core.constants.SqlId;
import com.galaxyinternet.framework.core.dao.impl.BaseDaoImpl;
import com.galaxyinternet.framework.core.exception.DaoException;
import com.galaxyinternet.model.user.UserRole;

@Repository("userRoleDao")
public class UserRoleDaoImpl extends BaseDaoImpl<UserRole, Long>
		implements
			UserRoleDao {

	@Override
	public List<Long> selectRoleIdByUserId(Long userID) {
		return sqlSessionTemplate.selectList(getSqlName("selectRoleIdByUserId"),
				userID);
	}

	@Override
	public Long insertUserRole(UserRole userRole) {
		Assert.notNull(userRole);
		try {
			userRole.setCreatedTime(new Date().getTime());
			sqlSessionTemplate.insert(getSqlName(SqlId.SQL_INSERT), userRole);
			return userRole.getId();
		} catch (Exception e) {
			throw new DaoException(
					String.format("添加对象出错！语句：%s", getSqlName(SqlId.SQL_INSERT)),
					e);
		}
	}

	@Override
	public UserRole selectByUserId(Long userId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(getSqlName("selectByUserId"),
				userId);
	}
}
