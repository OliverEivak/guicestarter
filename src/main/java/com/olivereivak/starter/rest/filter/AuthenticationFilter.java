package com.olivereivak.starter.rest.filter;

import com.olivereivak.starter.dao.AuthenticationDao;
import com.olivereivak.starter.dao.UserDao;
import com.olivereivak.starter.model.Authentication;
import com.olivereivak.starter.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.olivereivak.starter.guice.GuiceInjector.getInjector;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Map<String, Cookie> cookies = requestContext.getCookies();
        Cookie sessionCookie = cookies.get("token");

        if (sessionCookie != null) {
            String token = sessionCookie.getValue();
            Authentication authentication = getInjector().getInstance(AuthenticationDao.class).findByToken(token);

            if (authentication != null) {
                User user = getInjector().getInstance(UserDao.class).findById(authentication.getUser());
                if (user != null) {
                    ApplicationPrincipal applicationPrincipal = new ApplicationPrincipal(authentication, user);
                    ApplicationSecurityContext applicationSecurityContext = new ApplicationSecurityContext(applicationPrincipal);
                    requestContext.setSecurityContext(applicationSecurityContext);
                    return;
                }
            }

            LOGGER.info("Authentication NOT valid for token " + token);
            requestContext.abortWith(Response.status(UNAUTHORIZED).build());
        }
    }
}
