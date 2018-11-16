package com.olivereivak.starter.model;

public class Authentication {

	private Long id;
	private String token;
	private Long user;

	public Long getId() {
		return id;
	}

	public Authentication setId(Long id) {
		this.id = id;
		return this;
	}

	public String getToken() {
		return token;
	}

	public Authentication setToken(String token) {
		this.token = token;
		return this;
	}

	public Long getUser() {
		return user;
	}

	public Authentication setUser(Long user) {
		this.user = user;
		return this;
	}

	@Override
	public String toString() {
		return "Authentication{" + "id=" + id + ", token='" + token + '\'' + ", user=" + user + '}';
	}
}
