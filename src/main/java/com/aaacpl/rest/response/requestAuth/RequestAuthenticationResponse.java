package com.aaacpl.rest.response.requestAuth;

/**
 * Created by Sumedh on 31-03-2016.
 */
public class RequestAuthenticationResponse {
    String failureMessage;

    public String getFailureMessage() {
        return failureMessage;
    }

    public void setFailureMessage(String failureMessage) {
        this.failureMessage = failureMessage;
    }

    @Override
    public String toString() {
        return "RequestAuthenticationResponse{" +
                "failureMessage='" + failureMessage + '\'' +
                '}';
    }
}
