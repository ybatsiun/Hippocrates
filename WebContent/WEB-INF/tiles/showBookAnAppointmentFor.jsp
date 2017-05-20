<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<form id=form>

<div>
	<p>
		You are booking an appointment of <span id=firstName></span> <span
			id=lastName></span> , a specialist in a field of <span id=field></span>
		for <span id=dateTime></span>
		<br>
	</p>
		<table class="formtable">
		 <tr>
			<td class="label">Please enter your complain</td>
			<td><input class="control" name="complain"  
					type="text" /><br />
				</td>
		</tr>
		</table>
		<input onClick="getComplainAndRedirect()" type="button" value="Submit">
		
		
	
</div>



</form>



<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<script type="text/javascript">
<!--
	
	
	
	
	

	function updatePage() {
	
	var firstNameSpan = document.createElement("span");
	firstNameSpan.appendChild(document.createTextNode(getURLParameter('firstName')));
	$("span#firstName").append(firstNameSpan);
	
	var lastNameSpan = document.createElement("span");
	lastNameSpan.appendChild(document.createTextNode(getURLParameter('lastName')));
	$("span#lastName").append(lastNameSpan);
	
	var fieldSpan = document.createElement("span");
	fieldSpan.appendChild(document.createTextNode(getURLParameter('field')));
	$("span#field").append(fieldSpan);
	
	
	
	
	var dateTimeSpan = document.createElement("span");
	var intDate=getURLParameter('dateTime');
	var date = new Date(parseInt(intDate,10));
	dateTimeSpan.appendChild(document.createTextNode(date.toDateString() + "  " +  date.getHours()+":"+date.getMinutes()+ " "));
	$("span#dateTime").append(dateTimeSpan);
	
	}



function getURLParameter(name) {
	return decodeURIComponent((new RegExp('[?|&]' + name + '='
			+ '([^&;]+?)(&|#|;|$)').exec(location.search) || [ null, '' ])[1]
			.replace(/\+/g, '%20'))
			|| null;
}

function getComplainAndRedirect(){
	var complain = ""
		complain = document.getElementById("form").elements[0].value
		
		var intDate=getURLParameter('dateTime');
	    var date = new Date(parseInt(intDate,10));
	
	window.location.href = "${pageContext.request.contextPath}/bookAnAppointmentFor?doctorUsername="+getURLParameter('doctorUsername') + "&complain="+complain +"&dateTime="
			+intDate+"&firstName="+getURLParameter('firstName')+"&lastName="+getURLParameter('lastName')+"&field="+getURLParameter('field');
}

	function onLoad() {

		updatePage();

	}

	$(document).ready(onLoad);
//-->
</script>