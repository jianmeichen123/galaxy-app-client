
var flag;
var TOKEN ;
function formatStatus(index, row) {
	var arr=row.userListByRid;
	var nameStr="";
	var result="";
	if (arr!=null) {
		for( var i=0;i<arr.length;i++){
			nameStr=nameStr+arr[i].realName+",";
		}
		
		result=nameStr.substring(0,nameStr.length-1)
	} 
	return result;
}
//获取TOKEN 用于验证表单提交
sendPostRequest(platformUrl.getToken,callback);
//操作链接
function editor(index, row) {
	var id = row.id;
	var arr=row.userListByRid;
	if (typeof(arr)=="undefined"||arr==null) {
		var resetUrl = "<a class='blue' href='javascript:resetPwd(" + id
		+ ")'>编辑</a>&nbsp;<a class='blue' href='javascript:deleteRole(" + id
		+ ")'>删除</a>";
		return   resetUrl;
		
	} else {
		var resetUrl = "<a class='blue' href='javascript:resetPwd(" + id
		+ ")'>编辑</a>&nbsp;<a  style='color:#cfcfcf; text-decoration:none; cursor:not-allowed;' href='javascript:void(0)'>删除</a>";
		return resetUrl;
		
	}
}

//重置密码
function resetPwd(id) {
	
	forwardWithHeader(platformUrl.roleEdit+"?id="+id);
	
}
//删除角色
function deleteRole(id) {
	var data = {
			'id' : id
		};	
		layer.confirm('你确定要删除吗?', 
				{
				  btn: ['确定', '取消'] 
				}, 
				function(index, layero){
					sendPostRequestByJsonObj(platformUrl.deleteRole, data, deleteRoleCallBack);
				}, 
				function(index){
					
				}
			);}

function doSumbit(){
$(".poptxt").on("click","a[action='save']",function() {
			var pop = $(".pop");
			var json = {};
			//var pattern = /^[\u4e00-\u9fa5]{1,8}$/;
     	if (pop.find("input[name='name']").val() == "") {
				layer.msg("请填写角色名");
				return;
			} else {
				
				var value = pop.find("input[name='name']").val();
				if (value.length>8) {
					layer.msg("角色名称最多输入8个字符");
					return;
				}
				var json = {"name" : value};	
				sendPostRequestByJsonObj(platformUrl.checkRoleName,
						json, callbackcheckRoleName);
				if (flag == true) {
					layer.msg("角色名不能重复");
					return;
				}
			
			}
     	    var desc=pop.find("[name='description']").val();
	     	if ( desc!= ""){
	     		if(desc.length>200){
					layer.msg("角色描述最多输入200个字符");
					return;
				}else{
					json['description']=desc;
				}
	      	}
	     	$.ajax({		
	    		data : JSON.stringify(json),
	    		async : false,
	    		type : 'POST',
	    		contentType : "application/json; charset=UTF-8",
	    		dataType : "json",
	    		cache : false,
	    		url : platformUrl.addRole,
	    		beforeSend : function(xhr) {
	    			if (TOKEN) {
	    				xhr.setRequestHeader("TOKEN", TOKEN);
	    			}
	    			if (sessionId) {
	    				xhr
	    						.setRequestHeader("sessionId",
	    								sessionId);
	    			}
	    			if (userId) {
	    				xhr.setRequestHeader("guserId", userId);
	    			}
	    		},
	    		error : function() {
	    			layer.msg("操作失败");
	    		},
	    		success : function(data) {
	    			if (data.result.status != "OK") {
	    				if (data.result.message == "新增角色成功") {
	    					layer.msg("新增角色失败", {
	    						time : 1000
	    					}, function() {
	    						history.go(0);
	    					});
	    				} else {
	    					layer.msg("添加失败");
	    				}
	    			} else {
	    				// 清除表单数据
	    				$(pop).find("input").each(function() {
	    					if ($(this).val().trim() != "") {
	    						json[$(this).attr("name")] = null;
	    					}

	    				});
	    				json['description']=null;
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
function callback(data){
	TOKEN=data.TOKEN;
	 return TOKEN;
}
function callbackcheckRoleName(data) {
	flag = data.flag;
}

//删除角色---回调函数
function deleteRoleCallBack(data) {
	if (data.result.status!="OK") {
		layer.msg("删除失败");
	} else {
		layer.msg("删除成功", {
			time : 1000
		}, function() {
			history.go(0);
		});
	}
	
}

