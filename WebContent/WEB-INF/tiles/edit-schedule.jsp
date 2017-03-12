<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


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
Here you are able to change your schedule
<br/>

<sf:form  
	action="${flowExecutionUrl}&_eventId=submit-changes"
	commandName="doctor">
	
	

	  <table class="formtable">
	 	
		<tr>
				<td>Edit the time you want to work on Monday:</td>
				<td><form:checkboxes path="monday" items="${monday}"  /> 
				</td>
			</tr>
		
		
	
		
		
	 <tr>
			<td class="label"></td>
			<td><input class="control" value="submit changes and watch your new schedule" type="submit" /></td>
		</tr> 
	</table>

</sf:form>