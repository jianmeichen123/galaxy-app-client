var Constants = {
	sopEndpointURL : endpointObj["galaxy.project.sop.endpoint"],
	reportEndpointURL : endpointObj["galaxy.project.report.endpoint"]
}

var platformUrl = {
		
	/**
	 * 跳转登录页
	 */
	toLoginPage :"galaxy/userlogin/toLogin",
	/**
	 * 验证登录
	 */
	toLogin : "galaxy/userlogin/login",
	/**
	 * 跳转到首页
	 */
    toIndex :Constants.sopEndpointURL+  "/galaxy/redirect",
	/**
	 * 用户管理首页
	 */
	userIndex : "galaxy/user",
	/**
	 * 分页查询
	 */
	queryUserList : "galaxy/user/queryUserList",
	/**
	 * 添加用户
	 */
	addUser : "galaxy/user/updateUser",
	/**
	 * 启用禁用用户
	 */
	disableUser : "galaxy/user/disableUser",
	/**
	 * 重置密码
	 */
	resetPwd : "galaxy/user/resetPwd",

	/**
	 * 查询部门
	 */
	getDepartList : "galaxy/user/departmentList",
	/**
	 * 查询用户 自动补全
	 */
	getUserList : "galaxy/user/userList",

	/**
	 * 数据字典首页
	 */
	dictIndex : "galaxy/dict/index",

	/**
	 * 数据字典首页
	 */
	dictIndex : "galaxy/dict/index",
	/**
	 * 新增一个数据字典
	 */
	dictInsert : "galaxy/dict/insert",
	/**
	 * 批量新增数据字典
	 */
	dictBatchInsert : "galaxy/dict/batchInsert",
	/**
	 * 根据id更新数据字典
	 */
	dictUpdateById : "galaxy/dict/updateById/",
	/**
	 * 根据code更新数据字典
	 */
	dictUpdate : "galaxy/dict/update",
	/**
	 * 根据code查询数据字典
	 */
	dictFindByCode : "galaxy/dict/findByCode/",
	/**
	 * 根据findByParentCode查询数据字典
	 */
	dictFindByParentCode : "galaxy/dict/findByParentCode/",
}

/**
 * how to use? location.href = platformUrl.login
 */
