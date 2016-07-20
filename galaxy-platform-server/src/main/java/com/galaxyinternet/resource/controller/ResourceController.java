package com.galaxyinternet.resource.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.galaxyinternet.common.controller.BaseControllerImpl;
import com.galaxyinternet.framework.core.constants.Constants;
import com.galaxyinternet.framework.core.model.Page;
import com.galaxyinternet.framework.core.model.PageRequest;
import com.galaxyinternet.framework.core.model.ResponseData;
import com.galaxyinternet.framework.core.model.Result;
import com.galaxyinternet.framework.core.model.Result.Status;
import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.resource.PlatformResource;
import com.galaxyinternet.model.resource.RoleResource;
import com.galaxyinternet.model.user.User;
import com.galaxyinternet.service.ResourceService;
import com.galaxyinternet.service.RoleResourceService;
import com.galaxyinternet.service.UserRoleService;

/**
 * Ȩ����Դ����
 * @author chenjianmei
 *
 */
@Controller
@RequestMapping("/galaxy/resource")
public class ResourceController extends BaseControllerImpl<PlatformResource, PlatformResource> {
	final Logger logger = LoggerFactory.getLogger(ResourceController.class);
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private RoleResourceService roleResourceService;
	
	
	@Override
	protected BaseService<PlatformResource> getBaseService() {
		return this.resourceService;
	}

	/**
	 * 资源录入保存
	 * 
	 * 	produces="application/text;charset=utf-8"
	 */
	@ResponseBody
	@RequestMapping(value = "/addresource", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<PlatformResource> addResource(PlatformResource resource,HttpServletRequest request,HttpServletResponse response ) {
		
		ResponseData<PlatformResource> responseBody = new ResponseData<PlatformResource>();
		User user = (User) request.getSession().getAttribute(Constants.SESSION_USER_KEY);
		
		try{
			/*			
			private Long parentId;        父资源ID										前端传入录入
		    private String resourceMark;  资源标识										前端录入
		    private String resourceName;  资源名称										前端录入
		    private String resourceUrl;   资源URL										前端录入
		    private String resourceType;  资源类型1-菜单;2-页面;3-操作;4-其他(div等)		前端录入
		    private Long resourceOrder;	     资源顺序										前端录入
		    private String resourceStatus;资源状态0-禁用;1-启用							前端录入
		    private String productMark;   产品/项目标识									前端传入录入
		    private Long createdUid;      
		    private Long updatedUid;      
		    private String resourceDesc;  资源描述、备注									前端录入
*/			
			resource.setCreatedUid(user.getId());
			Long id = resourceService.insert(resource);
			
			responseBody.setResult(new Result(Status.OK, ""));
			responseBody.setId(id);
		} catch (Exception e) {
			responseBody.setResult(new Result(Status.ERROR,null, "资源录入保存失败"));
			logger.error("addResource 资源录入保存失败",e);
		}
		return responseBody;
	}
	
	
	/**
	 *  资源编辑保存
	 */
	@ResponseBody
	@RequestMapping(value = "/updateresource", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<PlatformResource> updateResource(@RequestBody PlatformResource resource,HttpServletRequest request)
	{
		ResponseData<PlatformResource> responseBody = new ResponseData<PlatformResource>();
		User user = (User) request.getSession().getAttribute(Constants.SESSION_USER_KEY);
		
		try {
			if(resource.getId()==null){
				responseBody.setResult(new Result(Status.ERROR, null, "缺失必要的参数!"));
				return responseBody;
			}
			/*			
			private Long parentId;        父资源ID										前端传入录入
		    private String resourceMark;  资源标识										前端录入
		    private String resourceName;  资源名称										前端录入
		    private String resourceUrl;   资源URL										前端录入
		    private String resourceType;  资源类型1-菜单;2-页面;3-操作;4-其他(div等)		前端录入
		    private Long resourceOrder;	     资源顺序										前端录入
		    private String resourceStatus;资源状态0-禁用;1-启用							前端录入
		    private String productMark;   产品/项目标识									前端传入录入
		    private Long createdUid;      
		    private Long updatedUid;      
		    private String resourceDesc;  资源描述、备注									前端录入
*/			
			
			resource.setUpdatedUid(user.getId());
			int updeteById = resourceService.updateById(resource);
			if(updeteById<=0){
				responseBody.setResult(new Result(Status.ERROR, null, "资源编辑保存失败!"));
				return responseBody;
			}
			responseBody.setResult(new Result(Status.OK,null));
			
		} catch (Exception e) {
			responseBody.setResult(new Result(Status.ERROR, null, "资源编辑保存失败!"));
			logger.error("资源编辑保存失败  updateResource ",e);
		}
		return responseBody;
	}
	
	
	
	/**
	 * 资源列表    分页返回
	 */
	@ResponseBody
	@RequestMapping(value = "/queryresource", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<PlatformResource> queryResource(HttpServletRequest request,@RequestBody PlatformResource query ) {
		
		ResponseData<PlatformResource> responseBody = new ResponseData<PlatformResource>();
		User user = (User) request.getSession().getAttribute(Constants.SESSION_USER_KEY);
		try {
			PageRequest pageable = new PageRequest();
			Integer pageNum = query.getPageNum() != null ? query.getPageNum() : 0;
			Integer pageSize = query.getPageSize() != null ? query.getPageSize() : 10;
			pageable = new PageRequest(pageNum, pageSize);
				
			Page<PlatformResource> pageList = resourceService.queryPageList(query, pageable);
			
			responseBody.setPageList(pageList);
			responseBody.setResult(new Result(Status.OK, ""));
			return responseBody;
		} catch (Exception e) {
			responseBody.setResult(new Result(Status.ERROR, null,"查询失败"));
			
			logger.error("queryResource 查询失败",e);
		}
		
		return responseBody;
	}
	
	
	
	/**
	 * 资源详情
	 */
	@ResponseBody
	@RequestMapping(value = "/resourceinfo/{resourceId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<PlatformResource> resourceInfo(HttpServletRequest request,@PathVariable("resourceId") Long resourceId ) {
		ResponseData<PlatformResource> responseBody = new ResponseData<PlatformResource>();
		User user = (User) request.getSession().getAttribute(Constants.SESSION_USER_KEY);
		try {
			PlatformResource resourceinfo = resourceService.queryById(resourceId);
			responseBody.setEntity(resourceinfo);
			responseBody.setResult(new Result(Status.OK, ""));
			return responseBody;
		} catch (Exception e) {
			responseBody.setResult(new Result(Status.ERROR, null,"资源详情查询失败"));
			logger.error("resourceInfo 资源详情查询失败",e);
		}
		
		return responseBody;
	}
	
	
	
	/**
	 * 资源 list返回所有 -- 树形加载
	 * @param resourceid   加载出该id的节点和其下的所有节点
	 * 					   为null  list返回全部
	 */
	@ResponseBody
	@RequestMapping(value = "/queryresourcetree", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<PlatformResource> queryResourceTree(HttpServletRequest request,@RequestBody Long resourceId ) {
		
		ResponseData<PlatformResource> responseBody = new ResponseData<PlatformResource>();
		User user = (User) request.getSession().getAttribute(Constants.SESSION_USER_KEY);
		try {
			List<PlatformResource> resourceList = null;
			
			if(resourceId == null){
				resourceList = resourceService.queryAll();
			}else{
			//	resourceList = resourceService.queryResourceTree(resourceId);
			}
			responseBody.setEntityList(resourceList);
			responseBody.setResult(new Result(Status.OK, ""));
			return responseBody;
		} catch (Exception e) {
			responseBody.setResult(new Result(Status.ERROR, null,"查询失败"));
			
			logger.error("queryResourceTree 查询失败",e);
		}
		
		return responseBody;
	}
	
	/**
	 * 树形菜单显示加载
	 */
	@ResponseBody
	@RequestMapping(value = "/resourceTree", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<PlatformResource> resourceTree(HttpServletRequest request) {
		
		ResponseData<PlatformResource> responseBody = new ResponseData<PlatformResource>();
		User user = (User) request.getSession().getAttribute(Constants.SESSION_USER_KEY);
		/**获取角色ID集合**/
		List<Long> roleIdList = userRoleService.selectRoleIdByUserId(user
				.getId());
		/**查询角色对应的资源ID集合**/
		RoleResource queryRole = new RoleResource();
		             queryRole.setRoleList(roleIdList);
		List<RoleResource> roleResourceBoList = roleResourceService.queryList(queryRole);
		/**获取资源ID的集合**/
		List<Long> resourceIdList = new ArrayList<Long>();
		if(!StringUtils.isEmpty(roleResourceBoList)){
			for(RoleResource resource:roleResourceBoList){
				resourceIdList.add(resource.getResourceId());
			}
		}
		/**获取全部的资源**/
		List<PlatformResource> resourceList = resourceService.queryAll();
		
		PlatformResource platformResource = new PlatformResource();
		platformResource.setResourceIdList(resourceIdList);
		/**组装数据**/
		responseBody.setEntityList(resourceList);
		responseBody.setEntity(platformResource);
		return responseBody;
	}
	
	
	/**
	 * 角色添加权限
	 */
	@ResponseBody
	@RequestMapping(value = "/addRoleResource", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<PlatformResource> addRoleResource(@RequestBody RoleResource roleResource,HttpServletRequest request,HttpServletResponse response ) {
		ResponseData<PlatformResource> responseBody = new ResponseData<PlatformResource>();
		User user = (User) request.getSession().getAttribute(Constants.SESSION_USER_KEY);
		if(StringUtils.isEmpty(roleResource) || StringUtils.isEmpty(roleResource.getRoleId())){
			responseBody.setResult(new Result(Status.ERROR,null, "参数丢失!"));
			return responseBody;
		}
		try{
			if(!StringUtils.isEmpty(roleResource.getResourceIds())){
				roleResource.setCreatedUid(user.getId());
				roleResourceService.insertBatch(roleResource);
				responseBody.setResult(new Result(Status.OK,null, "保存成功!"));
			}
		} catch (Exception e) {
			responseBody.setResult(new Result(Status.ERROR,null, "资源录入保存失败"));
			logger.error("addResource 资源录入保存失败",e);
		}
		return responseBody;
	}
	
	
	
	
	
	
	
	
	/**
	 * 测试
	 */
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public String test(HttpServletRequest request){
		return "system/user/test";
	}
	
	
	
	
}
