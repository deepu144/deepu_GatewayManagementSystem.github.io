<%@page import="java.util.Arrays"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.karpagam.bean.EntryDetail"%>
<%@page import="java.util.List"%>
<%@page import="com.karpagam.service.Administator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="ErrorPage.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home Gate Entry</title>
<link rel="stylesheet" href="AdminHomePage.css">
<script src="AdminHomePage.js" defer></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/e96d258b08.js"
	crossorigin="anonymous"></script>

</head>

<body>
	<% response.addHeader("Cache-control", "no-cache, no-store, must-revalidate"); %>
	<% if(session.getAttribute("userName")==null) {%>
		<% response.sendRedirect("AdminLogin.jsp"); %>
	<% } %>
	<div class="container-fluid">
		<div class="row main_header">
			<div class="gate_entry_header">
				<div class="gate_entry_header_img">
					<img src="/GateWay_Management_System/images/KCE.png" height="100px"
						id="KCE_img" alt="KCE">
				</div>
				<div class="gate_entry_login_details">
					<div class="time_updation">11/02/2005 , 10:55:59 PM</div>
					<div class="welcome_info">
						Welcome, <span id="owner_info"><%=session.getAttribute("userName") %></span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid   ">
		<div class="row">
			<nav
				class="col-lg-2 col-sm-3 navbar navbar-expand-lg navbar-expand-sm  navbar-light bg-light flex-column">
				<div class="collapse navbar-collapse">
					<ul class="navbar-nav flex-column">
						<li class="nav-item nav_item_home"><a class="nav-link"
							href="AdminHomePage.jsp">Home</a></li>
						<li class="nav-item"><a class="nav-link"
							href="AdminSearchEntryPage.jsp">Search Entry</a></li>
						<li class="nav-item"><a class="nav-link"
							href="AddAdmin.jsp">Add Member</a></li>
						<li class="nav-item"><a class="nav-link"
							href="AdminChangePassword.jsp">Change Password</a></li>
						<li class="nav-item"><a class="nav-link" href="AdminDatabaseUpload.jsp">Database</a>
						</li>
					</ul>
					<div class="nav-item logout">
                    <form action="logoutAdmin" method="post"> <button id="logout_btn" > <span class="glyphicon glyphicon-log-in"></span> <svg
                        xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                        class="bi bi-caret-left-fill" viewBox="0 0 16 16">
                        <path
                            d="m3.86 8.753 5.482 4.796c.646.566 1.658.106 1.658-.753V3.204a1 1 0 0 0-1.659-.753l-5.48 4.796a1 1 0 0 0 0 1.506z" />
                    </svg> Logout</button></form>
                    
                </div>
				</div>
			</nav>
			<% Long studentCount = new Administator().getTodayStudentCount(); %>
			<% Long staffCount = new Administator().getTodayStaffCount(); %>
			<% Long totalCount = studentCount+staffCount; %>
			<div class="col-lg-10 col-sm-9">
				<h5 class="text-center recent-entries-name">Today Entries</h5>
				<div class="dashboard">
					<div class="total_no_of_persons dashboard-item ">
						<div class="icon">
							<i class="fas fa-users "></i>
						</div>
						<div class="count-container">
							<div class="count"><%=totalCount%></div>
							<div class="person-name">Total Persons</div>
						</div>
					</div>
					<div class="total_no_of_students dashboard-item">
						<div class="icon">
							<i class="fas fa-user-graduate"></i>
						</div>
						<div class="count-container">
							<div class="count"><%= studentCount %></div>
							<div class="person-name">Students</div>
						</div>
					</div>
					<div class="total_no_of_staffs dashboard-item">
						<div class="icon">
							<i class="fas fa-user-tie"></i>
						</div>
						<div class="count-container">
							<div class="count"><%= staffCount %></div>
							<div class="person-name">Staffs</div>
						</div>
					</div>
					 
						<div class="total_no_of_others dashboard-item">
						<div class="icon">
							<i class="fa-solid fa-people-group"></i>
						</div>
						<div class="count-container">
							<div class="count">0</div>
							<div class="person-name">Others</div>
						</div>
					</div>
				</div>
				<div class="home_gate_entry_list">
					<table class="table table-bordered text-center ">
						<thead class="table-warning ">
							<tr>
								<th>Roll Number</th>
								<th>Name</th>
								<th>In Date</th>
								<th>In Time</th>
								<th>Out Date</th>
								<th>Out Time</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
							<% List<EntryDetail> li = new Administator().getAllRecords(); %>
							<% for(EntryDetail ed : li) { %>
							<tr>
								<td><%= ed.getRollNumber() %></td>
								<td><%= ed.getName() %></td>
								<td><%= ed.getInDate() %></td>
								<td>
									<% 
										SimpleDateFormat sf1 = new SimpleDateFormat("HH:mm:ss");
									 	int[] s1 = Arrays.stream(sf1.format(ed.getInTime()).split(":")).mapToInt(n->Integer.parseInt(n)).toArray();
							         %>
							         <% if(s1[0] > 12) {%>
							         	<%=(s1[0]-12)+":"+s1[1]+":"+s1[2]+" "+"PM"%>
							         <% }else { %>
							         	<%=s1[0]+":"+s1[1]+":"+s1[2]+" "+"AM" %>
							         <% } %>
								</td>

								<td>
									<% if(ed.getOutDate()!=null) {%>
										<%= ed.getOutDate() %>
									<% }else{ %>
										<%= "-" %>
									<% } %>
								</td>
								<td>
									<% if(ed.getOutTime()!=null){%>
										<% 
											SimpleDateFormat sf2 = new SimpleDateFormat("HH:mm:ss");
									 		int[] s2 = Arrays.stream(sf2.format(ed.getInTime()).split(":")).mapToInt(n->Integer.parseInt(n)).toArray();
							         	%>
							         		<% if(s2[0] > 12) {%>
							         			<%=(s2[0]-12)+":"+s2[1]+":"+s2[2]+" "+"PM"%>
							         		<% }else { %>
							         			<%=s2[0]+":"+s2[1]+":"+s2[2]+" "+"AM" %>
							         		<% } %>
									<% }else{ %>
										<%= "-" %>
									<% } %>
								</td>
								<td>
									<% if(ed.isEntered()) {%>
										<%= "Check In" %>
									<% }else{ %>
										<%= "Check Out" %>
									<% } %>
								</td>
							</tr>							
							<% } %>							
						</tbody>
					</table>

				</div>
				<div class="text-center view_more_entries_btn">
					<form action="AdminSearchEntryPage.jsp" method="get">
					<a class="btn btn-primary" href="AdminSearchEntryPage.jsp">
						View more
						<svg xmlns="http://www.w3.org/2000/svg" width="14" height="14"
							fill="currentColor" class="bi bi-caret-right-fill"
							viewBox="0 0 16 16">
                        <path
								d="m12.14 8.753-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z" />
                      </svg>
					</a>
					</form>
				</div>
			</div>
		</div>
	</div>
<script>
var loginDetailsElement = document.querySelector('.time_updation');
function updateDateTime() {
    var currentDate = new Date();
    var formattedDateTime = currentDate.toLocaleString('en-US', {
        hour: 'numeric',
        minute: 'numeric',
        second: 'numeric',
        month: 'numeric',
        day: 'numeric',
        year: 'numeric'
    });
    
    
    loginDetailsElement.innerHTML =  formattedDateTime;
}

 
updateDateTime();
setInterval(updateDateTime, 1000);
</script>
</body>

</html>