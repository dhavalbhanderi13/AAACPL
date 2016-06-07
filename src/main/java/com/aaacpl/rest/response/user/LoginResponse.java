package com.aaacpl.rest.response.user;

import javax.ws.rs.ext.Provider;

/**
 * Created by Hp on 07-02-2016.
 */
@Provider
public class LoginResponse {
	private int userId;
	private int departmentId;
	private String successMessage;
	private String failureMessage;

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getFailureMessage() {
		return failureMessage;
	}

	public void setFailureMessage(String failureMessage) {
		this.failureMessage = failureMessage;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "LoginResponse{" +
				"userId=" + userId +
				", departmentId=" + departmentId +
				", successMessage='" + successMessage + '\'' +
				", failureMessage='" + failureMessage + '\'' +
				'}';
	}
}
