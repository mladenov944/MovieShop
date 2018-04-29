package com.movieshop.model;

public interface IUserDAO {

	int login(String username, String password) throws UserException;

	int register(User user) throws UserException;

}
