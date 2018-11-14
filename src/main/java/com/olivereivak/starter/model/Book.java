package com.olivereivak.starter.model;

public class Book {

	private Long id;
	private String title;
	private String author;
	private boolean read;
	private Long user;

    public Long getId() {
        return id;
    }

    public Book setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    public boolean isRead() {
        return read;
    }

    public Book setRead(boolean read) {
        this.read = read;
        return this;
    }

    public Long getUser() {
        return user;
    }

    public Book setUser(Long user) {
        this.user = user;
        return this;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title='" + title + '\'' + ", author='" + author + '\'' + ", read=" + read
                + ", user=" + user + '}';
    }
}
