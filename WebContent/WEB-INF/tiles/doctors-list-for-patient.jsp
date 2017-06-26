

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<br />
<table id="doctorsList">
	<caption>List of available doctors</caption>


	<tr>
		<th>Name</th>
		<th>Field</th>
	</tr>

</table>
<br />


<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
<!--
	function showDoctorsList(data) {
		
	
   
   

	
	
		for (n = 0; n < data.doctorsList.length; n++) {
			
			var doctor = data.doctorsList[n];
			
			var name = document.createElement("span");
			name.appendChild(document.createTextNode(
					 doctor.firstName + " " + doctor.lastName));

			

			var field = document.createElement("span");
			field.appendChild(document.createTextNode(
					doctor.field));

			var viewTheScheduleButton = document.createElement("input");
			
			viewTheScheduleButton.setAttribute("type", "button");
			viewTheScheduleButton.setAttribute("value", "Click to see a schedule of this doctor");
			viewTheScheduleButton.setAttribute("data-docusername",doctor.username);
			viewTheScheduleButton.setAttribute("data-firstname",doctor.firstName);
			viewTheScheduleButton.setAttribute("data-lastname",doctor.lastName);
			viewTheScheduleButton.setAttribute("data-field",doctor.field)
			viewTheScheduleButton.setAttribute("onClick",
					"(window.location.href='${pageContext.request.contextPath}/showDocScheduleForPatient?doctorUsername='+dataset.docusername+'&field='+dataset.field+'&firstName='+dataset.firstname+'&lastName='+dataset.lastname)");
			
			
			
			 var table = document.getElementById("doctorsList");
				
			 var rowCount = table.rows.length;
			 var row = table.insertRow(rowCount);
	
			row.insertCell(0).innerHTML = name.innerHTML;
			row.insertCell(1).innerHTML = field.innerHTML;
			row.appendChild(td = document.createElement("td"));
		    td.appendChild(viewTheScheduleButton);
		
		}

			
		}
	



	
	
	
	
		
		

	

	function updatePage() {
		$.getJSON("<c:url value="/getDoctors-list"/>", showDoctorsList);
	}

	function onLoad() {

		updatePage();

	}

	$(document).ready(onLoad);
//-->
</script>



	