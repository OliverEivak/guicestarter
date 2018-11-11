package com.olivereivak.starter.guice.modules.resteasy;

import com.google.inject.servlet.ServletModule;
import com.olivereivak.starter.guice.modules.web.WebModule;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

import javax.inject.Singleton;

public class RestModule extends ServletModule {

	@Override
	protected void configureServlets() {
		install(new WebModule());
		install(new JaxrsModule());

		// Make sure RESTEasy picks this up so we get our ObjectMapper from guice
		bind(ObjectMapperContextResolver.class);

		bind(HttpServletDispatcher.class).in(Singleton.class);

		serve("/*").with(HttpServletDispatcher.class);
	}

}

