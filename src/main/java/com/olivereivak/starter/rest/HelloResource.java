package com.olivereivak.starter.rest;

import com.google.common.base.Charsets;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Path("/hello")
public class HelloResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloResource.class);

    @Inject
	private Provider<Connection> connectionProvider;

	@GET
    @Path("/world")
	public String hello() {
		return "Hello restful world!";
	}

    @GET
    @Path("/java11")
    public String java11() {
        return "".isBlank() ? "Hello Java 11!" : "";
    }

	@GET
    @Path("/init")
	public String initDatabase() throws SQLException, IOException {
		String schemaSql = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("schema.sql"), Charsets.UTF_8);
		String dataSql = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("data.sql"), Charsets.UTF_8);

		try (Connection connection = connectionProvider.get()) {
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(schemaSql);
                stmt.executeUpdate(dataSql);
            }
        }

        LOGGER.info("Database initialized!");
		return "Database initialized!";
    }

}
