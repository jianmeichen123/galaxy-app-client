<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>星河投</title>
<base href="<%=basePath%>">
	<link href="css/axure.css" type="text/css" rel="stylesheet"/>
	<!--[if lt IE 9]><link href="css/lfie8.css" type="text/css" rel="stylesheet"/><![endif]-->
	<!-- jsp文件头和头部 -->
	<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
	<link rel="stylesheet" href="bootstrap/bootstrap-table/bootstrap-table.css"  type="text/css">
	<link rel="stylesheet" href="css/pupup.css"  type="text/css">
	
	<script src="bootstrap/bootstrap-table/bootstrap-table-xhhl.js"></script>
	<script src="bootstrap/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
    <script src="js/init.js"></script>
    <script src="js/pushMsg.js"></script>
    <script src="js/layer/layer.js" type="text/javascript"></script>

</head>

<body>

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<div class="pagebox clearfix">
	<!--左侧导航-->
		<jsp:include page="/WEB-INF/views/common/leftmenu.jsp" flush="true"></jsp:include>
		<!--左侧导航-->
    <!--右中部内容-->
 	<div class="ritmin">
    	<h2>自定义PUSH消息</h2>
    	
    	<div style=' width:100px; overflow:hidden; margin-bottom:15px;'><a class="pbtn bluebtn h_bluebtn" href="html/popup_pushMsg.html"  data-btn="popup_pushMsg" data-name='自定义PUSH消息' >发送消息</a>
        </div>
        <!--表格内容-->
		<table width="100%" cellspacing="0" cellpadding="0" 
						 id="data-table" data-url="<%=request.getContextPath() %>/galaxy/pushMsg/queryMsgList"  data-page-list="[10, 20, 30]" data-show-refresh="true" 
				         data-toolbar="#custom-toolbar" >
			<thead>
		    <tr>
		        <th data-field="noticeTime" data-align="left"  data-formatter="dateformatter"  class="data-input">发送时间</th>
		        <th data-field="content" data-align="left"   class="data-input1">消息内容</th>
		        <th data-field="userStr" data-align="left"  class="col-md-1 status ">通知对象</th>
			 </tr>
			</thead>
		</table>
	

    </div>
</div>
<style>
.content_length{ width:500px; overflow:hidden; height:28px; line-height:28px;}
.data-input{ width:200px; margin-left:300px;}
.data-input1{ width:400px;}
</style>

</body>
<script>
/* $(function(){
	createMenus(9);
})  */
function dateformatter(value,row){
	var result = formatDate(value) 
	return result;
}
function formatDate(date, format) {   
    if (!date) date = new Date();   
    if (!format) format = "yyyy-MM-dd";   
    switch(typeof date) {   
        case "string":   
            date = new Date(date.replace(/-/, "/"));   
            break;   
        case "number":   
            date = new Date(date);   
            break;   
    }    
    if (!date instanceof Date) return;   
    var dict = {   
        "yyyy": date.getFullYear(),   
        "M": date.getMonth() + 1,   
        "d": date.getDate(),   
        "H": date.getHours(),   
        "m": date.getMinutes(),   
        "s": date.getSeconds(),   
        "MM": ("" + (date.getMonth() + 101)).substr(1),   
        "dd": ("" + (date.getDate() + 100)).substr(1),   
        "HH": ("" + (date.getHours() + 100)).substr(1),   
        "mm": ("" + (date.getMinutes() + 100)).substr(1),   
        "ss": ("" + (date.getSeconds() + 100)).substr(1)   
    };       
    return format.replace(/(yyyy|MM?|dd?|HH?|ss?|mm?)/g, function() {   
        return dict[arguments[0]];   
    });                   
}
</script>

</html>

