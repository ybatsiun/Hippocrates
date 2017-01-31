<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

Patients's registration form
<br/>

<sf:form  
	action="${flowExecutionUrl}&_eventId=submit-registration"
	commandName="patient">
	
	

	  <table class="formtable">
	 	<tr>
			<td class="label">Username:</td>
			<td> <input class="control" path="username" name="username" type="text" /><br /> <!-- Removed sf tag  -->
				</td>
		</tr>
		 
		
		
		
		
		
	  
		
		 <tr>
			<td class="label">password:</td>
			<td><input  class="control" path="password" name="password"
					 type="password" />
				</td>
		</tr>
		
		
	 <tr>
			<td class="label"></td>
			<td><input class="control" value="Create account" type="submit" /></td>
		</tr> 
	</table>

</sf:form>

