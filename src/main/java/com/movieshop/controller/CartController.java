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

	@RequestMapping(method = RequestMethod.GET, value = "/cart")
	public String cart(Model model, HttpServletRequest request, @RequestParam("itemID") Integer id) {

		try {
				List<Movie> movies = new ArrayList<Movie>();
				Movie movie = movieDAO.getMovieId(id);
				movies.add(movie);
				model.addAttribute(movie);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "cart";
	}

}
