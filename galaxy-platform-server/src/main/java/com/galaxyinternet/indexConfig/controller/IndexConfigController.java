package com.galaxyinternet.indexConfig.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.galaxyinternet.bo.IndexConfigBo;
import com.galaxyinternet.common.controller.BaseControllerImpl;
import com.galaxyinternet.framework.core.model.ResponseData;
import com.galaxyinternet.framework.core.model.Result;
import com.galaxyinternet.framework.core.model.Result.Status;
import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.sopIndex.IndexConfig;
import com.galaxyinternet.service.IndexConfigService;

@Controller
@RequestMapping("/galaxy/indexConfig")
public class IndexConfigController extends BaseControllerImpl<IndexConfig, IndexConfigBo> {
	
	final Logger logger = LoggerFactory.getLogger(IndexConfigController.class);
	
	@Autowired
	private IndexConfigService indexConfigService;
	
	@Autowired
	com.galaxyinternet.framework.cache.Cache cache;
	
	@Override
	protected BaseService<IndexConfig> getBaseService() {
		return this.indexConfigService;
	}
	
	
	/**
	 * 页面 
	 */
	@RequestMapping(value = "/toIndexConfig",  produces = MediaType.APPLICATION_JSON_VALUE)
	public String toIndexConfig(HttpServletRequest request) {
		return "system/desksetting/indexConfig";
	}
	
	/**
	 * 页面 
	 */
	@RequestMapping(value = "/toAddCon",  produces = MediaType.APPLICATION_JSON_VALUE)
	public String toAddCon(HttpServletRequest request) {
		return "system/desksetting/addCon";
	}
	
	
	/**
	 * 管理员 获取 可配置项
	 * resources 中 indexDivConfig = 1 的列表
	 * 选择度 indexConfig 中 未配置的
	 * 
	 * @param roleOrUser 标识 是为 某角色 或 某用户 配置首页
	 * @param id 标识属性的id
	 */
	@ResponseBody
	@RequestMapping(value = "/queryAvailableConfig/{roleOrUser}/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<IndexConfigBo> queryAvailableConfig(HttpServletRequest request,
			@PathVariable("roleOrUser") String roleOrUser, @PathVariable("id") Long id) {
		ResponseData<IndexConfigBo> responseBody = new ResponseData<IndexConfigBo>();
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			if(roleOrUser != null && !roleOrUser.equals("null")){
				if(roleOrUser.equals("user")){
					params.put("userId", id);
				}else if(roleOrUser.equals("role")){
					params.put("roleId", id);
				}
			}
			params.put("indexDivConfig", 1);	
			List<IndexConfigBo> configList = indexConfigService.queryAvailableConfig(params);
			
			responseBody.setEntityList(configList);
			responseBody.setResult(new Result(Status.OK, ""));
		} catch (Exception e) {
			responseBody.setResult(new Result(Status.ERROR, null,"查询失败"));
			logger.error("获取 可配置项 失败",e);
		}
		return responseBody;
	}
	

	
	/**
	 * 管理员 拉取  已配置项（获取全部， 所以不过滤null）
	 * 
	 * @param roleOrUser 标识 是为 某角色 或 某用户 配置首页
	 * @param id 标识属性的id
	 */
	@ResponseBody
	@RequestMapping(value = "/queryIndexModelConfig/{roleOrUser}/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<IndexConfigBo> queryIndexModelConfig(HttpServletRequest request,
			@PathVariable("roleOrUser") String roleOrUser, @PathVariable("id") Long id) {
		ResponseData<IndexConfigBo> responseBody = new ResponseData<IndexConfigBo>();
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			if(roleOrUser != null && !roleOrUser.equals("null")){
				if(roleOrUser.equals("user")){
					params.put("userId", id);
				}else if(roleOrUser.equals("role")){
					params.put("roleId", id);
				}
			}
			
			List<IndexConfigBo> configList = indexConfigService.queryConfigResource(params);
			
			responseBody.setEntityList(configList);
			responseBody.setResult(new Result(Status.OK, ""));
		} catch (Exception e) {
			responseBody.setResult(new Result(Status.ERROR, null,"查询失败"));
			logger.error("拉取  已配置项",e);
		}
		return responseBody;
	}
	
	
	
	/**
	 * 管理员 配置 某角色 或 某用户 首页后保存
	 * 
	 * 删除  IndexConfig 中 某角色 或 某用户 的列表
	 * 获取前台配置数据
	 * 保存到 IndexConfig
	 * 
	 * @param roleOrUser 标识 是为 某角色 或 某用户 配置首页
	 * @param id 标识属性的id
	 */
	@ResponseBody
	@RequestMapping(value = "/saveIndexConfig", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<IndexConfig> saveIndexConfig(HttpServletRequest request,
			@PathVariable("roleOrUser") String roleOrUser, @PathVariable("id") Long id,@RequestBody List<IndexConfig> indexConfigList) {
		ResponseData<IndexConfig> responseBody = new ResponseData<IndexConfig>();
		try {
			responseBody.setResult(new Result(Status.OK, ""));
			
			if(indexConfigList == null || indexConfigList.isEmpty()){
				return responseBody;
			}
			
			IndexConfig indexConfig = new IndexConfig();
			if(roleOrUser != null && !roleOrUser.equals("null")){
				if(roleOrUser.equals("user")){
					indexConfig.setUserId(id);
				}else if(roleOrUser.equals("role")){
					indexConfig.setRoleId(id);
				}
			}
			
			indexConfigService.delete(indexConfig);
			indexConfigService.saveIndexConfig(indexConfig,indexConfigList);
		} catch (Exception e) {
			responseBody.setResult(new Result(Status.ERROR, null,"保存失败"));
			logger.error("首页配置保存 失败",e);
		}
		return responseBody;
	}
	
	
	
	
	


	
}