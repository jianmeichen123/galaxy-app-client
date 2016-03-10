package com.galaxyinternet.dict.controller;

import java.util.List;

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

import com.galaxyinternet.bo.DictBo;
import com.galaxyinternet.common.controller.BaseControllerImpl;
import com.galaxyinternet.exception.PlatformException;
import com.galaxyinternet.framework.core.model.Page;
import com.galaxyinternet.framework.core.model.ResponseData;
import com.galaxyinternet.framework.core.model.Result;
import com.galaxyinternet.framework.core.model.Result.Status;
import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.dict.Dict;
import com.galaxyinternet.service.DictService;

@Controller
@RequestMapping("/galaxy/dict")
public class DictController extends BaseControllerImpl<Dict, DictBo> {
	
	final Logger logger = LoggerFactory.getLogger(DictController.class);
	
	@Autowired
	private DictService dictService;
	
	@Autowired
	com.galaxyinternet.framework.cache.Cache cache;
	
	@Override
	protected BaseService<Dict> getBaseService() {
		return this.dictService;
	}
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "system/dict/index";
	}
	
	/**
	 * 
	    * @Title: insert
	    * @Description: 新增一个数据字典
	    * @param @param dict
	    * @param @return    参数
	    * @return ResponseData<Dict>    返回类型
	    * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<Dict> insert(@RequestBody Dict dict) {
		ResponseData<Dict> responseBody = new ResponseData<Dict>();
		Result result = new Result();
		try {
			dictService.insert(dict);
			result.setStatus(Status.OK);
			responseBody.setEntity(dict);
		} catch (PlatformException e){
			result.addError(e.getMessage(), e.getCode()+"");
		} catch (Exception e) {
			result.addError("系统错误");
			logger.error("新增错误",e);
		}
		responseBody.setResult(result);
		return responseBody;
	}

	
	
/*
	*//**
	 * 
	    * @Title: batchInsert
	    * @Description: 批量新增数据字典
	    * @param @param batchDictInsetParam
	    * @param @return    参数
	    * @return ResponseData<Dict>    返回类型
	    * @throws
	 *//*
	@ResponseBody
	@RequestMapping(value = "/batchInsert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<Dict> batchInsert( @RequestBody BatchDictInsetParam batchDictInsetParam) {
		ResponseData<Dict> responseBody = new ResponseData<Dict>();
		Result result = new Result();
		try {
			dictService.insertInBatch(batchDictInsetParam);
			result.setStatus(Status.OK);
		} catch (PlatformException e){
			result.addError(e.getMessage());
		} catch (Exception e) {
			result.addError("系统错误");
			logger.error("批量新增错误",e);
		}
		responseBody.setResult(result);
		return responseBody;
	}
	*/
/*	*//**
	 * 
	    * @Title: update
	    * @Description: 根据id更新数据字典
	    * @param @param dict
	    * @param @return    参数
	    * @return ResponseData<Dict>    返回类型
	    * @throws
	 *//*
	@ResponseBody
	@RequestMapping(value = "/updateById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<Dict> updateById(@RequestBody Dict dict) {
		ResponseData<Dict> responseBody = new ResponseData<Dict>();
		Result result = new Result();
		try {
			dictService.updateById(dict);
			result.setStatus(Status.OK);
		} catch (PlatformException e){
			result.addError(e.getMessage());
		} catch (Exception e) {
			result.addError("系统错误");
			logger.error("更新错误",e);
		}
		responseBody.setResult(result);
		return responseBody;
	}
	*/
	
	
	/**
	 * 
	    * @Title: update
	    * @Description: 根据code更新数据字典
	    * @param @param dict
	    * @param @return    参数
	    * @return ResponseData<Dict>    返回类型
	    * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<Dict> updateByCode(@RequestBody Dict dict) {
		ResponseData<Dict> responseBody = new ResponseData<Dict>();
		Result result = new Result();
		try {
			dictService.updateByCode(dict);
			result.setStatus(Status.OK);
		} catch (PlatformException e){
			result.addError(e.getMessage(), e.getCode()+"");
		} catch (Exception e) {
			result.addError("系统错误");
			logger.error("更新错误",e);
		}
		responseBody.setResult(result);
		return responseBody;
	}
	
	
	
	
	/**
	 * 
	    * @Title: findByCode
	    * @Description: 根据code查询数据字典
	    * @param @param code
	    * @param @return    参数
	    * @return ResponseData<Dict>    返回类型
	    * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/findByCode/{code}", method = RequestMethod.POST)
	public ResponseData<Dict> findByCode(@PathVariable String code) {
		ResponseData<Dict> responseBody = new ResponseData<Dict>();
		Result result = new Result();
		try {
			Dict entity =dictService.selectByCode(code);
			responseBody.setEntity(entity);
			result.setStatus(Status.OK);
		} catch (PlatformException e){
			result.addError(e.getMessage(), e.getCode()+"");
		} catch (Exception e) {
			result.addError("系统错误");
			logger.error("根据code查找数据字典错误",e);
		}
		responseBody.setResult(result);
		return responseBody;
	}

	
	/**
	 * 
	    * @Title: z
	    * @Description: 根据findByParentCode查询数据字典
	    * @param @param parentId
	    * @param @return    参数
	    * @return ResponseData<Dict>    返回类型
	    * @throws
	 */
	@ResponseBody 
	@RequestMapping(value = "/findByParentCode/{parentCode}", method = RequestMethod.POST)
	public ResponseData<Dict> findByParentId(@PathVariable String parentCode) {
		ResponseData<Dict> responseBody = new ResponseData<Dict>();
		Result result = new Result();
		try {
			List<Dict> dicts = dictService.selectByParentCode(parentCode);
			Page<Dict> page = new Page<>(dicts, null, null);
			responseBody.setPageList(page);
			result.setStatus(Status.OK);
		} catch (PlatformException e){
			result.addError(e.getMessage(), e.getCode()+"");
		}  catch (Exception e) {
			result.addError("系统错误");
			logger.error("根据parentId查找数据字典错误",e);
		}
		responseBody.setResult(result);
		return responseBody;
	}
}
