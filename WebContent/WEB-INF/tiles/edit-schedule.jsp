<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
<!--
	function putDetailsIntoHiddenInputs(doctorDetailsList) {
		document.getElementById('email').value = doctorDetailsList[0][0];
		document.getElementById('field').value = doctorDetailsList[0][1];
	}

	function updatePage() {

		$.getJSON("/Hippocrates/getDoctorDetails", putDetailsIntoHiddenInputs);

	}

	function onLoad() {

		updatePage();

	}

	$(document).ready(onLoad);
//-->
</script>

Here you are able to change your schedule
<br />

<sf:form action="${pageContext.request.contextPath}/editing"
	commandName="doctor">



	<table class="formtable">





		<tr>
			<td>Edit the time you want to work on Monday:</td>
			<td><form:checkboxes path="monday" items="${doctor.mondayBlank}" />
				<br /> <sf:errors path="monday"></sf:errors></td>
		</tr>

		<tr>
			<td>Edit the time you want to work on Tuesday:</td>
			<td><form:checkboxes path="tuesday"
					items="${doctor.tuesdayBlank}" /> <br /> <sf:errors
					path="tuesday"></sf:errors></td>
		</tr>

		<tr>
			<td>Edit the time you want to work on Wednesday:</td>
			<td><form:checkboxes path="wednesday"
					items="${doctor.wednesdayBlank}" /> <br /> <sf:errors
					path="wednesday"></sf:errors></td>
		</tr>

		<tr>
			<td>Edit the time you want to work on Thursday:</td>
			<td><form:checkboxes path="thursday"
					items="${doctor.thursdayBlank}" /> <br /> <sf:errors
					path="thursday"></sf:errors></td>
		</tr>

		<tr>
			<td>Edit the time you want to work on Friday:</td>
			<td><form:checkboxes path="friday" items="${doctor.fridayBlank}" />
				<br /> <sf:errors path="friday"></sf:errors></td>
		</tr>
		
		<tr>
			
			<td><sf:input class="control" path="email" name="email"
					type="hidden"  id="email"/> </td>
		</tr>
		
		<tr>
			
			<td><sf:input class="control" path="field" name="field"
					type="hidden"  id="field"/> </td>
		</tr>



		<tr>
			<td class="label"></td>
			<td><input class="control"
				value="submit changes and watch your new schedule" type="submit" /></td>
		</tr>
	</table>

</sf:form>