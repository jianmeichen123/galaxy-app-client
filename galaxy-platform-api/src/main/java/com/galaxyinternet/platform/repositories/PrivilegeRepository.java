package com.galaxyinternet.platform.repositories;

import com.galaxyinternet.framework.core.mongodb.BaseRepository;
import com.galaxyinternet.model.privilege.bo.PrivilegeBo;

public interface PrivilegeRepository extends BaseRepository<PrivilegeBo, String> {
	
	PrivilegeBo findByUserId(Long id);
}
