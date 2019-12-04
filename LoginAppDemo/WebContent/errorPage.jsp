<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>
		<%
			out.println("failed to login");
		%>
	</h5>
	<form>

		<h4>You are not an authorized user. Please enter correct
			credentials</h4>
		<a href="login.html">Login</a>
	</form>
</body>
</html>