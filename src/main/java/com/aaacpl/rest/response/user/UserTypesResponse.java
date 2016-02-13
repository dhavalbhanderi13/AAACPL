package com.aaacpl.rest.response.user;

import java.util.List;

/**
 * Created by Hp on 10-02-2016.
 */
public class UserTypesResponse {
	List<GetTypesResponse> getTypesResponseList;

	public List<GetTypesResponse> getGetTypesResponseList() {
		return getTypesResponseList;
	}

	public void setGetTypesResponseList(
			List<GetTypesResponse> getTypesResponseList) {
		this.getTypesResponseList = getTypesResponseList;
	}

	@Override
	public String toString() {
		return "UserTypesResponse{" + "getTypesResponseList="
				+ getTypesResponseList + '}';
	}
}
