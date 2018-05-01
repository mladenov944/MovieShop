package com.movieshop.model;

import java.util.List;

public interface IUserDAO {

	User login(String username, String password) throws UserException;

	int register(User user) throws UserException;
	
	List<User> getSubscribedUsers();

}
