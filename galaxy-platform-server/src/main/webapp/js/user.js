//用户相关的js
var deptList = {};
var deptListByType = {};
var userList = {};
$(function() {

	var deptSelect = $('#selectDept');

	var json = {
		"type" : null
	};
	sendGetRequest(platformUrl.getDepartList, null, callbackFun, null);
	$(deptList).each(
			function() {
				var item = $(this)[0];
				// console.log(item);
				var option = "<option value='" + item.id + "'>" + item.name
						+ "</option>"
				deptSelect.append(option);
			});

});

// 检索
function searchForm() {
	var re = /^[0-9]+.?[0-9]*$/;
	var value = $("#search_text").val();
	var mobile = null;
	var realName = null;
	var status = null;
	var options = $("#selectDept option:selected");
	var departId = options.val();
	if (re.test(value)) {
		mobile = value;
	} else {
		realName = value;
	}
	var val = $('input:radio[name="status"]:checked').val();

	if (val == "1") {

		status = 1;
	}
	if (departId == "") {
		departId = null;
	}

	var data = {
		"status" : status,
		"mobile" : mobile,
		"realName" : realName,
		"departmentId" : departId
	};

	$.ajax({
		url : platformUrl.queryUserList,
		data : JSON.stringify(data),
		async : false,
		type : 'POST',
		contentType : "application/json; charset=UTF-8",
		dataType : "json",
		cache : false,
		error : function() {
			layer.msg('查询失败');
		},
		success : function(data) {
			// 填充列表
		}
	});
}

function toadd() {
	$("#userInfo").show();
}
// 新增
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
			layer.msg('添加失败');
		},
		success : function(data) {
			layer.msg("添加成功")
		}
	});
}
// 禁用用户
function disableUser(id, status) {
	var data = {
		'id' : id,
		'status' : status
	};
	$.ajax({
		url : platformUrl.disableUser,
		data : JSON.stringify(data),
		async : false,
		type : 'POST',
		contentType : "application/json; charset=UTF-8",
		dataType : "json",
		cache : false,
		error : function() {
			layer.msg('操作失败');
		},
		success : function(data) {
			layer.msg("操作成功", {
				time : 1000
			}, function() {
				history.go(0);
			});
		}
	});
}

// 重置密码
function resetPwd(id) {
	var data = {
		'id' : id
	};
	$.ajax({
		url : platformUrl.resetPwd,
		data : JSON.stringify(data),
		async : false,
		type : 'POST',
		contentType : "application/json; charset=UTF-8",
		dataType : "json",
		cache : false,
		error : function() {
			layer.msg('密码重置失败');
		},
		success : function(data) {
			if (data.result.status!="OK")  {
				layer.msg(data.result.errorCode);
			} else {
				layer.msg("密码已重置");
			}
			
		}
	});
}
function callbackFun(data) {
	deptList = data.entityList;

}
function callbackFun1(data) {
	deptListByType = data.entityList;

}
function setData(data) {

	userList = data.entityList;
	console.log(userList);

}
function doSumbit() {
	$('#birth').datetimepicker({
		format : 'yyyy-mm-dd', // 格式化日期
		timepicker : true, // 关闭时间选项
		minView : "month",
		autoclose : true,
		yearEnd : 2050, // 设置最大年份
		language : 'zh-CN', // 汉化
	});

	var deptSelect1 = $('#selectId');

	$("#realName").autocomplete(
			{
				/*
				 * source: function( request, response ) { var matcher = new
				 * RegExp( $.ui.autocomplete.escapeRegex( request.term ), "i" );
				 * response( $.grep( names, function( value ) { value =
				 * value.label || value.value || value; return matcher.test(
				 * value ) || matcher.test( normalize( value ) ); }) ); },
				 */
				source : function(request, response) {
					$.ajax({
						url : platformUrl.getUserList,
						dataType : "json",
						contentType : "application/json; charset=UTF-8",
						data : {
							realName : request.term
						},
						type : 'GET',
						/*
						 * data:{ searchDbInforItem: request.term },
						 */
						success : function(data) {
							response($.map(data.pageList.content,
									function(item) {
										return {
											label : item.realName, // 显示在匹配下拉框的内容
											// mytext: item.mail,
											// //可以自定义，用来赋值给其他input框或div域
											// value: item.displayname, //是返回值属性
											id : item.id,
											employNo : item.employNo,
											gender : item.gender,
											birth : item.birth,
											email : item.email,
											nickName : item.nickName,
											mobile : item.mobile,
											telephone : item.telephone,
											address : item.address,
											roleId : item.roleId,
											departmentId : item.departmentId
										}
									}));
						}
					});
				},
				minLength : 1,
				select : function(event, ui) {
					$("#userId").val(ui.item.id);
					$("#nickName").val(ui.item.nickName);
					if (ui.item.birth!= null) {
						 $("#birth").val(formatDate(ui.item.birth));
					}
					$("#employNo").val(ui.item.employNo);
					$("#gender").val(ui.item.gender);
					if (ui.item.gender == true) {
						$("input[name='gender'][value=1]")
								.attr("checked", true);
					} else {
						$("input[name='gender'][value=0]")
								.attr("checked", true);
					}

					$("#email").val(ui.item.email);
					$("#mobile").val(ui.item.mobile);
					$("#telephone").val(ui.item.telephone);
					$("#address").val(ui.item.address);
					$("#roleId").val(ui.item.roleId);
					$("input[name='roleId'][value='" + ui.item.roleId + "']")
							.attr("checked", true);

					if (ui.item.departmentId == 100) {
						$("input[name='departmentId'][value=100]").attr(
								"checked", true);
					} else {
						$("input[name='departmentId'][value=10000]").attr(
								"checked", true);
						$("#selectId").val(ui.item.departmentId);
					}
				}
			});

	var json = {
		"type" : 1
	};
	sendGetRequest(platformUrl.getDepartList, json, callbackFun1, null);
	$(deptListByType).each(
			function() {
				var item = $(this)[0];
				// console.log(item);
				var option = "<option value='" + item.id + "'>" + item.name
						+ "</option>"
				deptSelect1.append(option);
			});

	$(".poptxt")
			.on(
					"click",
					"a[action='save']",
					function() {
						var pop = $(".pop");
						var json = {};

						if (pop.find("input[name='realName']").val() == "") {
							layer.msg("请填写真实姓名");
							return;
						}

						if (pop.find("input[name='employNo']").val() == "") {
							layer.msg("请填写工号");
							return;
						}

						if (pop.find('input:radio[name="gender"]:checked')
								.val() == null) {
							layer.msg("请选择性别");
							return;
						}
						if (pop.find("input[name='email']").val() == "") {
							layer.msg("请填写邮箱");
							return;
						}
						if (pop.find("input[name='nickName']").val() == "") {
							layer.msg("请填写登录名");
							return;
						}
						if (pop.find("input[name='mobile']").val() == "") {
							layer.msg("请填写邮手机号");
							return;
						}

						if (pop
								.find(
										'input:radio[name="departmentId"]:checked')
								.val() == null) {
							layer.msg("请选择职能部门");
							return;
						}
						if (pop.find('input:radio[name="roleId"]:checked')
								.val() == null) {
							layer.msg("请选择角色");
							return;
						}
						$(pop).find("input").each(function() {
							if ($(this).val().trim() != "") {
								json[$(this).attr("name")] = $(this).val();
							}
						});
						var options = $("#selectId option:selected");
						var departId = options.val();

						var val = $('input:radio[name="roleId"]:checked').val();
						json['roleId'] = val;
						json['departmentId'] = departId;
						var value = $("input[name='departmentId']:checked")
								.val();
						json['departmentId'] = value;

						if ((json['departmentId'] == '10000' || json['departmentId'] == 'on')
								&& departId != '') {
							json['departmentId'] = departId;

						}
						$.ajax({
							url : platformUrl.addUser,
							data : JSON.stringify(json),
							async : false,
							type : 'POST',
							contentType : "application/json; charset=UTF-8",
							dataType : "json",
							cache : false,
							error : function() {
								layer.msg("添加失败");
							},
							success : function(data) {
								
								if (data.result.status!="OK")  {
									layer.msg(data.result.errorCode);
								} else {
									// 清除表单数据
									$(pop).find("input").each(function() {
										if ($(this).val().trim() != "") {
											json[$(this).attr("name")] = null;
										}

									});
									
									// 刷新列表
									// $('#popTxt').close();

									layer.msg("添加成功", {
										time : 1000
									}, function() {
										history.go(0);
									});
								}
								
							}
						});
					});
}

function useCompanyAddress() {
	$("#address").val("北京市海淀区上地创新大厦三层");
}

// 操作链接
function editor(index, row) {
	var id = row.id;
	var status = row.status;
	var text = status == 1 ? '启用用户' : '禁用用户';
	var disableUrl = "<a class='' href='javascript:disableUser(" + id + ","
			+ status + ")'>" + text + "</a>";
	var resetUrl = "<a class='' href='javascript:resetPwd(" + id
			+ ")'>重置密码</a>";
	return disableUrl + "  " + resetUrl;
}

function formatGender(index, row) {
	if (row.gender == true) {
		return "男";
	} else {
		return "女";
	}
}

function formatStatus(index, row) {
	if (row.status == 1) {
		return "已禁用";
	} else {
		return "正常";
	}
}

function formatDept(index, row) {
	if (row.departmentName == null) {
		return "总部职能部门";
	} else {
		return row.departmentName;
	}
}

function formatDate(date, format) {   
    if (!date) return;   
    if (!format) format = "yyyy-MM-dd";   
    switch(typeof date) {   
        case "string":   
            date = new Date(date.replace(/-/, "/"));   
            break;   
        case "number":   
            date = new Date(date);   
            break;   
    }    
    if (!date instanceof Date) return;   
    var dict = {   
        "yyyy": date.getFullYear(),   
        "M": date.getMonth() + 1,   
        "d": date.getDate(),   
        "H": date.getHours(),   
        "m": date.getMinutes(),   
        "s": date.getSeconds(),   
        "MM": ("" + (date.getMonth() + 101)).substr(1),   
        "dd": ("" + (date.getDate() + 100)).substr(1),   
        "HH": ("" + (date.getHours() + 100)).substr(1),   
        "mm": ("" + (date.getMinutes() + 100)).substr(1),   
        "ss": ("" + (date.getSeconds() + 100)).substr(1)   
    };       
    return format.replace(/(yyyy|MM?|dd?|HH?|ss?|mm?)/g, function() {   
        return dict[arguments[0]];   
    });                   
}   