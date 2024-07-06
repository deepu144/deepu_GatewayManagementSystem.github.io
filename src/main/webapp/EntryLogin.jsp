<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>ENTRY LOGIN</title>
<link rel="stylesheet" href="AdminLogin.css">
<script src="AdminLogin.js" defer></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon"
	href="https://res.cloudinary.com/dkbwdkthr/image/upload/v1694090918/KCE%20LOGO.jpg">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous" />
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
	integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
	crossorigin="anonymous"></script>

</head>

<body>
	<% response.addHeader("Cache-control", "no-cache, no-store, must-revalidate"); %>
 	<% if(session.getAttribute("incorrect")!=null) {%>
 		<!-- PASSWORD INCORRECT POP UP -->
 		<div class="pop_alert_error text-center">
       		Invalid Username or Password ! <span><i class="fa-solid fa-xmark"></i></span>
   		 </div>
   		 <% session.removeAttribute("incorrect"); %>
 	<% } %>
	<div class="container-fluid">
		<div class="row loginPageMainContainer">
			<div class="col-11 col-md-6 col-lg-5  loginBox">
				<div class="logoContainer"></div>
				<div class="kceNameContainer">
					<h1 class="kceName">KARPAGAM</h1>
					<p class="kceCollege">COLLEGE OF ENGINEERING</p>
					<p class="kceCollegeQuotes">Rediscover | Refine | Redefine</p>
				</div>
				<hr class="hrLine" />
				<div class="loginInputContainer">
					<h1 class="loginHeading">Login</h1>
					<form action="entryLogin" id="studentLogin" method="post">
						<input type="text" id="userName" class="inputBox"
							placeholder="User Name" name="username"> <br> <input
							type="password" id="loginPassword" class="inputBox"
							placeholder="Password" name="password"> <br>
						<div class="buttonContainer">
							<button class="clearButton" id="clearButton">Clear</button>
							<input type="submit" value="Login" class="loginButton"
								id="loginButton">
						</div>
					</form>
				</div>
				<p class="deicignedByLine">&#169; Designed by 2022-2026 Batch CS/IT🔥
					</p>
			</div>
		</div>
	</div>
</body>

</html>