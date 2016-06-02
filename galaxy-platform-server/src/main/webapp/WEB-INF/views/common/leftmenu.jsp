<%@ page language="java" pageEncoding="UTF-8"%>
<!--左侧导航-->
		<ul class="lft">
			<li>
        	<a href="javascript:void(-1);" target-url="userIndex" target="user">用户管理</a>
        </li>
        <li >
        	<a href="javascript:void(-1);" target-url="dictIndex" target="dict">数据字典</a>
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