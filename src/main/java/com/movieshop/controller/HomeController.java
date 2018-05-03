package com.movieshop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {

	@RequestMapping(method = RequestMethod.GET, value = "/home")
	public String homePage() {
		return "home";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/index")
	public String indexPage() {
		return "redirect:home";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/loggedInHome")
	public String loggedIn(HttpServletRequest request, HttpServletResponse response) {
		if ((request.getSession(false) == null) || (request.getSession().getAttribute("id") == null)) {
			return ("redirect:home");
		}
		return "loggedInHome";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/adminPage")
	public String adminPage(HttpServletRequest request, HttpServletResponse response) {
		if ((request.getSession(false) == null) || (request.getSession().getAttribute("id") == null)
				|| (!(request.getSession().getAttribute("email").equals("admin@admin.bg")))) {
			return ("redirect:home");
		}
		return "adminPage";
	}
}
