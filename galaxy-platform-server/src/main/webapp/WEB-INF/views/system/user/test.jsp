﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
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
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<div class="pagebox clearfix">
	
		<!--左侧导航-->
		<jsp:include page="/WEB-INF/views/common/leftmenu.jsp" flush="true"></jsp:include>
		<!--左侧导航-->
		
		<!--右中部内容-->
		<div class="ritmin"  source="user">
			<h2>测试页面</h2>
			 <!--  <table class="maintable">
                <tr><td id="test"></td></tr>
			
			  </table> -->
			  <div id="test">
			  
			  </div>

		</div>




	</div>
	<script src="js/layer/layer.js" type="text/javascript"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="dtree/dtree.js"></script>
	<script src="<%=request.getContextPath() %>/js/axure_ext.js" type="text/javascript"></script>
    <script>
      $(function(){
    	  $.ajax({
				url : "<%= path%>/galaxy/resource/resourceTree",
				type : "POST",
			    data : "",
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
					    var rolerights=data.entity.resourceIdList;
					    var relativePath="dtree/img/";
					    d = new dTree('d',relativePath);
					    d.add(0,-1,'所有权限');
					    var entityList = data.entityList;
					    
					    /*select 数据组装 ---------start--------*/
					    var obj = [];
					    var param = {};
					    param.name = "全公司的项目";
					    param.value = "全公司的项目";
					    obj.push(param);
					    var param1 = {};
					    param1.name = "本部门的项目";
					    param1.value = "本部门的项目";
					    obj.push(param1);
					    var param2 = {};
					    param2.name = "自己的项目";
					    param2.value = "自己的项目";
					    obj.push(param2);
					    var select= "<select id=\"select\" name =\"test\">";
						$.each(obj,function(i,entity){
							select += '<option value="'+entity.value+'">'+entity.name+'</option>'; 
						});
						select += "</select>";
						 /*select 数据组装 ---------end--------*/
						 
					    $.each(entityList,function(i,entity){
					    	if(rolerights.indexOf(entity.id) >= 0){
				    			 d.add(entity.id,entity.parentId,entity.resourceName,'',1,true,select.toString());
				    		}else{
				    			 d.add(entity.id,entity.parentId,entity.resourceName,'','',true,select.toString());
				    			
				    		}
					    });
				        $("#test").html(d.toString());
				}
			});
      });
    
    </script>

</body>
</html>

