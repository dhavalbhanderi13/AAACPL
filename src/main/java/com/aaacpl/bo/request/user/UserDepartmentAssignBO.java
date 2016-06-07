package com.aaacpl.bo.request.user;

/**
 * Created by Sumedh on 07-06-2016.
 */
public class UserDepartmentAssignBO {
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
    public String toString() {
        return "UserDepartmentAssignBO{" +
                "userId=" + userId +
                ", departmentId=" + departmentId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDepartmentAssignBO that = (UserDepartmentAssignBO) o;

        if (userId != that.userId) return false;
        return departmentId == that.departmentId;

    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + departmentId;
        return result;
    }
}
