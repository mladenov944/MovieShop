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
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("pass");
		
		UserDAO dao = new UserDAO();
		try {
				int id = dao.login(email, password);
				
				// trqbva da proverim login kak da se sluchva
				// proverka dali go ima v DB
				response.setHeader("Location", "./home");
				response.setStatus(302); 
//				Cookie c = new Cookie("email", email);
//				c.setMaxAge(5);
				
//				response.addCookie(c);
				
				HttpSession session = request.getSession();
				session.setAttribute("email", email);
				session.setMaxInactiveInterval(500);
				
				response.sendRedirect("./home");
			
			
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("sdfghjklfghjkl;");
		super.doGet(req, resp);
	}

}
