<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="ErrorPage.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change Password</title>
    <link rel="stylesheet" href="AdminChangePassword.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/e96d258b08.js" crossorigin="anonymous"></script>
</head>

<body>
	<% response.addHeader("Cache-control", "no-cache, no-store, must-revalidate"); %>
	<% if(session.getAttribute("userName")==null) {%>
		<% response.sendRedirect("AdminLogin.jsp"); %>
	<% } %>
	<% if(session.getAttribute("changed")!=null) {%>
			<% String status = (String)session.getAttribute("changed");%>
			<% if(status.equals("yes")) {%>
					<!-- Password Changed PopUp -->
					<div class="pop_alert_success text-center">
      					   Success ! <span ><i class="fa-solid fa-xmark"></i></span>
   					 </div>
				<% session.removeAttribute("changed"); %>
			<% }else if(status.equals("no")) {%>
					<!-- Password Not Changed PopUp (INVALID USERNAME OR PASSWORD)-->
					<div class="pop_alert_error text-center">
       					 Invalid Username or Password ! <span ><i class="fa-solid fa-xmark"></i></span>
   				    </div>
				<% session.removeAttribute("changed"); %>
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
                        Welcome, <span id="owner_info"><%= session.getAttribute("userName") %></span>
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
						<li class="nav-item nav_item_home"><a class="nav-link"
							href="AdminHomePage.jsp">Home</a></li>
						<li class="nav-item"><a class="nav-link"
							href="AdminSearchEntryPage.jsp">Search Entry</a></li>
						<li class="nav-item nav_item_add_member"><a class="nav-link"
							href="AddAdmin.jsp">Add Member</a></li>
						<li class="nav-item nav_item_change_pwd"><a class="nav-link"
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

            <!-- Content of the right side -->
            <div class="col-lg-5 col-md-10 col-sm-7 mx-auto my-auto">
                <form class="mt-3" action="adminChangePassword" method="post">
                    <div class="container change_password_form">
                        <div class="mb-3">
                            <h5 class="text-center change_password_header">Change Password</h5> <!-- Change to different texture -->
                        </div>
                        <div class="mb-3">
                            <label for="username" class="form-label">Username</label>
                            <input type="text" class="form-control" id="username" name="userName">
                        </div>
                        <div class="mb-3">
                            <label for="old_password" class="form-label">Old Password</label>
                            <input type="password" class="form-control" id="old_password" name="oldPassword">
                        </div>
                        <div class="mb-3">
                            <label for="new_password" class="form-label">New Password</label>
                            <input type="password" class="form-control" id="new_password" name="newPassword">
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary change_password_submit_btn">Submit</button> <!-- Center submit button -->
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
    setInterval(updateDateTime, 1000);</script>
</body>

</html>