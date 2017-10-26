package com.galaxyinternet.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.galaxyinternet.dao.user.UserLoginHisDao;
import com.galaxyinternet.framework.core.dao.impl.BaseDaoImpl;
import com.galaxyinternet.framework.core.exception.DaoException;
import com.galaxyinternet.model.user.UserLoginHis;

@Repository("UserLoginHisDao")
public class UserLoginHisDaoImpl extends BaseDaoImpl<UserLoginHis, Long>implements UserLoginHisDao {

	@Override
	public int insertUserLogonHis(UserLoginHis userLoginHis) {
		//ssAssert.notNull(userLoginHis);
		try {
			return sqlSessionTemplate.insert( "insertUserLogonHis" ,  userLoginHis);
		
		} catch (Exception e) {
			throw new DaoException(String.format("添加对象出错！语句：%s",  "insertUserLogonHis") ,  e);		
		}
	}
	@Override
	public int updateUserLogonHis(UserLoginHis userLoginHis){
		try {
			return sqlSessionTemplate.update( "updateUserLogonHis" ,  userLoginHis);
		} catch (Exception e) {
			throw new DaoException(String.format("添加对象出错！语句：%s",  "updateUserLogonHis") ,  e);		
		}
	}

	
	@Override
	public List<UserLoginHis> selectUserLogonHis(UserLoginHis userLoginHis){
		try {
			return sqlSessionTemplate.selectList( "selectUserLogonHis" ,  userLoginHis);
		} catch (Exception e) {
			throw new DaoException(String.format("添加对象出错！语句：%s",  "selectUserLogonHis") ,  e);		
		}
	}
	
}
