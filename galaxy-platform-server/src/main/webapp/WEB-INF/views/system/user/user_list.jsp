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
<base href="<%=basePath%>">

<!-- jsp文件头和头部 -->
<%@ include file="../../common/taglib.jsp"%>

</head>
<body>
	<div class="container-fluid" id="main-container">
		<div id="page-content" class="clearfix">
			<div class="top clearfix">
				<!--按钮-->
				<div class="btnbox_f btnbox_f1 clearfix">
					<a href="javascript:;" class="pubbtn bluebtn ico c4">添加人员</a>
				</div>
			</div>
			<div class="row-fluid">
				<!-- 搜索条件 -->
				<div class="min_document clearfix">
					<div class="bottom searchall clearfix">
						<dl class="fmdl fml fmdll clearfix">
							<dt>账户状态：</dt>
							<dd>
								<label for=""><input type="radio" name="status">不限</label>
								<label for=""><input type="radio" id="disabled" name="status">已禁用</label>
							</dd>
						</dl>
						<dl class="fmdl fml fmdll clearfix">
							<dt>所属部门：</dt>
							<dd>
								<select id="selectId">
									<option>全部</option>
								</select>
							</dd>
						</dl>
						<dl class="fmdl fmdll clearfix">
							<dt></dt>
							<dd>
								<input type="text" id="search_text" class="txt"
									placeholder="请输入姓名或手机号" />
							</dd>
							<dd>
								<a href="search();" class="bluebtn ico cx">查询</a>
							</dd>
						</dl>
					</div>
				</div>


				<table id="table_report"
					class="table table-striped table-bordered table-hover">
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

						<!-- 开始循环 -->
						<c:when test="${not empty content}">
							<c:forEach items="${content}" var="user" varStatus="vs">
								<tr>

									<td class='center' style="width: 30px;">${vs.index+1}</td>
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
											<a href="disableUser('${user.id }','${user.status }'};" class="blue">禁用</a>
											</c:if>
											<c:if test="${user.status == 1 }">
											<a href="disableUser('${user.id }','${user.status }'};" class="blue">启用</a>
											</c:if>
											<a href="resetPwd('${user.id }'};" class="blue">重置密码</a>
												

										</div>
									</td>
								</tr>

							</c:forEach>
						</c:when>
					</tbody>
				</table>

			</div>

		</div>
		<!--/#page-content-->
	</div>

 <script src="../user.js" type="text/javascript"></script>

</body>
</html>

