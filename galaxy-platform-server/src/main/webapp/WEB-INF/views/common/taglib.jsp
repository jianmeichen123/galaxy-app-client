<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.galaxyinternet.framework.core.constants.Constants"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%User user = (User)request.getSession().getAttribute(Constants.SESSION_ID);%>
<script type="text/javascript">
	var contextPath = "<%=request.getContextPath()%>";
	var currentUser={
			id:'<%=user.getId()%>'
		};
</script>

<!-- 这里写js和css文件---------start -->
<!-- end -->

