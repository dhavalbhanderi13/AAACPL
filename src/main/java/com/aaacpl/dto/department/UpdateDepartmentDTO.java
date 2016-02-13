package com.aaacpl.dto.department;

public class UpdateDepartmentDTO {

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
		return "UpdateDepartmentDTO{" + "name='" + name + '\'' + ", logoPath='"
				+ logoPath + '\'' + '}';
	}
}
