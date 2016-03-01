var platformUrl={
		/**
		 * 验证登录
		 */
		toLogin:"./galaxy/userlogin/login",
		/**
		 * 跳转到首页
		 */
		toIndex:"http://10.9.11.114:8080/galaxy-sop-server/galaxy/index",
		/**
		 * 用户管理首页
		 */
		userIndex:"/galaxy/user",
        /**
         * 分页查询
         */
        queryUserList:"galaxy/user/queryUserList",
        /**
         * 添加用户
         */
        addUser:"galaxy/user/add",
        /**
         * 启用禁用用户
         */
        disableUser:"galaxy/user/disableUser",
        /**
         * 重置密码
         */
        resetPwd:"galaxy/user/resetPwd",
        
        /**
         * 查询部门
         */
        getDepartList:"/galaxy/dept/queryList",
		
		/**
		 * 数据字典首页
		 */
		dictIndex:"galaxy/dict/index",

		/**
		 * 数据字典首页
		 */
		dictIndex:"galaxy/dict/index",
		/**
		 *  新增一个数据字典
		 */
		dictInsert:"galaxy/dict/insert",
		/**
		 * 批量新增数据字典
		 */
		dictBatchInsert:"galaxy/dict/batchInsert",
		/**
		 * 根据id更新数据字典
		 */		
		dictUpdateById:"galaxy/dict/updateById/",
		/**
		 * 根据code更新数据字典
		 */		
		dictUpdate:"galaxy/dict/update",
		/**
		 * 根据code查询数据字典
		 */	
		dictFindByCode:"galaxy/dict/findByCode/",
		/**
		 * 根据findByParentCode查询数据字典
		 */
		dictFindByParentCode:"galaxy/dict/findByParentCode/",
}

/**
 * how to use?
 * location.href = platformUrl.login
 */