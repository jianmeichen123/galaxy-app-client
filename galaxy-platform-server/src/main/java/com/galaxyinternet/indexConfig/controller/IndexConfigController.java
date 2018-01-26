package com.galaxyinternet.indexConfig.controller;

import com.galaxyinternet.bo.IndexConfigBo;
import com.galaxyinternet.common.controller.BaseControllerImpl;
import com.galaxyinternet.framework.core.model.ResponseData;
import com.galaxyinternet.framework.core.model.Result;
import com.galaxyinternet.framework.core.model.Result.Status;
import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.resource.PlatformResource;
import com.galaxyinternet.model.sopIndex.IndexConfig;
import com.galaxyinternet.service.IndexConfigService;
import com.galaxyinternet.utils.AuthRequest;
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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/galaxy/indexConfig")
public class IndexConfigController extends BaseControllerImpl<IndexConfig, IndexConfigBo> {
	
	final Logger logger = LoggerFactory.getLogger(IndexConfigController.class);
	
	@Autowired
	private IndexConfigService indexConfigService;
	
	@Autowired
	com.galaxyinternet.framework.cache.Cache cache;
	
	@Autowired
	AuthRequest authReq;
	
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
			List<IndexConfigBo> configList = new ArrayList<>();
			//已选择模块
			List<IndexConfig> list = indexConfigService.queryAll();
			Map<String,String> resCodes = new HashMap<>();
			for(IndexConfig item : list)
			{
				if(item.getResourceCode() != null)
				{
					resCodes.put(item.getResourceCode(), item.getResourceCode());
				}
			}
			PlatformResource resQuery = new PlatformResource();
			resQuery.setResourceType("5");//桌面组件
			List<PlatformResource> resList = authReq.getResources(resQuery);
			if(resList != null && resList.size() > 0)
			{
				for(PlatformResource item : resList)
				{
					if(resCodes.containsKey(item.getResourceMark()))
					{
						continue;
					}
					IndexConfigBo bo = new IndexConfigBo();
					bo.setResourceId(item.getId());
					bo.setResourceMark(item.getResourceMark());
					bo.setResourceName(item.getResourceName());
					bo.setContentUrl(item.getResourceUrl());
					configList.add(bo);
				}
			}
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
	@RequestMapping(value = "/queryIndexModelConfig", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<IndexConfigBo> queryIndexModelConfig(HttpServletRequest request) {
		ResponseData<IndexConfigBo> responseBody = new ResponseData<IndexConfigBo>();
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			List<IndexConfigBo> configList = indexConfigService.queryConfigResource(params);
			if(configList != null && configList.size() > 0)
			{
				
				PlatformResource resQuery = new PlatformResource();
				resQuery.setResourceType("5");//桌面组件
				List<PlatformResource> resList = authReq.getResources(resQuery);
				Map<String,String> codeNameMap = new HashMap<>();
				if(resList != null && resList.size() > 0)
				{
					for(PlatformResource item : resList)
					{
						codeNameMap.put(item.getResourceMark(), item.getResourceName());
					}
				}
				
				for(IndexConfigBo bo : configList)
				{
					bo.setResourceName(codeNameMap.get(bo.getResourceCode()));
				}
			}
			
			responseBody.setEntityList(configList);
			responseBody.setResult(new Result(Status.OK, ""));
		} catch (Exception e) {
			responseBody.setResult(new Result(Status.ERROR, null,"查询失败"));
			logger.error("拉取  已配置项",e);
		}
		return responseBody;
	}
	
	/**
	 * 保存模型
	 * @param roleOrUser 标识 是为 某角色 或 某用户 配置首页
	 * @param id 标识属性的id
	 */
	@ResponseBody
	@RequestMapping(value = "/saveModel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<IndexConfig> saveModel(HttpServletRequest request,
			@RequestBody IndexConfig  indexConfig ) {
		ResponseData<IndexConfig> responseBody = new ResponseData<IndexConfig>();
		try {
			//根据资源id查询资源的详细信
			List<Long> list=new ArrayList<Long>();
			IndexConfig param=new IndexConfig();
			param.setDirection("config_order");
			List<IndexConfig> queryList = indexConfigService.selectIndexConfigDesc();
			byte order1=0;
			byte order2=0;
			if(null!=queryList&&queryList.size()>0){
				IndexConfig indexConfig2 = queryList.get(0);
				order1=(byte)(indexConfig2.getConfigOrder()+1);
				order2=(byte)(indexConfig2.getConfigOrder()+2);
			}else{
				order1=(byte)1;
				order2=(byte)2;
			}
			indexConfig.setConfigOrder(order1);
			Long insert1 = indexConfigService.insert(indexConfig);
			IndexConfig de=new IndexConfig();
			de.setConfigOrder(order2);
			de.setShapeType(indexConfig.getShapeType());
			Long insert2 = indexConfigService.insert(de);
			list.add(insert1);
			list.add(insert2);
			 Map<String,Object> map=new HashMap<String,Object>();
			 map.put("arr", list);
			 responseBody.setUserData(map);
			 responseBody.setResult(new Result(Status.OK, "保存成功"));
		} catch (Exception e) {
			responseBody.setResult(new Result(Status.ERROR, null,"保存失败"));
			logger.error("首页配置保存 失败",e);
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
			@RequestBody IndexConfig  indexConfig ) {
		ResponseData<IndexConfig> responseBody = new ResponseData<IndexConfig>();
		try {
			indexConfigService.insert(indexConfig);
			//根据资源id查询资源的详细信息
			 /*int insert = indexConfigService.updateById(indexConfig);
			 if(insert>0){
				 responseBody.setResult(new Result(Status.OK, "保存成功"));
			 }else{
				 responseBody.setResult(new Result(Status.ERROR, "保存失败")); 
			 }*/
			 
		} catch (Exception e) {
			responseBody.setResult(new Result(Status.ERROR, null,"保存失败"));
			logger.error("首页配置保存 失败",e);
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
	@RequestMapping(value = "/deleteIndexModel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<IndexConfig> deleteIndexModel(HttpServletRequest request,
			@RequestBody IndexConfig  indexConfig ) {
		ResponseData<IndexConfig> responseBody = new ResponseData<IndexConfig>();
		try {
			//根据资源id查询资源的详细信息
			//indexConfig.setResourceId(null);
			indexConfigService.deleteById(indexConfig.getId());
			//int updateByResourceId = indexConfigService.updateByResourceId(indexConfig);
		//	 int delete = indexConfigService.delete(indexConfig);
			/* if(updateByResourceId>0){
				 responseBody.setResult(new Result(Status.OK, "删除配置项成功"));
			 }else{
				 responseBody.setResult(new Result(Status.ERROR, "删除配置项失败"));
			 }*/
			 
		} catch (Exception e) {
			responseBody.setResult(new Result(Status.ERROR, null,"保存失败"));
			logger.error("首页配置保存 失败",e);
		}
		return responseBody;
	}
	
	
	
	
	


	
}