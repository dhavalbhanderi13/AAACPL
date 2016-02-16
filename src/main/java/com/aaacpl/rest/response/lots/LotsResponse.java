/*
 * File Name : LotsResponse.java
 * Last Modified : 02/16/2016 10:32:35
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

public class LotsResponse {

	private int id;
	private int auctionId;
	private String name;
	private String description;
	private String startBid;
	private int differenceFactor;
	private String startDate;
	private String endDate;
	private String createdBy;
	private String updatedBy;

	public LotsResponse(final int id, final int auctionId, final String name, final String description, final String startBid,
			final int differenceFactor, final String startDate, final String endDate, final String createdBy, final String updatedBy) {
		this.id = id;
		this.auctionId = auctionId;
		this.name = name;
		this.description = description;
		this.startBid = startBid;
		this.differenceFactor = differenceFactor;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}

	public int getId() {
		return id;
	}

	public int getAuctionId() {
		return auctionId;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getStartBid() {
		return startBid;
	}

	public int getDifferenceFactor() {
		return differenceFactor;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	@Override
	public String toString() {
		return "LotsResponse{" +
				"id=" + id +
				", auctionId=" + auctionId +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", startBid='" + startBid + '\'' +
				", differenceFactor=" + differenceFactor +
				", startDate='" + startDate + '\'' +
				", endDate='" + endDate + '\'' +
				", createdBy='" + createdBy + '\'' +
				", updatedBy='" + updatedBy + '\'' +
				'}';
	}
}
