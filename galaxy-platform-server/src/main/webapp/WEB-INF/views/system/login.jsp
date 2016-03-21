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
	
	<script>
	var Sys = {};
        var ua = navigator.userAgent.toLowerCase();
        var s;
        (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
        (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
        (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
        (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
        (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
       var ie=Sys.ie;
        if (ie=='9.0'||ie=='8.0'||ie=='7.0'||ie=='6.0'){
            window.location.href="http://windows.microsoft.com/zh-cn/internet-explorer/download-ie"; 
        };
        if (Sys.opera){
            alert("为带来更好的用户体验，请使用谷歌、safari及IE10以上浏览器登陆！")
        }
    /*      if (Sys.chrome) document.write('Chrome: ' + Sys.chrome);
      if (Sys.firefox) document.write('Firefox: ' + Sys.firefox);
        if (Sys.chrome) document.write('Chrome: ' + Sys.chrome);
        if (Sys.opera) document.write('Opera: ' + Sys.opera);
        if (Sys.safari) document.write('Safari: ' + Sys.safari);
*/
</script>
</body>
</html>
