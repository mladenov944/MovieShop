package com.movieshop.model;

import java.sql.SQLException;
import java.util.List;

import com.movieshop.exceptions.UserException;

public interface IUserDAO {

	User login(String username, String password) throws UserException;

	int register(User user) throws UserException;

	void changePassword(int id, String passowrd) throws UserException;

	List<User> getSubscribedUsers() throws UserException;

	public void subscribe(int id) throws UserException;

	public void unsubscribe(int id) throws UserException;

	public User getUserForNewPassword(String email) throws ClassNotFoundException, SQLException;

}
