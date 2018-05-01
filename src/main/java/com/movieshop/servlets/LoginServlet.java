package com.movieshop.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.movieshop.model.User;
import com.movieshop.model.UserDAO;
import com.movieshop.model.UserException;

@WebServlet("/logins")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("pass");

		UserDAO dao = new UserDAO();
		try {
			User user = dao.login(email, password);

			if (user.getId() != 0) {

				HttpSession session = request.getSession();
				session.setAttribute("cash", user.getMoney());
				session.setAttribute("id", user.getId());
				session.setAttribute("name", user.getName());
				session.setAttribute("lastName", user.getLastName());
				session.setMaxInactiveInterval(600);
				Float money = user.getMoney();

				// trqbva da proverim login kak da se sluchva
				// proverka dali go ima v DB
				response.sendRedirect("./loggedIn");
			}

		} catch (UserException e) {
			response.getWriter().println("<h1> You did not log in ! </h1>");
			response.sendRedirect("./login");
			e.printStackTrace();
		}

	}

}
