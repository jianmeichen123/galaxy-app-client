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
<title>星河投</title>
<base href="<%=basePath%>">
<!-- jsp文件头和头部 -->

<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!-- 	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"  type="text/css">
 -->    <!-- bootstrap-table -->
	<link rel="stylesheet" href="bootstrap/bootstrap-table/bootstrap-table.css"  type="text/css">
	<link rel="stylesheet" href="bootstrap/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"  type="text/css">
    <link href="css/axure.css" type="text/css" rel="stylesheet" />
    <link href="css/jquery-ui.min.css" type="text/css" rel="stylesheet" />
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<div class="pagebox clearfix">
	
		<!--左侧导航-->
		<jsp:include page="/WEB-INF/views/common/leftmenu.jsp" flush="true"></jsp:include>
		<!--左侧导航-->
		
		<!--右中部内容-->
		<div class="ritmin"  source="role">
			<h2>角色权限</h2>
			<!--页眉-->
			<div class="top clearfix">
				<!--按钮-->
				<div class="btnbox_f btnbox_f1 clearfix" style="margin-bottom:15px;">
					<a href="html/role_add.html" data-btn="role_add"
						class="pubbtn bluebtn ico c4">添加角色</a>
				</div>
			</div>

			<div class="tab-pane active height_ding" id="view">		
					<table id="data-table" data-url="<%=path %>/galaxy/role/roleList" style='table-layout:fixed ;'  data-page-list="[10, 20, 30]" 
					data-toolbar="#custom-toolbar">
						   <thead>
						    <tr>
					        <th data-field="name"  class="data-input width_8" >角色名称</th>
					        <th data-field="description"   class="data-input width_450">角色描述</th>
					        <th data-field="description" data-formatter="formatStatus"  class="col-md-1 heightstatus ">用户列表</th>
					        <th  class="col-md-2 lpubbtn" data-formatter="editor" >操作</th>

   						 	</tr>	
   						 	</thead>
					</table>
	              </div>
		</div>
</div>
	</div>
	<script src="js/layer/layer.js" type="text/javascript"></script>
	
		<!-- bootstrap-table -->
	
	<!-- <script src="js/bootstrap3-typeahead.js"></script> -->
	<script src="js/jquery-ui.min.js"></script>
		<!-- bootstrap-table -->
	<script src="bootstrap/js/bootstrap.min.js"></script>	
	<!-- bootstrap-table -->
	<script src="bootstrap/bootstrap-table/bootstrap-table-xhhl.js"></script>
	<script src="bootstrap/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script src="bootstrap/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
	<script src="js/init.js"></script>	
	<script src="js/login.js"></script>
	<script src="<%=request.getContextPath() %>/js/axure_ext.js" type="text/javascript"></script>
	<script src="js/role.js" type="text/javascript"></script>
</body>
</html>

