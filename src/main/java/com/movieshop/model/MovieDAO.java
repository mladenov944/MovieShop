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
	private static final String MOVIES_BY_GENRE = "SELECT * FROM movies WHERE genre='?'";
	private static final String GET_SPECIFIC_MOVIE = "SELECT * FROM movies WHERE id = ?";
	private static final String UPDATE_MOVIE_QUANTITY = "UPDATE movies SET quantity = quantity - ? Where id = ?";
	private static final String SORT_MOVIES_BY_NAME_ASC = "SELECT * FROM movies ORDER BY name ASC";
	private static final String REMOVE_MOVIE_BY_ID = "DELETE from movies where id = ?";

	@Override
	public List<Movie> getAllMovies() throws MovieException {
		List<Movie> allMovies = new ArrayList<>();
		PreparedStatement pstmt;
		try {
			Connection conn = DBConnection.getInstance().getConnection();
			pstmt = conn.prepareStatement(ALL_MOVIES);
			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				Movie movie = new Movie();
				movie.setId(result.getInt("id"));
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

	public List<Movie> showMovieByGenre(String genre) throws MovieException {
		List<Movie> moviesByGenre = new ArrayList<Movie>();
		PreparedStatement pstmt;
		try {
			pstmt = DBConnection.getInstance().getConnection().prepareStatement(MOVIES_BY_GENRE);
			pstmt.setString(1, genre);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				Movie movie = new Movie();
				movie.setId(resultSet.getInt("id"));
				movie.setName(resultSet.getString("name"));
				movie.setDirector(resultSet.getString("director"));
				movie.setYear(resultSet.getShort("year"));
				movie.setSummary(resultSet.getString("summary"));
				movie.setPicture(resultSet.getString("picture"));
				movie.setPrice(resultSet.getFloat("price"));
				movie.setGenre(resultSet.getString("genre"));
				movie.setInfoLink(resultSet.getString("info_link"));
				movie.setQuantity(resultSet.getShort("quantity"));

				moviesByGenre.add(movie);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new MovieException("Something went wrong with the server! " + e.getMessage());
		}
		return moviesByGenre;
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
	public void removeMovie(int id) throws MovieException {
		PreparedStatement pstmt;

		try {
			pstmt = DBConnection.getInstance().getConnection().prepareStatement(REMOVE_MOVIE_BY_ID,
					Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, id);
			pstmt.executeUpdate();

			System.out.println("removed movie");

		} catch (Exception e) {
			e.printStackTrace();
			throw new MovieException("Cannot remove movie !", e);
		}
	}

	@Override
	public Movie getMovieByIndex(int index) throws MovieException {
		Movie movies;
		try {
			movies = getAllMovies().get(index);
		} catch (MovieException e) {
			e.printStackTrace();
			throw new MovieException("Something went wrong, please try again later!");
		}
		return movies;
	}

	public Movie getMovieId(int id) throws MovieException {
		try {
			PreparedStatement pstmt = DBConnection.getInstance().getConnection().prepareStatement(GET_SPECIFIC_MOVIE);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			Movie movie = new Movie();

			if (rs.next()) {
				movie.setId(rs.getInt("id"));
				movie.setName(rs.getString("name"));
				movie.setDirector(rs.getString("director"));
				movie.setYear(rs.getShort("year"));
				movie.setSummary(rs.getString("summary"));
				movie.setPicture(rs.getString("picture"));
				movie.setPrice(rs.getFloat("price"));
				movie.setGenre(rs.getString("genre"));
				movie.setInfoLink(rs.getString("info_link"));
				movie.setQuantity(rs.getInt("quantity"));

			}
			pstmt.close();
			return movie;

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new MovieException("There are no Movie with same ID!");
		}
	}

	@Override
	public void updateItemQuantity(int id, int quantity) throws MovieException {
		PreparedStatement stmt;
		try {
			stmt = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_MOVIE_QUANTITY);
			stmt.setInt(1, quantity);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			stmt.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new MovieException("There are no Movie with same ID!");
		}

	}

	@Override
	public List<Movie> sortMovieByName() throws MovieException {
		List<Movie> movies = new ArrayList<>();
		PreparedStatement pstmt;
		try {
			Connection conn = DBConnection.getInstance().getConnection();
			pstmt = conn.prepareStatement(SORT_MOVIES_BY_NAME_ASC);
			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				Movie movie = new Movie();
				movie.setId(result.getInt("id"));
				movie.setName(result.getString("name"));
				movie.setDirector(result.getString("director"));
				movie.setYear(result.getShort("year"));
				movie.setSummary(result.getString("summary"));
				movie.setPicture(result.getString("picture"));
				movie.setPrice(result.getFloat("price"));
				movie.setGenre(result.getString("genre"));
				movie.setInfoLink(result.getString("info_link"));
				movie.setQuantity(result.getShort("quantity"));

				movies.add(movie);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new MovieException("Something went wrong with DB, please try again later!", e);
		}
		return movies;
	}

}
