<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<%@ page buffer="10192kb" %>
Doctor's schedule
<div id="schedule"></div>
<br />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">

	function showSchedule(data) {
		$("div#schedule").html("");
		
	
		
		
		
		
		  for(var j=0;j<data.schedule.length;j++){
		
    	 var doctor = data.schedule[j];
    	 
    	 
		 var scheduleDiv = document.createElement("div");
		
		scheduleDiv.setAttribute("class", "doctor");
		
		var timeSpan = document.createElement("span");
		
		var date = new Date(doctor.dateTime);
		timeSpan.appendChild(document.createTextNode(date.toDateString() + " : " + "Time: " + date.getHours()+":"+date.getMinutes()+ " "));
		scheduleDiv.appendChild(timeSpan);
		
		
		var patientNameSpan= document.createElement("span"); 
		
			if (doctor.busy== true){
			
			patientNameSpan.appendChild(document.createTextNode(doctor.patientFirstName + " " + doctor.patientLastName));
			
		} else {
			patientNameSpan.appendChild(document.createTextNode("There is no appointment for this time "));
		}
			
			scheduleDiv.appendChild(patientNameSpan);
			$("div#schedule").append(scheduleDiv);
    	 }
     
		
		
		
		}
	function updatePage() {
		$.getJSON("<c:url value="/getDoctors-schedule"/>", showSchedule);
	}
	function onLoad() {
		updatePage();
	}
	$(document).ready(onLoad);
//-->
</script>

<% out.println("<p>bufferSize: " + out.getBufferSize() + " remaining: " + out.getRemaining() + " used: " + (out.getBufferSize() - out.getRemaining()) + " autoFlush: " + out.isAutoFlush() + "</p><br>"); %>
<a href="${pageContext.request.contextPath}/edit-schedule"><input
	type="submit" name="show-patients-info" value="edit your schedule" /></a>

<a href="${flowExecutionUrl}&_eventId=show-patients-info"><input
	type="submit" name="show-patients-info" value="patient's info" /></a>

<a href="${flowExecutionUrl}&_eventId=enter-complaint"><input
	type="submit" name="enter-complaint" value="enter your complaint" /></a>

</html>