package com.movieshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.movieshop.model.Movie;
import com.movieshop.model.MovieDAO;
import com.sun.mail.imap.protocol.Item;

@Controller
@RequestMapping("/")
public class CartController {
	
	@Autowired
	private MovieDAO movieDAO;
	
	
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

					Movie movie = movieDAO.getId(id);
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
}
