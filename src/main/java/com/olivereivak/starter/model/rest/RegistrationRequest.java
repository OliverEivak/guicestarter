package com.olivereivak.starter.model.rest;

public class RegistrationRequest {

	private String username;
	private String password;
	private String email;

	public String getUsername() {
		return username;
	}

	public RegistrationRequest setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public RegistrationRequest setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public RegistrationRequest setEmail(String email) {
		this.email = email;
		return this;
	}

	@Override
	public String toString() {
		return "RegistrationRequest{" + "username='" + username + '\'' + ", password='***', email='"
				+ email + '\'' + '}';
	}
}
