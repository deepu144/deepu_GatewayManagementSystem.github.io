<%@page import="com.karpagam.bean.OtherStudentEntryHistory"%>
<%@page import="com.karpagam.bean.OtherStudentEntry"%>
<%@page import="com.karpagam.bean.StaffHistory"%>
<%@page import="com.karpagam.bean.StudentHistory"%>
<%@page import="com.karpagam.bean.StaffEntryDetail"%>
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
	<% response.addHeader("Cache-control", "no-cache, no-store, must-revalidate"); %>
	<% if(session.getAttribute("userName")==null) {%>
		<% response.sendRedirect("AdminLogin.jsp"); %>
	<% } %>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Entry</title>
    <link rel="stylesheet" href="AdminSearchEntryPage.css">
    <script src="AdminSearchEntryPage.js" defer></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/e96d258b08.js" crossorigin="anonymous"></script>
</head>

<body id="invoice">
	
    <div class="container-fluid text-center col-lg-12">
        <div class="row main_header  " id="main_header_id">
            <div class="gate_entry_header" id="gate_entry_header_id">
                <div class="gate_entry_header_img" id="gate_entry_header_img_id">
                    <img src="/GateWay_Management_System/images/KCE.png" height="100px" id="KCE_img" alt="KCE">
                </div>
                <div class="gate_entry_login_details" id="gate_entry_login_details">
                    <div class=""></div>
                    <div class="welcome_info">
                        Welcome, <span id="owner_info"><%=session.getAttribute("userName") %></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light" id="nav_id">
        <div class="container-fluid">
            <a class="navbar-brand bg-warning p-2 py-0" href="#">Search Entry</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="AdminHomePage.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="AddAdmin.jsp">Add Member</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="AdminChangePassword.jsp">Change Password</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="AdminDatabaseUpload.jsp">Database</a>
                    </li>
                    <li class="nav-item">
                        <button class="nav-link" id="print_id" href="#">Print</button>
                    </li>
                    
                </ul>
                <form action="logoutAdmin" method="post">
                <ul class="navbar-nav">
                     <button id="logout_btn" > <span class="glyphicon glyphicon-log-in"></span> <svg
                        xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                        class="bi bi-caret-left-fill" viewBox="0 0 16 16">
                        <path
                            d="m3.86 8.753 5.482 4.796c.646.566 1.658.106 1.658-.753V3.204a1 1 0 0 0-1.659-.753l-5.48 4.796a1 1 0 0 0 0 1.506z" />
                    </svg> Logout</button>
                </ul>
              </form>
            </div>
        </div>
    </nav>
    
    <div class="container-fluid filter_details " id="filter_details_id">
    <form action="adminEntrypageServlet" method="post">
        <div class="row filter_details_bar">
            <div class="filter_by_date">
                <label for="dateInput"><sup> <span class="red_star">★</span> </sup>
                Entry Date : </label>
                <input type="date" id="dateInput" name="date" class="date-placeholder" required />
            </div>
            <div class="filter_by_catagory">
                <label for="category"><sup><span class="red_star">★</span></sup>
              					  Catagory : </label>
                <select name="category" id="category" class="category" required>
                    <option value="Staff">Staff</option>
                    <option value="Student" selected>Student</option>
                    <option value="Others">Pharmacy/Nursing</option>
                </select>
            </div>
            <div class="filter_by_fromtime">
                <label for="time">From Time : </label>
                <input type="time" id="fromTime" name="fromTime" class="fromTime" placeholder="From Time">
            </div>
            <div class="filter_by_totime">
                <label for="toTime">To Time &nbsp;: </label>
                <input type="time" id="toTime" name="toTime" class="toTime" placeholder="To Time">
            </div>
            <div class="filter_by_rollnum">
                <label for="rollNumber">RollNumber </label>
                <input type="text" id="rollNumber" name="rollNumber" class="rollNumber" placeholder="717822p212">
            </div>
             <div class="filter_by_sort">
                  <label for="sort_by"><sup><span class="red_star">★</span></sup> Sort by </label>
                <select id="sort_by" name = "sortBy"> 
                <option value="inDate">In Date</option>
                <option value="OutDate">Out Date</option>
                </select>
            </div>
        </div>
            <div class="text-center">
               <button class="btn btn-warning m-4" id="Search_btn">Search <svg xmlns="http://www.w3.org/2000/svg" width="18"
                height="18" fill="currentColor" class="bi bi-search p-1" viewBox="0 0 16 16">
                <path
                    d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0" />
            </svg></button>
            </div>
     
        </form>
    </div>
    
         
    <!-- Gate Entry Log Table -->
    <div class="container text-center" id="gateEntryLog">
    <!--     <button type="button" class="btn btn-warning m-4" id="Search_btn">Search <svg xmlns="http://www.w3.org/2000/svg" width="18"
                height="18" fill="currentColor" class="bi bi-search p-1" viewBox="0 0 16 16">
                <path
                    d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0" />
            </svg></button>
    --> 
            <div class="print_info_main container col-lg-12" id="print_info_main_id">
        <div class="print_info_details container">
            <div class="Print_date_info">
            <% List<Object> filterInfo = (List<Object>)request.getAttribute("filterInfo"); %>
            <% if(filterInfo!=null) { %>
                <p><b>Date : </b>
                    <span><%= filterInfo.get(0) %></span>
            </div>
            <div class="Print_Catagory_info">
                <p><b>Catagory : </b>
                    <span><%= filterInfo.get(1) %></span>
            </div>
            <div class="Print_Indate_info">
                <p><b> From Time : </b>
                    <span>
                    <% 
						SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
				 		int[] s = Arrays.stream(ft.format(filterInfo.get(2)).split(":")).mapToInt(n->Integer.parseInt(n)).toArray();
			         %>
			         <% if(s[0] > 12) {%>
			         	<%=(s[0]-12)+":"+s[1]+":"+s[2]+" "+"PM"%>
			         <% }else { %>
			         	<%=s[0]+":"+s[1]+":"+s[2]+" "+"AM" %>
			         <% } %>          
                    </span>
            </div>
            <div class="Print_Indate_info">
                <p><b> To Time : </b>
                    <span>
                    <% 
						SimpleDateFormat sf2 = new SimpleDateFormat("HH:mm:ss");
				 		int[] s2 = Arrays.stream(sf2.format(filterInfo.get(3)).split(":")).mapToInt(n->Integer.parseInt(n)).toArray();
			         %>
			         <% if(s2[0] > 12) {%>
			         	<%=(s2[0]-12)+":"+s2[1]+":"+s2[2]+" "+"PM"%>
			         <% }else { %>
			         	<%=s2[0]+":"+s2[1]+":"+s2[2]+" "+"AM" %>
			         <% } %>
                    </span>
         <% } %>
            </div>
        </div>
    </div>
        <div class="row">
            <div class="col">
            	<% if(request.getAttribute("filteredResult")!=null) {%>
                	<% List<Object> li = (List<Object>) request.getAttribute("filteredResult"); %>
	                <% if(li.size()==0 || li==null) {%>
	                		<!-- NO RECORD FOUND -->
	                		<h1>NO RECORD FOUND</h1>
	                <% }else { %>
	                 <div id="entry_report_name" class="text-center">Entry Report</div>
			                <table class="table table-bordered text-center ">
			                    <thead class="table-warning ">
			                        <tr>
			                            <th>Roll No</th>
			                            <th>Name</th>
			                            <th>In Date</th>
			                            <th>In Time</th>
			                            <th>Out Date</th>
			                            <th>Out Time</th>
			                            <th>Status</th>
			                        </tr>
			                    </thead>
			                    <tbody id="tbody">                                       		
	                    		<% String check =(String)request.getAttribute("check"); %>
	                    		<% if(check.equals("TSD") || check.equals("TSRT")) { %>
	                    		
	                    			<% for(Object obj : li) { %>
	                    				<% EntryDetail ed = (EntryDetail)obj; %>
			                    		<tr>
				                            <td><%=ed.getRollNumber() %></td>
				                            <td><%=ed.getName() %></td>
				                            <td><%=ed.getInDate() %> </td>
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
											 		int[] s2 = Arrays.stream(sf2.format(ed.getOutTime()).split(":")).mapToInt(n->Integer.parseInt(n)).toArray();
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
													<%= "In" %>
												<% }else{ %>
													<%= "Out" %>
												<% } %>
											</td>
			                        	</tr>
			                        <% } %>
			                        <% }else if(check.equals("TSFD")|| check.equals("TSFRD")) { %>
			                        	<% for(Object obj : li) {%>
			                        		<% StaffEntryDetail ed = (StaffEntryDetail)obj; %>
				                        	<tr>
					                            <td><%=ed.getRollNumber() %></td>
					                            <td><%=ed.getName() %></td>
					                            <td><%=ed.getInDate() %> </td>
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
												 		int[] s2 = Arrays.stream(sf2.format(ed.getOutTime()).split(":")).mapToInt(n->Integer.parseInt(n)).toArray();
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
														<%= "In" %>
													<% }else{ %>
														<%= "Out" %>
													<% } %>
												</td>
				                        	</tr>
				                        <% } %>
			                        <% }else if(check.equals("HSD")|| check.equals("HSRD")) { %>
			                        	<% for(Object obj : li) {%>
			                        		<% StudentHistory ed = (StudentHistory)obj; %>
				                        	<tr>
					                            <td><%=ed.getRollNumber() %></td>
					                            <td><%=ed.getName() %></td>
					                            <td><%=ed.getInDate() %> </td>
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
												 		int[] s2 = Arrays.stream(sf2.format(ed.getOutTime()).split(":")).mapToInt(n->Integer.parseInt(n)).toArray();
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
														<%= "In" %>
													<% }else{ %>
														<%= "Out" %>
													<% } %>
												</td>
				                        	</tr>
				                        <% } %>
			                        <% }else if(check.equals("HSFD")|| check.equals("HSFRD")) { %>
			                        	<% for(Object obj : li) { %>
			                        	<% StaffHistory ed = (StaffHistory)obj; %>
			                        	<tr>
				                            <td><%=ed.getRollNumber() %></td>
				                            <td><%=ed.getName() %></td>
				                            <td><%=ed.getInDate() %> </td>
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
											 		int[] s2 = Arrays.stream(sf2.format(ed.getOutTime()).split(":")).mapToInt(n->Integer.parseInt(n)).toArray();
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
		                        <% } else if(check.equals("TOSRT") || check.equals("TOSD")) { %>
		                        		<% for(Object obj : li) { %>
			                        	<% OtherStudentEntry ed = (OtherStudentEntry)obj; %>
			                        	<tr>
				                            <td><%=ed.getRollNumber() %></td>
				                            <td><%=ed.getName() %></td>
				                            <td>
				                            	<%if(ed.getInDate()!=null) { %>
				                            		<%= ed.getInDate() %>
				                            	<% }else { %>
				                            		<%= " - " %>
				                            	<% } %> 
				                            </td>
				                            <td>
				                            	<% if(ed.getInTime()!=null) { %>
					                            	<% 
														SimpleDateFormat sf1 = new SimpleDateFormat("HH:mm:ss");
												 		int[] s1 = Arrays.stream(sf1.format(ed.getInTime()).split(":")).mapToInt(n->Integer.parseInt(n)).toArray();
											         %>
											         <% if(s1[0] > 12) {%>
											         	<%=(s1[0]-12)+":"+s1[1]+":"+s1[2]+" "+"PM"%>
											         <% }else { %>
											         	<%=s1[0]+":"+s1[1]+":"+s1[2]+" "+"AM" %>
											         <% } %>
											     <% }else { %>
											     	<%= " - " %>
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
											<% if(ed.getOutTime()!=null){ %>
												<% 
													SimpleDateFormat sf2 = new SimpleDateFormat("HH:mm:ss");
											 		int[] s2 = Arrays.stream(sf2.format(ed.getOutTime()).split(":")).mapToInt(n->Integer.parseInt(n)).toArray();
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
												<% if(ed.isEntered()) { %>
													<%= "Check In" %>
												<% }else{ %>
													<%= "Check Out" %>
												<% } %>
											</td>
			                        	</tr>
			                        <% } %>
		                        <% }else if(check.equals("HOSRD") || check.equals("HOSD")) { %>
		                        		<% for(Object obj : li) { %>
			                        	<% OtherStudentEntryHistory ed = (OtherStudentEntryHistory)obj; %>
			                        	<tr>
				                            <td><%=ed.getRollNumber() %></td>
				                            <td><%=ed.getName() %></td>
				                            <td>
				                            	<%if(ed.getInDate()!=null) { %>
				                            		<%= ed.getInDate() %>
				                            	<% }else { %>
				                            		<%= " - " %>
				                            	<% } %> 
				                            </td>
				                            <td>
				                            	<% if(ed.getInTime()!=null) { %>
					                            	<% 
														SimpleDateFormat sf1 = new SimpleDateFormat("HH:mm:ss");
												 		int[] s1 = Arrays.stream(sf1.format(ed.getInTime()).split(":")).mapToInt(n->Integer.parseInt(n)).toArray();
											         %>
											         <% if(s1[0] > 12) {%>
											         	<%=(s1[0]-12)+":"+s1[1]+":"+s1[2]+" "+"PM"%>
											         <% }else { %>
											         	<%=s1[0]+":"+s1[1]+":"+s1[2]+" "+"AM" %>
											         <% } %>
											     <% }else { %>
											     	<%= " - " %>
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
											<% if(ed.getOutTime()!=null){ %>
												<% 
													SimpleDateFormat sf2 = new SimpleDateFormat("HH:mm:ss");
											 		int[] s2 = Arrays.stream(sf2.format(ed.getOutTime()).split(":")).mapToInt(n->Integer.parseInt(n)).toArray();
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
												<% if(ed.isEntered()) { %>
													<%= "Check In" %>
												<% }else{ %>
													<%= "Check Out" %>
												<% } %>
											</td>
			                        	</tr>
			                        <% } %>
		                        <% } %>
		                        </tbody>
		                    </table>
	                        <% } %>
                    	<% }else { %>
	                        <% List<EntryDetail> li = new Administator().getAllRecords(); %>
	                        	<% if(li.size()==0) {%>
	                        			<h1>NO RECORD FOUND</h1>
	                        			<!--NO RECORD FOUND-->
	                        	<% }else{ %>
	                        		<table class="table table-bordered text-center " id="table_info">
			                    		<thead class="table-warning ">
					                        <tr>
					                            <th>Roll No</th>
					                            <th>Name</th>
					                            <th>In Date</th>
					                            <th>In Time</th>
					                            <th>Out Date</th>
					                            <th>Out Time</th>
					                            <th>Status</th>
					                        </tr>
			                    		</thead>
					                    <tbody id="tbody">
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
													 		int[] s2 = Arrays.stream(sf2.format(ed.getOutTime()).split(":")).mapToInt(n->Integer.parseInt(n)).toArray();
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
														<%= "In" %>
													<% }else{ %>
														<%= "Out" %>
													<% } %>
												</td>
											</tr>								
											<% } %>
										</tbody>
								</table>
							<% } %>
						<% } %>
            	</div>
        	</div>
    	</div>
</body>

</html>