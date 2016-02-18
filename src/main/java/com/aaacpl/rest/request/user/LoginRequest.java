package com.aaacpl.rest.request.user;

public class LoginRequest {
	private String email;
	private String password;
	private Long authSessionId;

	public Long getAuthSessionId() {
		return authSessionId;
	}

	public void setAuthSessionId(Long authSessionId) {
		this.authSessionId = authSessionId;
	}

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

	@Override
	public String toString() {
		return "LoginRequest{" +
				"email='" + email + '\'' +
				", password='" + password + '\'' +
				", authSessionId=" + authSessionId +
				'}';
	}
}
