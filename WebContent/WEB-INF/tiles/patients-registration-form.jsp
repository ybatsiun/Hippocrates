<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

Patients's registration form
<br/>

<sf:form  
	action="${pageContext.request.contextPath}/creating-patient"
	commandName="patient">
	
	

	  <table class="formtable">
	 	<tr>
			<td class="label">Username:</td>
			<td> <sf:input class="control" path="username" name="username" type="text" /><br />
			<sf:errors path="username"></sf:errors><!-- Removed sf tag  -->
				</td>
		</tr>
		 <tr>
			<td class="label">firstName:</td>
			<td><sf:input class="control" path="firstName" name="firstName" type="text" /><br />
				<sf:errors path="firstName"></sf:errors>
				
				</td>
		</tr> 
		
		 <tr>
			<td class="label">lastName:</td>
			<td><sf:input class="control" name="lastName" path="lastName" 
					type="text" /><br />
					<sf:errors path="lastName"></sf:errors>
				</td>
		</tr>
		
		<tr>
			<td class="label">Email:</td>
			<td><sf:input class="control" name="email" path="email" 
					type="text" /> <br/>
					<sf:errors path="email"></sf:errors>
				</td>
		</tr>
		
	 <tr>
			<td class="label">phoneNumber:</td>
			<td><sf:input  class="control" path="phoneNumber" name="phoneNumber"
					 type="text" /> <br/>
					 <sf:errors path="phoneNumber"></sf:errors>
				</td>
		</tr>  
		
		 <tr>
			<td class="label">password:</td>
			<td><sf:input  class="control" path="password" name="password"
					 type="password" /> <br/>
					 <sf:errors path="password"></sf:errors>
				</td>
		</tr>
		
		
	 <tr>
			<td class="label"></td>
			<td><input class="control" value="Create account" type="submit" /></td>
		</tr> 
	</table>

</sf:form>

