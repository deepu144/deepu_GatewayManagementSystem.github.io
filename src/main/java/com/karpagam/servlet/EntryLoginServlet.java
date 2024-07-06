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

@WebServlet("/entryLogin")
public class EntryLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName= request.getParameter("username");
        String password=request.getParameter("password");
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(86400);
        AdminTable user = new AdminTable();
        user.setUserName(userName);
        user.setPassWord(password);
        
        if(new Administator().isValidAdminLogin(user)) {
        	session.setAttribute("Entry", "Yes");
        	response.sendRedirect("home");
        }
        else {
        	session.setAttribute("incorrect", "yes");
			response.sendRedirect("EntryLogin.jsp");
        }
	}

}
