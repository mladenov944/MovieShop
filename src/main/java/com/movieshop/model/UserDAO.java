package com.movieshop.model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.movieshop.db.DBConnection;

public class UserDAO implements IUserDAO {

	private static final int RESULT_SET_RETURN = 1;
	private static final String LOGIN_USER_SQL = "SELECT * FROM users WHERE email=? and password = sha1(?)";
	private static final String ADD_USER_SQL = "INSERT INTO users VALUES (null, ?,?, sha1(?), ?,0,?)";
	
	@Override
	public int login(String email, String password) throws UserException {
		PreparedStatement pstmt;
		try {
			pstmt = DBConnection.getInstance().getConnection()
			.prepareStatement(LOGIN_USER_SQL);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			ResultSet resultSet = pstmt.executeQuery();
			
			if (resultSet.next()) {
				return resultSet.getInt(RESULT_SET_RETURN);
			}
			
			throw new UserException("There is no user with this name !");
		} catch (Exception e) {
			throw new UserException("Something went wrong with the server... We are sorry! " + e.getMessage());
			
		}
	}
	
	@Override
	public int register(User user) throws UserException {
		PreparedStatement pstmt;
		try {
			pstmt = DBConnection.getInstance().getConnection()
			.prepareStatement(ADD_USER_SQL, Statement.RETURN_GENERATED_KEYS);
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
}
