package com.aaacpl.rest.request.user;


public class LogoutRequest {
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "LogoutRequest{" +
                "userId=" + userId +
                '}';
    }
}
