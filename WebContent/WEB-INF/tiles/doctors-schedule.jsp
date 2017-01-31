<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>

Doctor's schedule
<div id="schedule"></div>
<br />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
<!--
	function showSchedule(data) {

		$("div#schedule").html("");
for(var j=0;j<data.schedule.length;j++){
		var doctor = data.schedule[j];

		var scheduleDiv = document.createElement("div");
		scheduleDiv.setAttribute("class", "doctor");

		var timeSpan = document.createElement("span");
		timeSpan.appendChild(document.createTextNode(doctor.username + ":"));

		scheduleDiv.appendChild(timeSpan);

		for (var i = 10; i < 13; i++) {
			
			const patientFirstName=['mn_'+i+"_firstname"];
			const patientLastName=['mn_'+i+"_lastname"];
			const patientPhoneNumber=['mn_'+i+"_phonenumber"];
			
			const day = [ 'mn_' + i ];
			const text = [ 'mn_' + i + '_text' ];
			const isBusy= ['mn_'+i+'_isbusy'];
			
			if (doctor[day] == true) {
				var a = document.createElement("span");
				a.appendChild(document.createTextNode([ day ] + "/"));
				scheduleDiv.appendChild(a);

				var b = document.createElement("span");
				if (doctor[isBusy] == false) {
					
					b.appendChild(document.createTextNode("There is no appointment for this time."));
					
					
				} else {
					b.appendChild(document.createTextNode(doctor[patientFirstName]
									+ " "));
					
					b.appendChild(document.createTextNode(doctor[patientLastName]
							+ " "));
					
					b.appendChild(document.createTextNode(doctor[patientPhoneNumber]
							+ ";"));
					
					b.appendChild(document.createTextNode(doctor[text]
					+ ";"));
				}
				scheduleDiv.appendChild(b);
			} else {

			}
		}

		$("div#schedule").append(scheduleDiv);
	}}

	function updatePage() {
		$.getJSON("<c:url value="/getDoctors-schedule"/>", showSchedule);
	}

	function onLoad() {

		updatePage();

	}

	$(document).ready(onLoad);
//-->
</script>



<a href="${flowExecutionUrl}&_eventId=show-patients-info"><input
	type="submit" name="show-patients-info" value="patient's info" /></a>

<a href="${flowExecutionUrl}&_eventId=enter-complaint"><input
	type="submit" name="enter-complaint" value="enter your complaint" /></a>

</html>