package com.olivereivak.starter.rest.filter;

import com.olivereivak.starter.model.Authentication;
import com.olivereivak.starter.model.Role;
import com.olivereivak.starter.model.User;

import java.security.Principal;

public class ApplicationPrincipal implements Principal {

	private Authentication authentication;
	private User user;

	public ApplicationPrincipal(Authentication authentication, User user) {
		this.authentication = authentication;
		this.user = user;
	}

	@Override
	public String getName() {
		return String.format("%s:%s", authentication.getToken(), user.getUsername());
	}

	public Role getRole() {
		return user.getRole();
	}

//	public Authentication getAuthentication() {
//		return authentication;
//	}

	public User getUser() {
		return user;
	}
}

