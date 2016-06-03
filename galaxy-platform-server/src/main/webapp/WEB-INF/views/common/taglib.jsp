<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.galaxyinternet.framework.core.constants.Constants,com.galaxyinternet.framework.core.oss.OSSConstant,com.galaxyinternet.model.user.User"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
User user = (User)request.getSession().getAttribute(Constants.SESSION_USER_KEY);
String sessionId = "";
String realName = "";
Long userId=null;
String deptName = "";
String roleName = "";
Long roleId=null;
if(null != user) {
	sessionId = user.getSessionId();
	if(null != user.getRealName()){
		realName = user.getRealName();
	}
	userId = user.getId();
    if(null != user.getRoleId()){
	   roleId = user.getRoleId();
    }
    
    if(null != user && null != user.getDepartmentName()){
    	deptName = user.getDepartmentName();
     }
    if(null != user && null != user.getRole()){
    	roleName = user.getRole();
     }

}
String endpoint = (String)application.getAttribute(OSSConstant.GALAXYINTERNET_FX_ENDPOINT);
%>
<script src="<%=request.getContextPath() %>/js/jquery/1.11.1/jquery.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/js/json2.js" type="text/javascript"></script>
<script type="text/javascript">
	var contextEndPoint = '<%=endpoint%>';
	endpointObj = JSON.parse(contextEndPoint);
	sessionId = '<%=sessionId%>',realName = '<%=realName%>',userId = '<%=userId%>';
</script>
<script src="<%=request.getContextPath() %>/js/common.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/js/axure.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/js/platformUrl.js" type="text/javascript"></script>
<link rel="shortcut icon" href="<%=request.getContextPath() %>/img/favicon.ico" />