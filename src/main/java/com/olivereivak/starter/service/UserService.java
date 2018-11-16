package com.olivereivak.starter.service;

import com.olivereivak.starter.dao.AuthenticationDao;
import com.olivereivak.starter.dao.UserDao;
import com.olivereivak.starter.model.Authentication;
import com.olivereivak.starter.model.User;
import com.olivereivak.starter.model.rest.AuthenticationResult;
import com.olivereivak.starter.model.rest.RegistrationRequest;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import java.math.BigInteger;
import java.security.SecureRandom;

public class UserService {

	@Inject
	private UserDao userDao;

	@Inject
	private AuthenticationDao authenticationDao;

	private SecureRandom random = new SecureRandom();

	public AuthenticationResult register(RegistrationRequest registrationRequest) {
		if (userDao.findByUsername(registrationRequest.getUsername()) != null) {
			throw new RuntimeException("User already exists");
		}

		User user = new User()
				.setUsername(registrationRequest.getUsername())
				.setPassword(BCrypt.hashpw(registrationRequest.getPassword(), BCrypt.gensalt()).getBytes())
				.setEmail(registrationRequest.getEmail());
		userDao.createUser(user);

		return login(registrationRequest.getUsername(), registrationRequest.getPassword());
	}

	public AuthenticationResult login(String username, String password) {
		User user = userDao.findByUsername(username);

		if (user == null || !BCrypt.checkpw(password, new String(user.getPassword()))) {
			throw new RuntimeException("User and/or password not correct");
		}

		Authentication authentication = new Authentication();
		authentication.setUser(user.getId());
		authentication.setToken(new BigInteger(130, random).toString(32));
		authenticationDao.create(authentication);

		return new AuthenticationResult(authentication, user);
	}

}
