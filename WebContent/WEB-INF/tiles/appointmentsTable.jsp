<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="appointment"></div>

<table id="appointments">
	<caption>Table of booked appointments</caption>


	<tr>
		<th>Time and date</th>
		<th>Name of a doctor</th>
		<th>Field</th>
	</tr>

</table>

<br />

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<script type="text/javascript">
<!--
	function showAppointmentTable(data){
	for (i=0;i<data.calendarList.length;i++){
		
		
		var appointment = data.calendarList[i];
		
		
		var time = document.createElement("span");
		var date = new Date(data.calendarList[i][0]);
		time.appendChild(document.createTextNode(date.toDateString() + " : " + "Time: " + date.getHours()+":"+date.getMinutes()+ " "));
		
		var doctorInfo = document.createElement("span");
		doctorInfo.appendChild(document.createTextNode(data.calendarList[i][3]+" "+data.calendarList[i][4]));
		
		var field = document.createElement("span");
		field.appendChild(document.createTextNode(data.calendarList[i][2]));
		
		var cancelButton = document.createElement("input");
		
		cancelButton.setAttribute("type", "button");
		cancelButton.setAttribute("value", "Click to see cancel the appointment");
		cancelButton.setAttribute("data-id",data.calendarList[i][1]);
	 	cancelButton.setAttribute("onClick", "(window.location.href = '${pageContext.request.contextPath}/cancelAnAppointment?id='+this.dataset.id)");   
		
		
		var table = document.getElementById("appointments");
		
		 var rowCount = table.rows.length;
		 var row = table.insertRow(rowCount);

		row.insertCell(0).innerHTML = time.innerHTML;
		row.insertCell(1).innerHTML = doctorInfo.innerHTML;
		row.insertCell(2).innerHTML = field.innerHTML;
		row.appendChild(td = document.createElement("td"));
	    td.appendChild(cancelButton);
		
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