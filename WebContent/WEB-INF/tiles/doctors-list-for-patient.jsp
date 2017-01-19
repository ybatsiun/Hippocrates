

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
			var firstName = document.createElement("span");
			firstName.appendChild(document.createTextNode("First name: "
					+ doctor.firstName));
			doctorDiv.appendChild(firstName);

			var lastName = document.createElement("span");
			lastName.appendChild(document.createTextNode(". Last name: "
					+ doctor.lastName));
			doctorDiv.appendChild(lastName);

			var field = document.createElement("span");
			field.appendChild(document.createTextNode(". Field: "
					+ doctor.field));
			doctorDiv.appendChild(field);

			/*////////////////////////////////// Schedule module /////////////////////////////////////////////////// */

			for (var i = 10; i < 13; i++) {

				const
				dayAndTime = [ 'mn_' + i ];
				const
				isbusy = [ 'mn_' + i + '_isbusy' ]
				if (doctor[dayAndTime] == true) {

					
				
					var linkToBook = document.createElement("a");
					linkToBook.setAttribute("href", "#");
					linkToBook.setAttribute("id", n);
					linkToBook
							.setAttribute("onclick", "showReply(" + i + ",this.id)");
					linkToBook.appendChild(document
							.createTextNode([ dayAndTime ] + "/"));

					var a = document.createElement("span");

					a.appendChild(linkToBook);
					doctorDiv.appendChild(a);

					var b = document.createElement("span");
					if (doctor[isbusy] == false) {
						b
								.appendChild(document
										.createTextNode("There is no appointment for this time."));
					} else {
						b
								.appendChild(document
										.createTextNode("This appointment is already booked"));
					}
					doctorDiv.appendChild(b);
				} else {

				}

				var c = document.createElement("span");
				var complainForm = document.createElement("form");
				complainForm.setAttribute("class", "replyform");
				complainForm.setAttribute("id", "form" + i+n);

				var textarea = document.createElement("textarea");
				textarea.setAttribute("id","area" + i+n);
				var bookButton = document.createElement("input");
				
				bookButton.setAttribute("type", "button");
				bookButton.setAttribute("value", "Book an appointment");
				bookButton.setAttribute("data-textarea",textarea);
				bookButton.setAttribute("data-i",i);
				bookButton.setAttribute("data-n",n);
				bookButton.setAttribute("data-docusername", doctor.username);
			
				bookButton.setAttribute("data-dayandtime", dayAndTime);
				bookButton
						.setAttribute("onClick",
								"BookAnAppointment(dataset.docusername,dataset.dayandtime,test(dataset.n,dataset.i))");		
								

				complainForm.appendChild(textarea);
				complainForm.appendChild(bookButton);

				c.appendChild(complainForm);
				doctorDiv.appendChild(c);

				/*////////////////////////////////////////////////////////////////////////////////////////////////////*/
			}

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