package com.galaxyinternet.model.resource;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.galaxyinternet.framework.core.model.PagableEntity;

public class PlatformResource extends PagableEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long parentId;
    private String resourceMark;
    private String resourceName;
    private String resourceUrl;
    private String resourceType;
    private Long resourceOrder;
    private String resourceStatus;
    private String productMark;
    private Integer applicationPlatform;
    //样式
    private String style;
    private Long createdUid;
    private Long updatedUid;
    private String resourceDesc;
    
    private List<Long> resourceIdList;
    private Map<String,Object> mapList;
    
    
    public Map<String, Object> getMapList() {
		return mapList;
	}
	public void setMapList(Map<String, Object> mapList) {
		this.mapList = mapList;
	}
	public List<Long> getResourceIdList() {
		return resourceIdList;
	}
	public void setResourceIdList(List<Long> resourceIdList) {
		this.resourceIdList = resourceIdList;
	}
	public Long getParentId() {
        return parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public String getResourceMark() {
        return resourceMark;
    }
    public void setResourceMark(String resourceMark) {
        this.resourceMark = resourceMark;
    }
    public String getResourceName() {
        return resourceName;
    }
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
    public String getResourceUrl() {
        return resourceUrl;
    }
    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }
    public String getResourceType() {
        return resourceType;
    }
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
    public Long getResourceOrder() {
        return resourceOrder;
    }
    public void setResourceOrder(Long resourceOrder) {
        this.resourceOrder = resourceOrder;
    }
    public String getResourceStatus() {
        return resourceStatus;
    }
    public void setResourceStatus(String resourceStatus) {
        this.resourceStatus = resourceStatus;
    }
    public String getProductMark() {
        return productMark;
    }
    public void setProductMark(String productMark) {
        this.productMark = productMark;
    }
    public Integer getApplicationPlatform() {
		return applicationPlatform;
	}
	public void setApplicationPlatform(Integer applicationPlatform) {
		this.applicationPlatform = applicationPlatform;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public Long getCreatedUid() {
        return createdUid;
    }
    public void setCreatedUid(Long createdUid) {
        this.createdUid = createdUid;
    }
    public Long getUpdatedUid() {
        return updatedUid;
    }

    public void setUpdatedUid(Long updatedUid) {
        this.updatedUid = updatedUid;
    }
    public String getResourceDesc() {
        return resourceDesc;
    }
    public void setResourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc;
    }
    
    @Override
    public String toString() {
    	return JSON.toJSONString(this);
    }
}