package com.galaxyinternet.dict.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galaxyinternet.dao.dict.DictDao;
import com.galaxyinternet.exception.MessageStatus;
import com.galaxyinternet.exception.PlatformException;
import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.framework.core.service.impl.BaseServiceImpl;
import com.galaxyinternet.model.dict.BatchDictInsetParam;
import com.galaxyinternet.model.dict.Dict;
import com.galaxyinternet.service.DictService;


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
		Dict dict = dictDao.selectByParentIdAndName(entity);
		if(dict != null){
			throwPlatformException(MessageStatus.DATA_NOT_EXISTS, "该数据字典不存在");
		}
		entity.setUpdatedTime(System.currentTimeMillis());
		return dictDao.updateById(entity);
	}
	
	@Override
	public Long insert(Dict entity) {
		Dict dict = dictDao.selectByCode(entity.getCode());
		if(dict != null){
			throwPlatformException(MessageStatus.SAME_DATA_EXISTS, "该数据字典code已存在");
		}
		int count  = dictDao.selectCountSame(entity);
		if(count > 0){
			throwPlatformException(MessageStatus.SAME_DATA_EXISTS, "该父类下存在相同数据");
		}
		if(entity.getParentId() != null && entity.getParentId() != 0){
			Dict dictParent = dictDao.selectById(entity.getParentId());
			if(dictParent == null){
				throwPlatformException(MessageStatus.SAME_DATA_EXISTS, "该父类不存在");
			}
		}
		return super.insert(entity);
		
	}
	
	
	
	
	@Override
	public List<Dict> selectByParentId(Long parentId) {
		return dictDao.selectByParentId(parentId);
	}


	@Override
	public int insertInBatch(BatchDictInsetParam batchDictInsetParam) {
		if(batchDictInsetParam.getParentId() == null){
			return 0;
		}
		List<String> names = null;
		List<String> codes = null;
		List<Integer> values = null;
		List<Dict> dicts = batchDictInsetParam.getDicts();
		
		if(dicts == null ||dicts.isEmpty()){
			return 0;
		}
		names = new ArrayList<>();
		codes = new ArrayList<>();
		values = new ArrayList<>();
		for (Dict dict : dicts) {
			if(dict.getName()==null || names.contains(dict.getName())){
				return 0;
			}else {
				names.add(dict.getName());
			}
			
			if(dict.getCode()==null ||codes.contains(dict.getCode())){
				return 0;
			}else {
				codes.add(dict.getCode());
			}
			
			if(dict.getValue()==null ||values.contains(dict.getValue())){
				return 0;
			}else {
				values.add(dict.getValue());
			}
		}
		int count = dictDao.selectCountInCodes(codes);
		if(count > 0){
			return 0;
		}
		count = dictDao.selectCountSameIn(batchDictInsetParam);
		if(count > 0){
			return 0;
		}
		batchDictInsetParam.setCreatedTime(System.currentTimeMillis());
		return 0;
	}


	@Override
	public Dict selectByCode(String code) {
		return dictDao.selectByCode(code);
	}


	@Override
	public int updateSort(Dict entity) {
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
