<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


You have successfully booked an appointment
<br>


<button type="button" onClick="redirectToDocSchedule()">Click here to see schedule of the doctor </button>

<br>



<button type="button" onClick="redirectToAppointmentsTable()">Click here to see a table of your appointments </button>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	
<script type="text/javascript">


	 

	function onLoad() {

	

	}
	
	function redirectToDocSchedule(){
		window.location.href = "${pageContext.request.contextPath}/showDocScheduleForPatient?doctorUsername="+getURLParameter('doctorUsername') +"&firstName="+getURLParameter('firstName')+"&lastName="+getURLParameter('lastName')+"&field="+getURLParameter('field');
	}
	
	function redirectToAppointmentsTable(){
		window.location.href = "${pageContext.request.contextPath}/showAppointmentsTable";
	}
	
	function getURLParameter(name) {
		return decodeURIComponent((new RegExp('[?|&]' + name + '='
				+ '([^&;]+?)(&|#|;|$)').exec(location.search) || [ null, '' ])[1]
				.replace(/\+/g, '%20'))
				|| null;
	}
	
	
	

	$(document).ready(onLoad);
//-->
</script>