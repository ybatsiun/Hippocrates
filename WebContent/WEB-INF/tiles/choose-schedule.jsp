<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

Choose a schedule you want

<sf:form id="form" action="javascript: formJSON();"
	commandName="schedule">

	<table id="table" class=formatable>

	</table>

</sf:form>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
<!--
	function showSchedule() {
	
	var i;
	var days = ["Monday", "Tuesday", "Wednesday","Thursday","Friday"];
	

	
	for ( i=0;i<5;i++){
		
		for (var a=8;a<18;a++){
			for (var b=0;b<60;b+=15){
				(function(i){var time = new Date(17, 1, 8, a, b, 0, 0);
				
				var tr = document.createElement('tr');
				var td0 = document.createElement('td');
				var td1 = document.createElement('td');
				
				td0.appendChild(document.createTextNode("Day: "+days[i]+" .Time: "+ time.toLocaleTimeString()));
				
				/* console.log("Variable i is "+i+". Day is "+days[i]+" .Time:"+time.toLocaleString()) */ 
				
				var i = document.createElement("input"); 
				i.setAttribute('type',"checkbox");
				i.setAttribute('data-time',time.toLocaleString()); 
				i.setAttribute('data-day',days[i]); 
			 	i.setAttribute('name',"monday" ); 
			/* 	i.setAttribute('path',"monday" ); */
				td1.appendChild(i);
				
				tr.appendChild(td0);
				tr.appendChild(td1);
				
				table.appendChild(tr)})(i);
				
				
				
			}
		}
		
	}
	
	var tr0 = document.createElement('tr');
	var td3 = document.createElement('td');
	var i0 = document.createElement("input"); 
	i0.setAttribute('type',"submit");
	i0.setAttribute('value',"Finish registration");
	td3.appendChild(i0);
	tr0.appendChild(td3);
	table.appendChild(tr0);
	table.appendChild(tr0)
	form.appendChild(table);
	form.appendChild(i0);
	
		
	}

	 

	function onLoad() {

		showSchedule();
	

	}
	
	function formJSON(){
		var fields = {};
		$("#form").find(":checkbox").each(function() {
		   
		    fields[this.input] = $(this).val();
		    console.log($(this).val());
		    fields[this.day] = $(this).day;
		    console.log($(this).day);
		    fields[this.time] = $(this).time;
		    console.log($(this).time);
		});
		console.log("JSON formed");
		
		$.ajax({
			type : "POST",
			url : "testingJSON",
			data : fields,
			success : function(result) {
				alert(" performed successfully");
			},
			error : function(result) {
				alert('error');
			}
		});
	}

	$(document).ready(onLoad);
//-->
</script>