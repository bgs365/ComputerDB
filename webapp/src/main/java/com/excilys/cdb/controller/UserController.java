package com.excilys.cdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class UserController {

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "user", method = RequestMethod.GET)
	public String createUser(ModelMap model) {
		return "users";
	}

}
