package com.aaacpl.dto.user;

public class LoginResponseDTO {
	private Boolean isValidUser;
	private int id;
	private String email;
	private String password;
	private int departmentId;

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


	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}


	@Override
	public String toString() {
		return "LoginResponseDTO{" +
				"isValidUser=" + isValidUser +
				", id=" + id +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", departmentId=" + departmentId +
				", status='" + status + '\'' +
				'}';
	}
}
