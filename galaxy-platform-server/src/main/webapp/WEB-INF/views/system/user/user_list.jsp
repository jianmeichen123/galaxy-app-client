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
<base href="<%=basePath%>">

<!-- jsp文件头和头部 -->
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
    <!-- bootstrap-table -->
	<link rel="stylesheet" href="http://static.workflow.com/bootstrap-table/bootstrap-table.css"  type="text/css">
	<!-- datetimepicker -->
	<link rel="stylesheet" href="http://static.workflow.com/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css"  type="text/css">
		
<link href="css/axure.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div class="header clearfix">

	<a href="javascript:;" class="logo null">繁星</a>
	<!--头部中间-->
	<div class="min clearfix">
		<!--用户信息-->
		<div class="usermsg clearfix">
			<span class="light_blue">当前您有：</span> <a href="javascript:;"
				class="work">待办任务<em>23</em></a> <a href="javascript:;" class="work">紧急任务<em
				class="bubble">5</em></a> <a href="javascript:;" class="work">消息提醒<em>4</em></a>
		</div>
		<!--当日信息-->
		<div class="todaymsg clearfix">
			<span>北京</span> <span class="weather1">小雨</span> <span>7/-13度；</span>
			<span>今日限行尾号为 5、0，明日为不限行！</span>
		</div>
	</div>
	<!-- 头部右边 -->
	<div class="usermsg rit clearfix">
		<span class="ico name">早上好，闫皓</span> <b class="line null">分割线</b> <a
			href="javascript:;" class="loginout">退出</a>
	</div>
	</div>
	<div class="pagebox clearfix">
		<!--左侧导航-->
		<ul class="lft">
			<li class="on"><a href="javascript:;">用户管理</a></li>
			<li><a href="javascript:;">数据字典</a></li>
		</ul>
		<!--右中部内容-->
		<div class="ritmin">
			<h2>用户管理</h2>
			<!--页眉-->
			<div class="top clearfix">
				<!--按钮-->
				<div class="btnbox_f btnbox_f1 clearfix">
					<a href="html/userinfrotc.html" data-btn="userinfro"
						class="pubbtn bluebtn ico c4">添加人员</a>
				</div>
			</div>

			<!-- 搜索条件 -->
			<div class="min_document clearfix">
				<div class="bottom searchall clearfix">
					<dl class="fmdl fml fmdll clearfix">
						<dt>账户状态：</dt>
						<dd>
							<label for=""><input type="radio" name="status">不限</label>
							<label for=""><input type="radio" id="disabled" value="1"
								name="status">已禁用</label>
						</dd>
					</dl>
					<dl class="fmdl fml fmdll clearfix">
						<dt>所属部门：</dt>
						<dd>
							<select id='selectDept'>
								<option value="">全部</option>
							</select>
						</dd>
					</dl>
					<dl class="fmdl fmdll clearfix">
						<dt></dt>
						<dd>
							<input type="text" class="txt" id="search_text"
								placeholder="请输入姓名或手机号" />
						</dd>
						<dd>
							<a href="javascript:void(0)" class="bluebtn ico cx"
								onclick="searchForm()">查询</a>
						</dd>
					</dl>
				</div>
			</div>
			<div class="tab-pane active" id="view">		
					<div id="custom-toolbar">
					    <div class="form-inline" role="form">
					        <div class="form-group">
					            <div class="input-group">
					                <input class="form-control" type="text" placeholder="名称" name="nickName">
					            </div>
					        </div>
					        <div class="form-group">
					            <div class="input-group">
					                <input class="form-control" type="date" placeholder="创建时间(开始)" name="createTimeStart">
					            </div>
					        	<div class="input-group">
					                <input class="form-control" type="date" placeholder="创建时间(结束)" name="createTimeEnd">
					            </div>
					        </div>
					        <button type="submit" class="btn btn-default" name="querySearch">搜索</button>
					    </div>
					</div>
					<table id="data-table" data-url="galaxy/user/queryUserList" data-height="555" data-method="post" data-show-refresh="true" 
					data-side-pagination="server" data-pagination="true" data-page-list="[4, 20, 50]" data-search="false">
						   <thead>
						    <tr>
					        <th data-field="nickName" data-align="center" class="data-input">登陆名称</th>
					        <th data-field="status" data-align="center" class="data-input">账户状态<</th>
					        <th data-field="realName" data-align="center" class="col-md-1 status ">真实姓名</th>
					        <th data-field="gender" data-align="center" >性别</th>
					        <th data-field="departmentName" data-align="center" >所属部门</th>
					        <th data-field="role" data-align="center" class="col-md-2" >职能角色</th>
					        <th data-field="mobile" data-align="center" class="col-md-2" >手机号</th>
					        <th data-field="telephone" data-align="center" class="col-md-2" >办公分机</th>
					        <th data-align="center" class="col-md-2" data-formatter="editor">操作</th>

   						 	</tr>	
   						 	</thead>
					</table>
	              </div>

		</div>




	</div>
	</div>
	<script src="js/layer/layer.js" type="text/javascript"></script>
	<script src="js/user.js" type="text/javascript"></script>
	<script src="js/bootstrap3-typeahead.js"></script>
	<script src="js/bootstrap-table.js"></script>
	<script src="http://static.workflow.com/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
	<!-- datetimepicker -->
	<script src="http://static.workflow.com/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script src="http://static.workflow.com/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

	<script src="js/init.js"></script>	

</body>
</html>

