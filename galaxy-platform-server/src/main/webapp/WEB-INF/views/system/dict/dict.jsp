<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>繁星</title>
<link href="css/axure.css" type="text/css" rel="stylesheet"/>
<!--[if lt IE 9]><link href="css/lfie8.css" type="text/css" rel="stylesheet"/><![endif]-->
</head>

<body>
<div class="header clearfix">

  <a href="javascript:;" class="logo null">繁星</a>
    <!--头部中间-->
    <div class="min clearfix">
        <!--用户信息-->
        <div class="usermsg clearfix">
            <span class="light_blue">当前您有：</span>
            <a href="javascript:;" class="work">待办任务<em>23</em></a>
            <a href="javascript:;" class="work">紧急任务<em class="bubble">5</em></a>
            <a href="javascript:;" class="work">消息提醒<em>4</em></a> 
        </div>      <!--当日信息-->
      <div class="todaymsg clearfix">
          <span>北京</span>
            <span class="weather1">小雨</span>
            <span>7/-13度；</span>
            <span>今日限行尾号为 5、0，明日为不限行！</span>            
        </div>
    </div>
    <!-- 头部右边 -->
    <div class="usermsg rit clearfix">
        <span class="ico name">早上好，闫皓</span>
        <b class="line null">分割线</b>
        <a href="javascript:;" class="loginout">退出</a>
    </div>
</div>
<div class="pagebox clearfix">
	<!--左侧导航-->
	<ul class="lft">
    	<li>
        	<a href="javascript:;">用户管理</a>
        </li>
        <li class="on">
        	<a href="javascript:;">数据字典</a>
        </li>
    </ul>
    <!--右中部内容-->
 	<div class="ritmin">
    	<h2>数据字典</h2>
        <div class="dictionary clearfix">
        	<ul class="clearfix" id="dict_parent">
            </ul>
            <dl class="tabbox" data-tab="con" >
            	<dt class="clearfix" id="dcit_tab">
                	<div class="tool clearfix" >
                    	<a href="javascript:;" class="ico i1 null" action="insert_row">加号</a>
                        <a href="javascript:;" class="ico i2 null">上</a>
                        <a href="javascript:;" class="ico i3 null">下</a>
                        <a href="javascript:;" class="ico i4 null">×</a>
                        <b class="ico i5 null">|</b>
                        <a href="javascript:;" class="ico i4 null">×</a>
                        <a href="javascript:;" class="btn" action="batchInsert">添加多个</a>
                    </div>
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
<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="js/axure.js" type="text/javascript"></script>
<script src="js/axure_ext.js" type="text/javascript"></script>
<script src="js/dict.js" type="text/javascript"></script>
</body>
</html>
