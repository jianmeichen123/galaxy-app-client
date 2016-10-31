<%@ page language="java" pageEncoding="UTF-8"%>
<!--左侧导航-->
		<ul class="lft">
			<li>
        	<a href="javascript:void(-1);" target-url="userIndex" target="user"><span class="navbar nav6"></span>用户管理</a>
        </li>
        <li >
        	<a href="javascript:void(-1);" target-url="roleIndex" target="role"><span class="navbar nav8"></span>角色权限</a>
        </li>	
        <li >
        	<a href="javascript:void(-1);" target-url="dictIndex" target="dict"><span class="navbar nav7"></span>数据字典</a>
        </li>
         <li >
        	<a href="javascript:void(-1);" target-url="pushMsgIndex" target="msg"><span class="navbar nav7"></span>消息推送</a>
        </li>
         <li >
        	<a href="javascript:void(-1);" target-url="indexConfig" target="index"><span class="navbar nav7"></span>桌面配置</a>
        </li>	
		</ul>
 <script type = "text/javascript">
 	 $(".lft").on("click",'a',function(){
 		var a = $(this);
 		var url = platformUrl[a.attr("target-url")];
 		return forwardWithHeader(url);
 	}); 
</script>
<script src="<%=request.getContextPath() %>/js/platformUrl.js" type="text/javascript"></script>