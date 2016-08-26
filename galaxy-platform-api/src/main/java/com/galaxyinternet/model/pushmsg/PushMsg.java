
    /**  
    * @Title: PushMsg.java
    * @Package com.galaxyinternet.model.pushmsg
    * @Description: TODO(用一句话描述该文件做什么)
    * @company 星河互联	
    * @author zhangchunyuan
    * @date 2016年8月23日 下午2:41:59
    * @version V1.0  
    */
    
package com.galaxyinternet.model.pushmsg;

import java.util.Date;
import java.util.List;

import com.galaxyinternet.framework.core.model.PagableEntity;

/**
    * @ClassName: PushMsg
    * @Description: 自定义消息推送
    * @company 星河互联
    * @author zhangchunyuan
    * @date 2016年8月23日 下午2:41:59 
    *
    */

public class PushMsg extends PagableEntity{
	
	private static final long serialVersionUID = 1L;
	
	
	private Date noticeTime;
	
	private String content;
	
	private String userStr;
	
	private List<String> userIdList;
	
	

	/**
	* @return userIdList
	*/
	
	public List<String> getUserIdList() {
		return userIdList;
	}

	
	/**
	 * @param userIdList the userIdList to set
	 */
	
	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
	}

	/**
	* @return noticeTime
	*/
	
	public Date getNoticeTime() {
		return noticeTime;
	}

	/**
	 * @param noticeTime the noticeTime to set
	 */
	
	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}


	/**
	* @return content
	*/
	
	public String getContent() {
		return content;
	}

	
	/**
	 * @param content the content to set
	 */
	
	public void setContent(String content) {
		this.content = content;
	}

	
	/**
	* @return userStr
	*/
	
	public String getUserStr() {
		return userStr;
	}

	
	/**
	 * @param userStr the userStr to set
	 */
	
	public void setUserStr(String userStr) {
		this.userStr = userStr;
	}

	
	
	
	
	
}
