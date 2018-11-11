package com.olivereivak.starter.guice.modules.resteasy;

import com.google.inject.servlet.RequestScoped;
import com.google.inject.servlet.ServletModule;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import javax.inject.Provider;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 * <p>
 *     We need to make the JAXRS classes available. Resteasy provides a RequestScopeModule
 *     but that conflicts with Guice's servlet modules (and uses its own @RequestScoped),
 *     so we have to duplicate the relevant parts here. I have no idea what the Resteasy
 *     team is thinking, but that's not very Guicy.
 * </p>
 */
public class JaxrsModule extends ServletModule {
	@Override
	protected void configureServlets() {
		bind(Request.class)
				.toProvider(new ResteasyContextProvider<>(Request.class))
				.in(RequestScoped.class);
		bind(HttpHeaders.class)
				.toProvider(new ResteasyContextProvider<>(HttpHeaders.class))
				.in(RequestScoped.class);
		bind(UriInfo.class)
				.toProvider(new ResteasyContextProvider<>(UriInfo.class))
				.in(RequestScoped.class);
		bind(SecurityContext.class)
				.toProvider(new ResteasyContextProvider<>(SecurityContext.class))
				.in(RequestScoped.class);
	}

	private static class ResteasyContextProvider<T> implements Provider<T> {

		private final Class<T> instanceClass;

		public ResteasyContextProvider(Class<T> instanceClass) {
			this.instanceClass = instanceClass;
		}

		@Override
		public T get() {
			return ResteasyProviderFactory.getContextData(instanceClass);
		}
	}
}