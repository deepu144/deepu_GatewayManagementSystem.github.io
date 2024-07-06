package com.karpagam.servlet;

import java.io.IOException;
import java.io.InputStream;

import com.karpagam.service.Administator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/addStaffDBServlet")
@MultipartConfig
public class AddStaffInformationDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part filePart = request.getPart("staffDB");
        InputStream fileContent = filePart.getInputStream();
        HttpSession session = request.getSession();
        if(new Administator().uploadStaffInformationDB(fileContent)) {
        	session.setAttribute("StaffDB", "Updated");
        	response.sendRedirect("AdminDatabaseUpload.jsp");
        }else {
        	session.setAttribute("StaffDB", "no");
        	response.sendRedirect("AdminDatabaseUpload.jsp");
        }
	}

}
