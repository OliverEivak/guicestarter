package com.olivereivak.starter.rest;

import com.olivereivak.starter.model.rest.AuthenticationResult;
import com.olivereivak.starter.model.rest.LoginRequest;
import com.olivereivak.starter.model.rest.RegistrationRequest;
import com.olivereivak.starter.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("user")
@Produces("application/json")
public class UserResource {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Inject
    private UserService userService;

    @POST
    @Path("register")
    public Response register(RegistrationRequest registrationRequest) {
        LOGGER.debug("Registering new user for request {}", registrationRequest);
		AuthenticationResult result = userService.register(registrationRequest);
		return Response
				.ok(result.getUser().setPassword(null))
				.cookie(createCookie(result))
				.build();
    }

	@POST
    @Path("login")
    public Response login(LoginRequest loginRequest) {
        LOGGER.debug("Login request for username {}", loginRequest.getUsername());
		AuthenticationResult result = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
		return Response
				.ok(result.getUser().setPassword(null))
				.cookie(createCookie(result))
				.build();
    }

	private NewCookie createCookie(AuthenticationResult result) {
		return new NewCookie("token", result.getAuthentication().getToken(), "/", "localhost", "", -1, false);
	}

}
