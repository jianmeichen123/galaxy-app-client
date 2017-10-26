package com.galaxyinternet.dao.user;

import java.util.List;

import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.model.user.UserLoginHis;

public interface UserLoginHisDao extends BaseDao<UserLoginHis, Long> {
	
	public int insertUserLogonHis(UserLoginHis userLoginHis);
	
	public int updateUserLogonHis(UserLoginHis userLoginHis);
	
	public List<UserLoginHis> selectUserLogonHis(UserLoginHis userLoginHis);
	
}
