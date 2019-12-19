<%@ page language="java" contentType="text/html; charset=Utf-8"
    pageEncoding="Utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="Utf-8">
<title>Insert title here</title>
</head>
<body>
<h3>
${messageSuccessful}</h3>
<h2>
Username:   ${user.username}
</h2>
<br>
<form action="showLoginForme" >
		<input type="submit" value="Back" />
	</form>
</body>
</html>