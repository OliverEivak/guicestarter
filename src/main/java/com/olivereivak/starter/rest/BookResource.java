package com.olivereivak.starter.rest;

import com.olivereivak.starter.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Collections;
import java.util.List;

@Path("/books")
@Produces("application/json")
public class BookResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookResource.class);

	@GET
	public List<Book> getBooks() {
		LOGGER.debug("Getting books");

		return Collections.singletonList(new Book()
				.setId(1L)
				.setName("The Hitchhiker's Guide to the Galaxy")
				.setAuthor("Douglas Adams")
				.setUser(1L));
	}

}
