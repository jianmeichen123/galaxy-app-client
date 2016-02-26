package com.galaxyinternet.bo;

import com.galaxyinternet.model.dict.Dict;

/**
 * 
    * @ClassName: DictBo
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @company 星河互联
    * @author vincent
    * @date 2016年2月24日 下午1:24:50 
    *
 */
public class DictBo extends Dict {

	private static final long serialVersionUID = 1L;
	private String extendFiled;// 业务对象中扩展的字段

	public String getExtendFiled() {
		return extendFiled;
	}

	public void setExtendFiled(String extendFiled) {
		this.extendFiled = extendFiled;
	}

}
