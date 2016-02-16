package com.aaacpl.rest.response.participator;

/**
 * Created by Hp on 17-02-2016.
 */
public class CreateParticipatorResponse {
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
        return "CreateParticipatorResponse{" +
                "successMessage='" + successMessage + '\'' +
                ", failureMessage='" + failureMessage + '\'' +
                '}';
    }
}
