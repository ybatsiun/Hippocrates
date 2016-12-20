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
			<td class="label">firstName:</td>
			<td><sf:input class="control" path="firstName" name="firstName" type="text" /><br />
				</td>
		</tr> 
		
		 <tr>
			<td class="label">lastName:</td>
			<td><sf:input class="control" path="lastName" 
					type="text" /><br />
				</td>
		</tr>
		
		<tr>
			<td class="label">Email:</td>
			<td><sf:input class="control" path="email" 
					type="text" />
				</td>
		</tr>
		
	 <tr>
			<td class="label">phoneNumber:</td>
			<td><sf:input  class="control" path="phoneNumber"
					 type="number" />
				</td>
		</tr> 
		
	<%--	<tr>
			<td class="label">Password:</td>
			<td><sf:input  class="control" path="password"
					 type="password" />
				</td>
		</tr>  
		 <tr>
			<td class="label">Confirm Password:</td>
			<td><input id="confirmpass" class="control" name="confirmpass"
				type="password" />
				</td>
		</tr> --%>
	 <tr>
			<td class="label"></td>
			<td><input class="control" value="Create account" type="submit" /></td>
		</tr> 
	</table>

</sf:form>

<%-- <a href="${flowExecutionUrl}&_eventId=submit-registration"><input
	type="submit" name="submit-registration" value="submit registration" /></a> --%>