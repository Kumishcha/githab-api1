<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<!-- PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h2>This is registration page</h2>
	<h3>Please, fill in all fields</h3>

	<form action="registration" method="get">

		${messageUsernameAlreadyExists}
		<p>
			<input type="text" name="username" value="${username}" required>
			<label for="username">username</label>
		</p>
		<p>
			<input type="password" name="password" required> <label
				for="password">Password</label>
		</p>
		${messageWrongPassword}
		<p>
			<input type="password" name="password2" required> <label
				for="password">Re-enter password</label>
		</p>
		<button type="submit">Create your account</button>

	</form>

<br>
	<form action="showLoginForm">
		<input type="submit" value="Back" />
	</form>

</body>
</html>