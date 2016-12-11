<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<form name=form
	action='${pageContext.request.contextPath}/j_spring_security_check'
	method="post">

	<table>

		<tr>
			<td>User:</td>
			<td><input type='text' name='j_username' value=''></td>
		</tr>

		<tr>
			<td>Password:</td>
			<td><input type='password' name='j_password' value=''></td>
		</tr>
		
		<tr>
		<td><input type='submit' name='submit' value='login'></td>
		
		</tr>
	</table>





</form>