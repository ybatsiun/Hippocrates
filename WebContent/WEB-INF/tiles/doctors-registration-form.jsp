



<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

Doctor's registration form
<br/>

<sf:form  
	action="${pageContext.request.contextPath}/choose-schedule"
	commandName="doctor">
	
	

	  <table class="formtable">
	 	<tr>
			<td class="label">Username:</td>
			<td> <input class="control" path="username" name="username" type="text" /><br /> <!-- Removed sf tag  -->
				</td>
		</tr>
		 <tr>
			<td class="label">firstName:</td>
			<td><input class="control" path="firstName" name="firstName" type="text" /><br />
				</td>
		</tr> 
		
		 <tr>
			<td class="label">lastName:</td>
			<td><input class="control" name="lastName" path="lastName" 
					type="text" /><br />
				</td>
		</tr>
		
		<tr>
			<td class="label">Email:</td>
			<td><input class="control" name="email" path="email" 
					type="text" />
				</td>
		</tr>
		
	 <tr>
			<td class="label">phoneNumber:</td>
			<td><input  class="control" path="phoneNumber" name="phoneNumber"
					 type="number" />
				</td>
		</tr> 
		
		 <tr>
			<td class="label">field:</td>
			<td><input  class="control" path="field" name="field"
					 type="text" />
				</td>
		</tr> 
		
		 <tr>
			<td class="label">password:</td>
			<td><input  class="control" path="password" name="password"
					 type="password" />
				</td>
		</tr>
		
		<tr>
				<td>Pick up the time you want to work on Monday:</td>
				<td><form:checkboxes path="monday" items="${monday}"  /> 
				</td>
			</tr>
		
		
	 <tr>
		
		
	 <tr>
			<td class="label"></td>
			<td><input class="control" value="Create account" type="submit" /></td>
		</tr> 
	</table>

</sf:form>

