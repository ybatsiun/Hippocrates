 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


List of registered patients

<div id="patientRow"></div>
<br />


<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
<!--
	function showPatients(data) {

		for (var i=0;i<data.patientsList.length;i++){
			var patient= data.patientsList[i];
			
			var patientDiv = document.createElement("div");
			
			var usernameSpan = document.createElement("span");
			usernameSpan.appendChild(document.createTextNode(patient.username+". "));
			
			var firstNameSpan = document.createElement("span");
			firstNameSpan.appendChild(document.createTextNode(patient.firstName+". "));
			
			var lastNameSpan = document.createElement("span");
			lastNameSpan.appendChild(document.createTextNode(patient.lastName+". "));
			
			
			
			var emailSpan = document.createElement("span");
			emailSpan.appendChild(document.createTextNode(patient.email+". "));
			
			var phoneNumberSpan = document.createElement("span");
			phoneNumberSpan.appendChild(document.createTextNode(patient.phoneNumber+". "));
			
			var enabledSpan = document.createElement("span");
			enabledSpan.appendChild(document.createTextNode(patient.enabled+". "));
			
			
			
			patientDiv.appendChild(usernameSpan);
			patientDiv.appendChild(firstNameSpan);
			patientDiv.appendChild(phoneNumberSpan);
			patientDiv.appendChild(emailSpan);
			patientDiv.appendChild(enabledSpan);
			

			$("div#patientRow").append(patientDiv);
			
		
		}
		
	}

	function updatePage() {
		$.getJSON("<c:url value="/getPatients-list-for-admin"/>", showPatients);
	}

	function onLoad() {

		updatePage();

	}

	$(document).ready(onLoad);
	
	//-->
</script>