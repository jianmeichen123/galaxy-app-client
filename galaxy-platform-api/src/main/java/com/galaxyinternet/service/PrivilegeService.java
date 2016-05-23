package com.galaxyinternet.service;

import java.util.List;

import com.galaxyinternet.framework.core.mongodb.MongoRespData;
import com.galaxyinternet.model.privilege.bo.PrivilegeBo;
import com.galaxyinternet.platform.repositories.PrivilegeRepository;

/**
 * @description 权限服务类
 * @author keifer
 */
public interface PrivilegeService {

	public PrivilegeRepository getRepository();
	
	/**
	 * 根据记录主键获取权限
	 * 
	 * @param id
	 *            记录主键
	 * @return 用户具有的权限
	 */
	MongoRespData<PrivilegeBo> getPrivilegeById(String id);

	/**
	 * 根据用户主键获取权限
	 * 
	 * @param id
	 *            用户主键
	 * @return 用户具有的权限
	 */
	MongoRespData<PrivilegeBo> getPrivilegeByUserId(Long userId);

	/**
	 * 
	 * @Description:获取用户的权限
	 * @param userId
	 *            用户id
	 * @param visitedModuleId
	 *            访问模块id
	 * @return 返回权限的结果
	 *
	 */
	MongoRespData<PrivilegeBo> getPrivilege(Long userId, String visitedModuleId);

	/**
	 * 
	 * @Description:获取用户的权限
	 * @param userId
	 *            用户id
	 * @param visitedModuleId
	 *            访问模块id
	 * @param visitedResourceId
	 *            访问资源的id
	 * @return 返回权限的结果
	 *
	 */
	MongoRespData<PrivilegeBo> getPrivilege(Long userId, String visitedModuleId, String visitedResourceId);

	/**
	 * 
	 * @Title: getAllPrivileges
	 * @Description:根据角色id获取用户权限
	 * @param roleIds
	 *            用户具有的角色id集合
	 *
	 */
	MongoRespData<PrivilegeBo> getAllPrivileges(List<Long> roleIds);

	/**
	 *
	 * @Description:保存角色
	 * @param privilegeJson
	 *            用户具有的权限json对象
	 *
	 */
	MongoRespData<PrivilegeBo> insertPrivilege(String privilegeJson);

	/**
	 * 
	 * @Description:更新权限
	 * @param privilegeJson
	 *            用户新的权限json对象
	 */
	MongoRespData<PrivilegeBo> updatePrivilege(String privilegeJson);

	/**
	 * 
	 * @Description:根据mongo主键删除记录
	 * @param id
	 *            mongo主键
	 *
	 */
	MongoRespData<PrivilegeBo> deletePrivilegeById(String id);

	/**
	 * 
	 * @Description:根据用户id删除权限记录
	 * @param userId
	 *            用户id
	 *
	 */
	MongoRespData<PrivilegeBo> deletePrivilegeByUserId(Long userId);
}
