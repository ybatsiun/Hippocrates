
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<table id="docSchedule">
	<caption>Doctor schedule for the closest week</caption>


	<tr>
		<th>Time and date</th>
		
	</tr>

</table>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">



<!--
	function showcalendarsList(data) {
		
	
	for (n = 0; n < data.calendarsList.length; n++) {
		
		var calendar = data.calendarsList[n];
		
	

		var date = new Date(calendar.dateTime);
		var timeSpan = document.createElement("span");
		timeSpan.appendChild(document.createTextNode(date.toDateString() + " : " + "Time: " + date.getHours()+":"+date.getMinutes()+ " "));
		
		var patientNameSpan= document.createElement("span"); 
		
		 var table = document.getElementById("docSchedule");
			
		 var rowCount = table.rows.length;
		 var row = table.insertRow(rowCount);

		row.insertCell(0).innerHTML = timeSpan.innerHTML;
		
		if (calendar.busy== true){
		
		patientNameSpan.appendChild(document.createTextNode("The appointment is already booked"));
		row.insertCell(1).innerHTML = patientNameSpan.innerHTML;
	} else {
        var BookAnAppointmentButton = document.createElement("input");
		
		BookAnAppointmentButton.setAttribute("type", "button");
		BookAnAppointmentButton.setAttribute("value", "Click to book an appointment");
		BookAnAppointmentButton.setAttribute("data-docusername",calendar.username);
		BookAnAppointmentButton.setAttribute("data-datetime",calendar.dateTime);
		BookAnAppointmentButton.setAttribute("data-firstName",calendar.firstName);
		BookAnAppointmentButton.setAttribute("data-lastName",calendar.lastName);
		
		
		BookAnAppointmentButton.setAttribute("onClick", 
				"(window.location.href='${pageContext.request.contextPath}/showBookAnAppointmentFor?doctorUsername='+getURLParameter('doctorUsername')+'&dateTime='+dataset.datetime+'&firstName='+getURLParameter('firstName')+'&lastName='+getURLParameter('lastName')+'&field='+getURLParameter('field'))");
		
		row.appendChild(td = document.createElement("td"));
	    td.appendChild(BookAnAppointmentButton);
		
	
	}
		
		
		
		
	
		
		
		
		
	
		}

		
	
		}
	


function getURLParameter(name) {
	  return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search) || [null, ''])[1].replace(/\+/g, '%20')) || null;
	}
	

	

	function updatePage() {
		
		 
		$.getJSON("http://localhost:8080/Hippocrates/getDocScheduleForPatient?doctorUsername="+getURLParameter('doctorUsername'), showcalendarsList);
	}

	function onLoad() {

		updatePage();

	}

	$(document).ready(onLoad);
//-->
</script>







