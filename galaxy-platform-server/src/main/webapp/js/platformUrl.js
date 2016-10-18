var Constants = {
	sopEndpointURL : endpointObj["galaxy.project.sop.endpoint"],
	reportEndpointURL : endpointObj["galaxy.project.report.endpoint"],
	platformContentURL: endpointObj["galaxy.project.platform.endpoint"],
}

var platformUrl = {
		
	/**
	 * 跳转登录页
	 */
	toLoginPage :Constants.platformContentURL+ "galaxy/userlogin/toLogin",
	
	logout:Constants.platformContentURL+ "galaxy/userlogin/logout",
	/**
	 * 验证登录
	 */
	toLogin :"galaxy/userlogin/login",
	
	
	/**
	 * 桌面配置 页面
	 */
	indexConfig:"galaxy/indexConfig/toIndexConfig",
	/**
	 * 桌面配置 可用 资源拉取
	 */
	queryAvailableConfig:"galaxy/indexConfig/queryAvailableConfig/",
	/**
	 * 桌面配置 模版 资源拉取
	 */
	queryModelConfig:"galaxy/indexConfig/queryIndexModelConfig/",
	/**
	 * 桌面配置 模版 保存
	 */
	saveIndexModel:"galaxy/indexConfig/saveIndexConfig/",
	
	/**
	 * 桌面配置 模版 保存
	 */
	deleteIndexModel:"galaxy/indexConfig/deleteIndexModel/",
	
	
	
	
	
	
	
	/**
	 * 跳转到首页
	 */
    toIndex :Constants.sopEndpointURL+  "/galaxy/redirect",
	/**
	 * 用户管理首页
	 */
	userIndex :Constants.platformContentURL+ "galaxy/user",
	/**
	 * 角色管理
	 */
	roleIndex :Constants.platformContentURL+ "galaxy/role/index",
    /**
	 * 删除角色
	 */
	deleteRole :Constants.platformContentURL+ "galaxy/role/deleteRole",
	/**
	 * 删除角色
	 */
	checkRoleName :Constants.platformContentURL+ "galaxy/role/checkRoleName",
	
	/**
	 * 新增角色
	 */
	
	addRole :Constants.platformContentURL+ "galaxy/role/addRole",
	
	/**
	 * 跳转至角色编辑页面
	 */
	
	roleEdit :Constants.platformContentURL+ "galaxy/role/roleEdit",
	
	/**
	 * 跳转至角色编辑页面
	 */
	
	getRoleDetail :Constants.platformContentURL+ "galaxy/role/getRoleDetail",
	
	
	
	/**
	 * 分页查询
	 */
	queryUserList :"galaxy/user/queryUserList",
	/**
	 * 添加用户
	 */
	addUser : "galaxy/user/updateUser",
	/**
	 * 启用禁用用户
	 */
	disableUser :"galaxy/user/disableUser",
	/**
	 * 重置密码
	 */
	resetPwd :"galaxy/user/resetPwd",
	/**
	 * 修改密码
	 */
	updatePwd : Constants.sopEndpointURL + "/galaxy/home/updatePwd",

	/**
	 * 查询部门
	 */
	getDepartList :"galaxy/user/departmentList",
	
	/**
	 * 查询部门
	 */
	roleListBySelect :"galaxy/role/roleListBySelect",
	
	/**
	 * 查询用户 自动补全
	 */
	getUserList :"galaxy/user/userList",
	/**
	 * 检测用户名是否重复
	 */
	checkNickName:"galaxy/user/checkNickName",

	/**
	 * 检测邮箱是否重复
	 */
	checkEmail:"galaxy/user/checkEmail",

	/**
	 * 数据字典首页
	 */
	dictIndex :Constants.platformContentURL+ "galaxy/dict/index",
	/**
	 * 新增一个数据字典
	 */
	dictInsert : "galaxy/dict/insert",
	/**
	 * 批量新增数据字典
	 */
	dictBatchInsert :"galaxy/dict/batchInsert",
	/**
	 * 根据id更新数据字典
	 */
	dictUpdateById :"galaxy/dict/updateById/",
	/**
	 * 根据code更新数据字典
	 */
	dictUpdate :"galaxy/dict/update",
	/**
	 * 根据code查询数据字典
	 */
	dictFindByCode :"galaxy/dict/findByCode/",
	/**
	 * 根据findByParentCode查询数据字典
	 */
	dictFindByParentCode :"galaxy/dict/findByParentCode/",
	
	/**
	 * 获取token
	 */
	getToken:"galaxy/user/formtoken",
	
	/**
	 * 查询角色列表
	 */
	getRoleList :"galaxy/role/roleList",
	/**
	 * 自定义消息推送
	 */
	pushMsgIndex:"galaxy/pushMsg",
	/**
	 * 
	 */
	queryUserByDept:"galaxy/user/queryUserByDept",
		
	insertPushMsg:"galaxy/pushMsg/insertPushMsg"
		
		
}
/**
 * how to use? location.href = platformUrl.login
 */
