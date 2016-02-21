package com.aaacpl.bo.request.department;

public class UpdateDepartmentRequestBO {
	private String name;
	private String logoPath;
	private Integer id;
	private String status;

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

	public Integer getDeptId() {
		return id;
	}

	public void setDeptId(Integer id) {
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
		return "UpdateDepartmentRequestBO{" + "id='" + id + "\'" + ", name='"
				+ name + '\'' + ", logoPath='" + logoPath + '\'' + '}';
	}

}
