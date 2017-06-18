<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<c:if test="${param.error != null}">

	<p class="error">Login failed. Check that your username and
		password are correct.</p>

</c:if>

<form name=loginform id= "loginform"
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
		
		<tr>
		<td>Remember me</td>
		<td> <input type='checkbox' name='_spring_security_remember_me'
				checked="checked" /></td>
		</tr>
		
		<tr>
		<td>If you don't have an account-<a href="${pageContext.request.contextPath}/create-new-account">register</a></td>
		
	</tr>
	</table>





</form>