package com.olivereivak.starter.rest;

import com.olivereivak.starter.service.BookService;
import com.olivereivak.starter.model.Book;
import com.olivereivak.starter.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/books")
@Produces("application/json")
public class BookResource extends BaseResource {

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

	@GET
	@Path("/my")
	@RolesAllowed("USER")
	public List<Book> findByUser() {
		User user = getUser();
		LOGGER.debug("Getting books for user " + user);

		return bookService.findByUser(user.getId());
	}

	@POST
    public void save(Book book) {
        LOGGER.debug("Saving book " + book);

	    bookService.save(book);
    }

}
