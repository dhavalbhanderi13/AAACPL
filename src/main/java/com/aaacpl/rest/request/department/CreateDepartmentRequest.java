package com.aaacpl.rest.request.department;

public class CreateDepartmentRequest {
	private String name;
	private String logoPath;

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

	@Override
	public String toString() {
		return "CreateDepartmentRequest{" + "name='" + name + '\''
				+ ", logoPath='" + logoPath + '\'' + '}';
	}
}
