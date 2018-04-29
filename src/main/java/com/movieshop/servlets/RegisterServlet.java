package com.movieshop.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.movieshop.model.User;
import com.movieshop.model.UserDAO;
import com.movieshop.model.UserException;

@WebServlet("/reg")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 
       
	@Autowired // zashto ne raboti ?
	private UserDAO dao = new UserDAO();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String name = request.getParameter("name");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("mail");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		
		User u = new User(name, lastName, email, password);
		
		try {
			dao.register(u);
			
			response.sendRedirect("./home");
		
		} catch (UserException e1) {
			response.getWriter().println("<h1> MySQL e bullshit </h1>");
			e1.printStackTrace();
		}
		
		// todo da si izreja imeto s trim.. proverki tam da ima
//		if (name == null) {
//			try {
//				throw new UserException("Username cannot be empty!");
//			} catch (UserException e) {
//				e.printStackTrace();
//			}
//		}
		
		
		System.out.println(name);
		System.out.println(lastName);
		System.out.println(email);
		System.out.println(password);
		System.out.println(confirmPassword);
		
		
	}

}
