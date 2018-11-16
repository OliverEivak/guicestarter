package com.olivereivak.starter.model.rest;

import com.olivereivak.starter.model.Authentication;
import com.olivereivak.starter.model.User;

public class AuthenticationResult {

	private Authentication authentication;
	private User user;

	public AuthenticationResult(Authentication authentication, User user) {
		this.authentication = authentication;
		this.user = user;
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public User getUser() {
		return user;
	}
}
