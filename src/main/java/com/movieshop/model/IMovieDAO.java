package com.movieshop.model;

import java.util.List;

import com.movieshop.exceptions.MovieException;

public interface IMovieDAO {

	public void addMovie();

	public List<Movie> getAllMovies() throws MovieException;
}
