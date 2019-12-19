<%@ page language="java" contentType="text/html; charset=Utf-8"
    pageEncoding="Utf-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="Utf-8">
<title>Insert title here</title>
</head>
<body>
<h2>
${messageCongratulations}</h2>
<h3>
Username:   ${user.username}
<br>
<br>
Token:  ${user.token}
</h3>

<form:form action="connected"  method="get">
			<input type="hidden" name="token" value="${user.token}"> 
			<button type="submit">Connected</button>
	</form:form>
	<br>
<form action="showLoginForm" >
		<input type="submit" value="Back" />
	</form>
</body>
</html>