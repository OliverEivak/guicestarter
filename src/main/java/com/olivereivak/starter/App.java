package com.olivereivak.starter;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.olivereivak.starter.guice.modules.AppModule;
import com.olivereivak.starter.guice.modules.logging.LoggingModule;
import com.olivereivak.starter.guice.modules.web.WebModule;
import com.olivereivak.starter.guice.modules.web.WebServer;
import com.olivereivak.starter.guice.modules.resteasy.RestModule;
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
				new AppModule()
		);

		injector.getInstance(WebServer.class)
				.addEventListener(injector.getInstance(GuiceResteasyBootstrapServletContextListener.class))
				.start();
	}

}
