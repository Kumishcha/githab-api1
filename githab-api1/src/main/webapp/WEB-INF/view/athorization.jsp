<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
	<h2>
		name: ${version.name}
		<br> 
		version: ${version.version}
		<br> 
		time : ${version.date}
	</h2>
	<form action="go-to-registration-page">
		<input type="submit" value="registration" />
	</form>
	<br>
	<form action="view users list" method="get">
		<button type="submit">View users list</button>
	</form>

	<h3>${message}</h3>
	<form:form action="authorization" modelAttribute="user" method="get">
		<p>
			<input type="text" name="username" value="${username}"> <label
				for="username">Username</label>
		</p>
		<input type="password" name="password">
		<label for="password">Password</label>
		<p>
			<button type="submit">Sing in</button>
		</p>
	</form:form>
	<c:if test="${not empty users}">
		<table border="1">
			<caption>Users</caption>
			<tr>
				<th>username</th>
				<th>password</th>
			</tr>
			<c:forEach var="user" items="${users}">
				<tr>
					<td><c:out value="${user.username}" /></td>
					<td><c:out value="${user.password}" /></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>
