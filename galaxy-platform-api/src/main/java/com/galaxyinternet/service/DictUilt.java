package com.galaxyinternet.service;

import java.util.HashMap;

import com.galaxyinternet.model.dict.Dict;

public interface DictUilt {

	
	HashMap<String, Dict> getDictKV(String parentCode);
}
