

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div id="doctor"></div>
<br />

<br />
I will put here a short info of doctor that is useful for a patient.

<a href="${flowExecutionUrl}&_eventId=choosen-doctor"><input
	type="submit" name="choosen-doctor" value="choose a doctor" /></a>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
<!--
	function showDoctorsList(data) {
		$("div#doctor").html("");
		for (n = 0; n < data.doctorsList.length; n++) {
			
			var doctor = data.doctorsList[n];
			
			var doctorDiv = document.createElement("div");
			doctorDiv.setAttribute("class", "doctor");
			doctorDiv.setAttribute("id", n);
			var name = document.createElement("span");
			name.appendChild(document.createTextNode("Name of a doctor: "
					+ doctor.firstName + " " + doctor.lastName));
			doctorDiv.appendChild(name);

			

			var field = document.createElement("span");
			field.appendChild(document.createTextNode(". Field: "
					+ doctor.field+ " "));
			doctorDiv.appendChild(field);

			var viewTheScheduleButton = document.createElement("input");
			
			viewTheScheduleButton.setAttribute("type", "button");
			viewTheScheduleButton.setAttribute("value", "Click to see a schedule of this doctor");
			viewTheScheduleButton.setAttribute("data-docusername",doctor.username);
			viewTheScheduleButton.setAttribute("data-firstname",doctor.firstName);
			viewTheScheduleButton.setAttribute("data-lastname",doctor.lastName);
			viewTheScheduleButton.setAttribute("data-field",doctor.field)
			console.log(doctor.firstName);
			viewTheScheduleButton.setAttribute("onClick",
					"(window.location.href='${pageContext.request.contextPath}/showDocScheduleForPatient?doctorUsername='+dataset.docusername+'&field='+dataset.field+'&firstName='+dataset.firstname+'&lastName='+dataset.lastname)");
			console.log(doctor.firstName);
			doctorDiv.appendChild(viewTheScheduleButton);
			
			$("div#doctor").append(doctorDiv);
		
		}

			
		}
	



	function showReply(i, n) {
		console.log("#form" + i +n);
		$("#form" + i +n).toggle();
	}

	function test(n,i){
		 console.log("#area" + i + n);
		
		
		var text=$("#area" + i + n).val(); 
		
		
		return text;
	}
	
	
	function BookAnAppointment(doctorUsername, dayAndTime,complain) {
		var data = {
			doctorUsername : doctorUsername,
			dayAndTime : dayAndTime,
			complain : complain
		}
		
		

		$.ajax({
			type : "POST",
			url : "bookingAppointment",
			data : data,
			success : function(result) {
				alert('Booking with ' + doctorUsername + ' and ' + dayAndTime+ 'and'+ complain
						+ " performed successfully");
			},
			error : function(result) {
				alert('error');
			}
		});
		updatePage();
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



	