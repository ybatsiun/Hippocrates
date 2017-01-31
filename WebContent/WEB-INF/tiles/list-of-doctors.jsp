  
   
   <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>

Doctor's list for admin :

<div id="doctorRow"></div>
<br />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
<!--
	function showDoctors(data) {

		for (var i=0;i<data.doctorsList.length;i++){
			var doctor= data.doctorsList[i];
			
			var doctorDiv = document.createElement("div");
			
			var usernameSpan = document.createElement("span");
			usernameSpan.appendChild(document.createTextNode(doctor.username+". "));
			
			var firstNameSpan = document.createElement("span");
			firstNameSpan.appendChild(document.createTextNode(doctor.firstName+". "));
			
			var lastNameSpan = document.createElement("span");
			lastNameSpan.appendChild(document.createTextNode(doctor.lastName+". "));
			
			var fieldSpan = document.createElement("span");
			fieldSpan.appendChild(document.createTextNode(doctor.field+". "));
			
			var emailSpan = document.createElement("span");
			emailSpan.appendChild(document.createTextNode(doctor.email+". "));
			
			var enabledSpan = document.createElement("span");
			enabledSpan.appendChild(document.createTextNode(doctor.enabled+". "));
			
			
			
			doctorDiv.appendChild(usernameSpan);
			doctorDiv.appendChild(firstNameSpan);
			doctorDiv.appendChild(fieldSpan);
			doctorDiv.appendChild(emailSpan);
			doctorDiv.appendChild(enabledSpan);
			

			$("div#doctorRow").append(doctorDiv);
			
		
		}
		
	}

	function updatePage() {
		$.getJSON("<c:url value="/getDoctors-list-for-admin"/>", showDoctors);
	}

	function onLoad() {

		updatePage();

	}

	$(document).ready(onLoad);
//-->
</script>

<a href="${flowExecutionUrl}&_eventId=show-doctors-schedule"><input
	type="submit" name="show-doctors-schedule" value="view all doctors schedule" /></a>



</html>
   
   
   