package com.olivereivak.starter.guice.modules.web;

import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class WebServer {

	private Server server;

	private List<EventListener> eventListeners = new ArrayList<>();

	public void start() throws Exception {
		server = new Server(9090);

		ServletContextHandler sch = new ServletContextHandler(null, "/");
		sch.addFilter(GuiceFilter.class, "/*", null);
		// Must add DefaultServlet for embedded Jetty. Failing to do this will cause 404 errors.
		sch.addServlet(DefaultServlet.class, "/");

		for (EventListener eventListener : eventListeners) {
			sch.addEventListener(eventListener);
		}

		HandlerCollection handlers = new HandlerCollection();
		handlers.addHandler(sch);
		server.setHandler(handlers);

		server.start();

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try {
				stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));
	}

	public void stop() throws Exception {
		server.stop();
	}

	public WebServer addEventListener(EventListener eventListener) {
		eventListeners.add(eventListener);
		return this;
	}

}
