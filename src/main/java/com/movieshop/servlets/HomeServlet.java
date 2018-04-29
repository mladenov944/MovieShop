package com.movieshop.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movieshop.model.UserDAO;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/homeeeeeeeeeee")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if ((request.getSession(false) == null) || (request.getSession().getAttribute("username") == null)) {
			response.sendRedirect("./");
			return;
		}

		response.getWriter().println("<h1> Bravo ti se logna !</h1>");
		response.getWriter().println("<ul>");
		UserDAO dao = new UserDAO();

		// if ((request.getCookies() != null) && (request.getCookies().length >
		// 0)) {

		// String username = request.getCookies()[0].getValue();
		String username = (String) request.getSession().getAttribute("username");
		response.getWriter().println("</ul>");
		response.getWriter().print("<a href='./logout'> Log out </a>");
	}
}
