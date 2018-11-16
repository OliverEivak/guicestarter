package com.olivereivak.starter.dao;

import com.olivereivak.starter.model.Authentication;

import javax.inject.Inject;
import javax.inject.Provider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationDao {

    @Inject
    private Provider<Connection> connectionProvider;

    public Authentication findByToken(String token) {
        try (Connection connection = connectionProvider.get()) {
            try (PreparedStatement stmt = connection.prepareStatement("select * from authentication where token = ?")) {
            	stmt.setString(1, token);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    return new Authentication()
							.setId(rs.getLong("id"))
							.setToken(rs.getString("token"))
							.setUser(rs.getLong("user"));
                }
            }
        } catch (SQLException e) {
        	throw new RuntimeException("Failed to find authentication by token " + token, e);
		}

        return null;
    }

    public void create(Authentication authentication) {
		try (Connection connection = connectionProvider.get()) {
			try (PreparedStatement stmt = connection.prepareStatement("insert into authentication (token, user) values (?,?)")) {
				stmt.setString(1, authentication.getToken());
				stmt.setLong(2, authentication.getUser());

				stmt.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException("Failed to create authentication " + authentication, e);
		}
	}

}
