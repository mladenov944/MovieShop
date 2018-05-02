package com.movieshop.controller;

import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.movieshop.exceptions.MovieException;
import com.movieshop.model.Movie;
import com.movieshop.model.MovieDAO;

@Controller
@RequestMapping("/")
public class MovieController {

	@Autowired
	private MovieDAO dao;

	@RequestMapping(method = RequestMethod.GET, value = "/addmovie")
	public String homePage(HttpServletRequest request, HttpServletResponse response) {
		if (!(request.getSession().getAttribute("email").equals("admin@admin.bg") || (request.getSession(false) == null)
				|| (request.getSession().getAttribute("id") == null))) {
			return ("home");
		}

		return "addMovie";
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
			if ((isValidURL(imdb_link)) && (isValidName(name, director)) && isValidYear(year)
					&& isValidQuantity(quantityy) && checkPrice(pricee)) {
				dao.addMovie(temp);
				return ("adminPage");
			}
		} catch (MovieException e1) {
			response.getWriter().println("<h1> Something went wrong with the server! We are sorry! </h1>");
			e1.printStackTrace();
		}

		return ("addMovie");
	}

	public boolean isValidURL(String urlString) {
		try {
			URL url = new URL(urlString);
			url.toURI();
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	public boolean isValidName(String name, String director) {
		if ((name.length() > 2) && (director.length() > 2)) {
			return true;
		}
		return false;
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