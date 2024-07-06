package com.karpagam.servlet;

import java.io.IOException;

import com.karpagam.bean.StaffInformation;
import com.karpagam.bean.StudentInformation;
import com.karpagam.service.Administator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/entrySubmit")
public class EntryPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Administator administator = null; 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String rollNumber = request.getParameter("rollNo");
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(86400);
		administator = new Administator();
		if(rollNumber.length()>=6 && rollNumber.length()<=12){
			session.setAttribute("Person", "Student");
			StudentInformation sInformation = administator.getStudentInformation(rollNumber);	
			if(sInformation==null) {
				if (administator.whetherOtherStudentEntered(rollNumber)) {
					administator.UpdateOldOtherStudentEntry(rollNumber);
					session.setAttribute("newEntry", "Welcome to KCE !");
					Long count = (Long)session.getAttribute("inCount");
					count++;
					session.setAttribute("inCount", count);
				} else {
					if (administator.insertNewOtherStudentEntry(rollNumber)) {
						Long count = (Long)session.getAttribute("outCount");
						count++;
						session.setAttribute("outCount", count);
					}
				}
				StudentInformation si = new StudentInformation();
				si.setRollNumber(rollNumber);
				session.setAttribute("StudentInformation" , si);
				response.sendRedirect("EntryPage.jsp");
				return;
			}else {
				if(administator.whetherStudentEntered(rollNumber)) {
					administator.UpdateOldStudentEntry(rollNumber);
					Long count = (Long)session.getAttribute("outCount");
					count++;
					session.setAttribute("outCount", count);
				}
				else {
					if(administator.insertNewStudentEntry(rollNumber , sInformation.getName())) {
						session.setAttribute("newEntry", "Welcome to KCE !");
						Long count = (Long)session.getAttribute("inCount");
						count++;
						session.setAttribute("inCount", count);
					}
				}
				session.setAttribute("StudentInformation" , sInformation);
				response.sendRedirect("EntryPage.jsp");
				return;
			}
		}else if(rollNumber.length()==5) {
			session.setAttribute("Person", "Staff");
			StaffInformation staffInformation = administator.getStaffInformation(rollNumber);
			if(staffInformation==null) {
				session.setAttribute("noRecord", "yes");
				response.sendRedirect("EntryPage.jsp");
				return;
			}else {
				if(administator.whetherStaffEntered(rollNumber)) {
					administator.updateOldStaffEntry(rollNumber);
					Long count = (Long)session.getAttribute("outCount");
					count++;
					session.setAttribute("outCount", count);
				}else {
					if(administator.insertNewStaffEntry(rollNumber , staffInformation.getName())) {
						session.setAttribute("newEntry", "Welcome to KCE !");
						Long count = (Long)session.getAttribute("inCount");
						count++;
						session.setAttribute("inCount", count);
					}
				}
				session.setAttribute("StaffInformation" , staffInformation);
				response.sendRedirect("EntryPage.jsp");
				return;
			}
		}else {
			response.sendRedirect("EntryPage.jsp");
			return;
		}
	}

}
