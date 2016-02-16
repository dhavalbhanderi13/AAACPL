/*
 * File Name : LotsListResponse.java
 * Last Modified : 02/16/2016 10:38:23
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

package com.aaacpl.rest.response.lots;

import java.util.List;

public class LotsListResponse {
	private List<LotsResponse> lotsResponseList;

	public List<LotsResponse> getLotsResponseList() {
		return lotsResponseList;
	}

	public void setLotsResponseList(final List<LotsResponse> lotsResponseList) {
		this.lotsResponseList = lotsResponseList;
	}

	@Override
	public String toString() {
		return "LotsListResponse{" +
				"lotsResponseList=" + lotsResponseList +
				'}';
	}
}
