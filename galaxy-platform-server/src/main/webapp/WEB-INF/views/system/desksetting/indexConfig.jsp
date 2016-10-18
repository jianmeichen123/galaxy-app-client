<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<base href="<%=basePath%>"/>
<head>
<meta charset="utf-8">
<title>繁星</title>

<link href="css/axure.css" type="text/css" rel="stylesheet"/>
<script src="js/indexConfig/indexConfig.js" type="text/javascript"></script>
<!--[if lt IE 9]><link href="css/lfie8.css" type="text/css" rel="stylesheet"/><![endif]-->
</head>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<body>
 <%@ include file="/WEB-INF/views/common/header.jsp"%>   
 <!-- 头部右边 -->
<div class="pagebox clearfix">
	<!--左侧导航-->
	<jsp:include page="/WEB-INF/views/common/leftmenu.jsp" flush="true"></jsp:include>	
    <!--模块配置区域-->
 	<div class="ritmin">
    	<div class="new_tit_b">
        	<span class="new_color size18">桌面配置</span>
          <button class="pubbtn bluebtn fr save">保存</button>
      </div>
      <!-- 添加内容区域 -->
      <div class="equipmentBox"></div>
      <button class="bluebtn equipmentBoxBtn" data-btn="addBlock">添加模块</button>
                 
  </div>
<!--模块配置区域结束-->


</div>

<!-- 模块配置区js部分 -->
<script>
$(function(){
  var i=0;
  function addEblockRow(i){
     var eblockRow='<div class="eblockRow clearfix">'
          +'<div class="eblock fl">'
            +'<div class="addEblockCon addEblockCon_'+i+'">'
              +'<button class="addCircleBtn" href="<%=path%>/galaxy/indexConfig/toAddCon" data-btn="addEblockCon_'+i+'" data-name="选择菜单">+</button>'
            +'</div>' 
            +'<div class="deleteEblockCon deleteEblockCon_'+i+'">'
              +'<span></span>'
              +'<button class="deleteCircleBtn" data-btn="delete_'+i+'">-</button>'
            +'</div>'
          +'</div>'
          +'<div class="eblock fl">'
            +'<div class="addEblockCon addEblockCon_'+(i+1)+'">'
              +'<button class="addCircleBtn" href="<%=path%>/galaxy/indexConfig/toAddCon" data-btn="addEblockCon_'+(i+1)+'" data-name="选择菜单">+</button>'
            +'</div>'
            +'<div class="deleteEblockCon deleteEblockCon_'+(i+1)+'">'
              +'<span></span>'
              +'<button class="deleteCircleBtn" data-btn="delete_'+i+'">-</button>'
            +'</div>'
          +'</div>'
        +'</div>';
  $(".equipmentBox").append(eblockRow);
  };
  addEblockRow(0);  //默认显示一行

  addEblockCon();
  function addEblockCon(){  //点击弹出层
    $(".addEblockCon button").on("click",function(){ 
      var len=$(this).attr("data-btn").length;
      var last=$(this).attr("data-btn").substring(len-1,len);
      console.log(last);
      var $self = $(this);
      var _url = $self.attr("href");
      var _name= $self.attr("data-name");
      $.getHtml({
        url:_url,//模版请求地址
        data:"",//传递参数
        okback:function(){
          $("#popup_name").html(_name);
          queryAvailableConfig();
          $("#ok").click(function(){
               var inputVal=$(".addEblockContc label input:checked").parent("label").text();
               if(inputVal==""){
                alert("请选择菜单！")
               }else{
            	save_result();
                $(".pop , #popbg").remove();
                $(".addEblockCon_"+last+"").hide();
                $(".deleteEblockCon_"+last+"").show();
                $(".deleteEblockCon_"+last+" span").text(inputVal);
               }
          });
          $(".deleteEblockCon_"+last+" button").click(function(){
            $(".addEblockCon_"+last+"").show();
            $(".deleteEblockCon_"+last+"").hide();
            $(".deleteEblockCon_"+last+" span").text("");
          })
        }//模版反回成功执行 
      });
      return false;
  });
  }

  $("button[data-btn='addBlock']").click(function(){   
    i+=2;
    addEblockRow(i);  //点击一次添加一行
    addEblockCon();  //点击弹出层
    disposedWidth();
  });
  //首页获取ritmin的宽度
    disposedWidth();
    function disposedWidth(){
      var w_win=$(window).width();
        w_lft=$(".lft").width();
        w_ritmin=w_win-w_lft;
        $(".pagebox .ritmin").css("width",w_ritmin);
        $(".eblock").css("width",w_ritmin/2-20);
        $(".pagebox .ritmin").css("margin","70px 0 0 9.375%");
    }
})
  
</script>

</html>
