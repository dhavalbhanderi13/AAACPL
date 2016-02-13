package com.aaacpl.rest.response.user;

public class GetTypesResponse {
	private int typeId;
	private String type;
	private String label;

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "GetTypesResponse{" + "typeId=" + typeId + ", type='" + type
				+ '\'' + ", label='" + label + '\'' + '}';
	}
}
