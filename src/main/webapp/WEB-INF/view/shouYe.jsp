<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ustcsoft.framework.vo.UserVO"%>
<%@page import="com.ustcsoft.jt.vo.User"%>
<%@page
	import="org.springframework.security.core.context.SecurityContext"%>
<%
	SecurityContext context = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
	User user = (User) context.getAuthentication().getPrincipal();
	UserVO userInfo = user.getUserVo();
%>

<!DOCTYPE html>
<html>
<head>
<title>人力资源管理系统</title>
<style type="text/css">
</style>
</head>
<body style="overflow:hidden;">
	<div style="text-align:center;margin:0;">
		<img src="images/index.png" width="100%" />
	</div>
</body>
</html>
