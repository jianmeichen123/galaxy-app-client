package com.galaxyinternet.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxyinternet.model.dict.Dict;
import com.galaxyinternet.service.DictService;
import com.galaxyinternet.service.DictUilt;

@Service("com.galaxyinternet.service.DictUilt")
public class DictUtil implements DictUilt{

	private static final Map<String, HashMap<String, Dict>> dictKV =  new HashMap<String, HashMap<String,Dict>>();
	
	@Autowired
	private DictService dictService;
	

	public void initDictKV(){
		List<Dict> dicts = dictService.selectAll();
		for (Dict dict : dicts) {
			if(dict.getParentCode().equals(Dict.CODE_PARENT)){
				HashMap<String, Dict> son = new HashMap<String, Dict>();
				dictKV.put(dict.getCode(), son);
			}
		}
		for (Dict dict : dicts) {
			if(!dict.getParentCode().equals(Dict.CODE_PARENT)){
				dictKV.get(dict.getParentCode()).put(dict.getCode(), dict);
			}
		}
	}


	@Override
	public HashMap<String, Dict> getDictKV(String parentCode) {
		return dictKV.get(parentCode);
	}
	
}
