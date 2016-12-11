<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<p> Header goes hear...</p> 

<sec:authorize access='!isAuthenticated()'>

<a href="<c:url value='/login'/>"> Log in </a>

</sec:authorize>

<sec:authorize access='isAuthenticated()'>

<a href="<c:url value='/j_spring_security_logout'/>"> Log out </a>

</sec:authorize>
