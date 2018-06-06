<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pagination" uri="/WEB-INF/taglibs/pagination.tld"%>
<!DOCTYPE html>
<html>
<%@ include file="header.jsp"%>
<body>
	<form action='<spring:url value="/loginAction"/>' method="post">
		<table>
			<tr>
				<td>Username</td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td><button type="submit">Login</button></td>
			</tr>
		</table>
	</form>

</body>
</html>