package com.movieshop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.movieshop.exceptions.MovieException;
import com.movieshop.exceptions.UserException;
import com.movieshop.model.Movie;
import com.movieshop.model.MovieDAO;
import com.movieshop.model.User;
import com.movieshop.model.UserDAO;

@Controller
@RequestMapping("/")
public class UserController {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private MovieDAO movieDAO;

	@RequestMapping(method = RequestMethod.GET, value = "/loggedInHome")
	public String loggedIn(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
		if ((request.getSession(false) == null) || (request.getSession().getAttribute("id") == null)) {
			return ("redirect:home");
		}
		try {

			List<Movie> movies = movieDAO.getAllMovies();
			model.addAttribute("movies", movies);

		} catch (MovieException e) {
			e.printStackTrace();
		}
		return "loggedInHome";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/sortedMovies")
	public String sortedmovies(Model model, HttpServletRequest request, HttpServletResponse response) {
		if ((request.getSession(false) == null) || (request.getSession().getAttribute("id") == null)) {
			return ("redirect:home");
		}
		try {
			List<Movie> movies = movieDAO.sortMovieByName();
			model.addAttribute("movies", movies);

		} catch (MovieException e) {
			e.printStackTrace();
		}

		return "sortedMovies";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/sortedGenre")
	public String sortedMoviesByGenre(Model model, HttpServletRequest request, HttpServletResponse response,
			 @RequestParam("genreID") String genre) {
		if ((request.getSession(false) == null) || (request.getSession().getAttribute("id") == null)) {
			return ("redirect:home");
		}
			List<Movie> movies = new ArrayList<Movie>();;
			try {
				movies = movieDAO.showMovieByGenre(genre);
				model.addAttribute("movies", movies);
			} catch (MovieException e) {
				e.printStackTrace();
			}
		return "sortedGenre";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginCheck(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@RequestParam("email") String email, @RequestParam("pass") String password)
			throws IOException, UserException, ServletException {

		PrintWriter out = response.getWriter();

		try {

			User user = userDAO.login(email, password);

			if (user.getId() != 0) {

				if (user.isAdmin()) {
					// HttpSession session = request.getSession();

					session.setAttribute("id", user.getId());
					session.setAttribute("password", user.getPassword());
					session.setAttribute("name", user.getName());
					session.setAttribute("isAdmin", user.isAdmin());
					session.setAttribute("email", user.getEmail());
					session.setMaxInactiveInterval(6000);
					return "redirect:adminPage";
				} else {

					session.setAttribute("cash", user.getMoney());
					session.setAttribute("password", user.getPassword());
					session.setAttribute("id", user.getId());
					session.setAttribute("name", user.getName());
					session.setAttribute("lastName", user.getLastName());
					session.setMaxInactiveInterval(600);

					return "redirect:loggedInHome"; // za jsp redirect nova zaqvka kym drug url nov kontroler i sesiqta
				}

			} else {
				out.print("Sorry, username or password error!");
				return "login";
			}

		} catch (UserException e) {
			e.printStackTrace();
			out.print("Sorry, username or password error!");
			return "login";
		}
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
	public String register(HttpServletRequest request, HttpServletResponse response, @RequestParam("name") String name,
			@RequestParam("lastname") String lastName, @RequestParam("mail") String email,
			@RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword)
			throws IOException {

		User u = new User(name, lastName, email, password);

		try {
			if ((isValidEmail(email)) && (checkName(name, lastName)) && (checkPassword(password, confirmPassword))) {
				userDAO.register(u);
				return ("redirect:/loggedInHome");
			}
		} catch (UserException e1) {
			response.getWriter().println("<h1> Something went wrong with the server! We are sorry! </h1>");
			e1.printStackTrace();
		}
		return ("redirect:/register");
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String changePassword(HttpServletRequest request, HttpServletResponse response) {
		if ((request.getSession(false) == null) || (request.getSession().getAttribute("id") == null)) {
			return ("redirect:home");
		}
		return "changePassword";
	}

	@RequestMapping(value = "/subscribe", method = RequestMethod.POST)
	public String subscribe(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		if ((request.getSession(false) == null) || (request.getSession().getAttribute("id") == null)) {
			return ("redirect:home");
		}
		Integer id = (Integer) session.getAttribute("id");
		userDAO.subscribe(id);

		return "redirect:/loggedInHome";
	}

	@RequestMapping(value = "/unsubscribe", method = RequestMethod.POST)
	public String unsubscribe(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		if ((request.getSession(false) == null) || (request.getSession().getAttribute("id") == null)) {
			return ("redirect:home");
		}
		Integer id = (Integer) session.getAttribute("id");
		userDAO.unsubscribe(id);

		return "redirect:/loggedInHome";
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changingPassword(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String password = request.getParameter("new_password");
		String oldPassword = request.getParameter("old_password");

		String oldcrypt = encryptPassword(oldPassword);

		Integer id = (Integer) session.getAttribute("id");
		String temp_pass = (String) session.getAttribute("password");

		if (!(temp_pass).equals(oldcrypt)) {
			response.getWriter().println("Wrong old password !");
			return ("redirect:/changePassword");
		}

		try {
			userDAO.changePassword(id, password);
			return ("redirect:/login");

		} catch (UserException e1) {
			response.getWriter().println("<h1> Something went wrong with the server! We are sorry! </h1>");
			e1.printStackTrace();
		}
		return ("redirect:/changePassword");
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String reset() {
		return "resetPasswordPage";
	}

	private String encryptPassword(String password) {
		String sha1 = "";
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(password.getBytes("UTF-8"));
			sha1 = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sha1;
	}

	private String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static boolean isValidEmail(String email) throws UserException {
		if (email != null) {
			Pattern p = Pattern.compile(EMAIL_PATTERN);
			Matcher m = p.matcher(email);
			if (m.find()) {
				return true;
			}
		}
		throw new UserException("Invalid email!");
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
