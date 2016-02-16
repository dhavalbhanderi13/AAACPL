package com.aaacpl.bo.response;

public class LoginResponseBO {
	private Boolean isValidUser;
	private int id;
	private long sessionId;

	public long getSessionId() {
		return sessionId;
	}

	public void setSessionId(long sessionId) {
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

    @Override
    public String toString() {
        return "LoginResponseBO{" +
                "isValidUser=" + isValidUser +
                ", id=" + id +
                ", sessionId=" + sessionId +
                '}';
    }
}
