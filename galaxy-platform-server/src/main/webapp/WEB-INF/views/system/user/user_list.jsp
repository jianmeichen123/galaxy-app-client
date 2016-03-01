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
<link href="css/axure.css" type="text/css" rel="stylesheet" />
</head>
<body>
	div class="header clearfix">

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
					<a href="javascript:;" class="pubbtn bluebtn ico c4">添加人员</a>
				</div>
			</div>

			<!-- 搜索条件 -->
			<div class="min_document clearfix">
				<div class="bottom searchall clearfix">
					<dl class="fmdl fml fmdll clearfix">
						<dt>账户状态：</dt>
						<dd>
							<label for=""><input type="radio" name="status">不限</label>
							<label for=""><input type="radio" id="disabled"
								name="status">已禁用</label>
						</dd>
					</dl>
					<dl class="fmdl fml fmdll clearfix">
						<dt>所属部门：</dt>
						<dd>
							<select>
								<option>全部</option>
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



			<!--表格内容-->
			<table width="100%" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<th></th>
						<th class="width_gd">登陆名称</th>
						<th>账户状态</th>
						<th>真实姓名</th>
						<th>性别</th>
						<th>所属部门</th>
						<th>职能角色</th>
						<th>手机号</th>
						<th>办公分机</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${content}" var="user" varStatus="vs">
						<tr>

							<td><input type="radio" name="document" /></td>
							<td>${user.nickName }</td>
							<td><c:if test="${user.status == 0 }"> 正常</c:if> <c:if
									test="${user.status == 1 }"> 已禁用</c:if></td>
							<td>${user.realName }</td>
							<td><c:if test="${user.gender == 0 }"> 男</c:if> <c:if
									test="${user.gender == 1 }"> 女</c:if></td>
							<td>${user.departmentId}</td>
							<td>${user.roleId}</td>
							<td>${user.mobile}</td>
							<td>${user.telephone}</td>
							<td style="width: 60px;">
								<div class=''>
									<c:if test="${user.status == 0 }">
										<a href="disableUser('${user.id }','${user.status }'};"
											class="blue">禁用</a>
									</c:if>
									<c:if test="${user.status == 1 }">
										<a href="disableUser('${user.id }','${user.status }'};"
											class="blue">启用</a>
									</c:if>
									<a href="resetPwd('${user.id }'};" class="blue">重置密码</a>


								</div>
							</td>
						</tr>
						
					</c:forEach>
                       <tr>
							<td><input type="radio" name="document" checked /></td>
							<td>xiaoerzh</td>
							<td>正常</td>
							<td>张三</td>
							<td>男</td>
							<td>旅游</td>
							<td>投资经理</td>
							<td>186021312</td>
							<td>1059062211</td>
							<td><a href="#" class="blue">禁用</a><a href="#" class="blue">重置密码</a></td>
						</tr>
				</tbody>
			</table>

			<!--分页-->
			<div class="pagright clearfix">
				<ul class="paging clearfix">
					<li>每页<input type="text" class="txt" value="20" />条/共<span>9</span>条记录
					</li>
					<li class="margin">共1页</li>
					<li><a href="javascript:;">|&lt;</a></li>
					<li><a href="javascript:;">&lt;</a></li>
					<li><a href="javascript:;">&gt;</a></li>
					<li><a href="javascript:;">&gt;|</a></li>
					<li class="jump clearfix">第<input type="text" class="txt"
						value="1" />页 <input type="button" class="btn margin" value="GO">
					</li>
				</ul>
			</div>
		</div>

	</div>
	<!-- 
 <script src="js/user.js" type="text/javascript"></script> -->

	<script type="text/javascript">
//用户相关的js
//检索
function searchForm() {
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
		"departmentId" : departId
	};
	$.ajax({
		url : platformUrl.queryUserList,
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
</script>
</body>
</html>

