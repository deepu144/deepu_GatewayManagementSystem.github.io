package com.karpagam.servlet;

import java.io.IOException;

import com.karpagam.service.Administator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/doAction")
public class ShiftAndTrunc extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static Administator administator = new Administator();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(administator.shiftStudentToHistoryRecords()) {
			administator.truncateStudentEntryTable();
		}
		if(administator.shiftStaffToHistoryRecords()) {
			administator.truncateStaffEntryTable();
		}
		response.sendRedirect("home");
	}

}
