package com.aaacpl.bo.request.user;

import java.sql.Date;

public class RegistrationRequestBO {
	private int typeId;
	private String name;
	private String companyName;
	private String email;
	private String password;
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
	private Date registrationDate;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		RegistrationRequestBO that = (RegistrationRequestBO) o;

		if (typeId != that.typeId) return false;
		if (pin != that.pin) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) return false;
		if (email != null ? !email.equals(that.email) : that.email != null) return false;
		if (password != null ? !password.equals(that.password) : that.password != null) return false;
		if (material != null ? !material.equals(that.material) : that.material != null) return false;
		if (address != null ? !address.equals(that.address) : that.address != null) return false;
		if (city != null ? !city.equals(that.city) : that.city != null) return false;
		if (country != null ? !country.equals(that.country) : that.country != null) return false;
		if (state != null ? !state.equals(that.state) : that.state != null) return false;
		if (panNumber != null ? !panNumber.equals(that.panNumber) : that.panNumber != null) return false;
		if (vatNumber != null ? !vatNumber.equals(that.vatNumber) : that.vatNumber != null) return false;
		if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
		if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
		return registrationDate != null ? registrationDate.equals(that.registrationDate) : that.registrationDate == null;

	}

	@Override
	public int hashCode() {
		int result = typeId;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (material != null ? material.hashCode() : 0);
		result = 31 * result + (address != null ? address.hashCode() : 0);
		result = 31 * result + (city != null ? city.hashCode() : 0);
		result = 31 * result + (country != null ? country.hashCode() : 0);
		result = 31 * result + (state != null ? state.hashCode() : 0);
		result = 31 * result + pin;
		result = 31 * result + (panNumber != null ? panNumber.hashCode() : 0);
		result = 31 * result + (vatNumber != null ? vatNumber.hashCode() : 0);
		result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
		result = 31 * result + (phone != null ? phone.hashCode() : 0);
		result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "RegistrationRequestBO{" +
				"typeId=" + typeId +
				", name='" + name + '\'' +
				", companyName='" + companyName + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
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
				", registrationDate=" + registrationDate +
				'}';
	}
}
