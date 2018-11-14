package com.olivereivak.starter.dao;

import com.olivereivak.starter.model.Book;

import javax.inject.Inject;
import javax.inject.Provider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    @Inject
    private Provider<Connection> connectionProvider;

    public List<Book> findAll() throws SQLException {
        List<Book> books = new ArrayList<>();

        try (Connection connection = connectionProvider.get()) {
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery("select * from book");
                while (rs.next()) {
                    Book book = new Book()
                            .setId(rs.getLong("id"))
                            .setTitle(rs.getString("title"))
                            .setAuthor(rs.getString("author"))
                            .setRead(rs.getBoolean("read"))
                            .setUser(rs.getLong("user"));
                    books.add(book);
                }
            }
        }

        return books;
    }

    public void save(Book book) throws SQLException {
        try (Connection connection = connectionProvider.get()) {
            String sql = "insert into book (title, author, read, user) values (?,?,?,?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, book.getTitle());
                stmt.setString(2, book.getAuthor());
                stmt.setBoolean(3, book.isRead());
                stmt.setLong(4, book.getUser());
                stmt.execute();
            }
        }
    }

}
