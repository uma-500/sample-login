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
		HttpSession session1 = request.getSession();
		
		String userName = (String)session1.getAttribute("userName");
		
	%>
	<h2>You are successfully logged in !</h2>
	<h4>This is home page </h4>
	<%-- <%= 
	userName
	%> --%>
	
	<div>
		<form action="logout" method="post">
			<br>
			<input type="submit" value="logout">
		</form>
	</div>

</body>
</html>