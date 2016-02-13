package com.aaacpl.dto.department;

public class DepartmentDTO {
	private int id;
	private String name;
	private String logoPath;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
		return "DepartmentDTO{" + "id=" + id + ", name='" + name + '\''
				+ ", logoPath='" + logoPath + '\'' + '}';
	}
}
