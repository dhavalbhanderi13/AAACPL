package com.aaacpl.rest.response.email;

/**
 * Created by Sumedh on 20-03-2016.
 */
public class SendEmailResponse {
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
        return "SendEmailResponse{" +
                "successMessage='" + successMessage + '\'' +
                ", failureMessage='" + failureMessage + '\'' +
                '}';
    }
}
