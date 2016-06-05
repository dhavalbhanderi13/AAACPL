package com.aaacpl.rest.request.user;

public class ChangePasswordRequest {

	private int userId;
	private String oldPassword;
	private String newPassword;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "ChangePasswordRequest [userId=" + userId + ", oldPassword="
				+ oldPassword + ", newPassword=" + newPassword + "]";
	}

}
