package com.movieshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {

	@RequestMapping(method = RequestMethod.GET, value = "/home")
	public String sayHello(Model model) {
		return "home"; // vij che ti se povtarq request mappinga.. kak da raboti :D
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String login(Model model) {
		return "login"; 
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/register")
	public String register(Model model) {
		return "register"; 
	}

}
