package com.movieshop.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.movieshop.model.User;
import com.movieshop.model.UserDAO;
import com.movieshop.model.UserException;

@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserDAO dao;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("pass");

		try {

			User user = dao.login(email, password);

			if (user.getId() != 0 && user.isAdmin() == false) {

				HttpSession session = request.getSession();
				session.setAttribute("cash", user.getMoney());
				session.setAttribute("id", user.getId());
				session.setAttribute("name", user.getName());
				session.setAttribute("lastName", user.getLastName());
				session.setMaxInactiveInterval(600);

				return "loggedInHome";
			} else {
				HttpSession session = request.getSession();

				session.setAttribute("id", user.getId());
				session.setAttribute("name", user.getName());
				session.setAttribute("isAdmin", user.isAdmin());
				session.setAttribute("email", user.getEmail());
				session.setMaxInactiveInterval(6000);
				return "adminPage";
			}

		} catch (UserException e) {
			response.getWriter().println("<h1> You did not log in ! </h1>");
			response.sendRedirect("./login");
			e.printStackTrace();
		}
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		return "redirect:home";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerPage(Model model) {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String name = request.getParameter("name");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("mail");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		User u = new User(name, lastName, email, password);

		try {
			if ((checkEMail(email)) && (checkName(name, lastName)) && (checkPassword(password, confirmPassword))) {
				dao.register(u);
				return ("redirect:/login");
			}
		} catch (UserException e1) {
			response.getWriter().println("<h1> Something went wrong with the server! We are sorry! </h1>");
			e1.printStackTrace();
		}
		return ("redirect:/register");
	}

	private boolean checkEMail(String mail) {
		if ((mail.contains("@")) && (mail.contains("."))) {
			int indexQ = 0;
			int indexDot = 0;
			for (int i = 0; i < mail.length(); i++) {
				if (mail.charAt(i) == '@') {
					indexQ = i;
				}
				if (mail.charAt(i) == '.') {
					indexDot = i;
				}
			}
			if (indexQ < indexDot) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private boolean checkName(String name, String lastname) {
		if ((name.trim().length() >= 2) && (lastname.trim().length() >= 2)) {
			return true;
		}
		return false;
	}

	private boolean checkPassword(String password, String confirmPassword) {
		if ((password.length() >= 4) && (confirmPassword.equals(password))) {
			return true;
		}
		return false;
	}
}
