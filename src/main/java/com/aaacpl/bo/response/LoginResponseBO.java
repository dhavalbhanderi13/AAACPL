package com.aaacpl.bo.response;

public class LoginResponseBO {
	private Boolean isValidUser;
	private int id;
	private int departmentId;
	private String sessionId;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

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

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "LoginResponseBO{" +
				"isValidUser=" + isValidUser +
				", id=" + id +
				", departmentId=" + departmentId +
				", sessionId='" + sessionId + '\'' +
				'}';
	}
}
