package com.galaxyinternet.model.sopIndex;

import com.galaxyinternet.framework.core.model.PagableEntity;
import com.galaxyinternet.framework.core.utils.GSONUtil;

public class IndexConfig extends PagableEntity{
	
	private static final long serialVersionUID = 1L;
	
    private Long userId;

    private Long roleId;

    private Long resourceId;

    private String contentUrl;

    private Byte configOrder;

    private String styleCss;

    private Byte shapeType;

    private Long updatedUid;

    private Long createdUid;
    
    private String resourceCode;



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl == null ? null : contentUrl.trim();
    }


    public Byte getConfigOrder() {
		return configOrder;
	}

	public void setConfigOrder(Byte configOrder) {
		this.configOrder = configOrder;
	}

	public String getStyleCss() {
        return styleCss;
    }

    public void setStyleCss(String styleCss) {
        this.styleCss = styleCss == null ? null : styleCss.trim();
    }

    public Byte getShapeType() {
        return shapeType;
    }

    public void setShapeType(Byte shapeType) {
        this.shapeType = shapeType;
    }

    public Long getUpdatedUid() {
        return updatedUid;
    }

    public void setUpdatedUid(Long updatedUid) {
        this.updatedUid = updatedUid;
    }

    public Long getCreatedUid() {
        return createdUid;
    }

    public void setCreatedUid(Long createdUid) {
        this.createdUid = createdUid;
    }

    
    @Override
	public String toString() {
		return GSONUtil.toJson(this);
	}

	public String getResourceCode()
	{
		return resourceCode;
	}

	public void setResourceCode(String resourceCode)
	{
		this.resourceCode = resourceCode;
	}
    
    
}