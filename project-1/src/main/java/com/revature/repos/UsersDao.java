package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dto.UsersDto;
import com.revature.models.Users;
import com.revature.util.CloseStreams;
import com.revature.util.ConnectionUtil;

public class UsersDao {

	private static Logger log = Logger.getLogger(UsersDao.class);

	// insert into users

	public boolean insert(Users u) {
		PreparedStatement stmt = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "insert into ers_users (username, user_password,user_first_name,user_last_name,user_email,user_role_id ) values (?,?,?,?,?,'1')";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getPassword());
			stmt.setString(3, u.getFirstName());
			stmt.setString(4, u.getLastName());
			stmt.setString(5, u.getEmail());

			if (!stmt.execute()) {
				return false;
			}
		} catch (SQLException ex) {
			log.warn("Unable to insert user", ex);
			return false;
		} finally {
			// We want to close resources (in this case stmt)
			CloseStreams.close(stmt);
		}
		return true;

	}

	// get all values from users table
	public static List<Users> findAll() {

		List<Users> list = new ArrayList<Users>();

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_users;";

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("user_id");
				String username = rs.getString("username");
				String password = rs.getString("user_password");
				String firstName = rs.getString("user_first_name");
				String lastName = rs.getString("user_last_name");
				String email = rs.getString("user_email");
				int roleId = rs.getInt("user_role_id");

				Users u = new Users(id, username, password, firstName, lastName, email, roleId);
				list.add(u);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.warn("Unable to retrieve all users");
		}
		
		return list;
	}

	// find user by username

	public static Users findByUsername(String username) {

		List<Users> all = UsersDao.findAll();

		for (Users u : all) {
			if (u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}

	// return user id
	public static int userId(String username) {
		List<Users> all = UsersDao.findAll();

		for (Users u : all) {
			if (u.getUsername().equals(username)) {
				return u.getUserId();

			}
		}
		return 0;
	}

	// confirms login by checking username

	public static int confirmLogin(String username, String password) {

		Users u = findByUsername(username);
		if (u == null) {
			return 3;
		}
		if (u.getPassword().equals(password) && u.getRoleId() == 1) {
			return 1;

		}
		if (u.getPassword().equals(password) && u.getRoleId() == 2) {
			return 2;

		} else {
			return 3;
		}
	}

	// method convert to DTO
	public static UsersDto convertToDto(Users u) {
		return new UsersDto(u.getUserId(), u.getUsername(), u.getPassword(), u.getFirstName(), u.getLastName(),
				u.getEmail(), u.getRoleId());
	}

}
