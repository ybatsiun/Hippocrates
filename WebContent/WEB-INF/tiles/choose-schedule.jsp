<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

Choose a schedule you want

<form:form method="POST" commandName="doctor" action="${pageContext.request.contextPath}/">
		<table>
			
			<tr>
				<td>Pick up the time you want to work:</td>
				<td><form:checkboxes path="schedule" items="${schedule} itemLabel="days" /> 
				
				
				
				
				</td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="Submit"></td>
			</tr>
			<tr>
		</table>
	</form:form>