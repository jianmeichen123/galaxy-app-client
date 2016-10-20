<%@ page language="java" pageEncoding="UTF-8"%>
<%-- <div class="erwm">
    <img src="<%=path %>/img/erwm.gif" alt="" style="margin:0 auto;">
    <span data-btn="close_erwm">关闭</span> 
    <a href="<%=path %>/html/installReadme.html?realname=1" target="_blank">查看安装说明</a>
</div> --%>
<div class="erwms erwm">
	<p>繁星APP</p>
   	<img src="<%=path %>/img/ewms.gif" alt="">
</div>
<div class="erwmb erwm">
   	<img src="<%=path %>/img/ewmb.gif" alt="">
   	<p>繁星APP客户端</p>
   	<p><a href="<%=path %>/html/installReadme.html?realname=1" target="_blank">查看安装说明</a></p>
</div>
<div class="header clearfix">
	<a href="javascript:;" class="logo null">繁星</a>
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

	/*需要时二维码放大*/
		$(".erwms").on("mouseover",function(){
			 $(this).hide();
		     $(".erwmb").show();
		 })
		  $(".erwmb").on("mouseout",function(){
			 $(".erwms").show();
		     $(this).hide();
		 })
		 $(".man_info .name").hover(function(){
	      $(".man_info ul").show();
	    });
	    $(".man_info ul").closeDom();
</script>

