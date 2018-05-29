package com.excilys.cdb.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String IndexComputer(Locale locale, ModelMap model) {
		String welcome = messageSource.getMessage("welcome.message", null, locale);
		model.addAttribute("message", welcome);
		return "index";
	}

}
