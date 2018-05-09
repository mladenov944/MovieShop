package com.movieshop.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.movieshop.db.DBConnection;
import com.movieshop.exceptions.UserException;

@Component
public class UserDAO implements IUserDAO {

	private static final String SELECT_SUBSCRIBED_USERS = "SELECT * FROM users WHERE isSubscribed = 1";
	private static final int RESULT_SET_RETURN = 1;
	private static final String LOGIN_USER_SQL = "SELECT * FROM users WHERE email=? and password = sha1(?)";
	private static final String ADD_USER_SQL = "INSERT INTO users VALUES (null, ?,?, sha1(?), ?,0,?,0)";
	private static final String CHANGE_PASS_SQL = "UPDATE users SET password = sha1(?) WHERE id = ?";
	private static final String SUBSCRIBE = "UPDATE users SET isSubscribed = 1 where id = ? ";
	private static final String UNSUBSCRIBE = "UPDATE users SET isSubscribed = 0 where id = ? ";
	private static final String SELECT_EMAIL = "SELECT * FROM users WHERE email=?";

	@Override
	public User login(String email, String password) throws UserException {
		PreparedStatement pstmt;
		try {
			pstmt = DBConnection.getInstance().getConnection().prepareStatement(LOGIN_USER_SQL);
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			ResultSet resultSet = pstmt.executeQuery();
			User user = new User();

			while (resultSet.next()) {
				user.setId(resultSet.getInt("id"));
				user.setPassword(resultSet.getString("password"));
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
	public void changePassword(int id, String password) throws UserException {
		PreparedStatement pstmt;

		try {
			pstmt = DBConnection.getInstance().getConnection().prepareStatement(CHANGE_PASS_SQL,
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, password);
			pstmt.setInt(2, id);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException("Cannot change password!", e);
		}
	}

	@Override
	public User getUserForNewPassword(String email) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt;
		UUID uuid = UUID.randomUUID();
		String code = uuid.toString();
		User user = new User();
		try {
			pstmt = DBConnection.getInstance().getConnection().prepareStatement(SELECT_EMAIL);
			pstmt.setString(1, email);
			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {

				user.setName(resultSet.getString("name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setEmail(resultSet.getString("email"));
				user.setId(resultSet.getInt("id"));
				user.setCode(code);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return user;

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
	public List<User> getSubscribedUsers() throws UserException {
		List<User> users = new ArrayList<>();
		PreparedStatement pstmt;
		try {
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
			throw new UserException("Cannot get subscribed users...");
		}
		return users;
	}

	@Override
	public void subscribe(int id) {
		PreparedStatement pstmt;

		try {
			pstmt = DBConnection.getInstance().getConnection().prepareStatement(SUBSCRIBE,
					Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, id);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unsubscribe(int id) {
		PreparedStatement pstmt;

		try {
			pstmt = DBConnection.getInstance().getConnection().prepareStatement(UNSUBSCRIBE,
					Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, id);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
