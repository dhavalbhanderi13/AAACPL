package com.aaacpl.rest.response.user;

public class UserResponseList {
	private int id;
	private String name;
	private String companyName;

	public UserResponseList(int id, String name, String companyName) {
		this.id = id;
		this.name = name;
		this.companyName = companyName;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCompanyName() {
		return companyName;
	}

	@Override
	public String toString() {
		return "UserResponseList [id=" + id + ", name=" + name
				+ ", companyName=" + companyName + "]";
	}
}
