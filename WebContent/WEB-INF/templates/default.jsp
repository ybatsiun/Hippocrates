<!-- The default construction view for tiles using Apache tiles -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> <tiles:insertAttribute name="title"></tiles:insertAttribute> </title>
</head>


<div class="header">
<tiles:insertAttribute name="header"></tiles:insertAttribute>
</div>

<div class="content">
<tiles:insertAttribute name="content"></tiles:insertAttribute>
</div>

<div class="footer">
<tiles:insertAttribute name="footer"></tiles:insertAttribute>
</div>

<body>

</body>
</html>