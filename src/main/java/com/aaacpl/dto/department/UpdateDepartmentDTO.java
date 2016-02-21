package com.aaacpl.dto.department;

public class UpdateDepartmentDTO {

	private String name;
	private String logoPath;
	private Integer deptId;
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
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UpdateDepartmentDTO{" + "departmentId='" + deptId + "\'"
				+ ", name='" + name + '\'' + ", logoPath='" + logoPath + '\''
				+ '}';
	}

}
