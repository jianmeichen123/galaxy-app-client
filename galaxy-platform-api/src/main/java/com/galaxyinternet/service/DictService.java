package com.galaxyinternet.service;

import java.util.List;

import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.dict.BatchDictInsetParam;
import com.galaxyinternet.model.dict.Dict;


/**
 * 
    * @ClassName: DictService
    * @Description: 数据字典服务接口
    * @company 星河互联
    * @author vincent
    * @date 2016年2月24日 下午1:24:40 
    *
 */
public interface DictService extends BaseService<Dict> {
	
	/**
	 * 
	    * @Title: selectByParentId
	    * @Description: 根据parent查询数据字典集合
	    * @param @param parentId
	    * @param @return    参数
	    * @return List<Dict>    数据字典list集合
	    * @throws
	 */
	List<Dict> selectByParentId(Long parentId);
	
	/**
	 * 
	    * @Title: 
	    * @Description: 根据code查询数据字典单个
	    * @param @param code
	    * @param @return    参数
	    * @return Dict    待查询的数据字典
	    * @throws
	 */
	Dict selectByCode(String code);
	/**
	 * 
	    * @Title: insertInBatch
	    * @Description: 批量添加数据字典
	    * @param @param batchDictInsetParam
	    * @param @return    参数
	    * @return int    成功插入几条
	    * @throws
	 */
	int insertInBatch(BatchDictInsetParam batchDictInsetParam);

	/**
	 * 
	    * @Title: updateSort
	    * @Description: 单独更新排序
	    * @param @param entity
	    * @param @return    参数
	    * @return int    成功更新几条
	    * @throws
	 */
	int updateSort(Dict entity);

}


