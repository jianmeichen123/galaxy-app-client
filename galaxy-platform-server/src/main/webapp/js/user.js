//用户相关的js
var deptList ={};
var deptListByType ={};
 $(function() {

	var deptSelect =$('#selectDept'); 
	
	var json={"type":null};
	sendGetRequest(platformUrl.getDepartList,null,callbackFun,null);
	$(deptList).each(function(){
		var item = $(this)[0];
		console.log(item);
		var option = "<option value='"+item.id+"'>"+item.name+"</option>"
		deptSelect.append(option);
	});
	

	
});

function save(){
	alert("save")
	var pop = $("#pop");
	var json = {};
	$(pop).find("input").each(function(){
		if($(this).val().trim()!=""){
			json[$(this).attr("name")]= $(this).val();
		}
	});
	
	console.log(json);
}
//检索
function searchForm() {
	var re = /^[0-9]+.?[0-9]*$/;
	var value = $("#search_text").val();
	var mobile = null;
	var realName = null;
	var status = null;
	var options=$("#selectDept option:selected");  
	var departId = options.val();
	if (re.test(value)) {
		mobile = value;
	} else {
		realName = value;
	}
	var val=$('input:radio[name="status"]:checked').val();
	
	if (val=="1") {
		
		status = 1;
	}
	if (departId ==""){
		departId =null;
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
			alert('查询失败');
		},
		success : function(data) {
			//填充列表
		}
	});
}

function toadd() {
	$("#userInfo").show();
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
function callbackFun(data){
	deptList =data.entityList;
  	
 }
function callbackFun1(data){
	deptListByType =data.entityList;
  	
 }
function test(){
	var deptSelect1 =$('#selectId'); 
	
	var json={"type":1};
	sendGetRequest(platformUrl.getDepartList,json,callbackFun1,null);
	$(deptListByType).each(function(){
		var item = $(this)[0];
		console.log(item);
		var option = "<option value='"+item.id+"'>"+item.name+"</option>"
		deptSelect1.append(option);
	});
	
	$("#popTxt").on("click","a[action='save']",function() {
		var pop = $("#pop");
		var json = {};
		$(pop).find("input").each(function(){
			if($(this).val().trim()!=""){
				json[$(this).attr("name")]= $(this).val();
			}
		});
		var options=$("#selectId option:selected");  
		var departId = options.val();
		if(json['departmentId']=="on"&&departId!=''){
			json['departmentId'] = departId;
		}
		console.log(json);
		$.ajax({
			url : platformUrl.addUser,
			data : JSON.stringify(json),
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
	});
}