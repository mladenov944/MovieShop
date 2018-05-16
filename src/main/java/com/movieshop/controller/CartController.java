package com.movieshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.movieshop.exceptions.UserException;
import com.movieshop.model.Movie;
import com.movieshop.model.MovieDAO;
import com.movieshop.model.UserDAO;

@Controller
@RequestMapping("/")
public class CartController {

	@Autowired
	private MovieDAO movieDAO;

	@Autowired
	private UserDAO userDAO;

	@RequestMapping(method = RequestMethod.GET, value = "/cart")
	public String cart(Model model, HttpServletRequest request, @RequestParam("itemID") Integer id) {

		try {
			List<Movie> movies = new ArrayList<Movie>();
			Movie movie = movieDAO.getMovieId(id);
			movies.add(movie);
			model.addAttribute(movie);
		} catch (Exception e) {
			e.printStackTrace();
			return ("redirect:error");
		}
		return "cart";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/placeorder")
	public String text(HttpServletRequest request, HttpSession session, @RequestParam("price") Float price) {

		if ((request.getSession(false) == null) || (request.getSession().getAttribute("id") == null)) {
			return "redirect:home";
		}

		int id = (int) request.getSession().getAttribute("id");

		Float userMoney = (Float) request.getSession().getAttribute("cash");
		float total = userMoney - price;

		try {
			if (userMoney < price) {
				return "cart";
			}
			userDAO.pay(id, total);
			request.getSession().removeAttribute("cash");
			request.getSession().setAttribute("cash", total);
		} catch (UserException e) {
			e.printStackTrace();
			return "redirect:home";
		}
		return "placeorder";
	}
}
