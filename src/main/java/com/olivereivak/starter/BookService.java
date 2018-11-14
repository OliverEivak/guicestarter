package com.olivereivak.starter;

import com.olivereivak.starter.dao.BookDao;
import com.olivereivak.starter.model.Book;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

public class BookService {

    @Inject
    private BookDao bookDao;

    public List<Book> findAll() {
        try {
            return bookDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get all books", e);
        }
    }

    public void save(Book book) {
        try {
            bookDao.save(book);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save book " + book, e);
        }
    }

}
