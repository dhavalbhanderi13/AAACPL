package com.aaacpl.rest.response.lots;

public class CreateLotResponse {
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
        return "CreateLotResponse{" +
                "successMessage='" + successMessage + '\'' +
                ", failureMessage='" + failureMessage + '\'' +
                '}';
    }
}
