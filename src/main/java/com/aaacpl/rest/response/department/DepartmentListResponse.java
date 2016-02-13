package com.aaacpl.rest.response.department;

import java.util.List;

public class DepartmentListResponse {
	List<DepartmentResponse> departmentResponseList;

	public List<DepartmentResponse> getDepartmentResponseList() {
		return departmentResponseList;
	}

	public void setDepartmentResponseList(
			List<DepartmentResponse> departmentResponseList) {
		this.departmentResponseList = departmentResponseList;
	}

	@Override
	public String toString() {
		return "DepartmentListResponse{" + "departmentResponseList="
				+ departmentResponseList + '}';
	}
}
