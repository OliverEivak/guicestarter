package com.olivereivak.starter.dao;

import com.olivereivak.starter.model.Role;
import com.olivereivak.starter.model.User;

import javax.inject.Inject;
import javax.inject.Provider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    @Inject
    private Provider<Connection> connectionProvider;

    public User findById(Long id) {
        try (Connection connection = connectionProvider.get()) {
            try (PreparedStatement stmt = connection.prepareStatement("select * from user where id = ?")) {
            	stmt.setLong(1, id);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
//                    return new User()
//							.setId(rs.getLong("id"))
//							.setUsername(rs.getString("username"))
//							.setPassword(rs.getBytes("password"))
//							.setEmail(rs.getString("email"))
//							.setRole(Role.valueOf(rs.getString("role")));
					return mapUser(rs);
				}
            }
        } catch (SQLException e) {
        	throw new RuntimeException("Failed to find user by id " + id, e);
		}

        return null;
    }

	public User findByUsername(String username) {
		try (Connection connection = connectionProvider.get()) {
			try (PreparedStatement stmt = connection.prepareStatement("select * from user where username = ?")) {
				stmt.setString(1, username);

				ResultSet rs = stmt.executeQuery();

				if (rs.next()) {
					return mapUser(rs);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Failed to find user by username " + username, e);
		}

		return null;
	}

	public void createUser(User user) {
		try (Connection connection = connectionProvider.get()) {
			try (PreparedStatement stmt = connection.prepareStatement("insert into user (username, password. email, role) values (?,?)")) {
				stmt.setString(1, user.getUsername());
				stmt.setBytes(2, user.getPassword());
				stmt.setString(3, user.getEmail());
				stmt.setString(3, user.getRole().toString());

				stmt.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException("Failed to create user " + user, e);
		}
	}

	private User mapUser(ResultSet rs) throws SQLException {
		return new User()
				.setId(rs.getLong("id"))
				.setUsername(rs.getString("username"))
				.setPassword(rs.getBytes("password"))
				.setEmail(rs.getString("email"))
				.setRole(Role.valueOf(rs.getString("role")));
	}

}
