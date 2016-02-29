package com.galaxyinternet.dao.dict;

import java.util.List;

import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.model.dict.BatchDictInsetParam;
import com.galaxyinternet.model.dict.Dict;

/**
 * 
    * @ClassName: DictDao
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @company 星河互联
    * @author vincent
    * @date 2016年2月24日 下午1:25:09 
    *
 */
public interface DictDao extends BaseDao<Dict, Long>{

	
	int selectCountByParentCode(String parentCode);
	
	List<Dict> selectByParentCode(String parentCode);
	
	Dict selectByCode(String code);
	
	int selectCountInCodes(List<String> codes);
	
	int selectCountSame(Dict dict);
	
	int selectCountSameIn(BatchDictInsetParam batchDictInsetParam);
	
	int selectCountByParentCodeAndName(Dict dict);

	int insertInBatch(BatchDictInsetParam batchDictInsetParam);

	Integer selectMaxValueByParentCode(String parentCode);
}
