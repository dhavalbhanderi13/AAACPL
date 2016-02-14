package com.aaacpl.bo.request.department;

public class CreateDepartmentRequestBO {
	private String name;
	private String logoPath;
	private String deptId;

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

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Override
	public String toString() {
		return "CreateDepartmentRequestBO{ '" + "'departmentId='" + deptId
				+ "\'" + ", name='" + name + '\'' + ", logoPath='" + logoPath
				+ '\'' + '}';
	}

}
