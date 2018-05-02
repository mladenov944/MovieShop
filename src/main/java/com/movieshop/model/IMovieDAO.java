package com.movieshop.model;

import com.movieshop.exceptions.MovieException;

public interface IMovieDAO {

	public void addMovie();
	
	public void getAllMovies() throws MovieException;
}
