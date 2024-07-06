<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="ErrorPage.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add member</title>
<link rel="stylesheet" href="AddAdmin.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/e96d258b08.js"
	crossorigin="anonymous" defer></script>
</head>

<body>
	<% response.addHeader("Cache-control", "no-cache, no-store, must-revalidate"); %>
	<% if(session.getAttribute("userName")==null) {%>
		<% response.sendRedirect("AdminLogin.jsp"); %>
	<% } %>
	<% if(session.getAttribute("checkInserted")!=null) {%>
		<% String status = (String)session.getAttribute("checkInserted"); %>
		<% if(status.equals("yes")){ %>
				<div class="pop_alert_success text-center">
       					 Success ! <span ><i class="fa-solid fa-xmark"></i></span>
  			    </div>
	  			<% session.removeAttribute("checkInserted"); %>
	  			
		<% }else if(status.equals("no")) { %>
				 <div class="pop_alert_error text-center">
       				Username already Exists ! <span><i class="fa-solid fa-xmark"></i></span>
   				 </div>
	  			<% session.removeAttribute("checkInserted"); %>
		<% } %>
	<% } %>
	<div class="container-fluid">
		<div class="row main_header">
			<div class="gate_entry_header">
				<div class="gate_entry_header_img">
					<img src="/GateWay_Management_System/images/KCE.png" height="100px" id="KCE_img" alt="KCE">
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
	<div class="container-fluid">
		<div class="row">
			<nav
				class="col-lg-2 col-sm-3 navbar navbar-expand-lg navbar-expand-sm  navbar-light bg-light flex-column">
				<div class="collapse navbar-collapse">
					<ul class="navbar-nav flex-column">
						<li class="nav-item  "><a class="nav-link"
							href="AdminHomePage.jsp">Home</a></li>
						<li class="nav-item"><a class="nav-link"
							href="AdminSearchEntryPage.jsp">Search Entry</a></li>
						<li class="nav-item nav_item_add_member"><a class="nav-link"
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


			<div class="col-lg-5 col-md-8   mx-auto my-auto border_top_add_member">
				<form class="mt-3" action="addAdminServlet" method="post">
					<div class="container add_member_form">
						<div class="mb-3">
							<h5 class="text-center add_member_header">Add Member</h5>
						</div>
						<div class="mb-3">
							<label for="username" class="form-label">Username</label> <input
								type="text" name="userName" class="form-control" id="username">
						</div>
						<div class="mb-3">
							<label for="old_password" class="form-label"> Create
								Password</label> <input type="password" name="password1" class="form-control"
								id="old_password">
						</div>
						<div class="mb-3">
							<label for="new_password" class="form-label">Confirm
								Password</label> <input type="password" name="password2" class="form-control"
								id="new_password">
						</div>
						<div class="text-center">
							<button type="submit"
								class="btn btn-primary add_member_submit_btn">Submit</button>
						</div>
					</div>
				</form>
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