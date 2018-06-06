package com.excilys.cdb.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.cdb.model.Authorities;
import com.excilys.cdb.model.User;

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
		/*User user = new User();
		user.setUsername("bgs");
		String encoded=new BCryptPasswordEncoder().encode("bbbbbbbb");
		user.setPassword(encoded);
		Set<Authorities> authorities = new HashSet<>();
		Authorities authority = new Authorities();
		authority.setUser(user);
		authority.setAuthority("ADMIN");
		authorities.add(authority);
		authority = new Authorities();
		authority.setUser(user);
		authority.setAuthority("VISITOR");
		authorities.add(authority);
		user.setAuthorities(authorities);*/
		return "users";
	}

}
