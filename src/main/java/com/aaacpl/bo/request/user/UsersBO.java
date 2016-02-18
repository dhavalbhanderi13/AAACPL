/*
 * File Name : UsersBO.java
 * Last Modified : 02/11/2016 10:16:59
 * User : Mohammed Muchhala
 *
 * Copyright (c) 2000-2016 Zedo, Inc.
 * 850, Montgomery Street, Suite 150, San Francisco, CA 94133, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Zedo, Inc. ("Confidential Information").  You shall not disclose
 * such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Zedo.
 */

package com.aaacpl.bo.request.user;

public class UsersBO {

	private int id;
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

	public int getId() {
		return id;
	}

	public UsersBO setId(final int id) {
		this.id = id;
		return this;
	}

	public int getTypeId() {
		return typeId;
	}

	public UsersBO setTypeId(final int typeId) {
		this.typeId = typeId;
		return this;
	}

	public String getName() {
		return name;
	}

	public UsersBO setName(final String name) {
		this.name = name;
		return this;
	}

	public String getCompanyName() {
		return companyName;
	}

	public UsersBO setCompanyName(final String companyName) {
		this.companyName = companyName;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public UsersBO setEmail(final String email) {
		this.email = email;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UsersBO setPassword(final String password) {
		this.password = password;
		return this;
	}

	public String getMaterial() {
		return material;
	}

	public UsersBO setMaterial(final String material) {
		this.material = material;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public UsersBO setAddress(final String address) {
		this.address = address;
		return this;
	}

	public String getCity() {
		return city;
	}

	public UsersBO setCity(final String city) {
		this.city = city;
		return this;
	}

	public String getCountry() {
		return country;
	}

	public UsersBO setCountry(final String country) {
		this.country = country;
		return this;
	}

	public String getState() {
		return state;
	}

	public UsersBO setState(final String state) {
		this.state = state;
		return this;
	}

	public int getPin() {
		return pin;
	}

	public UsersBO setPin(final int pin) {
		this.pin = pin;
		return this;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public UsersBO setPanNumber(final String panNumber) {
		this.panNumber = panNumber;
		return this;
	}

	public String getVatNumber() {
		return vatNumber;
	}

	public UsersBO setVatNumber(final String vatNumber) {
		this.vatNumber = vatNumber;
		return this;
	}

	public String getMobile() {
		return mobile;
	}

	public UsersBO setMobile(final String mobile) {
		this.mobile = mobile;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public UsersBO setPhone(final String phone) {
		this.phone = phone;
		return this;
	}

	@Override
	public String toString() {
		return "UsersBO{" + "id=" + id + ", typeId=" + typeId + ", name='"
				+ name + '\'' + ", companyName='" + companyName + '\''
				+ ", email='" + email + '\'' + ", password='" + password + '\''
				+ ", material='" + material + '\'' + ", address='" + address
				+ '\'' + ", city='" + city + '\'' + ", country='" + country
				+ '\'' + ", state='" + state + '\'' + ", pin=" + pin
				+ ", panNumber='" + panNumber + '\'' + ", vatNumber='"
				+ vatNumber + '\'' + ", mobile=" + mobile + ", phone=" + phone
				+ '}';
	}
}
