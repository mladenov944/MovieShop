package com.movieshop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.movieshop.db.DBConnection;
import com.movieshop.exceptions.MovieException;

@Component
public class MovieDAO implements IMovieDAO {

	private static final String ALL_MOVIES = "SELECT * FROM movies";
	private static final String ADD_MOVIE_SQL = "INSERT INTO movies VALUES (null, ?,?,?,?,?,?,?,?,?)";
	// @Autowired
	// MovieDAO dao;

	@Override
	public List<Movie> getAllMovies() throws MovieException {
		List<Movie> allMovies = new ArrayList<>();
		PreparedStatement pstmt;
		try (Connection conn = DBConnection.getInstance().getConnection()) {
			pstmt = conn.prepareStatement(ALL_MOVIES);
			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				Movie movie = new Movie();

				movie.setName(result.getString("name"));
				movie.setDirector(result.getString("director"));
				movie.setYear(result.getShort("year"));
				movie.setSummary(result.getString("summary"));
				movie.setPicture(result.getString("picture"));
				movie.setPrice(result.getFloat("price"));
				movie.setGenre(result.getString("genre"));
				movie.setInfoLink(result.getString("info_link"));
				movie.setQuantity(result.getShort("quantity"));

				allMovies.add(movie);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new MovieException("Nqma q bazata!", e);
		}
		return allMovies;
	}

	public void addMovie(Movie movie) throws MovieException {
		PreparedStatement pstmt;

		try {
			pstmt = DBConnection.getInstance().getConnection().prepareStatement(ADD_MOVIE_SQL,
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, movie.getName());
			pstmt.setString(2, movie.getDirector());
			pstmt.setInt(3, movie.getYear());
			pstmt.setString(4, movie.getSummary());
			pstmt.setString(5, movie.getPicture());
			pstmt.setFloat(6, movie.getPrice());
			pstmt.setString(7, movie.getGenre());
			pstmt.setString(8, movie.getInfoLink());
			pstmt.setInt(9, movie.getQuantity());

			pstmt.executeUpdate();

			ResultSet resultSet = pstmt.getGeneratedKeys();
			resultSet.next();
			System.out.println("added movie");

		} catch (Exception e) {
			e.printStackTrace();
			throw new MovieException("Cannot add movie !", e);
		}
	}

	@Override
	public void addMovie() {
		// TODO Auto-generated method stub

	}
}
