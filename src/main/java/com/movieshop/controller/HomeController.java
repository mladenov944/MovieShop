package com.movieshop.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.movieshop.exceptions.MovieException;
import com.movieshop.model.Movie;
import com.movieshop.model.MovieDAO;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private MovieDAO movieDao;

	@RequestMapping(method = RequestMethod.GET, value = "/home")
	public String homePage(Model model) throws MovieException {
		// try {
		// List<Movie> movies = movieDao.getAllMovies();
		// System.out.println(movies.size());
		// model.addAttribute("movies", movies);
		//
		// } catch (MovieException e) {
		// e.printStackTrace();
		// throw new MovieException("Sorry, can't find Movies!", e);
		// }
		return "home";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/index")
	public String indexPage() {
		return "redirect:home";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/loggedInHome")
	public String loggedIn(Model model, HttpServletRequest request, HttpServletResponse response) {
		if ((request.getSession(false) == null) || (request.getSession().getAttribute("id") == null)) {
			return ("redirect:home");
		}
		try {
			List<Movie> movies = movieDao.getAllMovies();
			model.addAttribute("movies", movies);

		} catch (MovieException e) {
			e.printStackTrace();
		}
		return "loggedInHome";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/adminPage")
	public String adminPage(Model model, HttpServletRequest request, HttpServletResponse response)
			throws MovieException {
		if ((request.getSession(false) == null) || (request.getSession().getAttribute("id") == null)
				|| (!(request.getSession().getAttribute("email").equals("admin@admin.bg")))) {
			return ("redirect:home");
		}
		try {
			List<Movie> movies = movieDao.getAllMovies();
			System.out.println(movies.size());
			model.addAttribute("movies", movies);

		} catch (MovieException e) {
			e.printStackTrace();
			throw new MovieException("Sorry, can't find Movies!", e);
		}
		return "adminPage";
	}
}
