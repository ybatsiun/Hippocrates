<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>

Doctor's schedule
<div id="schedule">

</div>
<br/>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" >

<!--

	function showSchedule(data) {
		
		$("div#schedule").html("");

		var doctor = data.schedule[0];

		var scheduleDiv = document.createElement("div");
		scheduleDiv.setAttribute("class", "doctor");
		
		var timeSpan = document.createElement("span");
		timeSpan.appendChild(document.createTextNode(doctor.username));

		scheduleDiv.appendChild(timeSpan);
		
		for (var i=10;i<13;i++){
			const abc= ['mn_'+i];
			if (doctor[abc]==true){
				alert( 'is true0');
			}
			else {alert('error')}
		}

		$("div#schedule").append(scheduleDiv);
	}
	
	function updatePage() {
		$.getJSON("<c:url value="/getDoctors-schedule"/>", showSchedule);
	}
	
	function onLoad() {

		updatePage();
		
	}
	
	 $(document).ready(onLoad); 
/* 	window.onload = onload(); */
 
 /* $(document).ready ( function(){
   alert('ok');
}); */
//-->
</script>



<a href="${flowExecutionUrl}&_eventId=show-patients-info"><input
	type="submit" name="show-patients-info" value="patient's info" /></a>

<a href="${flowExecutionUrl}&_eventId=enter-complaint"><input
	type="submit" name="enter-complaint" value="enter your complaint" /></a>
	
	</html>