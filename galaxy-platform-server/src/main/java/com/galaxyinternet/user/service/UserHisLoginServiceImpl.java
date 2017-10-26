package com.galaxyinternet.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxyinternet.dao.user.UserLoginHisDao;
import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.framework.core.service.impl.BaseServiceImpl;
import com.galaxyinternet.model.user.UserLoginHis;
import com.galaxyinternet.service.UserLoginHisService;

@Service("com.galaxyinternet.service.UserLoginHisService")
public class UserHisLoginServiceImpl extends BaseServiceImpl<UserLoginHis> implements UserLoginHisService {

	@Autowired
	private UserLoginHisDao userLoginHisDao;

	@Override
	protected BaseDao<UserLoginHis, Long> getBaseDao() {
		return this.userLoginHisDao;
	} 
	

	@Override
	public int insertUserLogonHis(UserLoginHis userLoginHis) {
		// TODO Auto-generated method stub
		try{
			return userLoginHisDao.insertUserLogonHis(userLoginHis);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		 
	}
	@Override
	public int updateUserLogonHis(UserLoginHis userLoginHis) {
		// TODO Auto-generated method stub
		 return userLoginHisDao.updateUserLogonHis(userLoginHis);
	}


	@Override
	public List<UserLoginHis> selectUserLogonHis(UserLoginHis userLoginHis) {
		// TODO Auto-generated method stub
		return userLoginHisDao.selectUserLogonHis(userLoginHis);
	}
	
}
