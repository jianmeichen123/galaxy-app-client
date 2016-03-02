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
					<a href="javascript:;" onclick="toadd()" class="pubbtn bluebtn ico c4">添加人员</a>
				</div>
			</div>

			<!-- 搜索条件 -->
			<div class="min_document clearfix">
				<div class="bottom searchall clearfix">
					<dl class="fmdl fml fmdll clearfix">
						<dt>账户状态：</dt>
						<dd>
							<label for=""><input type="radio" name="status">不限</label>
							<label for=""><input type="radio" id="disabled" value="1" name="status">已禁用</label>
						</dd>
					</dl>
					<dl class="fmdl fml fmdll clearfix">
						<dt>所属部门：</dt>
						<dd>
							<select id='selectDept'>
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
                      <!--  <tr>
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
						</tr> -->
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


<div class="userinfrotc" id="userInfo" style="display:none">
  
  <div class="userinfro">
  <dl>
    <dt>真实姓名：</dt>
    <dd><input type="text" id="realName" name="user.realName" value=""/></dd>
  </dl>
  <dl>
    <dt>工号：</dt>
   <dd><input type="text" id="employNo" name="user.employNo" value=""/></dd>
  </dl>
  <dl>
    <dt>性别：</dt>
    <dd>
      <label for=""><input type="radio" name="user.gender">男</label>
      <label for=""><input type="radio" name="user.gender">女</label>
    </dd>
  </dl>
  <dl>
    <dt>出生日期：</dt>
    <dd>1989-01-01</dd>
  </dl>
  <dl>
    <dt>公司邮箱：</dt>
     <dd><input type="text" id="email" name="user.email" value=""/></dd>
  </dl>
  <dl>
    <dt>登录名称：</dt>
     <dd><input type="text" id="nickName" name="user.nickName" value=""/></dd>
  </dl>
  <dl>
    <dt>手机号：</dt>
     <dd><input type="text" id="mobile" name="user.mobile" value=""/></dd>
  </dl>
  <dl>
    <dt>办公分机：</dt>
     <dd><input type="text" id="telephone" name="user.telephone" value=""/></dd>
  </dl>
  <dl>
    <dt>常用收件地址：</dt>
    <dd><span> <dd><input type="text" id="address" name="user.address" value=""/></dd></span><a href="#" class="flr">使用公司地址</a></dd>
  </dl>
  <dl class="role">
    <dt>职能角色：</dt>
    <dd>
      <div class="">
        <label for=""><input type="radio" name="role">投资线</label>
        <label for=""><input type="radio" name="role">总部职能部门</label>
      </div>
      <div class="">
        <label for=""><input type="radio" value='1' name="role">董事长</label>
        <label for=""><input type="radio" value='3' name="role">投资线合伙人 </label>
        <label for=""><input type="radio" value='2' name="role">CEO </label>
        <label for=""><input type="radio" value='4' name="role">投资经理 </label>
        <label for=""><input type="radio" value='7' name="role">人事总监 </label>
        <label for=""><input type="radio" value='8' name="role">人事经理  </label>
        <label for=""><input type="radio" value='10' name="role">法务经理 </label>
        <label for=""><input type="radio" value='12' name="role">财务经理 </label>
      </div>
    </dd>
    <dd>
    </dd>
  </dl>
  </div>
    <div class="btnbox">
      <a href="javascript:;" class="pubbtn bluebtn">保存</a><a href="javascript:;" class="pubbtn fffbtn"data-close="close">取消</a>
    </div>
</div>
	</div>

 <script src="js/user.js" type="text/javascript"></script> 

	

</body>
</html>

