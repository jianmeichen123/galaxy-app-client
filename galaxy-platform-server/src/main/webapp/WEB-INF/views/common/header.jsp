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
    	<div class="todaymsg clearfix">
        	<span class="weather"><iframe allowtransparency="true" frameborder="0" width="220" height="36" scrolling="no" src="http://tianqi.2345.com/plugin/widget/index.htm?s=3&z=2&t=1&v=0&d=3&bd=0&k=000000&f=004080&q=1&e=1&a=1&c=54511&w=180&h=36&align=center"></iframe></span>
            <span>
                <em id="sday" style="display:none">
                2014-01-08
                </em>
                <div id="xianhao" class="xianhao">
                <em class="today" id="todayweek"></em>限行尾号为&nbsp;<em class="todaynum" id="todaynum"></em><em>，</em><em class="tomorrow" id="tomorrowweek"></em>限行尾号为&nbsp;<em class="tomorrownum" id="tomorrownum"></em><em>！</em>　
                </div>            
            </span>         
        </div>
    </div>
    <!-- 头部右边 -->
    <div class="usermsg rit clearfix">
    	 <span class="ico name"  href="<%=path%>/html/register.html?&realName=<%=realName%>&deptName=<%=deptName%>&roleName=<%=roleName%>" data-btn="login_infor">您好，<%=realName%></span>
        <b class="line null">分割线</b>
        <a href="javascript:logout()" class="loginout">退出</a>
    </div>
</div>
<script src="<%=path %>/js/login.js"></script>
<script src="<%=path %>/js/car_limit.js"></script>
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
</script>

