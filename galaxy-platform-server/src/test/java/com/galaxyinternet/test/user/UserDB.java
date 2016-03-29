/**
 * 
 */
package com.galaxyinternet.test.user;

import com.galaxyinternet.model.user.User;

/**
 * @author zhangchunyuan
 *
 */
public class UserDB extends User{
	private Long departmentCode ;

	/**
	 * @return the departmentCode
	 */
	public Long getDepartmentCode() {
		return departmentCode;
	}

	/**
	 * @param departmentCode the departmentCode to set
	 */
	public void setDepartmentCode(Long departmentCode) {
		this.departmentCode = departmentCode;
	}

	
	
}
