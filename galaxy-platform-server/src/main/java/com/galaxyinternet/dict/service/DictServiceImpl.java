package com.galaxyinternet.dict.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxyinternet.dao.dict.DictDao;
import com.galaxyinternet.exception.PlatformException;
import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.framework.core.service.impl.BaseServiceImpl;
import com.galaxyinternet.model.dict.BatchDictInsetParam;
import com.galaxyinternet.model.dict.Dict;
import com.galaxyinternet.service.DictService;
import com.galaxyinternet.utils.MessageStatus;
import com.galaxyinternet.utils.ValidationUtil;


@Service("com.galaxyinternet.service.DictService")
public class DictServiceImpl extends BaseServiceImpl<Dict>implements DictService {
	//private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private DictDao dictDao;

	@Override
	protected BaseDao<Dict, Long> getBaseDao() {
		return this.dictDao;
	}
	
	@Override
	public int updateById(Dict entity) {
		
		//验证
		ValidationUtil.isNull(Dict.COMMENT,entity);
		ValidationUtil.isMoreThan(Dict.NAME, entity.getName(), 32);
		ValidationUtil.isNull(Dict.ID,entity.getId());
		//
		Dict dict = dictDao.selectById(entity.getId());
		if(dict == null){
			throwPlatformException(MessageStatus.DATA_NOT_EXISTS, "该数据字典不存在");
		}
		entity.setParentCode(entity.getParentCode());
		int count = dictDao.selectCountByParentCodeAndName(entity);
		if(count >0 ){
			throwPlatformException(MessageStatus.SAME_DATA_EXISTS, "该名称已存在");
		}
		entity.setUpdatedTime(System.currentTimeMillis());
		return dictDao.updateById(entity);
	}
	
	@Override
	public int updateByCode(Dict entity) {
		
		//验证
		ValidationUtil.isNull(Dict.COMMENT,entity);
		ValidationUtil.isMoreThan(Dict.NAME, entity.getName(), 32);
		ValidationUtil.isMoreThan(Dict.CODE, entity.getCode(), 32);
		//
		Dict dict = dictDao.selectByCode(entity.getCode());
		if(dict == null){
			throwPlatformException(MessageStatus.DATA_NOT_EXISTS, "该数据字典不存在");
		}
		entity.setId(dict.getId());
		entity.setParentCode(entity.getParentCode());
		
		int count = dictDao.selectCountByParentCodeAndName(entity);
		if(count >0 ){
			throwPlatformException(MessageStatus.SAME_DATA_EXISTS, "该名称已存在");
		}
		entity.setUpdatedTime(System.currentTimeMillis());
		return dictDao.updateById(entity);
	}
	
	
	private void validInsert(Dict dict){
		//验证
		ValidationUtil.isNull(Dict.COMMENT,dict);
		ValidationUtil.isEmptyOrMoreThan(Dict.NAME, dict.getName(), 32);
		//

	}
	@Override
	public Long insert(Dict entity) {
		
		validInsert(entity);
		ValidationUtil.isNull(Dict.PARENT_CODE,entity.getParentCode());
		
		Dict parentDict = dictDao.selectByCode(entity.getParentCode());
		if( parentDict == null){
			ValidationUtil.throwPlatformException(MessageStatus.DATA_NOT_EXISTS,"待添加的数据字典父类型不存在");
		}
		int count = dictDao.selectCountByParentCodeAndName(entity);
		if(count > 0){
			ValidationUtil.throwPlatformException(MessageStatus.SAME_DATA_EXISTS,"数据已存在");
		}
		Integer max = dictDao.selectMaxValueByParentCode(entity.getParentCode());
		if(max == null){
			max = 1;
		}else {
			max++;
		}
		entity.setValue(max);
		entity.setSort(max);
		entity.setCode(parentDict.getCode()+":"+max);
		entity.setCreatedTime(System.currentTimeMillis());
		return dictDao.insert(entity);
		
	}
	
	
	
	
	@Override
	public List<Dict> selectByParentCode(String parentCode) {
		return dictDao.selectByParentCode(parentCode);
	}


	@Override
	public int insertInBatch(BatchDictInsetParam batchDictInsetParam) {
		
		ValidationUtil.isNull(Dict.COMMENT,batchDictInsetParam);
		ValidationUtil.isNull(Dict.PARENT_CODE,batchDictInsetParam.getParentCode());
		
		List<String> names = null;
		List<Dict> dicts = batchDictInsetParam.getDicts();
		
		if(dicts == null ||dicts.isEmpty()){
			throwPlatformException(MessageStatus.FIELD_NOT_ALLOWED_EMPTY, Dict.COMMENT);
		}
		names = new ArrayList<>();
		
		Dict parentDict = dictDao.selectByCode(batchDictInsetParam.getParentCode());
		if(parentDict == null){
			ValidationUtil.throwPlatformException(MessageStatus.DATA_NOT_EXISTS,"待添加的数据字典父类型不存在");
		}
		
		Integer max = dictDao.selectMaxValueByParentCode(batchDictInsetParam.getParentCode());
		if(max == null){
			max = 1;
		}else {
			max++;
		}
		for (Dict dict : dicts) {
			dict.setSort(max);
			dict.setValue(max);
			dict.setCode(parentDict.getCode()+":"+max);
			max ++;
			validInsert(dict);
			if(dict.getName()==null || names.contains(dict.getName())){
				throwPlatformException(MessageStatus.PARAME_SAME, Dict.NAME);
			}else {
				names.add(dict.getName());
			}
		}
		int count = dictDao.selectCountSameIn(batchDictInsetParam);
		if(count > 0){
			ValidationUtil.throwPlatformException(MessageStatus.SAME_DATA_EXISTS,"待添加数据已存在");
		}
		batchDictInsetParam.setCreatedTime(System.currentTimeMillis());
		return dictDao.insertInBatch(batchDictInsetParam);
	}


	@Override
	public Dict selectByCode(String code) {
		ValidationUtil.isEmptyOrMoreThan(Dict.CODE, code, 32);
		return dictDao.selectByCode(code);
	}


	@Override
	public int updateSort(Dict entity) {

		ValidationUtil.isNull(Dict.COMMENT,entity);
		ValidationUtil.isNull(Dict.ID,entity.getId());
		
		entity.setName(null);
		entity.setUpdatedTime(System.currentTimeMillis());
		return dictDao.updateById(entity);
	}

	public  void throwPlatformException(MessageStatus status ,Object...args ){
		String message = null;
		if(args.length == 0){
			message = status.getMessage();
		}else {
			message = String.format(status.getMessage(), args);
		}
		throw new PlatformException(status.getStatus(), message);
	}

}
