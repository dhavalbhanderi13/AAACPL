package com.aaacpl.bo.request.user;

public class ChangePasswordBO {
	private int userId;
	private String oldPassword;
	private String newPassword;

	public ChangePasswordBO(int userId, String oldPassword, String newPassword) {
		this.userId = userId;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public int getUserId() {
		return userId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	@Override
	public String toString() {
		return "ChangePasswordRequest [userId=" + userId + ", oldPassword="
				+ oldPassword + ", newPassword=" + newPassword + "]";
	}
}
