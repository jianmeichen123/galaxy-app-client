package com.galaxyinternet.model.resource;

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
    private Long createdUid;
    private Long updatedUid;
    private String resourceDesc;
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
}