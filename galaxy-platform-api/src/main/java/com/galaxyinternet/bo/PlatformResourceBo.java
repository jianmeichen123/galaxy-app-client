package com.galaxyinternet.bo;

import com.galaxyinternet.model.resource.PlatformResource;

public class PlatformResourceBo extends PlatformResource
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//查询用户权限使用
    private Long userId;
	public Long getUserId()
	{
		return userId;
	}
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
    
}
