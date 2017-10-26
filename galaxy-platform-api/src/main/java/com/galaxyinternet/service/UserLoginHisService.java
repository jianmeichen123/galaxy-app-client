package com.galaxyinternet.service;

import java.util.List;

import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.user.UserLoginHis;

/**
 * 
 * @author jianmeichen
 *
 */
public interface UserLoginHisService extends BaseService<UserLoginHis> {
	
	public int insertUserLogonHis(UserLoginHis userLoginHis);
	public int updateUserLogonHis(UserLoginHis userLoginHis);
	public List<UserLoginHis> selectUserLogonHis(UserLoginHis userLoginHis);
	
}

