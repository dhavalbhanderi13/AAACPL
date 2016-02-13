package com.aaacpl.dto.user;

public class LoginResponseDTO {
	private Boolean isValidUser;
	private int id;
	private String name;
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		LoginResponseDTO that = (LoginResponseDTO) o;

		if (id != that.id)
			return false;
		if (name != null ? !name.equals(that.name) : that.name != null)
			return false;
		return password != null ? password.equals(that.password)
				: that.password == null;

	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "LoginResponseDTO{" + "id=" + id + ", name='" + name + '\''
				+ ", password='" + password + '\'' + '}';
	}
}
