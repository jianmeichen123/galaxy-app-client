<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.galaxyinternet.com/tags/acl" prefix="acl" %>
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
			<h2>用户管理</h2>
			<!--页眉-->
			<div class="top clearfix">
				<!--按钮-->
				<div class="btnbox_f btnbox_f1 clearfix">
					<a href="html/userinfrotc.html" data-btn="userinfro"
						class="pubbtn bluebtn ico c4">添加人员</a>
				</div>
				<acl:acl resourceMark="interView">
				<div class="btnbox_f btnbox_f1 clearfix" style="display:none;" resource-mark="interView">
					<a href="javascript:void(0);" data-btn="userinfro1" style="width:200px;" 
						class="pubbtn bluebtn ico c4">权限隐藏测试-有</a>
				</div>
				</acl:acl>
				<div class="btnbox_f btnbox_f1 clearfix" style="display:none;" resource-mark="no">
					<a href="javascript:void(0);" data-btn="userinfro2" style="width:200px;" 
						class="pubbtn bluebtn ico c4">权限隐藏测试-无</a>
				</div>
				
				
				<div class="btnbox_f btnbox_f1 clearfix"  resource-mark="interView1">
					<a href="javascript:void(0);" data-btn="userinfro3" style="width:200px;" 
						class="pubbtn bluebtn ico c4">非法模拟HTTP请求-权限测试</a>
				</div>
				<div class="btnbox_f btnbox_f1 clearfix"  resource-mark="interView2">
					<a href="javascript:void(0);" data-btn="userinfro4" style="width:200px;" 
						class="pubbtn bluebtn ico c4">非法模拟HTTP请求-权限测试</a>
				</div>
				<script type="text/javascript">
				   /**
				    * @time 2016-07-29
				    */
				    if(isContainResourceByMark("interView")){
				    	$('div[resource-mark="interView"]').css("display","block");
				    }
				    if(isContainResourceByMark("no")){
				    	$('div[resource-mark="no"]').css("display","block");
				    }
				    
				    
				    $('a[data-btn="userinfro1" ]').click(function(){
						/**
						 * 模拟正常的请求
						 */
						$.ajax({
							url : "<%= path%>/galaxy/test/ajax",
							type : "POST",
						    data : "",
							dataType : "json",
							cache : false,
							contentType : "application/json; charset=UTF-8",
							beforeSend : function(xhr) {
								xhr.setRequestHeader("resourceMark", "interView");
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
								 layer.msg(data.result.message);
							}
						});
					});
					$('a[data-btn="userinfro3" ]').click(function(){
						/**
						 * 模拟不传入资源标识
						 */
						$.ajax({
							url : "<%= path%>/galaxy/test/ajax",
							type : "POST",
						    data : "",
							dataType : "json",
							cache : false,
							contentType : "application/json; charset=UTF-8",
							beforeSend : function(xhr) {
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
								 layer.msg(data.result.message);
							}
						});
					});
					$('a[data-btn="userinfro4" ]').click(function(){
						/**
						 * 模拟传入自己未拥有的资源标识
						 */
						$.ajax({
							url : "<%= path%>/galaxy/test/ajax",
							type : "POST",
						    data : "",
							dataType : "json",
							cache : false,
							contentType : "application/json; charset=UTF-8",
							beforeSend : function(xhr) {
								xhr.setRequestHeader("resource_mark", "interView_test");
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
								 layer.msg(data.result.message);
							}
						});
					});
				</script>
				
				
			</div>

			<!-- 搜索条件 -->
			<div class="min_document clearfix"  id="custom-toolbar">
				<div class="bottom searchall clearfix">
					<dl class="fmdl fml fmdll clearfix">
						<dt>账户状态： </dt>
						<dd>
							<label for=""><input type="radio"  value="" name="status" checked="checked">不限</label>
							<label for=""><input type="radio" id="disabled" value="1" name="status">已禁用</label>
						</dd>
					</dl>
					<dl class="fmdl fml fmdll clearfix">
						<dt>所属部门：</dt>
						<dd>  
							<select id='selectDept'  name="departmentId">
								<option value="">全部</option>
							</select>
						</dd>
					</dl>
					<dl class="fmdl fmdll clearfix">
						<dt></dt>
						<dd>
							<input type="text" class="txt" id="search_text"
								placeholder="请输入姓名或手机号" name="keyword" />
						</dd>
						<dd>
							<a href="javascript:void(0)" class="bluebtn ico cx" id="searchButton"
								action="querySearch">查询</a>
						</dd>
					</dl>
				</div>
			</div>
			<div class="tab-pane active" id="view">		
					<table id="data-table" data-url="galaxy/user/queryUserList"  data-page-list="[10, 20, 30]" 
					data-toolbar="#custom-toolbar">
						   <thead>
						    <tr>
					        <th data-field="nickName"  class="data-input">登陆名称</th>
					        <th data-field="status"  data-formatter="formatStatus" class="data-input">账户状态</th>
					        <th data-field="realName"  class="col-md-1 status ">真实姓名</th>
					        <th data-field="gender"  data-formatter="formatGender">性别</th>
					        <th data-field="departmentName"  data-formatter="formatDept">所属部门</th>
					        <th data-field="role"  class="col-md-2" >职能角色</th>
					        <th data-field="mobile"  class="col-md-2" >手机号</th>
					        <th data-field="telephone"  class="col-md-2" >办公分机</th>
					        <th  class="col-md-2" data-formatter="editor">操作</th>

   						 	</tr>	
   						 	</thead>
					</table>
	              </div>

		</div>




	</div>
	</div>
	<script src="js/layer/layer.js" type="text/javascript"></script>
	<script src="js/user.js" type="text/javascript"></script>
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
</body>
</html>

