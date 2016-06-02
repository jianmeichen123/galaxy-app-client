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
	<title>星河-通用平台|权限管理</title>
	<base href="<%=basePath%>">
	<!-- jsp文件头和头部 -->
	<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
	<link rel="stylesheet" href="<%=path %>/bootstrap-3.3.5/dist/css/bootstrap.min.css"  type="text/css">
	<link rel="stylesheet" href="<%=path %>/bootstrap-table-master/dist/bootstrap-table-customer.css"  type="text/css">
    <link rel="stylesheet" href="<%=path %>/css/common.css"  type="text/css">
    <link rel="stylesheet" href="<%=path %>/css/style.css"  type="text/css">
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<div class="pagebox clearfix">
	
		<!--左侧导航-->
		<jsp:include page="/WEB-INF/views/common/leftmenu.jsp" flush="true"></jsp:include>
		<!--左侧导航-->
		
		
		<!--右中部内容-->
		<div class="ritmin"  source="user">
			
			<h2>系统管理</h2>
			
			<ul class="nav nav-tabs">
			  <li role="presentation" class="active"><a href="javascript:void(0);">用户管理</a></li>
			  <li role="presentation"><a href="#">权限管理</a></li>
			  <li role="presentation"><a href="#">资源管理</a></li>
			</ul>
			<div class="options">
				<button type="button" class="btn btn-info"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 添加用户</button>
			</div>
			<div class="search-group">
				dddddddd
			</div>
			<div class="table-group" id="view">	
				<table id="data-table"
					data-url="<%=path %>/galaxy/user/queryUserList" 
					data-method="post" 
					data-height="550" 
					data-pagination ="true"
					data-side-pagination = "server"
					data-page-list="[10, 20, 30]">
					<thead>
						<tr>
							<th data-align="center" class="data-input" data-formatter="numberFormatter">序号</th>
							<th data-field="nickName" data-align="center" class="data-input">登陆名称</th>
				        	<th data-field="status" data-align="center" data-formatter="formatStatus" class="data-input">账户状态</th>
					        <th data-field="realName" data-align="center" class="col-md-1 status ">真实姓名</th>
					        <th data-field="gender" data-align="center" data-formatter="formatGender">性别</th>
					        <th data-field="departmentName" data-align="center" data-formatter="formatDept">所属部门</th>
					        <th data-field="role" data-align="center" class="col-md-2" >职能角色</th>
					        <th data-field="mobile" data-align="center" class="col-md-2" >手机号</th>
					        <th data-field="telephone" data-align="center" class="col-md-2" >办公分机</th>
				        	<th data-align="center" class="col-md-2" data-formatter="optionsFormatter">操作</th>
						</tr>	
					</thead>
				</table>	
            </div>
		</div>
	</div>
	<script src="<%=path %>/bootstrap-3.3.5/dist/js/bootstrap.min.js"></script>	
	<script src="<%=path %>/bootstrap-table-master/dist/bootstrap-table-customer.js"></script>
	<script src="<%=path %>/bootstrap-table-master/dist/locale/bootstrap-table-zh-CN-customer.js"></script>
	<script type="text/javascript">
		createMenus(31);
		$(function () {
			$("#data-table").bootstrapTable({
				showRefresh : false,
				queryParamsType: 'customer',
				sidePagination: 'server',
				method : 'post',
				sortName : "aaaa,bbb,ccc",
				pagination: true,
		        search: false
			});
		});
		function numberFormatter(value, row, index){
			return index+1;
		}
		function optionsFormatter(value, row, index){
			var id=row.id;
			var optHtml= "<a href='"+id+"' class='blue'>查看</a>";
			optHtml+="<a href='"+id+"' class='blue'>编辑</a>";
			optHtml+="<a href='"+id+"' class='blue'>删除</a>";
			return optHtml;
		}
		$("#querySearch").on("click",function(){
			var optionItem=$.trim($("#optionItem").val());
			var username=$.trim($("#username").val());
			var queryParams=[];
			queryParams.push({
				optionItem:optionItem,
				username:username
		    });
			$("#data-table").bootstrapTable('refresh',
					{  
				      queryParams:queryParams
					}
			);
		});
	</script>
</body>
</html>

