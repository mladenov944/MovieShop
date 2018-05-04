package com.movieshop.controller;

import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.movieshop.exceptions.MovieException;
import com.movieshop.model.Movie;
import com.movieshop.model.MovieDAO;

@Controller
@RequestMapping("/") // smeni gi za da izlizat poveche papki..
public class MovieController {

	@Autowired
	private MovieDAO movieDAO;

	@RequestMapping(method = RequestMethod.GET, value = "/movieDetails")
	public String movieDetail() {
		return "movieDetails";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/movieDetails/{id}")
	public String viewMovie(Model model, @PathVariable Integer id) {
		Movie movie;
		try {
			movie = movieDAO.getMovieByIndex(id);
			model.addAttribute(movie);
		} catch (MovieException e) {
			e.printStackTrace();
		}
		return "movieDetails";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/movieGenre")
	public String movieByGenre(Model model) {
		Movie movieGenre;
		// try {
		// movieGenre = dao.showMovieByGenre();
		// model.addAttribute(movieGenre);
		// } catch (MovieException e) {
		// e.printStackTrace();
		// }
		return "movieGenre";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/addmovie")
	public String homePage(HttpServletRequest request, HttpServletResponse response) {
		if (!(request.getSession().getAttribute("email").equals("admin@admin.bg") || (request.getSession(false) == null)
				|| (request.getSession().getAttribute("id") == null))) {
			return "home";
		}

		return "addmovie";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addmovie")
	public String addMovie(HttpServletRequest request, HttpServletResponse response)
			throws MovieException, IOException {

		String name = request.getParameter("movie_name");
		String director = request.getParameter("movie_director");
		String get_year = request.getParameter("movie_year");
		Short year = Short.parseShort(get_year);
		String genre = request.getParameter("movie_genre");
		String picture = request.getParameter("movie_picture");
		String price = request.getParameter("movie_price");
		Float pricee = Float.parseFloat(price);
		String imdb_link = request.getParameter("movie_link");
		String quantity = request.getParameter("movie_quantity");
		Short quantityy = Short.parseShort(quantity);
		String summary = request.getParameter("movie_summary");

		Movie temp = new Movie(name, director, year, summary, picture, pricee, genre, imdb_link, quantityy);

		try {
			if ((isValidURL(imdb_link)) && isValidYear(year) && isValidQuantity(quantityy) && checkPrice(pricee)) {
				movieDAO.addMovie(temp);
				return "redirect:adminPage";
			} else {
				return "addmovie";
			}
		} catch (MovieException e1) {
			response.getWriter().println("<h1> Something went wrong with the server! We are sorry! </h1>");
			e1.printStackTrace();
		}
		return "redirect:adminPage";
	}

	// Validation sector starts here...

	public boolean isValidURL(String urlString) {
		try {
			URL url = new URL(urlString);
			url.toURI();
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	public boolean isValidYear(short year) {
		if (year > 1800 && year < 2019) {
			return true;
		}
		return false;
	}

	public boolean isValidQuantity(short quantity) {
		if (quantity > 0) {
			return true;
		}
		return false;
	}

	public boolean checkPrice(float price) {
		if (price > 0.0) {
			return true;
		}
		return false;
	}

}
