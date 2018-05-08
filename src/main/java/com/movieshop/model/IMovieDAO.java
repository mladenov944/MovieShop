package com.movieshop.model;

import java.util.List;

import com.movieshop.exceptions.MovieException;

public interface IMovieDAO {

	public void addMovie(Movie movie) throws MovieException;

	public List<Movie> getAllMovies() throws MovieException;

	public Movie getMovieByIndex(int index) throws MovieException;

	public List<Movie> showMovieByGenre(String genre) throws MovieException;

	public List<Movie> sortMovieByName() throws MovieException;

	public void updateItemQuantity(int id, int quantity) throws MovieException;

	public void removeMovie(int id) throws MovieException;
}
