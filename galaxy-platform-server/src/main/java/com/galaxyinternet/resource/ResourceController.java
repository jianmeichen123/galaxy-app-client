package com.galaxyinternet.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.galaxyinternet.common.controller.BaseControllerImpl;
import com.galaxyinternet.framework.core.constants.Constants;
import com.galaxyinternet.framework.core.constants.UserConstant;
import com.galaxyinternet.framework.core.exception.BusinessException;
import com.galaxyinternet.framework.core.exception.DaoException;
import com.galaxyinternet.framework.core.file.OSSHelper;
import com.galaxyinternet.framework.core.file.UploadFileResult;
import com.galaxyinternet.framework.core.id.IdGenerator;
import com.galaxyinternet.framework.core.model.Page;
import com.galaxyinternet.framework.core.model.PageRequest;
import com.galaxyinternet.framework.core.model.ResponseData;
import com.galaxyinternet.framework.core.model.Result;
import com.galaxyinternet.framework.core.model.Result.Status;
import com.galaxyinternet.framework.core.oss.OSSFactory;
import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.framework.core.utils.DateUtil;
import com.galaxyinternet.framework.core.utils.GSONUtil;
import com.galaxyinternet.framework.core.utils.JSONUtils;
import com.galaxyinternet.model.resource.PlatformResource;
import com.galaxyinternet.model.user.User;
import com.galaxyinternet.service.ResourceService;


@Controller
@RequestMapping("/galaxy/resource")
public class ResourceController extends BaseControllerImpl<PlatformResource, PlatformResource> {
	
	private static final Logger logger = Logger.getLogger(ResourceController.class);
	
	@Autowired
	private ResourceService resourceService;
	
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
	 * @param idea
	 * @param request
	 * @return
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
	
	
	
	
	
		
		
}
