<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>繁星</title>
<link href="<%=path %>/css/axure.css" type="text/css" rel="stylesheet">
<!-- jsp文件头和头部 -->
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>


</head>

<body>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<div class="pagebox clearfix">
	
<!--左侧导航-->
		<jsp:include page="/WEB-INF/views/common/leftmenu.jsp" flush="true"></jsp:include>
<!--左侧导航-->
		<!--右中部内容-->
 	<div class="ritmin prj_all"  source="role">
    	<div class="new_tit_b">
        	 <span class="new_color size18">角色权限</span>
        </div>
        
        
        <div class="top clearfix">
                <!--按钮-->
                <div class="btnbox_f btnbox_f1 clearfix">
                    <a  href="tchtml/role_add.html" data-btn="role_add" data-name='添加角色' class="pubbtn bluebtn ico c4 add_prj">添加角色</a>
                </div>
            </div>
    	<div class='role_center'>
        	<table width="100%" cellspacing="0">
        	<tr>
                <th>角色名称</th>
                <th>角色描述</th>
                <th>用户列表</th>
                <th>操作</th>
            </tr>
            <tr>
                <td>角色名称</td>
                <td>角色描述</td>
                <td>用户列表</td>
                <td><a href="javascript:; " class="blue" style="margin-right:10px;">编辑</a><a href="javascript:; " class="blue">删除</a></td>
            </tr>
            <tr>
                <td>角色名称</td>
                <td>角色描述</td>
                <td>用户列表</td>
                <td><a href="javascript:; " class="blue" style="margin-right:10px;">编辑</a><a href="javascript:;" class='delete'>删除</a></td>
            </tr>
            <tr>
                <td>角色名称</td>
                <td>角色描述</td>
                <td>用户列表</td>
                <td><a href="javascript:; " class="blue" style="margin-right:10px;">编辑</a><a href="javascript:; " class="blue">删除</a></td>
            </tr>
        </table>
        <!--分页-->
          <div class="pagright clearfix">
              <ul class="paging clearfix">
                  <li>每页<input type="text" class="txt" value="20"/>条/共<span>9</span>条记录</li>
                  <li class="margin">共1页</li>
                  <li><a href="javascript:;">|&lt;</a></li>
                  <li><a href="javascript:;">&lt;</a></li>
                  <li><a href="javascript:;">&gt;</a></li>
                  <li><a href="javascript:;">&gt;|</a></li>
                  <li class="jump clearfix">
                      第<input type="text" class="txt" value="1"/>页
                      <input type="button" class="btn margin" value="GO">
                  </li>
              </ul>
          </div>
        </div>
        

          

    </div>
</div>
<script src="<%=path %>/js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="<%=path %>/js/axure.js" type="text/javascript"></script>
<script src="<%=path %>/js/axure_tc.js" type="text/javascript"></script>
</body>
</html>