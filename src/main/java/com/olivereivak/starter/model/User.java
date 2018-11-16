package com.olivereivak.starter.model;

public class User {

	private Long id;
	private String username;
	private byte[] password;
	private String email;
	private Role role;

	public Long getId() {
		return id;
	}

	public User setId(Long id) {
		this.id = id;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public User setUsername(String username) {
		this.username = username;
		return this;
	}

	public byte[] getPassword() {
		return password;
	}

	public User setPassword(byte[] password) {
		this.password = password;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public Role getRole() {
		return role;
	}

	public User setRole(Role role) {
		this.role = role;
		return this;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", username='" + username + '\'' + ", password=***, email='" + email + '\'' + ", role=" + role + '}';
	}
}
