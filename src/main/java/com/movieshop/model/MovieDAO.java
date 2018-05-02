package com.movieshop.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	public void getAllMovies() throws MovieException {
		Statement stmt;
		try {
				stmt = DBConnection.getInstance().getConnection().prepareStatement(ALL_MOVIES);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new MovieException("Nqma q bazata!", e);
		}
		
		
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
