package com.movieshop.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/loggedIn")

public class LoggedInHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if ((request.getSession(false) == null) || (request.getSession().getAttribute("id") == null)) {
			response.sendRedirect("./home");
			System.out.println("potrebirelq ne e lognat");
			return;
		}
		System.out.println(request.getSession(false));
		System.out.println(request.getSession().getAttribute("id"));
		System.out.println("logged in");

		String name = (String) request.getSession().getAttribute("lastName");
		String nextJSP = "./loggedInHome"; // brao ! | ne razbrah kvo imash predvid :D ama sa mislq che bachka :D q vij neshto..
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);
		return;
	}
	

}
