package com.karpagam.servlet;

import java.io.IOException;

import com.karpagam.bean.AdminTable;
import com.karpagam.service.Administator;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/adminChangePassword")
public class AdminChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userName = request.getParameter("userName");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		HttpSession session = request.getSession();
		AdminTable adminTable = new AdminTable();
		adminTable.setUserName(userName);
		adminTable.setPassWord(oldPassword);
		if(new Administator().isValidAdminLogin(adminTable)) {
			AdminTable alter = new AdminTable();
			alter.setUserName(userName);
			alter.setPassWord(newPassword);
			if(new Administator().changePassword(alter)) {
				session.setAttribute("changed", "yes");
				response.sendRedirect("AdminChangePassword.jsp");
			}
		}else {
			session.setAttribute("changed", "no");
			response.sendRedirect("AdminChangePassword.jsp");
		}
	}

}
