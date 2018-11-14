package com.olivereivak.starter.rest;

import com.olivereivak.starter.BookService;
import com.olivereivak.starter.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/books")
@Produces("application/json")
public class BookResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookResource.class);

	@Inject
	private BookService bookService;

	@GET
	public List<Book> findAll() {
		LOGGER.debug("Getting all books");

//		return Collections.singletonList(new Book()
//				.setId(1L)
//				.setTitle("The Hitchhiker's Guide to the Galaxy")
//				.setAuthor("Douglas Adams")
//              .setRead(true)
//				.setUser(1L));

		return bookService.findAll();
	}

	@POST
    public void save(Book book) {
        LOGGER.debug("Saving book " + book);

	    bookService.save(book);
    }

}
