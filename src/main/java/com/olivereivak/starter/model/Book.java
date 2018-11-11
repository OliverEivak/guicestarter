package com.olivereivak.starter.model;

public class Book {

	private Long id;
	private String name;
	private String author;
	private Long user;

	public Long getId() {
		return id;
	}

	public Book setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Book setName(String name) {
		this.name = name;
		return this;
	}

	public String getAuthor() {
		return author;
	}

	public Book setAuthor(String author) {
		this.author = author;
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
		return "Book{" + "id=" + id + ", name='" + name + '\'' + ", author='" + author + '\'' + ", user=" + user + '}';
	}
}
