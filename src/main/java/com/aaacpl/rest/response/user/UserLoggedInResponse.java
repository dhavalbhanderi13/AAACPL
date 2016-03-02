package com.aaacpl.rest.response.user;

public class UserLoggedInResponse {
	private int userId;
	private String companyName;

	public UserLoggedInResponse(int userId, String companyName) {
		this.userId = userId;
		this.companyName = companyName;
	}

	public int getUserId() {
		return userId;
	}

	public String getCompanyName() {
		return companyName;
	}

	@Override
	public String toString() {
		return "UserLoggedInResponse [userId=" + userId + ", companyName="
				+ companyName + "]";
	}

}
