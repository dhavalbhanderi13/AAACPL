package com.aaacpl.rest.request.department;

public class UpdateDepartmentRequest {
	private String name;
	private String logoPath;
	private String status;
	private Integer id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UpdateDepartmentRequest{" + "id='" + id + "\'" + ", name='"
				+ name + '\'' + ", logoPath='" + logoPath + '\'' + '}';
	}
}
