package com.olivereivak.starter.guice.modules.h2;

import com.google.inject.AbstractModule;

import java.sql.Connection;

public class H2Module extends AbstractModule {

    @Override
    protected void configure() {
        bind(Connection.class).toProvider(ConnectionProvider.class);
    }

}
