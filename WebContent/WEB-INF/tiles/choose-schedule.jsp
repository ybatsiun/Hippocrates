<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

Choose a schedule you want

<sf:form id=form action="${flowExecutionUrl}&_eventId=schedule-is-choosen"
	commandName="schedule">

	<table id=table class=formatable>


		

		<tr>
			<td colspan="2"><input class="control" type="submit"
				value="Finish registration" /></td></tr>
	</table>

</sf:form>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
<!--
	function showSchedule() {
	var days = ["Monday", "Tuesday", "Wednesday","Thursday","Friday"];
	var f = document.createElement("form");
	var tbl = document.createElement('table');
	
	
	for (var i=0;i<5;i++){
		for (var a=8;a<18;a++){
			for (var b=0;b<60;b+=15){
				
				var time = new Date(17, 2, 8, a, b, 0, 0);
				
				var tr = document.createElement('tr');
				var td0 = document.createElement('td');
				var td1 = document.createElement('td');
				
				td0.appendChild(document.createTextNode("Day: "+days[i]+" .Time: "+ time.getTime()));
				
				var i = document.createElement("input"); 
				i.setAttribute('type',"checkbox");
				i.setAttribute('name',schedule <days[i],time.getTime());
				i.setAttribute('path',schedule <days[i],time.getTime());
				td1.appendChild(i);
				
				tr.appendChild(td0);
				tr.appendChild(td1);
				
				table.appendChild(tr);
				
			}
		}
		
	}
	
	var tr0 = document.createElement('tr');
	var td3 = document.createElement('td');
	var i0 = document.createElement("input"); 
	i0.setAttribute('type',"submit");
	i0.setAttribute('name',"Finish registration");
	td3.appendChild(i0);
	tr0.appendChild(td3);
	table.appendChild(tr0);
	
		
	}

	

	function onLoad() {

		showSchedule();

	}

	$(document).ready(onLoad);
//-->
</script>