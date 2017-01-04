<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

Choose a schedule you want

<sf:form action="${flowExecutionUrl}&_eventId=schedule-is-choosen"
	commandName="doctor">

	<table class=formatable>

		<tr>
			<td>Monday 10-11 :</td>
			<td><form:checkbox path="Mn_10" name="Mn_10" /></td>
		</tr>

		<tr>
			<td>Monday 11-12 :</td>
			<td><form:checkbox path="Mn_11" name="Mn_11" /></td>
	</tr>

		<tr>
			<td>Monday 12-13 :</td>
			<td><form:checkbox path="Mn_12" name="Mn_12" /></td>
	</tr>

		<tr>
			<td colspan="2"><input class="control" type="submit"
				value="Finish registration" /></td></tr>
	</table>







</sf:form>

<%-- <a href="${flowExecutionUrl}&_eventId=schedule-is-choosen"><input
	type="submit" name="schedule-is-choosen"
	value="confirm choosen schedule" /></a>  --%>