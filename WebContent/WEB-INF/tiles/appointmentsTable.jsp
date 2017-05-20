<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

AppointmentsTable
<div id="appointment"></div>
<br />

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<script type="text/javascript">
<!--
	function showAppointmentTable(data){
	console.log ('hello')
	for (i=0;i<data.calendarList.length;i++){
		
		
		var appointment = data.calendarList[i];
		
		var appointmentDiv = document.createElement("div");
		
		var time = document.createElement("span");
		var date = new Date(data.calendarList[i][0]);
		time.appendChild(document.createTextNode(date.toDateString() + " : " + "Time: " + date.getHours()+":"+date.getMinutes()+ " "));
		appointmentDiv.appendChild(time);
		
		var doctorInfo = document.createElement("span");
		doctorInfo.appendChild(document.createTextNode(data.calendarList[i][3]+" "+data.calendarList[i][4]+" Field: "+data.calendarList[i][2]));
		appointmentDiv.appendChild(doctorInfo);
		
		var cancelButton = document.createElement("input");
		
		cancelButton.setAttribute("type", "button");
		cancelButton.setAttribute("value", "Click to see cancel the appointment");
		cancelButton.setAttribute("data-id",data.calendarList[i][1]);
	 	cancelButton.setAttribute("onClick", "(window.location.href = '${pageContext.request.contextPath}/cancelAnAppointment?id='+this.dataset.id)");   
		appointmentDiv.appendChild(cancelButton);
		
		$("div#appointment").append(appointmentDiv);
		
		
	}
}
	
	
	
	

	function updatePage() {
		$.getJSON("/Hippocrates/getAppointmentTable", showAppointmentTable);
	
	
	}



function getURLParameter(name) {
	return decodeURIComponent((new RegExp('[?|&]' + name + '='
			+ '([^&;]+?)(&|#|;|$)').exec(location.search) || [ null, '' ])[1]
			.replace(/\+/g, '%20'))
			|| null;
}



	function onLoad() {

		updatePage();

	}

	$(document).ready(onLoad);
//-->
</script>