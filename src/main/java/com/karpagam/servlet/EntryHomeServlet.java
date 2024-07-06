package com.karpagam.servlet;

import java.io.IOException;

import com.karpagam.service.Administator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/home")
public class EntryHomeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(86400);
		Administator administator = new Administator();
		session.setAttribute("inCount", administator.getInCount());
		session.setAttribute("outCount", administator.getOutCount());
		response.sendRedirect("EntryPage.jsp");
		return;
	}

}
