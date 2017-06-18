<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<p>Header goes hear...</p>
<style>
body {
	background-color: linen;
}

form.replyform {
	padding: 20px;
	display: none;
}

form.loginform {
	height: 200px;
	width: 400px;
	background: black;
	position: fixed;
	top: 50%;
	left: 50%;
	margin-top: -100px;
	margin-left: -200px;
}
</style>
<sec:authorize access='!isAuthenticated()'>

	<a href="<c:url value='/login'/>"> Log in </a>

</sec:authorize>

<sec:authorize access='isAuthenticated()'>

	<a href="<c:url value='/j_spring_security_logout'/>"> Log out </a>

</sec:authorize>

<sec:authorize access="hasRole('ROLE_PATIENT')">

	<a
		href="<c:url value='/patientsPage'/>">
		Go to home page </a>

</sec:authorize>

<sec:authorize access="hasRole('ROLE_DOCTOR')">

	<a
		href="<c:url value='/doctorsPage'/>">
		Go to home page </a>

</sec:authorize>



