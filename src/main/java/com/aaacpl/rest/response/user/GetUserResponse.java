package com.aaacpl.rest.response.user;

public class GetUserResponse {
	private int id;
	private int typeId;
	private String userTypeLabel;
	private String name;
	private String companyName;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserTypeLabel() {
		return userTypeLabel;
	}

	public void setUserTypeLabel(String userTypeLabel) {
		this.userTypeLabel = userTypeLabel;
	}

	@Override
	public String toString() {
		return "GetUserResponse{" + "id=" + id + ", typeId=" + typeId
				+ ", name='" + name + '\'' + ", companyName='" + companyName
				+ '\'' + ", userTypeLabel='" + userTypeLabel + '\''
				+ ", email='" + email + '\'' + ", material='" + material + '\''
				+ ", address='" + address + '\'' + ", city='" + city + '\''
				+ ", country='" + country + '\'' + ", state='" + state + '\''
				+ ", pin=" + pin + ", panNumber='" + panNumber + '\''
				+ ", vatNumber='" + vatNumber + '\'' + ", mobile=" + mobile
				+ ", phone=" + phone + '}';
	}
}
