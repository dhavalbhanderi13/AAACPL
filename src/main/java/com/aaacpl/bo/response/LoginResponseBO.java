package com.aaacpl.bo.response;

/**
 * Created by Hp on 11-02-2016.
 */
public class LoginResponseBO {
	private Boolean isValidUser;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getValidUser() {
		return isValidUser;
	}

	public void setValidUser(Boolean validUser) {
		isValidUser = validUser;
	}

	@Override
	public String toString() {
		return "LoginResponseBO{" + "isValidUser=" + isValidUser + ", id=" + id
				+ '}';
	}
}
