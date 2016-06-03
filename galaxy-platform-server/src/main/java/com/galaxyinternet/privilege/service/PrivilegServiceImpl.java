package com.galaxyinternet.privilege.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxyinternet.framework.core.id.IdGenerator;
import com.galaxyinternet.framework.core.mongodb.MongoRespData;
import com.galaxyinternet.framework.core.utils.GSONUtil;
import com.galaxyinternet.model.privilege.bo.PrivilegeBo;
import com.galaxyinternet.platform.repositories.PrivilegeRepository;
import com.galaxyinternet.service.PrivilegeService;

//@Service
public class PrivilegServiceImpl implements PrivilegeService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final PrivilegeRepository repository;

	@Autowired
	public PrivilegServiceImpl(PrivilegeRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public PrivilegeRepository getRepository(){
		return this.repository;
	}
	
	@Override
	public MongoRespData<PrivilegeBo> getPrivilegeById(String id) {
		MongoRespData<PrivilegeBo> result = new MongoRespData<PrivilegeBo>();
		result.set_id(id);
		result.setEntity(repository.findOne(id));
		return result;
	}

	@Override
	public MongoRespData<PrivilegeBo> getPrivilegeByUserId(Long userId) {
		MongoRespData<PrivilegeBo> result = new MongoRespData<PrivilegeBo>();
		PrivilegeBo resultBo = repository.findByUserId(userId);
		if (null != resultBo) {
			result.setEntity(resultBo);
			result.set_id(resultBo.get_id());
		}
		return result;
	}

	@Override
	public MongoRespData<PrivilegeBo> getPrivilege(Long userId, String visitedModuleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MongoRespData<PrivilegeBo> getPrivilege(Long userId, String visitedModuleId, String visitedResourceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MongoRespData<PrivilegeBo> getAllPrivileges(List<Long> roleIds) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public MongoRespData<PrivilegeBo> insertPrivilege(String privilegeJson) {
		PrivilegeBo resultBo = GSONUtil.fromJson(privilegeJson, PrivilegeBo.class);
		MongoRespData<PrivilegeBo> result = new MongoRespData<PrivilegeBo>();
		if (null != resultBo) {
			result.set_id(String.valueOf(IdGenerator.generateId(this.getClass())));
			resultBo = repository.save(resultBo);
			result.setEntity(resultBo);
		} else {
			logger.error("Request params were invalid. Request json ==>> " + privilegeJson);
			throw new IllegalArgumentException("Request params were invalid. Request json ==>> " + privilegeJson);
		}
		return result;
	}

	@Override
	public MongoRespData<PrivilegeBo> updatePrivilege(String privilegeJson) {
		PrivilegeBo resultBo = GSONUtil.fromJson(privilegeJson, PrivilegeBo.class);
		MongoRespData<PrivilegeBo> result = new MongoRespData<PrivilegeBo>();
		if (null != resultBo) {
			if (StringUtils.isBlank(resultBo.get_id())) {
				Long userId = resultBo.getUserId();
				if (null == userId || userId < 0L) {
					logger.error(
							"Request params not completed, _id or userId field must exist one of both. Request json ==>> "
									+ privilegeJson);
					throw new IllegalArgumentException(
							"Request params not completed, _id or userId field must exist one of both. Request json ==>> "
									+ privilegeJson);
				} else {
					PrivilegeBo resultBoTemp = repository.findByUserId(userId);
					resultBo.set_id(resultBoTemp.get_id());
					resultBo = repository.save(resultBo);
				}
			} else {
				resultBo = repository.save(resultBo);
			}
			result.setEntity(resultBo);
		} else {
			logger.error("Request params were invalid. Request json ==>> " + privilegeJson);
			throw new IllegalArgumentException("Request params were invalid. Request json ==>> " + privilegeJson);
		}
		return result;
	}

	@Override
	public MongoRespData<PrivilegeBo> deletePrivilegeById(String id) {
		MongoRespData<PrivilegeBo> result = new MongoRespData<PrivilegeBo>();
		repository.delete(id);
		return result;
	}

	@Override
	public MongoRespData<PrivilegeBo> deletePrivilegeByUserId(Long userId) {
		MongoRespData<PrivilegeBo> result = new MongoRespData<PrivilegeBo>();
		PrivilegeBo resultBo = repository.findByUserId(userId);
		repository.delete(resultBo);
		return result;
	}
}
