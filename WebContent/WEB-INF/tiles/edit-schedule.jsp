<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


Here you are able to change your schedule
<br/>

<sf:form  
	action="${pageContext.request.contextPath}/edition-completed"
	commandName="doctor">
	
	

	  <table class="formtable">
	 	
	 	
	 	
	 	
	 	
		<tr>
				<td>Edit the time you want to work on Monday:</td>
				<td><form:checkboxes path="monday" items="${monday}"  /> 
				</td>
			</tr> 
		
		
	
		
		
	 <tr>
			<td class="label"></td>
			<td><input class="control" value="submit changes and watch your new schedule" type="submit" /></td>
		</tr> 
	</table>

</sf:form>