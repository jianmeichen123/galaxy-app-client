package com.galaxyinternet.model.dict;

import java.util.List;

import com.galaxyinternet.framework.core.model.BaseEntity;

/**
 * 
    * @ClassName: BatchDictInsetParam
    * @Description: 批量新增数据字典
    * @company 星河互联
    * @author vincent
    * @date 2016年2月25日 上午11:27:34 
    *
 */
public class BatchDictInsetParam extends BaseEntity{

	
	private static final long serialVersionUID = -7408202309914932785L;

	List<Dict> dicts;
	
	private String parentCode;

	public List<Dict> getDicts() {
		return dicts;
	}

	public void setDicts(List<Dict> dicts) {
		this.dicts = dicts;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	
	
}
