package com.karpagam.servlet;

import java.io.IOException;
import com.karpagam.bean.AdminTable;
import com.karpagam.service.Administator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addAdminServlet")
public class AddAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		HttpSession session = request.getSession();
		
		if(password1.equals(password2)) {
			AdminTable adminTable = new AdminTable();
			adminTable.setUserName(userName);
			adminTable.setPassWord(password1);
			if(new Administator().addAdmin(adminTable)) {
				session.setAttribute("checkInserted", "yes");
				response.sendRedirect("AddAdmin.jsp");
			}
			else {
				session.setAttribute("checkInserted", "no");
				response.sendRedirect("AddAdmin.jsp");
			}
		}
	}

}
