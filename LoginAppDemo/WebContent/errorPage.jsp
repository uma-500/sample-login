<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	out.println("failed to login");
	%>
	<form>
	
	<h2>You are not an authorized user. Please enter correct credentials</h2>
	<a href="login.html">Login</a>
	</form>
</body>
</html>