<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<title>繁星</title>
<base href="<%=basePath%>">
<!-- jsp文件头和头部 -->
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!-- 	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"  type="text/css">
 -->    <!-- bootstrap-table -->
	<link rel="stylesheet" href="bootstrap/bootstrap-table/bootstrap-table.css"  type="text/css">
	<link rel="stylesheet" href="bootstrap/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"  type="text/css">
    <link href="css/axure.css" type="text/css" rel="stylesheet" />
     <link href="dtree/dtree.css" type="text/css" rel="stylesheet" />
    <link href="css/jquery-ui.min.css" type="text/css" rel="stylesheet" />
    	<script src="js/role.js" type="text/javascript"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<div class="pagebox clearfix">
	
		<!--左侧导航-->
		<jsp:include page="/WEB-INF/views/common/leftmenu.jsp" flush="true"></jsp:include>
		<!--左侧导航-->
		<div class="ritmin prj_all">
    	<div class="new_tit_a"><a href="javascript:void(0);"  onclick="backIndex()">角色权限</a>&gt;编辑</div>
       	<div class='role_center'>
        	<div class="role_center_info">
            	<ul class="ul_role">
                	<li>
                	<input type="hidden" id="id" value="${id}" />
                    	<div class="fl width_150 align_r">角色名称：</div>
                        <div class="fl"><input type="text" name="name" value="" class="new_nputr"></div>
                        <input type="hidden" name="oldName">
                    </li>
                    <li>
                    	<div class="fl width_150 align_r">角色描述：</div>
                        <div class="fl"><textarea type="text" name="description" value=""  class="role_textarea"></textarea></div>
                    </li>
                    <li>
                    	<div class="fl width_150 align_r">用户列表：</div>
                        <div class="fl role_list">
                           
                        </div>
                    </li>
                    <li>
                    	<div class="fl width_150 align_r">权限配置：</div>
                        <div class="fl role_border">
                            
                            
                            <div class="role_set" id="form">
                            	<div id="roleResource">
			                          
			  					</div>
                            </div>
                        </div>
                    </li>
                    <li>
                    	<div class="fl width_150 align_r">　</div>
                        <div class="fl">
                        	<div class="role_save">
                               <span class="pubbtn bluebtn" onclick="add();">保存</span>
                               <span class="pubbtn fffbtn" data-name="industry" onclick="sole_close()">取消</span>
                           </div>
                        </div>
                    </li>
                    
                </ul>
            </div>
        </div>
    </div>
		
	
	<script src="js/layer/layer.js" type="text/javascript"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="dtree/dtree.js"></script>
	<script src="<%=request.getContextPath() %>/js/axure_ext.js" type="text/javascript"></script>
    <script>
      $(function(){
    	  var roleDetail
    	  var roleId='${id}';
    	  /**获取角色信息**/
    	  sendGetRequest(platformUrl.getRoleDetail + "/"+roleId, {}, function(data){	
    			roleDetail = data.entity;
    			$("input[name='name']").val(roleDetail.name);
    			$("textarea[name='description']").val(roleDetail.description);
    			$("input[name='oldName']").val(roleDetail.name);
    			
    			var arr=roleDetail.userListByRid;
    			var nameStr="";
    			var result="";
    			if (arr!=null) {
    				for( var i=0;i<arr.length;i++){
    					nameStr=nameStr+arr[i].realName+" ";
    				}
    				
    				result=nameStr.substring(0,nameStr.length-1)
    			} 
    			$(".role_list").text(result);
    		
    		});
    	  /**获取角色权限信息**/
          var jsonData = {"roleId":roleId};
    	  $.ajax({
				url : "<%= path%>/galaxy/resource/resourceTree",
				type : "POST",
			    data :  JSON.stringify(jsonData),
				dataType : "json",
				cache : false,
				contentType : "application/json; charset=UTF-8",
				beforeSend : function(xhr) {
					xhr.setRequestHeader("resource_mark", "interView");
					if (sessionId) {
						xhr.setRequestHeader("sessionId", sessionId);
					}
					if(userId){
						xhr.setRequestHeader("guserId", userId);
					}
				},
				async : false,
				error : function(request) {
				},
				success : function(data) {
					    var rolerights=data.entity.mapList.resourceIdList;
					    var resourceRangeMap=data.entity.mapList.resourceRangeMap;
					    var relativePath="dtree/";
					    d = new dTree('d',relativePath,"form");
					    d.add(0,-1,'所有权限');
					    var entityList = data.entityList;
					    
					    /*select 数据组装 ---------start--------*/
					    var obj = [];
					    var param = {};
					        param.name = "全公司的项目";
					        param.value = "0";
					        obj.push(param);
					    var param1 = {};
						    param1.name = "本部门的项目";
						    param1.value = "1";
						    obj.push(param1);
					    var param2 = {};
						    param2.name = "自己的项目";
						    param2.value = "2";
						    obj.push(param2);
						 /*select 数据组装 ---------end--------*/
						 
					    $.each(entityList,function(i,entity){
					    	var select= "<select id=\"select\" class=\"cx_select\" name =\"test\">";
								$.each(obj,function(i,objEntity){
									select += '<option value="'+objEntity.value+'"';
									if(resourceRangeMap[entity.id]){
										if(resourceRangeMap[entity.id] == objEntity.value){
											select +=' selected="selected"';
										}
									}
									select +='>'+objEntity.name+'</option>'; 
								});
							select += "</select>";
					    	if(rolerights.indexOf(entity.id) >= 0){
				    			 d.add(entity.id,entity.parentId,entity.resourceDesc,'',1,'',true,select);
				    		}else{
				    			 d.add(entity.id,entity.parentId,entity.resourceDesc,'','','',true,select);
				    			
				    		}
					    });
				        $("#roleResource").html(d.toString());
				}
			});
      });
      /**添加角色权限**/
      function add(){
  		var obj={};
  		var spCodesTemp = "";
    	var id = $("input[id='id']").val();
   	  	var name = $("input[name='name']").val();
   		var description = $("textarea[name='description']").val();
   		var pattern = /^[A-Za-z\u4e00-\u9fa5]{1,8}$/;
   	  	if(name==''){
	   	  	layer.msg("请填写角色名!");
	   	  	return ;
   	  	} else {
			if (!pattern.test(name)) {
				layer.msg("角色名称只能输8个汉字或者字母");
				return;
			}
		    var oldName=$("input[name='oldName']").val();
			if(oldName!=name){
				var json = {"name" : name};	
				sendPostRequestByJsonObj(platformUrl.checkRoleName,
						json, callbackcheckRoleName);
				if (flag == true) {
					layer.msg("角色名不能重复");
					return;
				}
		    }
		} 
   	 var patternd = /^[\u4e00-\u9fa5]{0,200}$/;
   	 if ( description!= ""){
  		if(!patternd.test(description)){
				layer.msg("角色描述最多输入200个汉字");
				return;
			}else{
				//json['description']=description;
				obj.description = description;
			}
   	}
	   	    obj.roleId = id;
	   	    obj.name = name;
	   	    
	  		$('#roleResource input:checkbox[name=checkid]:checked').each(function(i){
	  			if(0==i){
	  				spCodesTemp += $(this).val();
	  				spCodesTemp+=(":"+$(this).parent().find($('select option:selected')).val());
	  			}else{
	  				spCodesTemp += (","+$(this).val()+":"+$(this).parent().find($('select option:selected')).val());
	  			}
	  		});
  		    obj.resourceIds = spCodesTemp;
 		$.ajax({
			url : "<%= path%>/galaxy/resource/addRoleResource",
			type : "POST",
		    data :  JSON.stringify(obj),
			dataType : "json",
			cache : false,
			contentType : "application/json; charset=UTF-8",
			beforeSend : function(xhr) {
				xhr.setRequestHeader("resource_mark", "interView");
				if (sessionId) {
					xhr.setRequestHeader("sessionId", sessionId);
				}
				if(userId){
					xhr.setRequestHeader("guserId", userId);
				}
			},
			async : false,
			error : function(request) {
			},
			success : function(data) {
				  if(data.result.status == "OK"){
					  forwardWithHeader("<%= path%>/galaxy/role/index");
				  }else{
					  alert(data.result.message);
				  }
			}
	    });
  	
  	}
    function sole_close(){
    	forwardWithHeader("<%= path%>/galaxy/role/index");
    }
    function backIndex(){
    	forwardWithHeader("<%= path%>/galaxy/role/index");
    }
    </script>

</body>
</html>

