<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html>
<base href="<%=basePath%>">
<head>
<meta charset="utf-8">
<title>繁星</title>
<!-- jsp文件头和头部 -->
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<link href="css/axure.css" type="text/css" rel="stylesheet"/>
<!--[if lt IE 9]><link href="css/lfie8.css" type="text/css" rel="stylesheet"/><![endif]-->
</head>

<body>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<div class="pagebox clearfix">
	<!--左侧导航-->
	<jsp:include page="/WEB-INF/views/common/leftmenu.jsp" flush="true"></jsp:include>
	<!-- -左侧导航 -->
 	<div class="ritmin" source="dict">
    	<h2>数据字典</h2>
        <div class="dictionary clearfix">
        	<ul class="clearfix" id="dict_parent">
            </ul>
            <dl class="tabbox" data-tab="con" >
            	<dt class="clearfix" id="dcit_tab">
                	<div class="tool clearfix" >
                    	<a href="javascript:;" class="ico i1 null" action="insert_row">加号</a>
 <!--                        <a href="javascript:;" class="ico i2 null">上</a>
                        <a href="javascript:;" class="ico i3 null">下</a>
                        <a href="javascript:;" class="ico i4 null">×</a>
                        <b class="ico i5 null">|</b>
                        <a href="javascript:;" class="ico i4 null">×</a>
                        <a href="javascript:;" class="btn" action="batchInsert">添加多个</a>
  -->                   </div>
                    <h2>档案类型</h2>
                </dt>
				<dd>
                	<div class="scroll">
                    <div class="btnbox">
                        <a href="javascript:;" class="pubbtn bluebtn" action="save">保存</a>
                        <a href="javascript:;" class="pubbtn fffbtn" action="cancel">取消</a>
                    </div>

                        <table width="100%" cellspacing="0"  cellpadding="0" id="dict_son">
                         <thead>
                              <tr>
                                  <th>类型名称</th>
                                  <th>备注</th>
                              </tr>
                          </thead>
                          <tbody>
                          </tbody>
                        </table>
                               
                    </div>
             </dd>
            </dl>
        </div>
    </div>
 
</div>
<script src="js/layer/layer.js" type="text/javascript"></script>
<script src="js/platformUrl.js" type="text/javascript"></script>
<script src="js/dict.js" type="text/javascript"></script>

</body>
</html>
