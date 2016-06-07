package com.aaacpl.rest.request.user;

public class UserDepartmentAssignRequest {
    private int userId;
    private int departmentId;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDepartmentAssignRequest that = (UserDepartmentAssignRequest) o;

        if (userId != that.userId) return false;
        return departmentId == that.departmentId;

    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + departmentId;
        return result;
    }


    @Override
    public String toString() {
        return "UserDepartmentAssignRequest{" +
                "userId=" + userId +
                ", departmentId=" + departmentId +
                '}';
    }
}
