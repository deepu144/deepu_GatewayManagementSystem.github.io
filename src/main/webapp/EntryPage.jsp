<%@page import="com.karpagam.service.Administator"%>
<%@page import="com.karpagam.bean.StaffInformation"%>
<%@page import="com.karpagam.bean.StudentInformation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="ErrorPage.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ID Card Entry System</title>
<link rel="stylesheet" href="EntryPage.css">
<script src="EntryPage.js" defer></script>
</head>
<body>

	<%
		if(session.getAttribute("Entry")==null){
			response.sendRedirect("EntryLogin.jsp");
			return;
		}
	%>
	<% 
		if(session.getAttribute("inCount")==null) { 
			response.sendRedirect("home"); 
			 return; 
		 } 
	 %>
	 <% if(session.getAttribute("noRecord")!=null) { %>
	 	<div class="pop_alert_error text-center">
       			 No Record Found ! <span ><i class="fa-solid fa-xmark"></i></span>
    	</div>
    	<% session.removeAttribute("noRecord"); %>
	 <% } %>
	<div class="container">
		<div id="header">
			<h1>
				<img src="/GateWay_Management_System/images/KCE.png" alt="Kce"
					id="kce_logo" height="100px" width="60%">
			</h1>
			<h3>Main Gate Entry</h3>
		</div>
		<div class="count_update">
	       <div>In : <%= session.getAttribute("inCount") %></div>/
	       <div>Out : <%= session.getAttribute("outCount")%> </div>
     	</div>
		<div id="time" style="float: right;"></div>
		<form id="entryForm" method="get" name="rollNumber" action="entrySubmit">
			<div class="input-group">
				<label for="rollNo">Roll Number:</label> <input type="text"
					id="rollNo" name="rollNo" required autofocus="autofocus">
			</div>
		</form>
		<div id="details">
			<div class="person_details">
				<h3>Information</h3>
				<div class="person_info">
					<div class="person_info_question">
						<div class="">Roll Number :</div>
						<div class="">Name :</div>
						<div class="">Department :</div>
						<div class="">Year  :</div>
					</div>
					<% StudentInformation si = (StudentInformation)session.getAttribute("StudentInformation"); %>
					<% StaffInformation staffInformation = (StaffInformation) session.getAttribute("StaffInformation"); %>
					<% if(si!=null) {%>
							<% session.removeAttribute("StudentInformation"); %>
							<div class="person_info_answer_from_db">
								<div class=""><%= si.getRollNumber() %></div>
								<div class=""><%= si.getName() %></div>
								<div class=""><%= si.getDept() %></div>
								<div class=""><%= si.getYear() %></div>
							</div>
					<% }else if(staffInformation!=null){ %>
							<% session.removeAttribute("StaffInformation"); %>
							<div class="person_info_answer_from_db">
								<div class=""><%=staffInformation.getRollNumber()%></div>
								<div class=""><%=staffInformation.getName()%></div>
								<div class=""><%=staffInformation.getDept() %></div>
								<div class=""><%="-"%></div>
							</div>
					<% }else { %>
						<div class="person_info_answer_from_db">
							<div class="">Null</div>
							<div class="">Null</div>
							<div class="">Null</div>
							<div class="">Null</div>
						</div>
					<% } %>
				</div>
			</div>
			<div class="person_img">
				<svg xmlns="http://www.w3.org/2000/svg" id="gate_entry_person_img" width="16" height="16"
					fill="currentColor" class="bi bi-person-bounding-box"
					viewBox="0 0 16 16">
  <path
						d="M1.5 1a.5.5 0 0 0-.5.5v3a.5.5 0 0 1-1 0v-3A1.5 1.5 0 0 1 1.5 0h3a.5.5 0 0 1 0 1zM11 .5a.5.5 0 0 1 .5-.5h3A1.5 1.5 0 0 1 16 1.5v3a.5.5 0 0 1-1 0v-3a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 1-.5-.5M.5 11a.5.5 0 0 1 .5.5v3a.5.5 0 0 0 .5.5h3a.5.5 0 0 1 0 1h-3A1.5 1.5 0 0 1 0 14.5v-3a.5.5 0 0 1 .5-.5m15 0a.5.5 0 0 1 .5.5v3a1.5 1.5 0 0 1-1.5 1.5h-3a.5.5 0 0 1 0-1h3a.5.5 0 0 0 .5-.5v-3a.5.5 0 0 1 .5-.5" />
  <path
						d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm8-9a3 3 0 1 1-6 0 3 3 0 0 1 6 0" />
</svg>
			</div>
		</div>
		
		<% if(si==null && staffInformation==null) { %>
			<div class="welcome">
					<%= "Status : Null" %>
			</div>
		<% }else { %>
			<% if(session.getAttribute("newEntry")!=null) {%>
				<div class="welcome_red">
					<%= "Status : Check In" %>
				</div>
				<% session.removeAttribute("newEntry"); %>
			<% }else{ %>
				<div class="welcome_green">
					<%= "Status : Check Out" %>
				</div>
			<% } %>
		<% } %>
	</div>
	<form id="form-1" name="form-1" action="doAction" method="post">
	</form>
</body>
</html>