//用户相关的js
//检索
function search() {
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
		"departId" : departId
	};
	$.ajax({
		url : galaxy / user / queryUserList,
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
		url : galaxy / user / add,
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
		url : galaxy / user / disableUser,
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
		url : galaxy / user / resetPwd,
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