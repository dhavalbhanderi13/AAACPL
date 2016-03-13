package com.aaacpl.dto.user;

public class LoginResponseDTO {
	private Boolean isValidUser;
	private int id;
	private String email;
	private String password;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String status;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getValidUser() {
		return isValidUser;
	}

	public void setValidUser(Boolean validUser) {
		isValidUser = validUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "LoginResponseDTO{" +
				"isValidUser=" + isValidUser +
				", id=" + id +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", status='" + status + '\'' +
				'}';
	}
}
