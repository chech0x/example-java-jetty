package org.example;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CurrentTime extends HttpServlet {
	private SimpleDateFormat formatter = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 484819681427284877L;
	public CurrentTime() {
		String dateMask="HH:mm:ss dd/MM/yy Z";
		formatter = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
		formatter.applyPattern(dateMask);

	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println("Hora Actual:"+ formatter.format(new Date()));
	}
}
