package com.excilys.cdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class indexController {
	
	public String IndexComputer(ModelMap model) {
		return "index";
	}

}
