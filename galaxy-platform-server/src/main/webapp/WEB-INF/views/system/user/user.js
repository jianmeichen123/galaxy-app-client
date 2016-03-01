function searchForm() {
	var re = /^[0-9]+.?[0-9]*$/;
	var value = $("#search_text").val();
	var mobile = null;
	var realName = null;
	var status = null;
	var departId = null;
	if (re.test(value)) {
		mobile = value;
	} else {
		realName = value;
	}
	if ($("#disabled").checked) {
		status = 1;
	}

	var data = {
		"status" : status,
		"mobile" : mobile,
		"realName" : realName,
		"departmentId" : departId
	};
	$.ajax({
		url : platformUrl.queryUserList,
		data : data,
		async : false,
		type : 'POST',
		contentType : "application/json; charset=UTF-8",
		dataType : "json",
		cache : false,
		error : function() {
			alert('查询失败');
		},
		success : function(data) {
			//填充列表
		}
	});
}
//新增
function add() {
	$.ajax({
		url : platformUrl.addUser,
		data : $('#formid').serialize(),
		async : false,
		type : 'POST',
		contentType : "application/json; charset=UTF-8",
		dataType : "json",
		cache : false,
		error : function() {
			alert('添加失败');
		},
		success : function(data) {
			alert("添加成功")
		}
	});
}
//禁用用户
function disableUser(userId, status) {
	var data = {
		'userId' : userId,
		'status' : status
	};
	$.ajax({
		url : platformUrl.disableUser,
		data : data,
		async : false,
		type : 'POST',
		contentType : "application/json; charset=UTF-8",
		dataType : "json",
		cache : false,
		error : function() {
			alert('操作失败');
		},
		success : function(data) {
			alert("操作成功")
		}
	});
}

//重置密码
function resetPwd(userId) {
	var data = {
		'userId' : userId
	};
	$.ajax({
		url : platformUrl.resetPwd,
		data : data,
		async : false,
		type : 'POST',
		contentType : "application/json; charset=UTF-8",
		dataType : "json",
		cache : false,
		error : function() {
			alert('密码重置失败');
		},
		success : function(data) {
			alert("密码已重置")
		}
	});
}

function getDepartList() {
	$.ajax({
		url : platformUrl.getDepartList,
		data : data,
		async : false,
		type : 'POST',
		contentType : "application/json; charset=UTF-8",
		dataType : "json",
		cache : false,
		error : function() {
			alert('查询部门出错');
		},
		success : function(data) {
			//初始化数据
		}
	});
}