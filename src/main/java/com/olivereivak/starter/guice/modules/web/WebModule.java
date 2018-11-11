package com.olivereivak.starter.guice.modules.web;

import com.google.inject.servlet.ServletModule;

public class WebModule extends ServletModule {

	@Override
	protected void configureServlets() {
		bind(WebServer.class).asEagerSingleton();
	}

}