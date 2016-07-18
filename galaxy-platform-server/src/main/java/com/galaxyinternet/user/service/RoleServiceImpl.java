package com.galaxyinternet.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxyinternet.dao.role.RoleDao;
import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.framework.core.service.impl.BaseServiceImpl;
import com.galaxyinternet.model.role.Role;
import com.galaxyinternet.model.user.User;
import com.galaxyinternet.service.RoleService;
import com.galaxyinternet.service.UserService;

@Service("com.galaxyinternet.service.RoleService")
public class RoleServiceImpl extends BaseServiceImpl<Role>implements RoleService {
	//private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserService userService;
	

	@Override
	protected BaseDao<Role, Long> getBaseDao() {
		return this.roleDao;
	}

	@Override
	public List<Role> queryRoleList() {
		List<Role> selectAll = roleDao.selectAll();
		// TODO Auto-generated method stub
		for (Role r : selectAll) {
			List<User> userByRoleId = userService.
					getUserByRoleId(r.getId());
			if(null!=userByRoleId){
				r.setUserListByRid(userByRoleId);
			}
		}
		
		return selectAll;
	}

}
