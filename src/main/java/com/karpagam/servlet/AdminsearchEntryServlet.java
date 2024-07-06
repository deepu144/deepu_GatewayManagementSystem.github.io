package com.karpagam.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import com.karpagam.service.Administator;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/adminEntrypageServlet")
public class AdminsearchEntryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Administator administator = new Administator();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
		Date date = null;
		Time fromTime = null;
		Time toTime = null;
		try {
			String from = request.getParameter("fromTime");
			String to = request.getParameter("toTime");
			if (from.length() == 0) {
				from = "00:00:00";
			} else {
				from += ":00";
			}
			if (to.length() == 0) {
				to = "23:59:59";
			} else {
				to += ":00";
			}
			java.util.Date d = sdfDate.parse(request.getParameter("date"));
			java.util.Date t1 = sdfTime.parse(from);
			java.util.Date t2 = sdfTime.parse(to);
			date = new Date(d.getTime());
			fromTime = new Time(t1.getTime());
			toTime = new Time(t2.getTime());
		} catch (ParseException e) {
		}
		String category = request.getParameter("category");
		String rollNumber = request.getParameter("rollNumber");
		String sort = request.getParameter("sortBy");
		Boolean inDate = null ;
		if(sort.equals("inDate")) {
			inDate = true;
		}else {
			inDate = false;
		}
		String today = sdfDate.format(Calendar.getInstance().getTime());
		java.util.Date today1 = null;
		try {
			today1 = sdfDate.parse(today);
		} catch (ParseException e) {
		}
		Date today2 = new Date(today1.getTime());
		System.out.println(date+" "+fromTime+" "+toTime);
		
		List<Object> filter = null;

		if (today2.equals(date)) {
			if (category.equalsIgnoreCase("Student")) {
				if (rollNumber.length() == 0) {
					filter = administator.getTodayStudentDetailInFilter(date,inDate, fromTime, toTime);
					request.setAttribute("check", "TSD");
				} else {
					filter = administator.getTodayStudentDetailInFilter(date,inDate, fromTime, toTime, rollNumber);
					request.setAttribute("check", "TSRT");
				}
			} else if (category.equalsIgnoreCase("Staff")) {
				if (rollNumber.length() == 0) {
					filter = administator.getTodayStaffDetailInFilter(date,inDate, fromTime, toTime);
					request.setAttribute("check", "TSFD");
				} else {
					filter = administator.getTodayStaffDetailInFilter(date,inDate, fromTime, toTime, rollNumber);
					request.setAttribute("check", "TSFRD");
				}
			}else if(category.equalsIgnoreCase("Others")){
				if (rollNumber.length() == 0) {
					filter = administator.getTodayOtherStudentDetailInFilter(date,inDate, fromTime, toTime);
					request.setAttribute("check", "TOSD");
				} else {
					filter = administator.getTodayOtherStudentDetailInFilter(date,inDate, fromTime, toTime, rollNumber);
					request.setAttribute("check", "TOSRT");
				}
			}
		} else {
			if (category.equalsIgnoreCase("Student")) {
				if (rollNumber.length() == 0) {
					filter = administator.getStudentHistoryDetailInFilter(date,inDate, fromTime, toTime);
					request.setAttribute("check", "HSD");
				} else {
					filter = administator.getStudentHistoryDetailInFilter(date,inDate, category, fromTime, toTime,
							rollNumber);
					request.setAttribute("check", "HSRD");
				}
			} else if (category.equalsIgnoreCase("Staff")) {
				if (rollNumber.length() == 0) {
					filter = administator.getStaffHistoryDetailInFilter(date,inDate, category, fromTime, toTime);
					request.setAttribute("check", "HSFD");
				} else {
					filter = administator.getStaffHistoryDetailInFilter(date,inDate, category, fromTime, toTime,
							rollNumber);
					request.setAttribute("check", "HSFRD");
				}
			}else if(category.equalsIgnoreCase("Others")){
				if (rollNumber.length() == 0) {
					filter = administator.getOtherStudentHistoryDetailInFilter(date,inDate, fromTime, toTime);
					request.setAttribute("check", "HOSD");
				} else {
					filter = administator.getOtherStudentHistoryDetailInFilter(date,inDate, category, fromTime, toTime,
							rollNumber);
					request.setAttribute("check", "HOSRD");
				}
			}
		}
		List<Object> printAttribute = Arrays.asList(date,category,fromTime,toTime);
		RequestDispatcher rd = request.getRequestDispatcher("AdminSearchEntryPage.jsp");
		request.setAttribute("filteredResult", filter);
		request.setAttribute("filterInfo", printAttribute);
		rd.forward(request, response);
	}

}
