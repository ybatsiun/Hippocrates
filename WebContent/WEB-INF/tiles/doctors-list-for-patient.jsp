

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
       
			var firstName = document.createElement("span");
			firstName.appendChild(document.createTextNode("First name: "+doctor.firstName));
			doctorDiv.appendChild(firstName);
			
			var lastName = document.createElement("span");
			lastName.appendChild(document.createTextNode(". Last name: "+doctor.lastName));
			doctorDiv.appendChild(lastName);
			
			var field = document.createElement("span");
			field.appendChild(document.createTextNode(". Field: "+doctor.field));
			doctorDiv.appendChild(field);
			
			/*///////////////// Schedule module ///////////////// */
			
			for (var i = 10; i < 13; i++) {
				
			const username=doctor.username;	
			
			const
				dayAndTime = [ 'mn_' + i ];
				const
				text = [ 'mn_' + i + '_text' ]
				if (doctor[dayAndTime] == true) {
				
					/* alert(doctor.username); */
					
					var linkToBook=document.createElement("a");
					linkToBook.setAttribute("class", "replylink");
					linkToBook.setAttribute("href", "#");
					linkToBook.setAttribute("data-docusername",doctor.username);
					
					linkToBook.setAttribute("data-dayandtime",dayAndTime);
					
					const du=linkToBook.dataset.docusername;
					const dat=linkToBook.dataset.dayandtime;
					linkToBook.setAttribute("onclick","BookAnAppointment(dataset.docusername,dataset.dayandtime)");
					
					
					linkToBook.appendChild(document.createTextNode([ dayAndTime ] + "/"));
					
					
					var a = document.createElement("span");
					a.appendChild(document.createTextNode([ dayAndTime ] + "/"));
					
					a.appendChild(linkToBook);
					doctorDiv.appendChild(a);

					
					
					var b = document.createElement("span");
					if (doctor[text] == null) {
						b
								.appendChild(document
										.createTextNode("There is no appointment for this time."));
					} else {
						b.appendChild(document.createTextNode( "This appointment is already booked"));
					}
					doctorDiv.appendChild(b);
				} else {

				}
				/*//////////////////////////////////////////////////////////////////*/
			}

			$("div#doctor").append(doctorDiv);
		}
	}








 function BookAnAppointment( doctorUsername,dayAndTime){
	 alert('Booking with '+ doctorUsername+ ' and ' + dayAndTime);
    	var data = { 
    			doctorUsername : doctorUsername,
    			dayAndTime : dayAndTime
    		}
    	
    	$.ajax({
            type: "POST",
            url: "bookingAppointment",
            data: data,
            success: function (result) {
                alert('success');
            },
            error: function (result) {
            	alert('error');
            }
        });
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