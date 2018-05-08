package com.movieshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
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
import com.movieshop.model.Movie;
import com.movieshop.model.MovieDAO;

@Controller
@RequestMapping("/")
public class CartController {
	
	@Autowired
	private MovieDAO movieDAO;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/addItem")
	public String addItem(Model model, @RequestParam(value = "movieId", required = false) int id,
			@RequestParam(value = "movieQuantity", required = false) int quantity, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int movieId = id;
			int requestQuantity = quantity;
			Movie movie = movieDAO.getMovieId(movieId);

			if (movie.getQuantity() > requestQuantity) {
				
				HttpSession session = request.getSession(false);
				//create cookie with  (userId + itemId) ( itemId and quantity)
				Cookie cookie = new Cookie((session.getAttribute("id")) + "&" + id, "" + id + "&" + quantity);
				cookie.setMaxAge(5000);
				response.addCookie(cookie);
			} else {
				return "redirect:/" + movieId;
			}

		} catch (MovieException e) {
			e.printStackTrace();
		}
		return "redirect:cart";
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/cart")
	public String cart(Model model, HttpServletRequest request) {

		try {

			HttpSession session = request.getSession(false);
			Cookie[] cookies = request.getCookies();
			List<Cookie> userCookies = new ArrayList<Cookie>();
			float totalPrice = 0;

			if (cookies.length > 1 && session.getAttribute("id") != null) {
				for (Cookie cookie : cookies) {

					if (cookie.getName().split("&")[0].equals(session.getAttribute("id").toString())) {
						userCookies.add(cookie);
					}
				}

				List<Movie> movies = new ArrayList<Movie>();

				int totalMovies = 0;

				for (Cookie cookie : userCookies) {
					String itemId = cookie.getValue().split("&")[0];
					String itemQuantity = cookie.getValue().split("&")[1];

					int id = Integer.parseInt(itemId);
					int quantity = Integer.parseInt(itemQuantity);
					totalMovies += quantity;

					Movie movie = movieDAO.getMovieId(id);
					movie.setQuantity(quantity);
					movie.setPrice(movie.getPrice() * movie.getQuantity());

					totalPrice += movie.getPrice();
					movies.add(movie);

				}

				session.setAttribute("userMovies", totalMovies);
				session.setAttribute("totalPrice", totalPrice);

				model.addAttribute("totalPrice", totalPrice);
				model.addAttribute("movies", movies);
			} else {

				session.setAttribute("userMovies", null);
				session.setAttribute("totalPrice", null);
				return "cart";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "cart";
	}	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/removeMovie")
	public String removeItem(Model model, @RequestParam(value = "movieId", required = false) int id,
			HttpServletRequest request, HttpServletResponse response) {

			HttpSession session = request.getSession(false);
			Cookie cookie = new Cookie((session.getAttribute("id")) + "&" + id, "");
			cookie.setMaxAge(0);

			response.addCookie(cookie);
			return "redirect:cart";
	}
}
