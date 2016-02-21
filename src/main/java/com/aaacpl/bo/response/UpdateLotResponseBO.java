package com.aaacpl.bo.response;

public class UpdateLotResponseBO {

	private int id;

	public UpdateLotResponseBO(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "CreateLotResponseBO{" + "id=" + id + '}';
	}
}
