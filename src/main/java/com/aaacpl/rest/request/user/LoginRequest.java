package com.aaacpl.rest.request.user;

public class LoginRequest {
	private String name;
	private String password;
	private Long authSessionId;

	public Long getAuthSessionId() {
		return authSessionId;
	}

	public void setAuthSessionId(Long authSessionId) {
		this.authSessionId = authSessionId;
	}

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

	@Override
	public String toString() {
		return "LoginRequest{" + "name='" + name + '\'' + ", password='"
				+ password + '\'' + ", authSessionId=" + authSessionId + '}';
	}
}
