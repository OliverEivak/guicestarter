package com.olivereivak.starter.guice.modules;

import com.google.inject.AbstractModule;
import com.olivereivak.starter.rest.BookResource;
import com.olivereivak.starter.rest.HelloResource;
import com.olivereivak.starter.rest.UserResource;

public class AppModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(HelloResource.class);
		bind(BookResource.class);
		bind(UserResource.class);
	}

}
