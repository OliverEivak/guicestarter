package com.olivereivak.starter;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.olivereivak.starter.guice.GuiceInjector;
import com.olivereivak.starter.guice.modules.AppModule;
import com.olivereivak.starter.guice.modules.h2.H2Module;
import com.olivereivak.starter.guice.modules.logging.LoggingModule;
import com.olivereivak.starter.guice.modules.resteasy.RestModule;
import com.olivereivak.starter.guice.modules.web.WebModule;
import com.olivereivak.starter.guice.modules.web.WebServer;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;

/**
 * Hello world!
 */
public class App {

	public static void main(String[] args) throws Exception {
		Injector injector = Guice.createInjector(
				new LoggingModule(),
				new WebModule(),
				new RestModule(),
				new H2Module(),
				new AppModule()
		);

		injector.getInstance(WebServer.class)
				.addEventListener(injector.getInstance(GuiceResteasyBootstrapServletContextListener.class))
				.addInitParameter("resteasy.role.based.security", "true")
				.addInitParameter("resteasy.providers", "com.olivereivak.starter.rest.filter.AuthenticationFilter")
				.start();

		GuiceInjector.setInjector(injector);
	}

}
