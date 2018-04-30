package com.movieshop.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movieshop.model.UserDAO;

@WebServlet("/hometwo")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		if ((request.getSession(false) == null) || (request.getSession().getAttribute("id") == null)) {
//			response.sendRedirect("./home");
//			System.out.println("abvbcvc");
//			return;
//		}
//		System.out.println(request.getSession(false));
//		System.out.println(request.getSession().getAttribute("id"));
//		System.out.println("testing");
//
//		String name = (String) request.getSession().getAttribute("lastName");
	}
}
