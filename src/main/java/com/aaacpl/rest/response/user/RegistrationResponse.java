package com.aaacpl.rest.response.user;

public class RegistrationResponse {
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

	@Override
	public String toString() {
		return "RegistrationResponse{" + "successMessage='" + successMessage
				+ '\'' + ", failureMessage='" + failureMessage + '\'' + '}';
	}
}
