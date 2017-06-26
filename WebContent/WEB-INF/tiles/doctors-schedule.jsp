<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>

Doctor's schedule
<table id="currentWeek">
	<caption>The schedule for the nearest week</caption>


	<tr>
		<th>Time and date</th>
		<th>Status</th>
		<th>Complain</th>
	</tr>

</table>
<br />
<table id="nextWeek">

<caption>The schedule for next week</caption>


  <tr>
<th>Time and date</th>
    <th>Status</th> 
    <th>Complain</th>
</tr>


</table>
<br />
<table id="nextnextWeek">

<caption>The schedule for the next after next week</caption>


  <tr>
<th>Time and date</th>
    <th>Status</th> 
    <th>Complain</th>
</tr>


</table>
<br />

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">

	function showSchedule(data) {
		$("div#schedule").html("");
		
		var inAWeek = new Date();
		inAWeek.setDate(inAWeek.getDate()+7);
		inAWeek.setHours(0);
		var in2Weeks = new Date();
		in2Weeks.setDate(in2Weeks.getDate()+14); 
		in2Weeks.setHours(0);
		
		  for(var j=0;j<data.schedule.length;j++){
		
    	 var doctor = data.schedule[j];
    	 
    	 
		
		
		var timeSpan = document.createElement("span");
		
		var date = new Date(doctor.dateTime);
		timeSpan.appendChild(document.createTextNode(date.toDateString() + " : " + "Time: " + date.getHours()+":"+date.getMinutes()+ " "));
		
		
		var patientNameSpan= document.createElement("span"); 
		var complainSpan= document.createElement("span"); 
		
			if (doctor.busy== true){
			
			patientNameSpan.appendChild(document.createTextNode(doctor.patientFirstName + " " + doctor.patientLastName));
			complainSpan.appendChild(document.createTextNode( doctor.complain));
		} else {
			patientNameSpan.appendChild(document.createTextNode("There is no appointment for this time "));
		}
			
			
			
			if (date<inAWeek){
				 var table = document.getElementById("currentWeek");
				
				 var rowCount = table.rows.length;
				 var row = table.insertRow(rowCount);
		
				row.insertCell(0).innerHTML = timeSpan.innerHTML;
				row.insertCell(1).innerHTML = patientNameSpan.innerHTML;
				row.insertCell(2).innerHTML = complainSpan.innerHTML;
			
			}
			
			else if (date>in2Weeks) {
		
				 var table = document.getElementById("nextnextWeek");
					
				 var rowCount = table.rows.length;
				 var row = table.insertRow(rowCount);
		
				row.insertCell(0).innerHTML = timeSpan.innerHTML;
				row.insertCell(1).innerHTML = patientNameSpan.innerHTML;
				row.insertCell(2).innerHTML = complainSpan.innerHTML;
			}
			
			else if (date>inAWeek || date<in2Weeks){
				 var table = document.getElementById("nextWeek");
					
				 var rowCount = table.rows.length;
				 var row = table.insertRow(rowCount);
		
				row.insertCell(0).innerHTML = timeSpan.innerHTML;
				row.insertCell(1).innerHTML = patientNameSpan.innerHTML;
				row.insertCell(2).innerHTML = complainSpan.innerHTML;
				
			}
			
			
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

<a href="${pageContext.request.contextPath}/edit-schedule"><input
	type="submit" name="show-patients-info" value="edit your schedule" /></a>





</html>