<%@ page language="java" pageEncoding="UTF-8"%>
<%-- <div class="erwm">
    <img src="<%=path %>/img/erwm.gif" alt="" style="margin:0 auto;">
    <span data-btn="close_erwm">关闭</span> 
    <a href="<%=path %>/html/installReadme.html?realname=1" target="_blank">查看安装说明</a>
</div> --%>
<link href="<%=path %>/css/more1280.css" type="text/css" rel="stylesheet" id="mainCss"/>
<%-- <div class="erwms erwm">
	<p>星河投APP</p>
   	<img src="<%=path %>/img/ewms.gif" alt="">
</div>
<div class="erwmb erwm">
   	<img src="<%=path %>/img/ewmb.gif" alt="">
   	<p>星河投APP客户端</p>
   	<p><a href="<%=path %>/html/installReadme.html?realname=1" target="_blank">查看安装说明</a></p>
</div> --%>
<div class="header clearfix">
	<a href="javascript:;" class="logo null">星河投</a>
    <!--头部中间-->
    <div class="min clearfix">
        <!--用户信息-->
        <div class="usermsg clearfix">
<!--             <span class="light_blue">当前您有：</span>
            <a href="javascript:forwardWithHeader(platformUrl.soptaskshouye)" class="work">待办任务<em class="totalUrgent"></em></a>
            <a href="javascript:forwardWithHeader(platformUrl.message)" class="work">消息提醒<em action="remind">4</em></a> -->
        </div>    	<!--当日信息-->
    	
    </div>
    <!-- 头部右边 -->
      <div class="usermsg rit clearfix">
      <div class="erwm fl">
    		<span class="erwmfont">APP客户端</span>
    		<div class="erwm_show">
    		<span class="triangle">三角形</span>
    			<ul class="clearfix">
    				<li class="fl">
    					<img alt="" src="<%=path %>/img/Android.png">
    					<span>Android</span>
    				</li>
    				<li class="fr">
    					<img alt="" src="<%=path %>/img/ios.png">
    					<span>iOS</span>
    				</li>    				
    			</ul>
    			<p>
    				<a href="<%=path %>/html/installReadme.html?realname=1" target="_blank">查看iOS安装说明></a>
    			</p>
    		</div>
    	</div>
      <div class="man_info fl">
        <span class="ico name"><span class="avator"></span><%=realName%></span>
        <ul>
          <li><a href="/sop/html/change_password.html" id="hid" data-btn="change_password">修改密码</a></li>
           <a href="javascript:;" onclick="logout()" class="loginout">退出</a>
        </ul>
      </div>
    </div>
     </div>
<script src="<%=path %>/js/login.js"></script>
 <script type = "text/javascript">
		 $(".man_info .name").hover(function(){
	      $(".man_info ul").show();
	    });
	    $(".man_info ul").closeDom();
	    var man_info_width=$(".man_info").width();
	    $(".man_info ul").css("left",(man_info_width-140)/2);
	    $(function(){
	      //首页获取ritmin的宽度
	      disposedWidth();
	      function disposedWidth(){
	    	  var w_win=$(window).width();
	          w_lft=$(".lft").width();
	          w_ritmin=w_win-w_lft;
	          $(".pagebox .ritmin").css("width",w_ritmin-20);
	          $(".eblock").css("width",w_ritmin/2-20);
	          $(".pagebox .ritmin").css("margin","60px 0 0 9.375%");
	          $(".pagebox .ritmin").css("margin-left",w_lft);
	          $(".pagebox .ritmin-index").css("width",w_ritmin-10);
	        }
	  //浏览器小于1280的时候左侧导航
	    var w_win=$(window).width();
	    if(w_win<=1280){   //浏览器屏幕等于1280，默认加载样式
	         $("#mainCss").attr("href","<%=path%>/css/less1280.css");
	         $(".pagebox .lft").css("width","60px");
	         disposedWidth();
	          w_lft=$(".lft").width();        
	      }else{
	        $(".pagebox .lft").css("width","9.375%");
	         disposedWidth();
	      }
	  //浏览器窗口该变，自适应
	  $(window).resize(function(){
	  	var w_win=$(window).width();
	      disposedWidth();
	      if(w_win<=1280){
	          $("#mainCss").attr("href","<%=path%>/css/less1280.css");
	          $(".pagebox .lft").css("width","60px");
	       }else{
	         $("#mainCss").attr("href","<%=path%>/css/more1280.css");
	          $(".pagebox .lft").css("width","9.375%");

	       }
	    })
	        /*二维码*/
		  $(".erwm").hover(function(){
				 $(".erwm_show").toggle();
			 })
	    
	    })
</script>

