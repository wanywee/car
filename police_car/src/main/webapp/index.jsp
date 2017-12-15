<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- <%@ page language="java" import="com.carTravelsky.common.YSTConstants" pageEncoding="UTF-8"%>
 --%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<% 
pageContext.setAttribute("version", "20170227");
%>
<html>
<head>
<script>
 window.location.href = "${ctx}/index";
</script>
</head>
<body>

你好！

</body>
</html>