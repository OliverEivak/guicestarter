package com.olivereivak.starter.rest;

import com.olivereivak.starter.model.User;
import com.olivereivak.starter.rest.filter.ApplicationPrincipal;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

public class BaseResource {

	@Context
	private SecurityContext securityContext;

	protected User getUser() {
		ApplicationPrincipal applicationPrincipal = (ApplicationPrincipal) securityContext.getUserPrincipal();

		User user = null;

		if (applicationPrincipal != null) {
			user = applicationPrincipal.getUser();
		}

		return user;
	}

}

