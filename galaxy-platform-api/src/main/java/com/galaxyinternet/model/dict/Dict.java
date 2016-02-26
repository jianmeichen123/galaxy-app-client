package com.galaxyinternet.model.dict;

import com.galaxyinternet.framework.core.model.BaseEntity;

/**
 * 
    * @ClassName: Dict
    * @Description: 数据字典实体类
    * @company 星河互联
    * @author vincent
    * @date 2016年2月23日 下午6:01:21 
    *
 */
public class Dict extends BaseEntity{

	public final static String COMMENT ="数据字典";
	public final static String NAME = "属性名称";
	public final static String VALUE = "属性值";
	public final static String CODE = "属性编码";
	public final static String SORT = "属性排序值";
	public final static String IS_DELETE = "是否删除";
	public final static String PARENT_ID = "父id";
	
	private static final long serialVersionUID = -8582891750808839253L;
	/**
	 * 名称
	 */
	private String name;
	private Integer value;
	private String code;
	/**
	 * 排序值
	 */
	private Integer sort;

	/**
	 * 是否删除
	 * 0 未删除
	 * 1 已删除
	 */
	private Integer isDelete;

	private Long parentId;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
