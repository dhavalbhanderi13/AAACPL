package com.aaacpl.rest.response.user;

public class UserResponseList {
	private int id;
	private String name;
	private String companyName;
	private int typeId;
	private String email;
	private String material;
	private String address;
	private String city;
	private String country;
	private String state;
	private int pin;
	private String panNumber;
	private String vatNumber;
	private String mobile;
	private String phone;
	private String status;
	private String isVerified;

	public UserResponseList(int id, String name, String companyName, int typeId, String email, String material, String address, String city, String country, String state, int pin, String panNumber, String vatNumber, String mobile, String phone, String status, String isVerified) {
		this.id = id;
		this.name = name;
		this.companyName = companyName;
		this.typeId = typeId;
		this.email = email;
		this.material = material;
		this.address = address;
		this.city = city;
		this.country = country;
		this.state = state;
		this.pin = pin;
		this.panNumber = panNumber;
		this.vatNumber = vatNumber;
		this.mobile = mobile;
		this.phone = phone;
		this.status = status;
		this.isVerified = isVerified;
	}

	public String getIsVerified() {
		return isVerified;
	}

	public int getTypeId() {
		return typeId;
	}

	public String getEmail() {
		return email;
	}

	public String getMaterial() {
		return material;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getState() {
		return state;
	}

	public int getPin() {
		return pin;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public String getVatNumber() {
		return vatNumber;
	}

	public String getMobile() {
		return mobile;
	}

	public String getPhone() {
		return phone;
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

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "UserResponseList{" +
				"id=" + id +
				", name='" + name + '\'' +
				", companyName='" + companyName + '\'' +
				", typeId=" + typeId +
				", email='" + email + '\'' +
				", material='" + material + '\'' +
				", address='" + address + '\'' +
				", city='" + city + '\'' +
				", country='" + country + '\'' +
				", state='" + state + '\'' +
				", pin=" + pin +
				", panNumber='" + panNumber + '\'' +
				", vatNumber='" + vatNumber + '\'' +
				", mobile='" + mobile + '\'' +
				", phone='" + phone + '\'' +
				", status='" + status + '\'' +
				", isVerified='" + isVerified + '\'' +
				'}';
	}
}
