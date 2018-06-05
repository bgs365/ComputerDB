package com.excilys.cdb.dao;

import org.springframework.data.repository.CrudRepository;

import com.excilys.cdb.model.User;

public interface UserDao extends CrudRepository<User, String>{
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	User findUserByUsername(String username);

}
