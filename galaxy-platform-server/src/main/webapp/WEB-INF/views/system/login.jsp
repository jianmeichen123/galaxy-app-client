<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>繁星</title>
<base href="<%=basePath%>">
<link rel="shortcut icon" href="<%=basePath %>/img/favicon.ico" />
<!-- jsp文件头和头部 -->
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<link href="css/axure.css" type="text/css" rel="stylesheet" />
<!--[if lt IE 9]><link href="css/lfie8.css" type="text/css" rel="stylesheet"/><![endif]-->
</head>
<body class="login">
	<div class="loginpage">
		<div class="top">
			<p class="rit ico">如遇问题请联系010-59065160</p>
			<a href="javascript:;" class="logo null">星河互联</a>
		</div>
		<div class="min clearfix">
		 	<div class="pic"></div>
			<div class="loginbox">
				<h2 class="ico null">繁星</h2>
				<form id="defaultForm" method="post" class="form-horizontal" onkeydown="keylogin();">
					<input id="email" name="email" type="text"
						class="txt ico uname" placeholder="用户名" /> <input id="password"
						name="password" type="password" class="txt ico uword"
						placeholder="密码" /> <!-- <label><input type="checkbox">自动登录</label> -->
					<a href="javascript:;" class="login null ico" onclick="login();">登录</a>
					  <p class="tips ico">建议使用IE10以上浏览器<a href="http://windows.microsoft.com/zh-cn/internet-explorer/download-ie" class="blue">版本过低，点击下载！</a>
            		</p>
				</form>
			</div>
		</div>
		 <div class="login_bg"></div>
		<div class="btm">
			<a href="http://www.galaxyinternet.com/" target="_blank">www.galaxyinternet.com</a>
		</div>
	</div>
	<script src="js/axure.js" type="text/javascript"></script>
	<script src="js/axure_ext.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/base64.js"></script>
	<script type="text/javascript" src="js/layer/layer.js"></script>
	<script type="text/javascript" src="js/jquery.cookie.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
	
</body>
</html>
