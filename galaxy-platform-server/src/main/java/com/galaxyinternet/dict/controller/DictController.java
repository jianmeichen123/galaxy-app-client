package com.galaxyinternet.dict.controller;

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
import com.galaxyinternet.framework.core.model.ResponseData;
import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.dict.BatchDictInsetParam;
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
	
	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<Dict> insert(@RequestBody Dict dict) {
		ResponseData<Dict> responseBody = new ResponseData<Dict>();
		dictService.insert(dict);
		responseBody.setEntity(dict);
		return responseBody;
	}

	
	

	@ResponseBody
	@RequestMapping(value = "/batchInsert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<Dict> batchInsert( @RequestBody BatchDictInsetParam batchDictInsetParam) {
		ResponseData<Dict> responseBody = new ResponseData<Dict>();
		int count =dictService.insertInBatch(batchDictInsetParam);
		return responseBody;
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<Dict> update(@RequestBody Dict dict) {
		ResponseData<Dict> responseBody = new ResponseData<Dict>();
		int count = dictService.updateById(dict);
		return responseBody;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/findByCode/{code}", method = RequestMethod.GET)
	public ResponseData<Dict> findByCode(@PathVariable String code) {
		ResponseData<Dict> responseBody = new ResponseData<Dict>();
		Dict entity =dictService.selectByCode(code);
		responseBody.setEntity(entity);
		return responseBody;
	}

}
