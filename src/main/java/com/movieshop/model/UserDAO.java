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
import com.movieshop.exceptions.UserException;

@Component
public class UserDAO implements IUserDAO {

	private static final String SELECT_SUBSCRIBED_USERS = "SELECT * FROM users WHERE isSubscribed = 1";
	private static final int RESULT_SET_RETURN = 1;
	private static final String LOGIN_USER_SQL = "SELECT * FROM users WHERE email=? and password = sha1(?)";
	private static final String ADD_USER_SQL = "INSERT INTO users VALUES (null, ?,?, sha1(?), ?,0,?,0)";

	@Override
	public User login(String email, String password) throws UserException {
		PreparedStatement pstmt;
		Movie movie = new Movie();
		try {
			pstmt = DBConnection.getInstance().getConnection().prepareStatement(LOGIN_USER_SQL);
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			ResultSet resultSet = pstmt.executeQuery();
			User user = new User();

			while (resultSet.next()) {
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setMoney(resultSet.getFloat("money"));
				user.setAdmin(resultSet.getBoolean("isAdmin"));
				user.setEmail(resultSet.getString("email"));
				return user;
			}

			throw new UserException("Wrong email or password");
		} catch (Exception e) {
			throw new UserException("Something went wrong with the server! " + e.getMessage());

		}
	}

	@Override
	public int register(User user) throws UserException {
		PreparedStatement pstmt;
		try {
			pstmt = DBConnection.getInstance().getConnection().prepareStatement(ADD_USER_SQL,
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getEmail());
			pstmt.setFloat(5, user.getMoney());

			pstmt.executeUpdate();

			ResultSet resultSet = pstmt.getGeneratedKeys();
			resultSet.next();

			return resultSet.getInt(RESULT_SET_RETURN);

		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException("This user is already a member!", e);
		}
	}

	@Override
	public List<User> getSubscribedUsers() {
		List<User> users = new ArrayList<>();
		PreparedStatement pstmt;
		try (Connection conn = DBConnection.getInstance().getConnection()) {
			pstmt = DBConnection.getInstance().getConnection().prepareStatement(SELECT_SUBSCRIBED_USERS);
			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				User user = new User();

				user.setName(resultSet.getString("name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setEmail(resultSet.getString("email"));
				users.add(user);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// tuka ipshi nekvi laina
			e.printStackTrace();
		}
		return users;
	}
}
